package com.zyj.baidumap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.zyj.baidumap.overlayutil.DrivingRouteOverlay;
import com.zyj.baidumap.overlayutil.OverlayManager;

/**
 * 此demo用来展示如何进行驾车、步行、公交路线搜索并在地图使用RouteOverlay、TransitOverlay绘制
 * 同时展示如何进行节点浏览并弹出泡泡
 */
public class MRouteActivity extends Activity implements
		BaiduMap.OnMapClickListener, OnGetGeoCoderResultListener,
		OnGetRoutePlanResultListener, OnClickListener {
	OverlayManager routeOverlay = null;
	MapView mMapView = null; // 地图View
	// 搜索相关
	BaiduMap mBaidumap = null;
	GeoCoder gSearch = null; // 搜索模块，也可去掉地图模块独立使用
	RoutePlanSearch rSearch = null; // 搜索模块，也可去掉地图模块独立使用
	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	boolean isFirstLoc = true; // 是否首次定位
	private Marker mMarkerA;
	private Marker mMarkerB;
	private InfoWindow mInfoWindow;
	ArrayList<LatLng> list1 = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	ArrayList<Marker> markers = new ArrayList<>();
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);
	Context mcontext;
	EditText search_location;
	Button searchbtn, jiudian, huichang, jingdian, dingwei_btn, openbaidu_btn,
			route_btn;
	// l1是定位的坐标，l2是点击的搜索地点或者常用的酒店类地点
	LatLng ml1 = null;
	LatLng ml2 = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mroute);
		mcontext = MRouteActivity.this;
		CharSequence titleLable = "路线规划功能";
		setTitle(titleLable);
		// 初始化地图
		mMapView = (MapView) findViewById(R.id.map);
		mBaidumap = mMapView.getMap();
		initView();
		mBaidumap.setMyLocationConfigeration(new MyLocationConfiguration(
				LocationMode.NORMAL, true, null));
		// 百度地图定位----开启定位图层
		mBaidumap.setMyLocationEnabled(true);
		// 地图点击事件处理
		mBaidumap.setOnMapClickListener(this);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(10);
		mLocClient.setLocOption(option);
		mLocClient.start();
		initData();
		setAllClickListener();
	}

	private void initView() {
		search_location = (EditText) findViewById(R.id.search_location);
		searchbtn = (Button) findViewById(R.id.search);
		jiudian = (Button) findViewById(R.id.jiudian);
		huichang = (Button) findViewById(R.id.huichang);
		jingdian = (Button) findViewById(R.id.jingdian);
		dingwei_btn = (Button) findViewById(R.id.dingwei_btn);
		openbaidu_btn = (Button) findViewById(R.id.openbaidu_btn);
		route_btn = (Button) findViewById(R.id.route_btn);
		searchbtn.setOnClickListener(this);
		jiudian.setOnClickListener(this);
		huichang.setOnClickListener(this);
		jingdian.setOnClickListener(this);
		dingwei_btn.setOnClickListener(this);
		openbaidu_btn.setOnClickListener(this);
		route_btn.setOnClickListener(this);

		// 初始化搜索模块，注册事件监听
		gSearch = GeoCoder.newInstance();
		gSearch.setOnGetGeoCodeResultListener(this);
		rSearch = RoutePlanSearch.newInstance();
		rSearch.setOnGetRoutePlanResultListener(this);
	}

	private void initData() {
		LatLng l1 = new LatLng(40.038493, 116.373727);
		LatLng l2 = new LatLng(40.042749, 116.360883);
		LatLng l3 = new LatLng(40.046996, 116.344705);
		String name1 = "7天连锁酒店(北京清河宝盛里店)";
		String name2 = "7天酒店(北京清河永泰庄地铁站店)停车场";
		String name3 = "7天连锁酒店(北京上地小营桥店)";
		list1.add(l1);
		list1.add(l2);
		list1.add(l3);
		names.add(name1);
		names.add(name2);
		names.add(name3);
	}

	private void setAllClickListener() {
		mBaidumap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(final Marker marker) {
				// Button button = new Button(getApplicationContext());
				// button.setBackgroundResource(R.drawable.popup);
				// button.setTextColor(Color.parseColor("#3a3b3b"));
				// button.setTextSize(12);
				TextView nametv = new TextView(getApplicationContext());
				nametv.setBackgroundResource(R.drawable.popup);
				nametv.setTextColor(Color.parseColor("#3a3b3b"));
				nametv.setTextSize(12);
				if (marker == mMarkerA || marker == markers.get(0)
						|| marker == markers.get(1) || marker == markers.get(2)) {
					// button.setText(marker.getTitle());
					nametv.setText(marker.getTitle());
					ml2 = marker.getPosition();
					mInfoWindow = new InfoWindow(BitmapDescriptorFactory
							.fromView(nametv), ml2, -47, null);
					mBaidumap.showInfoWindow(mInfoWindow);
				}
				return true;
			}
		});
		search_location.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				String search = search_location.getText().toString();
				if (search.equals("")) {
					Toast.makeText(mcontext, "输入为空", Toast.LENGTH_SHORT).show();
				} else {
					((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(MRouteActivity.this
									.getCurrentFocus().getWindowToken(),
									InputMethodManager.HIDE_NOT_ALWAYS);
					gSearch.geocode(new GeoCodeOption().city("")
							.address(search));
					Toast.makeText(mcontext, "执行搜索", Toast.LENGTH_SHORT).show();
				}
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v.equals(searchbtn)) {
			String search = search_location.getText().toString();
			if (search.equals("")) {
				Toast.makeText(mcontext, "输入为空", Toast.LENGTH_SHORT).show();
			} else {
				((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(MRouteActivity.this
								.getCurrentFocus().getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				Toast.makeText(mcontext, "执行搜索", Toast.LENGTH_SHORT).show();
				// Geo搜索>去掉了city那个参数
				gSearch.geocode(new GeoCodeOption().city("").address(search));
			}
		} else if (v.equals(jiudian)) {
			mBaidumap.clear();
			markers.clear();
			for (int i = 0; i < list1.size(); i++) {
				MarkerOptions oob = new MarkerOptions().title(names.get(i))
						.position(list1.get(i)).icon(bdA).zIndex(i)
						.draggable(true);
				mMarkerB = (Marker) (mBaidumap.addOverlay(oob));
				markers.add(mMarkerB);
			}
		} else if (v.equals(huichang)) {
			Toast.makeText(mcontext, "添加会场的marker", Toast.LENGTH_SHORT).show();
		} else if (v.equals(jingdian)) {
			Toast.makeText(mcontext, "添加景点的marker", Toast.LENGTH_SHORT).show();
		} else if (v.equals(dingwei_btn)) {
			MapStatus.Builder builder = new MapStatus.Builder();
			builder.target(ml1).zoom(15.0f);
			mBaidumap.animateMapStatus(MapStatusUpdateFactory
					.newMapStatus(builder.build()));
		} else if (v.equals(openbaidu_btn)) {
			if ( ml2 != null) {
				Toast.makeText(mcontext, "正在打开百度地图！", Toast.LENGTH_SHORT)
						.show();
				// 构建 导航参数
				NaviParaOption para = new NaviParaOption().startPoint(ml1)
						.endPoint(ml2).startName("天安门").endName("百度大厦");
				try {
					BaiduMapNavigation.openBaiduMapNavi(para, this);
				} catch (BaiduMapAppNotSupportNaviException e) {
					e.printStackTrace();
					showDialog();
				}
			} else {
				Toast.makeText(mcontext, "请先确定目的地！", Toast.LENGTH_SHORT).show();
			}
		} else if (v.equals(route_btn)) {
			// 重置浏览节点的路线数据
			if (routeOverlay != null)
				routeOverlay.removeFromMap();
			PlanNode stNode1 = PlanNode.withLocation(ml1);
			PlanNode enNode1 = PlanNode.withLocation(ml2);
			rSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode1)
					.to(enNode1));
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null) {
				return;
			}
			// 这里一般不会走，走到了ifFrirstLoc里面
			ml1 = new LatLng(location.getLatitude(), location.getLongitude());
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaidumap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				MapStatus.Builder builder = new MapStatus.Builder();
				builder.target(ml1).zoom(15.0f);
				mBaidumap.animateMapStatus(MapStatusUpdateFactory
						.newMapStatus(builder.build()));
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	@Override
	public void onMapClick(LatLng point) {
		mBaidumap.hideInfoWindow();
	}

	@Override
	public boolean onMapPoiClick(MapPoi poi) {
		return false;
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 回收 bitmap 资源
		bdA.recycle();
		gSearch.destroy();
		rSearch.destroy();
		mMapView.onDestroy();
		mLocClient.stop();
		BaiduMapNavigation.finish(this);
		super.onDestroy();
	}

	// 获取到经纬度的方法
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(mcontext, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
			return;
		}
		ml2 = result.getLocation();
		mBaidumap.clear();
		mMarkerA = (Marker) mBaidumap.addOverlay(new MarkerOptions()
				.position(result.getLocation()).title(result.getAddress())
				.icon(bdA));
		mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		String strInfo = String.format("纬度：%f 经度：%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(mcontext, strInfo, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

	}

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MRouteActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaidumap);
			routeOverlay = overlay;
			mBaidumap.setOnMarkerClickListener(overlay);
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}
	}

	@Override
	public void onGetBikingRouteResult(BikingRouteResult arg0) {

	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult arg0) {

	}

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult arg0) {

	}

	/**
	 * 提示未安装百度地图app或app版本过低
	 */
	public void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				OpenClientUtil.getLatestBaiduMapApp(mcontext);
			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();

	}
}
