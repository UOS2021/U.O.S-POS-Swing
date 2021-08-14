package main;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Menu {
	private JSONArray jsonArr;
	private FileWriter jsonWriter;
	
	public Menu() {
		JSONParser parser = new JSONParser();
		try {
			jsonArr = (JSONArray)(parser.parse(new FileReader("menu.json")));			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMenu() {
		return jsonArr.toString();
	}
	
	public JSONArray getMenuJSON() {
		return jsonArr;
	}
	
	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();
		for(int i=0;i<jsonArr.size();i++) {
			JSONObject categoryArr = (JSONObject) jsonArr.get(i);
			String categoryStr = (String) categoryArr.get("category");
			ret.add(categoryStr);
		}
		return ret;
	}
	
	public int getCategorySize() {
		return jsonArr.size();
	}
	
	public void addMenu(JSONObject menu, int idx) {
		JSONObject category = (JSONObject) jsonArr.get(idx);
		JSONArray me = (JSONArray)category.get("menu");
		me.add(menu);
		try {
			jsonWriter = new FileWriter("menu.json");
			// 여기해야대
//			String jsonString = 
//			jsonWriter.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(jsonArr);		
	}
	
	public void addCategory() {
		
	}
	
	public JSONObject makeMenu(String name, String price, String description, String img_url) {
		JSONObject newMenu = new JSONObject();
		newMenu.put("name", name);
		newMenu.put("price", price);
		newMenu.put("description", description);
		newMenu.put("img_url", img_url);
		return newMenu;
	}
	
}
