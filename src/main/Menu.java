package main;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	public Menu() {
		JSONParser parser = new JSONParser();
		try {
			jsonArr = (JSONArray)(parser.parse(new FileReader("menu.json")));			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
}
