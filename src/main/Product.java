package main;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.json.simple.JSONObject;

public class Product {
	private String name;
	private String price;
	private String desc;
	private String image_url;
	
	private ImageIcon image;
	
	public Product(JSONObject obj) {
		name = (String)obj.get("name");
		price = (String)obj.get("price");
		desc = (String)obj.get("desc");
		image_url = (String)obj.get("image");
		ImageIcon foodIcon = new ImageIcon(image_url);
		image = new ImageIcon(
				(foodIcon.getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
				);
	}
	
	public Product(String name, String price, String description, String img_url) {
		this.name = name;
		this.price = price;
		this.desc = description;
		this.image_url = img_url;
	}

	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getDesc() {
		return desc;
	}
	public ImageIcon getImage() {
		return image;
	}
}
