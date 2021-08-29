package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Menu {
	private ArrayList<Category> category_list;
	
	public Menu(JSONArray category_list) {
		this.category_list = new ArrayList<Category>();
		for(int i=0;i<category_list.size();i++) {
			JSONObject n = (JSONObject)category_list.get(i);
			this.category_list.add(new Category(n));
		}
	}
	
	public ArrayList<Category> getCategoryList() {
		return category_list;
	}
	public int getCategoryListSize() {
		return category_list.size();
	}

	public void addProduct(String name, String price, String description, String img_url, int indexNumber) {
//		Category targetCategory = category_list.get(indexNumber);
//		targetCategory.addProduct(name, price, description, img_url);
		try {
			JSONParser parser = new JSONParser();
			JSONObject whole = (JSONObject)parser.parse(new FileReader("information.json"));
			JSONArray category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("category_list");
			JSONObject category = (JSONObject)category_list2.get(indexNumber);
			JSONArray product_list = (JSONArray)category.get("product_list");

			JSONObject add = new JSONObject();
			add.put("name", name);
			add.put("price", price);
			add.put("description", description);
			add.put("image", img_url);
			product_list.add(add);
			
			FileWriter writer = new FileWriter("information.json");
			writer.write(whole.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print() {
		for(Category cat : category_list) {
			for(Product a : cat.getProductList()) {
				System.out.println(a.getName());
			}
		}
	}
}