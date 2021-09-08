package restaurant;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RestaurantSideDialog extends JDialog {

	private JFrame frame;
	

	private JSONArray category_list;
	private JSONArray product_list;
	
	private JLabel chooseFoodLabel;
	private JLabel categoryLable;
	private JTextField categoryTF;
	private JLabel nameLabel;
	private JTextField nameTextArea;
	private JLabel priceLabel;
	private JTextField priceTextArea;
	private JLabel descLabel;
	private JTextField descriptionTextArea;
	
	public RestaurantSideDialog(Frame frame) {
		super(frame,"사이드 메뉴 추가",true );
		this.setSize(300,500);
		this.setLayout(new GridLayout(9,1));
		
		category_list = new JSONArray();
		product_list = new JSONArray();
		
		categoryLable = new JLabel("카테고리");
		categoryTF = new JTextField(10);
		nameLabel = new JLabel("이름");
		nameTextArea = new JTextField(10);
		priceLabel = new JLabel("가격");
		priceTextArea = new JTextField(10);
		descLabel = new JLabel("설명");
		descriptionTextArea = new JTextField(10);
		
		this.add(categoryLable);
		this.add(categoryTF);
		this.add(nameLabel);
		this.add(nameTextArea);
		this.add(priceLabel);
		this.add(priceTextArea);
		this.add(descLabel);
		this.add(descriptionTextArea);
	}
	
	public JSONObject getSideMenu() { 
		JSONObject ret = new JSONObject();
		JSONObject product = new JSONObject();
		
		String categoryName = categoryTF.getText();
		String name = nameTextArea.getText();
		int price = Integer.parseInt(priceTextArea.getText());
		String description = descriptionTextArea.getText();
		
		product.put("name",name);
		product.put("price",(int)price);
		product.put("desc",description);
		
		ret.put("category", categoryName);
		ret.put("product", product);
		
		return ret;
	}
}
