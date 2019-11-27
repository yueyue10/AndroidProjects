package test;

import java.util.HashMap;

public class MapTest {
	public static void main(String[] args) {
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("one", "first");
		map1.put("two", "second");
		map1.put("three", "third");
		map1.put("four", "fourth");
		map1.put("five", "fivth");
		System.out.println(map1.get("three"));
	}
}
