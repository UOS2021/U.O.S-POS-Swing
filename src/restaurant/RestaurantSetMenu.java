package restaurant;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
	private JLabel productConfLable;
	private JLabel productDescLabel;
	
	public RestaurantSetMenu(JSONObject setMenu, int index) {
		this.setMenu = setMenu;
		this.index = index;
		this.setLayout(new GridLayout(5,1));
		this.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(Color.BLACK,2)), BorderFactory.createEmptyBorder(1, 1, 1, 1)));

		image = (String)setMenu.get("image");
		name = (String)setMenu.get("name");
		price = (long)setMenu.get("price");
		conf = (String)setMenu.get("conf");
		desc = (String)setMenu.get("desc");
		category_list = (JSONArray)setMenu.get("category_list");
		
		productImgLabel = new JLabel(new ImageIcon(
				((new ImageIcon(image).getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
				)));
		productNameLabel = new JLabel(name);
		productPriceLabel = new JLabel(String.valueOf(price));
		productConfLable = new JLabel(conf);
		productDescLabel = new JLabel(desc);
		
		this.add(productImgLabel);
		this.add(productNameLabel);
		this.add(productPriceLabel);
		this.add(productConfLable);
		this.add(productDescLabel);
	}
	
	public int getIndex() {
		return this.index;
	}
}
