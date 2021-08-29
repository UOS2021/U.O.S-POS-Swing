package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Category{
	private String categoryName;
	private JSONArray set_list;
	
	private ArrayList<Product> product_list;
	
	public Category(JSONObject category) {
		product_list = new ArrayList<Product>();
		categoryName = (String)category.get("category");
		for(int i=0;i<((JSONArray)category.get("product_list")).size();i++) {
			product_list.add(new Product((JSONObject)((JSONArray)category.get("product_list")).get(i)));
		}
		set_list = (JSONArray)category.get("set_list");
	}

	public String getCategoryName() {
		return categoryName;
	}

	public ArrayList<Product> getProductList() {
		return product_list;
	}

	public JSONArray getSetList() {
		return set_list;
	}

	public void addProduct(String name, String price, String description, String img_url) {
//		Product add = new Product(name, price, description, img_url);
//		product_list.add(add);
//		
//		
//		
//		return 
	}
}