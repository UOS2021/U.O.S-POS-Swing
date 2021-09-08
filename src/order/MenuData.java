package order;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MenuData {
	private JSONObject menu;
	public MenuData() {
		menu = new JSONObject();
		
		menu.put("Hamburger", 10000);
		menu.put("Pizza", 15000);
		menu.put("Chicken", 20000);
		menu.put("Butter", 5000);
	}
	
	public String getData() {
		return menu.toString();
	}
}
