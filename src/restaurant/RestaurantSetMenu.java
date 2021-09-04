package restaurant;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RestaurantSetMenu extends JPanel {
	private JSONObject setMenu;
	private int index;
	
	private String name;
	private long price;
	private String desc;
	private String conf;
	private String image;
	private JSONArray category_list;
	
	private JLabel productImgLabel;
	private JLabel productNameLabel;
	private JLabel productPriceLabel;
	private JLabel productDescLabel;
	
	public RestaurantSetMenu(JSONObject setMenu, int index) {
		this.setMenu = setMenu;
		this.index = index;
		this.setLayout(new GridLayout(4,1));
		
		name = (String)setMenu.get("name");
		price = (long)setMenu.get("price");
		desc = (String)setMenu.get("desc");
		conf = (String)setMenu.get("conf");
		image = (String)setMenu.get("image");
		category_list = (JSONArray)setMenu.get("category_list");
		
		productImgLabel = new JLabel(new ImageIcon(
				((new ImageIcon(image).getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
				)));
		productNameLabel = new JLabel(name);
		productPriceLabel = new JLabel(String.valueOf(price));
		productDescLabel = new JLabel(desc);
	}
	
	public int getIndex() {
		return this.index;
	}
}
