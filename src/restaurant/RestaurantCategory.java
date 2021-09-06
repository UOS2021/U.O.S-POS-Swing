package restaurant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestaurantCategory extends JPanel{
		private JSONObject category;
		private JSONArray product_list;
		
		// 메뉴 영역
		private JPanel[] menuPanel;
		
		public RestaurantCategory() {
			this.setLayout(new FlowLayout());
		}
		
		public RestaurantCategory(JSONObject category) {
			this.setLayout(new FlowLayout());
			this.category = category;
			this.product_list = (JSONArray)category.get("product_list");
		}
		
		public String getCategoryName() {
			String categoryName = (String)category.get("category");
			return categoryName;
		}
}
