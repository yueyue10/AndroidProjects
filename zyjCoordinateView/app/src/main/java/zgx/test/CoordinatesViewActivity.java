package zgx.test;

import java.io.File;

import zgx.utils.AppTools;
import zgx.utils.FileManager;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//打开放在assets目录下的pdf文件
public class CoordinatesViewActivity extends Activity {

	Button openpdf;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coordinatersview);
		context = CoordinatesViewActivity.this;
		openpdf = (Button) findViewById(R.id.openpdf);
		// 1.将assets目录下的huiyishouce.pdf文件复制到手机内存里面
		copyPdfFile();
		openpdf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 2.打开手机本地的pdf文件
				String fileP = AppTools.getRootPath() + File.separator
						+ "Download" + File.separator + "大会手册.pdf";
				File zfile = new File(fileP);
				FileManager.openFile(context, zfile);
			}
		});
	}

	private static void copyPdfFile() {

		String fileP = MyApplication.getInstance().getCacheDir().getParent()
				+ File.separator + "Download";
		fileP = AppTools.getRootPath() + File.separator + "Download";
		File file = new File(fileP);
		if (!file.exists()) {
			file.mkdir();
		}
		file = new File(file, "大会手册.pdf");
		if (file.exists()) {
			file.delete();
		}
		AppTools.copy(MyApplication.getInstance(), "huiyishouce.pdf", fileP,
				"大会手册.pdf");
	}
}
