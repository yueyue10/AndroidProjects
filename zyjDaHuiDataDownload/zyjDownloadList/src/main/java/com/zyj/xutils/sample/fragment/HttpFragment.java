package com.zyj.xutils.sample.fragment;

import java.io.File;
import java.lang.ref.WeakReference;

import org.apache.http.impl.cookie.BasicClientCookie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;
import com.lidroid.xutils.view.ResType;
import com.lidroid.xutils.view.annotation.ResInject;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.zyj.xutils.sample.DownloadListActivity;
import com.zyj.xutils.sample.R;
import com.zyj.xutils.sample.download.DownloadInfo;
import com.zyj.xutils.sample.download.DownloadManager;
import com.zyj.xutils.sample.download.DownloadService;
import com.zyj.xutils.sample.open.FileManager;

/**
 * Author: wyouflf Date: 13-9-14 Time: 下午3:35
 */
public class HttpFragment extends Fragment {

	// private HttpHandler handler;

	private Context mAppContext;
	private DownloadManager downloadManager;
	private DownloadListAdapter downloadListAdapter;
	private PreferencesCookieStore preferencesCookieStore;
	@ViewInject(R.id.download_list)
	private ListView downloadList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.http_fragment, container, false);
		ViewUtils.inject(this, view);

		mAppContext = inflater.getContext().getApplicationContext();

		downloadManager = DownloadService.getDownloadManager(mAppContext);

		preferencesCookieStore = new PreferencesCookieStore(mAppContext);
		BasicClientCookie cookie = new BasicClientCookie("test", "hello");
		cookie.setDomain("192.168.1.5");
		cookie.setPath("/");
		preferencesCookieStore.addCookie(cookie);
		downloadListAdapter = new DownloadListAdapter(mAppContext);
		downloadList.setAdapter(downloadListAdapter);
		return view;
	}

	@ViewInject(R.id.download_addr_edit)
	private EditText downloadAddrEdit;

	@ViewInject(R.id.download_btn)
	private Button downloadBtn;

	@ViewInject(R.id.download_page_btn)
	private Button downloadPageBtn;

	@ViewInject(R.id.result_txt)
	private TextView resultText;

	@ResInject(id = R.string.download_label, type = ResType.String)
	private String label;

	@OnClick(R.id.download_btn)
	public void download(View view) {
		String target = "/sdcard/xUtils/" + System.currentTimeMillis()
				+ ".png";
		// String target = "/sdcard/xUtils/" + "aaa.txt";

		try {
			downloadManager.addNewDownload(downloadAddrEdit.getText()
					.toString(), "hhaha", target, true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
					true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
					null);
		} catch (DbException e) {                      
			LogUtils.e(e.getMessage(), e);
		}
		downloadListAdapter.notifyDataSetChanged();
	}

	@OnClick(R.id.download_page_btn)
	public void downloadPage(View view) {
		Intent intent = new Intent(this.getActivity(),
				DownloadListActivity.class);
		this.getActivity().startActivity(intent);
	}

	// ///////////////////////////////////// other
	// ////////////////////////////////////////////////////////////////

	// @OnClick(R.id.download_btn)
	public void testUpload(View view) {

		// 设置请求参数的编码
		// RequestParams params = new RequestParams("GBK");
		RequestParams params = new RequestParams(); // 默认编码UTF-8

		// params.addQueryStringParameter("qmsg", "你好");
		// params.addBodyParameter("msg", "测试");

		// 添加文件
		params.addBodyParameter("file", new File("/sdcard/test.zip"));
		// params.addBodyParameter("testfile", new File("/sdcard/test2.zip"));
		// // 继续添加文件

		// 用于非multipart表单的单文件上传
		// params.setBodyEntity(new FileUploadEntity(new
		// File("/sdcard/test.zip"), "binary/octet-stream"));

		// 用于非multipart表单的流上传
		// params.setBodyEntity(new InputStreamUploadEntity(stream ,length));

		HttpUtils http = new HttpUtils();

		// 设置返回文本的编码， 默认编码UTF-8
		// http.configResponseTextCharset("GBK");

		// 自动管理 cookie
		http.configCookieStore(preferencesCookieStore);

		http.send(HttpRequest.HttpMethod.POST,
				"http://192.168.1.5:8080/UploadServlet", params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						resultText.setText("conn...");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
							resultText.setText("upload: " + current + "/"
									+ total);
						} else {
							resultText.setText("reply: " + current + "/"
									+ total);
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						resultText.setText("reply: " + responseInfo.result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						resultText.setText(msg);
					}
				});
	}

	// @OnClick(R.id.download_btn)
	public void testGet(View view) {

		// RequestParams params = new RequestParams();
		// params.addHeader("name", "value");
		// params.addQueryStringParameter("name", "value");

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10);
		http.send(HttpRequest.HttpMethod.GET, "http://www.baidu.com",
		// params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						resultText.setText("conn...");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						resultText.setText(current + "/" + total);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						resultText.setText("response:" + responseInfo.result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						resultText.setText(msg);
					}
				});
	}

	// @OnClick(R.id.download_btn)
	public void testPost(View view) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("method", "mkdir");
		params.addQueryStringParameter("access_token",
				"3.1042851f652496c9362b1cd77d4f849b.2592000.1377530363.3590808424-248414");
		params.addBodyParameter("path", "/apps/测试应用/test文件夹");

		HttpUtils http = new HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,
				"https://pcs.baidu.com/rest/2.0/pcs/file", params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
						resultText.setText("conn...");
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						resultText.setText(current + "/" + total);
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						resultText.setText("upload response:"
								+ responseInfo.result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						resultText.setText(msg);
					}
				});
	}

	// 同步请求 必须在异步块儿中执行
	private String testGetSync() {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("wd", "lidroid");

		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10);
		try {
			ResponseStream responseStream = http.sendSync(
					HttpRequest.HttpMethod.GET, "http://www.baidu.com/s",
					params);
			// int statusCode = responseStream.getStatusCode();
			// Header[] headers =
			// responseStream.getBaseResponse().getAllHeaders();
			return responseStream.readString();
		} catch (Exception e) {
			LogUtils.e(e.getMessage(), e);
		}
		return null;
	}

	// ////////////////////////////////////////////////////////////////////
	private class DownloadListAdapter extends BaseAdapter {

		private final Context mContext;
		private final LayoutInflater mInflater;

		private DownloadListAdapter(Context context) {
			mContext = context;
			mInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			if (downloadManager == null)
				return 0;
			return downloadManager.getDownloadInfoListCount();
		}

		@Override
		public Object getItem(int i) {
			return downloadManager.getDownloadInfo(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@SuppressWarnings("unchecked")
		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			DownloadItemViewHolder holder = null;
			DownloadInfo downloadInfo = downloadManager.getDownloadInfo(i);
			if (view == null) {
				view = mInflater.inflate(R.layout.download_item, null);
				holder = new DownloadItemViewHolder(downloadInfo);
				ViewUtils.inject(holder, view);
				view.setTag(holder);
				holder.refresh();
			} else {
				holder = (DownloadItemViewHolder) view.getTag();
				holder.update(downloadInfo);
			}

			HttpHandler<File> handler = downloadInfo.getHandler();
			if (handler != null) {
				RequestCallBack callBack = handler.getRequestCallBack();
				if (callBack instanceof DownloadManager.ManagerCallBack) {
					DownloadManager.ManagerCallBack managerCallBack = (DownloadManager.ManagerCallBack) callBack;
					if (managerCallBack.getBaseCallBack() == null) {
						managerCallBack
								.setBaseCallBack(new DownloadRequestCallBack());
					}
				}
				callBack.setUserTag(new WeakReference<DownloadItemViewHolder>(
						holder));
			}

			return view;
		}
	}

	public class DownloadItemViewHolder {
		@ViewInject(R.id.download_label)
		TextView label;
		@ViewInject(R.id.download_state)
		TextView state;
		@ViewInject(R.id.download_pb)
		ProgressBar progressBar;
		@ViewInject(R.id.download_stop_btn)
		Button stopBtn;
		@ViewInject(R.id.download_remove_btn)
		Button removeBtn;
		@ViewInject(R.id.open_btn)
		Button open_btn;
		private DownloadInfo downloadInfo;

		public DownloadItemViewHolder(DownloadInfo downloadInfo) {
			this.downloadInfo = downloadInfo;
		}

		@OnClick(R.id.download_stop_btn)
		public void stop(View view) {
			HttpHandler.State state = downloadInfo.getState();
			switch (state) {
			case WAITING:
			case STARTED:
			case LOADING:
				try {
					downloadManager.stopDownload(downloadInfo);
				} catch (DbException e) {
					LogUtils.e(e.getMessage(), e);
				}
				break;
			case CANCELLED:
			case FAILURE:
				try {
					downloadManager.resumeDownload(downloadInfo,
							new DownloadRequestCallBack());
				} catch (DbException e) {
					LogUtils.e(e.getMessage(), e);
				}
				downloadListAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		}

		@OnClick(R.id.download_remove_btn)
		public void remove(View view) {
			try {
				downloadManager.removeDownload(downloadInfo);
				downloadListAdapter.notifyDataSetChanged();
			} catch (DbException e) {
				LogUtils.e(e.getMessage(), e);
			}
		}

		public void update(DownloadInfo downloadInfo) {
			this.downloadInfo = downloadInfo;
			refresh();
		}

		@OnClick(R.id.open_btn)
		public void open(View view) {
			File file = new File("/sdcard/xUtils/" + downloadInfo.getFileName());
			HttpHandler<File> handler = downloadInfo.getHandler();
			String localname = downloadInfo.getLocalname();
			Toast.makeText(mAppContext, "打开" + localname, Toast.LENGTH_SHORT)
					.show();
			FileManager.openFile(mAppContext, file);
		}

		public void refresh() {
			label.setText(downloadInfo.getFileName());
			state.setText(downloadInfo.getState().toString());
			if (downloadInfo.getFileLength() > 0) {
				progressBar
						.setProgress((int) (downloadInfo.getProgress() * 100 / downloadInfo
								.getFileLength()));
			} else {
				progressBar.setProgress(0);
			}
			open_btn.setVisibility(View.INVISIBLE);
			stopBtn.setVisibility(View.VISIBLE);
			stopBtn.setText(mAppContext.getString(R.string.stop));
			HttpHandler.State state = downloadInfo.getState();
			switch (state) {
			case WAITING:
				stopBtn.setText(mAppContext.getString(R.string.stop));
				break;
			case STARTED:
				stopBtn.setText(mAppContext.getString(R.string.stop));
				break;
			case LOADING:
				stopBtn.setText(mAppContext.getString(R.string.stop));
				break;
			case CANCELLED:
				stopBtn.setText(mAppContext.getString(R.string.resume));
				break;
			case SUCCESS:
				stopBtn.setVisibility(View.INVISIBLE);
				open_btn.setVisibility(View.VISIBLE);
				open_btn.setText(mAppContext.getString(R.string.open));
				break;
			case FAILURE:
				stopBtn.setText(mAppContext.getString(R.string.retry));
				break;
			default:
				break;
			}
		}
	}

	private class DownloadRequestCallBack extends RequestCallBack<File> {

		@SuppressWarnings("unchecked")
		private void refreshListItem() {
			if (userTag == null)
				return;
			WeakReference<DownloadItemViewHolder> tag = (WeakReference<DownloadItemViewHolder>) userTag;
			DownloadItemViewHolder holder = tag.get();
			if (holder != null) {
				holder.refresh();
			}
		}

		@Override
		public void onStart() {
			refreshListItem();
		}

		@Override
		public void onLoading(long total, long current, boolean isUploading) {
			refreshListItem();
		}

		@Override
		public void onSuccess(ResponseInfo<File> responseInfo) {
			refreshListItem();
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			refreshListItem();
		}

		@Override
		public void onCancelled() {
			refreshListItem();
		}
	}
}
