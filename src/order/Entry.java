package order;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Entry {
	private String type;
	private order_data myOrder;

	public Entry(String type, JSONObject obj) {
		this.type = type;
		myOrder = new order_data(obj);
	}
	
	public order_data getMyOrder() {
		return myOrder;
	}
	public String getType() {
		return type;
	}
}

class order_data {
	
	private String id;
	private String card_num;
	private int total_price;
	private ArrayList<menu> order_menues;
	private String fcm_token;
	
	public order_data(JSONObject obj) {
		order_menues = new ArrayList<>();
		JSONObject message = (JSONObject) obj.get("message");
		this.id = (String) message.get("id");
		this.card_num = (String) ((JSONObject) message.get("card")).get("num");
		this.fcm_token = (String) message.get("fcm_token");
		JSONArray order = (JSONArray) message.get("order");
		total_price = 0;
		int index = 0;
		for(Object o : order) {
			
			JSONObject or = (JSONObject)o;
			int menu_count = Integer.parseInt(String.valueOf(or.get("count")));
			int price = Integer.parseInt(String.valueOf(or.get("price")));
			
			menu m = new menu(Integer.parseInt(String.valueOf(or.get("type"))) ,(String)or.get("menu"), (String)or.get("submenu"), menu_count, price);
			order_menues.add(m);
			
			total_price += menu_count*price;
				
			index++;
		}
	}
	
	public ArrayList<menu> getOrderMenues(){
		return order_menues;
	}
	
	public String getID() {
		return id;
	}
	
	public String getFcmToken() {
		return fcm_token;
	}
	
	public String getCardNum() {
		return card_num;
	}
	
	public int getTotalPrice() {
		return total_price;
	}
	

}

class menu {
	private int isSet;
	private String mainMenu;
	private String subMenu;
	private int count;
	private int price;
	
	public menu(int isSet, String mainMenu, String subMenu, int count, int price) {
		this.isSet = isSet;
		this.mainMenu = mainMenu;
		this.subMenu = subMenu;
		this.count = count;
		this.price = price;
	}
	public String getMainMenu() {
		return mainMenu;
	}
	public String getSubMenu() {
		return subMenu;
	}
	public int getCount() {
		return count;
	}
	public int getPrice() {
		return price;
	}
	
}
