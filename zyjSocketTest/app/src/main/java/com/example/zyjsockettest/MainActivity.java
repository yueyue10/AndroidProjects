package com.example.zyjsockettest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText ip_et, port_et, message_et;
	Button btn_send;
	Spinner spinner1, spinner2;
	Context context;
	private List<String> data_list1 = new ArrayList<String>();
	private List<String> data_list2 = new ArrayList<String>();
	private ArrayAdapter<String> arr_adapter1;
	private ArrayAdapter<String> arr_adapter2;
	Handler mhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
//				Toast.makeText(context, "发送成功！", Toast.LENGTH_SHORT).show();
				getCache();
				arr_adapter1.notifyDataSetChanged();
				arr_adapter2.notifyDataSetChanged();
				break;
			case 1:
				Toast.makeText(context, "发送失败！", Toast.LENGTH_SHORT).show();
				break;
			 }
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		context = MainActivity.this;
		SharePreferenceUtils.getAppConfig(context);
		initView();
		// mhandler.sendEmptyMessage(0);
		setAllClick();
	}

	private void initView() {
		ip_et = (EditText) findViewById(R.id.ip_et);
		port_et = (EditText) findViewById(R.id.port_et);
		message_et = (EditText) findViewById(R.id.message_et);
		btn_send = (Button) findViewById(R.id.btn_send);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		// 适配器
		arr_adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data_list1);
		// 设置样式
		arr_adapter1
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 加载适配器
		spinner1.setAdapter(arr_adapter1);
		// 第二个适配器设置
		arr_adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, data_list2);
		arr_adapter2
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(arr_adapter2);
	}

	private void setAllClick() {
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String ipstr = ip_et.getText().toString();
				String port = port_et.getText().toString();
				final String message = message_et.getText().toString();
				 refreshCache(ipstr, port + "");
				 mhandler.sendEmptyMessage(0);
				try {
					final int port1 = Integer.parseInt(port);
					System.out.println("ipstr=" + ipstr);
					System.out.println("port1=" + port1);
					new Thread(new Runnable() {

						@Override
						public void run() {
							process(ipstr, port1, message);
						}
					}).start();
				} catch (Exception e) {
					System.out.println("没有ip地址或端口");
				}
			}
		});
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ip_et.setText(data_list1.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				port_et.setText(data_list2.get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	private void process(String ipstr, int port, String message) {
		try {
			// 1、创建客户端Socket，指定服务器地址和端口
			// Socket socket=new Socket("127.0.0.1",5200);
			// Socket socket = new Socket("192.168.1.115", 5209);
			Socket socket = new Socket(ipstr, port);
			System.out.println("客户端启动成功");
			// 2、获取输出流，向服务器端发送信息
			// 向本机的52000端口发出客户请求
			// BufferedReader br = new BufferedReader(new InputStreamReader(
			// System.in));
			InputStream in_nocode = new ByteArrayInputStream(message.getBytes());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					in_nocode));
			// 由系统标准输入设备构造BufferedReader对象
			PrintWriter write = new PrintWriter(socket.getOutputStream());
			// 由Socket对象得到输出流，并构造PrintWriter对象
			// 3、获取输入流，并读取服务器端的响应信息
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			// 由Socket对象得到输入流，并构造相应的BufferedReader对象
			String readline;
			readline = br.readLine(); // 从系统标准输入读入一字符串
			while (!readline.equals("end")) {
				// 若从标准输入读入的字符串为 "end"则停止循环
				write.println(readline);
				// 将从系统标准输入读入的字符串输出到Server
				write.flush();
				// 刷新输出流，使Server马上收到该字符串
				System.out.println("Client:" + readline);
				// 在系统标准输出上打印读入的字符串
				System.out.println("Server:" + in.readLine());
				// 从Server读入一字符串，并打印到标准输出上
				readline = br.readLine(); // 从系统标准输入读入一字符串
			} // 继续循环
				// 4、关闭资源
			write.close(); // 关闭Socket输出流
			in.close(); // 关闭Socket输入流
			socket.close(); // 关闭Socket
//			refreshCache(ipstr, port + "");
//			mhandler.sendEmptyMessage(0);
			message_et.setText("");
		} catch (Exception e) {
			mhandler.sendEmptyMessage(1);
			System.out.println("can not listen to:" + e);// 出错，打印出错信息
		}
	}

	private void getCache() {
		String ipstr_cache = (String) SharePreferenceUtils
				.getParam("ipstr", "");
		String port_cache = (String) SharePreferenceUtils.getParam("port", "");
		String[] split1 = ipstr_cache.split(",");
		String[] split2 = port_cache.split(",");
		data_list1.clear();
		for (String string : split1) {
			data_list1.add(string);
		}
		data_list2.clear();
		for (String string : split2) {
			data_list2.add(string);
		}
		// 初始的时候给edittext设置默认值是最新的输入
		if (data_list1.size() > 0)
			ip_et.setText(data_list1.get(data_list1.size() - 1));
		if (data_list2.size() > 0)
			port_et.setText(data_list2.get(data_list2.size() - 1));
	}

	private void refreshCache(String ipstr, String port) {
		String ipstr_cache = (String) SharePreferenceUtils
				.getParam("ipstr", "");
		String port_cache = (String) SharePreferenceUtils.getParam("port", "");
		if (!ipstr_cache.equals("")) {
			SharePreferenceUtils.setParam("ipstr", ipstr_cache + "," + ipstr);
		} else {
			SharePreferenceUtils.setParam("ipstr", ipstr);
		}
		if (!port_cache.equals("")) {
			SharePreferenceUtils.setParam("port", port_cache + "," + port);
		} else {
			SharePreferenceUtils.setParam("port", port);
		}
	}
}
