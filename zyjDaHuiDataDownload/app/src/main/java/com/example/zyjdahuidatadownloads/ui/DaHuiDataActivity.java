package com.example.zyjdahuidatadownloads.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zyjdahuidatadownloads.R;
import com.example.zyjdahuidatadownloads.adapter.DaHuiDataAdapter;
import com.example.zyjdahuidatadownloads.bean.Base;
import com.example.zyjdahuidatadownloads.bean.DaHuiData;
import com.example.zyjdahuidatadownloads.bean.DaHuiDataFiles;
import com.example.zyjdahuidatadownloads.bean.ResponseBody;
import com.example.zyjdahuidatadownloads.config.Constant;
import com.example.zyjdahuidatadownloads.download.DownloadInfo;
import com.example.zyjdahuidatadownloads.download.DownloadManager;
import com.example.zyjdahuidatadownloads.download.DownloadService;
import com.example.zyjdahuidatadownloads.utils.AppTools;
import com.example.zyjdahuidatadownloads.utils.FileManager;
import com.example.zyjdahuidatadownloads.utils.SharePreferenceUtils;
import com.example.zyjdahuidatadownloads.widget.PinnedHeaderExpandableListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class DaHuiDataActivity extends Activity implements View.OnClickListener {

	@ViewInject(R.id.swipe_dahuidata)
	SwipeRefreshLayout swipe_dahuidata;
	@ViewInject(R.id.dahuidata_lv)
	PinnedHeaderExpandableListView dahuidata_lv;
	@ViewInject(R.id.emptyView)
	private TextView emptyView;
	@ViewInject(R.id.in_title)
	TextView in_title;
	@ViewInject(R.id.but_fanhui)
	Button but_fanhui;

	ArrayList<DaHuiData> datas = new ArrayList<DaHuiData>();
	ProgressDialog pro;
	Gson g = new Gson();
	// User parent;
	private DaHuiDataAdapter adapter;
	// 下载文件
	private DownloadManager downloadManager;

	Context context;

	Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			swipe_dahuidata.setRefreshing(false);
			swipe_dahuidata.setEnabled(true);
			switch (msg.what) {
			case 0:
				ResponseBody<DaHuiData> res = (ResponseBody<DaHuiData>) msg.obj; //
				// 首先创建接收方法
				// 其实就是拿到ResponseBody<HuiYiRiChengRiQiHuoQu>里面的list对象
				datas.clear();
				datas.addAll(res.list);
				adapter.getDatas().clear();
				adapter.setDatas(datas);
				adapter.notifyDataSetChanged();
				System.out.println("mhandler:res.list=" + res.list.toString());
				break;
			case 1:

				break;
			default:
				break;
			}
		};
	};

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 透明状态栏
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dahuidata);
		ViewUtils.inject(this);
		context = DaHuiDataActivity.this;
		SharePreferenceUtils.getAppConfig(context);
		downloadManager = DownloadService.getDownloadManager(context);
		emptyView.setText("暂时没有数据");
		in_title.setText("大会资料");
		process();
		loadData();
		setAllClickListener();
	}

	private void process() {
		adapter = new DaHuiDataAdapter(context, dahuidata_lv, downloadManager);
		dahuidata_lv.setAdapter(adapter);
		// pro = ProgressDialog.show(this, "", "加载中...");// google自带dialog
		// pro.setCancelable(true);// 点击dialog外空白位置是否消失
		// pro.setCanceledOnTouchOutside(false);// 点击返回键对话框是否消失

		swipe_dahuidata.setColorSchemeColors(this.getResources().getColor(
				R.color.swiperefreshlayoutcolor));

		but_fanhui.setOnClickListener(this);
		emptyView.setVisibility(View.GONE);
		// 对已经删除的没下载完的任务进行删除
		String target = AppTools.getRootPath() + "/wenbohui/cache/";
		File[] files = new File(target).listFiles();
		if (downloadManager != null)
			for (int i = 0; i < downloadManager.getDownloadInfoListCount(); i++) {
				int flag = 0;// 本地不存在DownManager里面的文件
				DownloadInfo info = downloadManager.getDownloadInfo(i);
				for (File fff : files) {
					if ((info.getFileName().equals(fff.getName()))) {
						flag = 1;
					}
				}
				if (flag == 0) {
					try {
						downloadManager.removeDownload(info);
					} catch (DbException e) {
						e.printStackTrace();
					}
				}
			}
		String downloadurl_cache = (String) SharePreferenceUtils.getParam(
				"downloadUrl", "");
		System.out.println("DaHuiDataActivity:downloadurl_cache="
				+ downloadurl_cache);
	}

	private void loadData() {
		try {
			Type typeToken = new TypeToken<ArrayList<DaHuiData>>() {
			}.getType();
			InputStream in = getAssets().open("DaHuiData.json");
			// 将字符流转换成字节流
			// InputStreamReader json = new InputStreamReader(in);
			String content = convertStreamToString(in);
			Base entity = Constant.gson.fromJson(content, Base.class);
			//
			ResponseBody<DaHuiData> body = new ResponseBody<DaHuiData>();
			body.list = Constant.gson.fromJson(entity.info, typeToken);
			Message msg = mhandler.obtainMessage();
			msg.what = 0;
			msg.obj = body;
			msg.sendToTarget();
			// mhandler.sendMessageDelayed(msg, 1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setAllClickListener() {
		swipe_dahuidata.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// loadData();
				// 启动线程然后只为了延时让swipe停止刷新
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(1 * 1000);
							mhandler.sendEmptyMessage(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					};
				}.start();
			}
		});

		dahuidata_lv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// 获取到点击位置的文件
				DaHuiDataFiles file = datas.get(groupPosition).filedata
						.get(childPosition);
				if (isDownloadHalf(file)) {
					// 文件在下载过程中不操作
					// Toast.makeText(context, "正在下载中...", Toast.LENGTH_SHORT)
					// .show();
				} else {
					if (isDownload(file)) {
						// 文件下载完成时就打开文件
						File fff = new File(Constant.target
								+ file.dataUploadName + file.fileFormat);
						FileManager.openFile(context, fff);
					} else {
						// 文件没有开始下载时，就开始下载文件
						try {
							downloadManager.addNewDownload(file.dataUploadUrl,
									file.dataUploadName + file.fileFormat,
									Constant.target + file.dataUploadName
											+ file.fileFormat, true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
									true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
									null);
						} catch (DbException e) {
							LogUtils.e(e.getMessage(), e);
						}
					}
				}
				adapter.notifyDataSetChanged();
				return true;
			}
		});
	}

	// 判断downloadManager里面是否有该文件，并返回状态。
	private boolean isDownloadHalf(DaHuiDataFiles file) {
		if (downloadManager != null)
			for (int i = 0; i < downloadManager.getDownloadInfoListCount(); i++) {
				DownloadInfo info = downloadManager.getDownloadInfo(i);
				if (info.getDownloadUrl().equals(file.dataUploadUrl)) {
					HttpHandler.State state = info.getState();
					String mstate = "";
					switch (state) {
					case WAITING:
						mstate = "WAITING";
						break;
					case STARTED:
						mstate = "STARTED";
						break;
					case LOADING:
						mstate = "LOADING";
						break;
					case CANCELLED:
						mstate = "CANCELLED";
						break;
					case SUCCESS:
						mstate = "SUCCESS";
						break;
					case FAILURE:
						File fff = new File(Constant.target
								+ file.dataUploadName + file.fileFormat);
						try {
							downloadManager.removeDownload(info);
						} catch (DbException e) {
							e.printStackTrace();
						}
						// Toast.makeText(context, "文件下载失败，执行删除！",
						// Toast.LENGTH_SHORT).show();
						if (fff.isFile() && fff.exists()) {
							Toast.makeText(context, "文件下载失败，开始重新下载！",
									Toast.LENGTH_SHORT).show();
							return !fff.delete();
						}
						mstate = "FAILURE";
						break;
					default:
						mstate = "default";
						break;
					}
					// 测试用。。。
					Toast.makeText(context, mstate, Toast.LENGTH_SHORT).show();
					if (state != state.SUCCESS) {
						return true;
					}
				}
			}
		return false;
	}
	//判断文件夹下是否有该文件。如果有则返回一下载文件
	public boolean isDownload(DaHuiDataFiles file) {
		String needdown = file.dataUploadName + file.fileFormat;
		String target = AppTools.getRootPath() + "/wenbohui/cache/";
		File[] files = new File(target).listFiles();
		for (File fff : files) {
			if (fff.getName().equals(needdown)) {
				return true;
			}
		}
		return false;
	}
	//这个方法没有使用到。判断文件夹下有没有该文件明的文件。
	public boolean searchFile(String filename) {
		String target = AppTools.getRootPath() + "/wenbohui/cache/";
		File[] files = new File(target).listFiles();
		for (File file : files) {
			if (file.getName().equals(filename)) {
				return true;
			}
		}
		return false;
	}
	//这个方法没有使用到。判断downloadManager里面有没有DaHuiDataFile这个文件。
	public DownloadInfo getDownloadInfo(DaHuiDataFiles dfile) {
		if (downloadManager != null)
			for (int i = 0; i < downloadManager.getDownloadInfoListCount(); i++) {
				DownloadInfo info = downloadManager.getDownloadInfo(i);
				if (info.getDownloadUrl().equals(dfile.dataUploadUrl))
					return info;
			}
		return null;
	}

	@Override
	public void onClick(View v) {
		if (but_fanhui.getId() == v.getId()) {
			DaHuiDataActivity.this.finish();
		}
	}

	@Override
	protected void onDestroy() {// 退出应用，清除图片缓存
		try {
			if (adapter != null && downloadManager != null) {
				for (int i = 0; i < downloadManager.getDownloadInfoListCount(); i++) {
					DownloadInfo info = downloadManager.getDownloadInfo(i);
					HttpHandler.State state = info.getState();
					if (state != state.LOADING)
						downloadManager.removeDownload(info);
				}
				downloadManager.backupDownloadInfoList();
			}
		} catch (DbException e) {
			LogUtils.e(e.getMessage(), e);
		}
		super.onDestroy();
	}

	// 将下载的流转成string
	private String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
