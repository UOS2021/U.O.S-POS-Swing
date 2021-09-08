package restaurant;

import org.json.simple.JSONObject;

public class RestaurantSideMenu {
	public String category;
	public String name;
	public int price;
	public String desc;
	
	public RestaurantSideMenu(String category2, String name2, int price2, String desc2) {
		category = category2;
		name = name2;
		price = price2;
		desc = desc2;
	}

	public RestaurantSideMenu(JSONObject obj) {
		// TODO Auto-generated constructor stub
		category = (String)obj.get("category");
		
		JSONObject product  = (JSONObject)obj.get("product");
		name = (String)product.get("name");
		price = (int)product.get("price");
		desc = (String)product.get("desc");
	}
}
