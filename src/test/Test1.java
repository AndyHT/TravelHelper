package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.JsonHelper;
import entity.User;

public class Test1 {

	public static void main(String[] args) throws JSONException, ParseException {
		// JSON格式数据解析对象
		JSONObject jo = new JSONObject();

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("name", "Alexia");
		map1.put("sex", "female");
		map1.put("age", "23");

		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("name", "Edward");
		map2.put("sex", "male");
		map2.put("age", "24");

		@SuppressWarnings("rawtypes")
		List<Map> list = new ArrayList<Map>();
		list.add(map1);
		list.add(map2);

		User user = new User();

		user.setGender("male");
		user.setEmail("111@11.com");
		user.setPassword("111");
		user.setProfile(null);
		user.setUserName("Bob");

		// 将Map转换为JSONArray数据
		JSONArray ja = new JSONArray();
		ja.put(map1);

		System.out.println("JSONArray对象数据格式: ");
		System.out.println(ja.toString());

		// 将JavaBean转换为Json数据(需要Map中转)
		JSONObject jo1 = JsonHelper.toJSON(user);

		System.out.println("仅含User对象的Json数据格式: ");
		System.out.println(jo1.toString());

		jo.put("map", ja);
		jo.put("user", jo1.toString());
		System.out.println("最终构造的JSON数据格式: ");
		System.out.println(jo.toString());
		
		// 将Json数据转换为Map
		JSONObject jo2 = new JSONObject("{\"map\": [{\"name\": \"a\", \"sex\" : \"male\", \"age\": \"12\"}]}");
		JSONArray ja2 = jo2.getJSONArray("map");
		System.out.println("\n将Json数据解析为Map：");
		System.out.println("name: " + ja2.getJSONObject(0).getString("name")
				+ " sex: " + ja2.getJSONObject(0).getString("sex") + " age: "
				+ ja2.getJSONObject(0).getInt("age"));
		
		
		// 将Json数据转换为JavaBean
//		JSONObject jo3 = new JSONObject("\"user\":\"{\"mail\":\"111@11.com\",\"userName\":\"Bob\",\"gender\":\"male\",\"user_id\":\"0\",\"password\":\"111\",\"profile\":\"\"}");
//		String jsonStr = jo.getString("user");
		String jsonStr = "{\"mail\":\"111@11.com\",\"userName\":\"Bob\",\"gender\":\"male\",\"user_id\":\"0\",\"password\":\"111\",\"profile\":\"\"}";
		User user1 = new User();
		JsonHelper.toJavaBean(user1, jsonStr);

		System.out.println(jsonStr);
		System.out.println("\n将Json数据解析为User对象：");
		System.out.println("name: " + user1.getUserName() + " sex: " + user1.getGender()
				+ " email: " + user1.getEmail());

	}

}
