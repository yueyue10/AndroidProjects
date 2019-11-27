package com.zyj.baidumap;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.zyj.baidumap.overlayutil.DrivingRouteOverlay;
import com.zyj.baidumap.overlayutil.OverlayManager;
import com.zyj.baidumap.overlayutil.TransitRouteOverlay;
import com.zyj.baidumap.overlayutil.WalkingRouteOverlay;

/**
 * 此demo用来展示如何进行驾车、步行、公交路线搜索并在地图使用RouteOverlay、TransitOverlay绘制
 * 同时展示如何进行节点浏览并弹出泡泡
 */
public class MyRouteActivity extends Activity implements
		BaiduMap.OnMapClickListener, OnGetGeoCoderResultListener,
		OnGetRoutePlanResultListener, OnClickListener {
	// 浏览路线节点相关
	Button mBtnPre = null; // 上一个节点
	Button mBtnNext = null; // 下一个节点
	int nodeIndex = -1; // 节点索引,供浏览节点时使用
	RouteLine route = null;
	OverlayManager routeOverlay = null;
	private TextView popupText = null; // 泡泡view
	EditText search_location;
	Button searchbtn, jiudian;
	LatLng l1 = null;
	LatLng l2 = new LatLng(40.043692, 116.361159);
	// 地图相关，使用继承MapView的MyRouteMapView目的是重写touch事件实现泡泡处理
	// 如果不处理touch事件，则无需继承，直接使用MapView即可
	MapView mMapView = null; // 地图View
	BaiduMap mBaidumap = null;
	// 搜索相关
	GeoCoder gSearch = null; // 搜索模块，也可去掉地图模块独立使用
	RoutePlanSearch rSearch = null; // 搜索模块，也可去掉地图模块独立使用
	// 定位相关
	LocationClient mLocClient;
	public MyLocationListenner myListener = new MyLocationListenner();
	boolean isFirstLoc = true; // 是否首次定位
	Context mcontext;
	private Marker mMarkerA;
	private Marker mMarkerB;
	private InfoWindow mInfoWindow;
	ArrayList<LatLng> list1 = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	ArrayList<Marker> markers = new ArrayList<>();
	BitmapDescriptor bdA = BitmapDescriptorFactory
			.fromResource(R.drawable.icon_marka);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myroute);
		mcontext = MyRouteActivity.this;
		CharSequence titleLable = "路线规划功能";
		setTitle(titleLable);
		// 初始化地图
		mMapView = (MapView) findViewById(R.id.map);
		mBaidumap = mMapView.getMap();
		mBtnPre = (Button) findViewById(R.id.pre);
		mBtnNext = (Button) findViewById(R.id.next);
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);
		search_location = (EditText) findViewById(R.id.search_location);
		searchbtn = (Button) findViewById(R.id.search);
		jiudian = (Button) findViewById(R.id.jiudian);
		jiudian.setOnClickListener(this);
		searchbtn.setOnClickListener(this);
		// 地图点击事件处理
		mBaidumap.setOnMapClickListener(this);
		// 初始化搜索模块，注册事件监听
		gSearch = GeoCoder.newInstance();
		gSearch.setOnGetGeoCodeResultListener(this);
		rSearch = RoutePlanSearch.newInstance();
		rSearch.setOnGetRoutePlanResultListener(this);
		// 百度地图定位----开启定位图层
		mBaidumap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(10);
		mLocClient.setLocOption(option);
		mLocClient.start();
		mBaidumap.setMyLocationConfigeration(new MyLocationConfiguration(
				LocationMode.NORMAL, true, null));
		initData();
		setAllClickListener();
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
				Button button = new Button(getApplicationContext());
				button.setBackgroundResource(R.drawable.popup);
				button.setTextColor(Color.parseColor("#3a3b3b"));
				button.setTextSize(12);
				OnInfoWindowClickListener listener = null;

				if (marker == mMarkerA || marker == markers.get(0)
						|| marker == markers.get(1) || marker == markers.get(2)) {
					button.setText(marker.getTitle());
					listener = new OnInfoWindowClickListener() {
						public void onInfoWindowClick() {
							if (routeOverlay != null)
								routeOverlay.removeFromMap();
							PlanNode stNode1 = PlanNode.withLocation(l1);
							PlanNode enNode1 = PlanNode.withLocation(marker
									.getPosition());
							rSearch.drivingSearch((new DrivingRoutePlanOption())
									.from(stNode1).to(enNode1));
						}
					};
					LatLng ll = marker.getPosition();
					mInfoWindow = new InfoWindow(BitmapDescriptorFactory
							.fromView(button), ll, -47, listener);
					mBaidumap.showInfoWindow(mInfoWindow);
				}
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v.equals(searchbtn)) {
			String search = search_location.getText().toString();
			// Geo搜索>去掉了city那个参数
			gSearch.geocode(new GeoCodeOption().city("").address(search));
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
		}
	}

	public void openBaidu(View view) {
		Toast.makeText(mcontext, "haha", Toast.LENGTH_SHORT).show();
		// 天安门坐标
		double mLat1 = 39.915291;
		double mLon1 = 116.403857;
		// 百度大厦坐标
		double mLat2 = 40.056858;
		double mLon2 = 116.308194;
		LatLng pt1 = new LatLng(mLat1, mLon1);
		LatLng pt2 = new LatLng(mLat2, mLon2);
		// 构建 导航参数
		NaviParaOption para = new NaviParaOption().startPoint(pt1)
				.endPoint(pt2).startName("天安门").endName("百度大厦");
		try {
			BaiduMapNavigation.openBaiduMapNavi(para, this);
		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			showDialog();
		}
	}

	/**
	 * 发起路线规划搜索示例
	 */
	public void searchButtonProcess(View v) {
		// 重置浏览节点的路线数据
		route = null;
		mBtnPre.setVisibility(View.INVISIBLE);
		mBtnNext.setVisibility(View.INVISIBLE);
		mBaidumap.clear();

		// 设置起终点信息，对于tranist search 来说，城市名无意义
		// PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", editSt
		// .getText().toString());
		// PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", editEn
		// .getText().toString());

		PlanNode stNode1 = PlanNode.withLocation(l1);
		PlanNode enNode1 = PlanNode.withLocation(l2);
		// 实际使用中请对起点终点城市进行正确的设定
		if (v.getId() == R.id.drive) {
			// rSearch.drivingSearch((new DrivingRoutePlanOption()).from(
			// stNode).to(enNode));
			rSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode1)
					.to(enNode1));
		} else if (v.getId() == R.id.transit) {
			rSearch.transitSearch((new TransitRoutePlanOption()).from(stNode1)
					.city("北京").to(enNode1));
		} else if (v.getId() == R.id.walk) {
			rSearch.walkingSearch((new WalkingRoutePlanOption()).from(stNode1)
					.to(enNode1));
		} else if (v.getId() == R.id.bike) {
			rSearch.bikingSearch((new BikingRoutePlanOption()).from(stNode1)
					.to(enNode1));
		}
	}

	/**
	 * 节点浏览示例
	 */
	public void nodeClick(View v) {
		if (route == null || route.getAllStep() == null) {
			return;
		}
		if (nodeIndex == -1 && v.getId() == R.id.pre) {
			return;
		}
		// 设置节点索引
		if (v.getId() == R.id.next) {
			if (nodeIndex < route.getAllStep().size() - 1) {
				nodeIndex++;
			} else {
				return;
			}
		} else if (v.getId() == R.id.pre) {
			if (nodeIndex > 0) {
				nodeIndex--;
			} else {
				return;
			}
		}
		// 获取节结果信息
		LatLng nodeLocation = null;
		String nodeTitle = null;
		Object step = route.getAllStep().get(nodeIndex);
		if (step instanceof DrivingRouteLine.DrivingStep) {
			nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance()
					.getLocation();
			nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
		} else if (step instanceof WalkingRouteLine.WalkingStep) {
			nodeLocation = ((WalkingRouteLine.WalkingStep) step).getEntrance()
					.getLocation();
			nodeTitle = ((WalkingRouteLine.WalkingStep) step).getInstructions();
		} else if (step instanceof TransitRouteLine.TransitStep) {
			nodeLocation = ((TransitRouteLine.TransitStep) step).getEntrance()
					.getLocation();
			nodeTitle = ((TransitRouteLine.TransitStep) step).getInstructions();
		} else if (step instanceof BikingRouteLine.BikingStep) {
			nodeLocation = ((BikingRouteLine.BikingStep) step).getEntrance()
					.getLocation();
			nodeTitle = ((BikingRouteLine.BikingStep) step).getInstructions();
		}

		if (nodeLocation == null || nodeTitle == null) {
			return;
		}
		// 移动节点至中心
		mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
		// show popup
		popupText = new TextView(MyRouteActivity.this);
		popupText.setBackgroundResource(R.drawable.popup);
		popupText.setTextColor(0xFF000000);
		popupText.setText(nodeTitle);
		mBaidumap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));

	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MyRouteActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			mBtnPre.setVisibility(View.VISIBLE);
			mBtnNext.setVisibility(View.VISIBLE);
			route = result.getRouteLines().get(0);
			WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaidumap);
			mBaidumap.setOnMarkerClickListener(overlay);
			routeOverlay = overlay;
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}

	}

	@Override
	public void onGetTransitRouteResult(TransitRouteResult result) {

		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MyRouteActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			mBtnPre.setVisibility(View.VISIBLE);
			mBtnNext.setVisibility(View.VISIBLE);
			route = result.getRouteLines().get(0);
			TransitRouteOverlay overlay = new TransitRouteOverlay(mBaidumap);
			mBaidumap.setOnMarkerClickListener(overlay);
			routeOverlay = overlay;
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}
	}

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MyRouteActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
			// 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
			// result.getSuggestAddrInfo()
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			nodeIndex = -1;
			mBtnPre.setVisibility(View.VISIBLE);
			mBtnNext.setVisibility(View.VISIBLE);
			route = result.getRouteLines().get(0);
			DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaidumap);
			routeOverlay = overlay;
			mBaidumap.setOnMarkerClickListener(overlay);
			overlay.setData(result.getRouteLines().get(0));
			overlay.addToMap();
			overlay.zoomToSpan();
		}
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
			l1 = new LatLng(location.getLatitude(), location.getLongitude());
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaidumap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatus.Builder builder = new MapStatus.Builder();
				builder.target(ll).zoom(13.0f);
				mBaidumap.animateMapStatus(MapStatusUpdateFactory
						.newMapStatus(builder.build()));
			}
			// 添加文字
			LatLng llText = new LatLng(location.getLatitude(),
					location.getLongitude());
			OverlayOptions ooText = new TextOptions().bgColor(0xAAFFFF00)
					.fontSize(24).fontColor(0xFFFF00FF).text("百度地图SDK")
					.rotate(-30).position(llText);
			mBaidumap.addOverlay(ooText);
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
		BaiduMapNavigation.finish(this);
		super.onDestroy();
	}

	@Override
	public void onGetBikingRouteResult(BikingRouteResult arg0) {

	}

	// 获取到经纬度的方法
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(mcontext, "抱歉，未能找到结果", Toast.LENGTH_LONG).show();
			return;
		}
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
