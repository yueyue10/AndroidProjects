package test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RexTest {

	public static void main(String[] args) throws Exception {
		String message = "【中电科年会】您的手机动态验证密码为：2924，请在2分钟内使用，该密码不可用重复使用，确保安全";
		System.out.println("短信内容为："+message);
		String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
		Pattern p = Pattern.compile(patternCoder);
		Matcher m = p.matcher(message);
        ArrayList<String> strs = new ArrayList<String>();
        while (m.find()) {
            strs.add(m.group(0));
            System.out.println("六位数字:"+m.group(0));
        } 
	}
}
