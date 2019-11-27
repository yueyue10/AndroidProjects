package test;

public class StringTest {

	public static void main(String[] args) throws Exception {
		String photourl = "storage/emulated/0/Pictures/multi_image_20161222_163801.jpg";
		String fileName = photourl.substring(photourl.lastIndexOf("/") + 1);
		System.out.println("fileName:" + fileName);
	}
}
