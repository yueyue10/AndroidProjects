package com.zyj.xutils.sample.open;//package com.zyj.xutils.sample.open;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.lidroid.xutils.exception.DbException;
//import com.lidroid.xutils.util.LogUtils;
//import com.zyj.xutils.sample.R;
//import com.zyj.xutils.sample.download.DownloadManager;
//
//public class MyAdapter extends BaseAdapter {
//	static List<HashMap<String, String>> urls = new ArrayList<HashMap<String, String>>();
//	Context context;
//	LayoutInflater inflater;
//	DownloadManager downloadManager;
//	static {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("name", "百度图片");
//		map1.put(
//				"url",
//				"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
//		HashMap<String, String> map2 = map1;
//		HashMap<String, String> map3 = map1;
//		HashMap<String, String> map4 = map1;
//		HashMap<String, String> map5 = map1;
//		urls.add(map1);
//		urls.add(map2);
//		urls.add(map3);
//		urls.add(map4);
//		urls.add(map5);
//	}
//
//	public MyAdapter(Context context, DownloadManager downloadManager) {
//		this.context = context;
//		this.downloadManager = downloadManager;
//		this.inflater = LayoutInflater.from(context);
//	}
//
//	@Override
//	public int getCount() {
//		return urls.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return null;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return 0;
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		convertView = inflater.inflate(R.layout.item_test, null);
//		TextView name = (TextView) convertView.findViewById(R.id.name);
//		ProgressBar progress = (ProgressBar) convertView
//				.findViewById(R.id.download_pb);
//		TextView down = (TextView) convertView.findViewById(R.id.download);
//		name.setText(urls.get(position).get("name").toString());
//
//		down.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				String target = "/sdcard/xUtils/" + System.currentTimeMillis()
//						+ "aaa.txt";
//				try {
//					downloadManager.addNewDownload(urls.get(position).get("url").toString()
//							.toString(), "hhaha", target, true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
//							true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
//							null);
//				} catch (DbException e) {
//					LogUtils.e(e.getMessage(), e);
//				}
//			}
//		});
//		return convertView;
//	}
// }
