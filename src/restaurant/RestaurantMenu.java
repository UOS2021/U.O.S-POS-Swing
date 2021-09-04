package restaurant;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

public class RestaurantMenu extends JPanel {
	private JSONObject menu;
	private int index;
	
	private JLabel productImgLabel;
	private JLabel productNameLabel;
	private JLabel productPriceLabel;
	private JLabel productDescLabel;
	
	public RestaurantMenu(JSONObject menu, int index) {
		this.setLayout(new GridLayout(4,1));
		this.menu = menu;
		this.index = index;
		
		productImgLabel = new JLabel(new ImageIcon(
				((new ImageIcon((String)menu.get("image"))).getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
				));
		productNameLabel = new JLabel((String)menu.get("name"));
		productPriceLabel = new JLabel(String.valueOf((long)menu.get("price")));
		productDescLabel = new JLabel((String)menu.get("desc"));
		
		this.add(productImgLabel);
		this.add(productNameLabel);
		this.add(productPriceLabel);
		this.add(productDescLabel);
	}

	public int getIndex() {
		return this.index;
	}
	
}
