package restaurant;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PosGUIMenuEditRestaurantPanel extends JPanel implements ActionListener{
	
	private CardLayout cards;
	private JFrame frame;
	private JButton backButton;
	private JTabbedPane tp;
	private RestaurantInformation info;
	
	// 카테고리 메뉴 영역
	private JSONArray category_list;
	private JSONArray set_list;
	private JButton addCategoryButton;
	private JButton renameCategoryButton;
	private JTextField renameCategoryTF;
	private JButton deleteCategoryButton;
	
	// 메뉴 추가 영역
	private JButton addMenuButton;
	private JDialog addMenuDialog;
	private JButton imgChooseButton;
	private JButton menuSaveButton;
	private JFileChooser fc;
	private JLabel chooseFoodLabel;
	private JLabel nameLabel;
	private JTextField nameTF;
	private JLabel priceLabel;
	private JTextField priceTF;
	private JLabel descLabel;
	private JTextField descriptionTF;
	
	public PosGUIMenuEditRestaurantPanel(CardLayout cards, JFrame frame) {
		this.cards = cards;
		this.frame = frame;
		
		initTabbedPane();
		insertCategoryIntoTabbedPane();
		
		// 카테고리 이름 변경 패널
		JPanel renameCategoryPanel = new JPanel();
		JLabel renameCategoryLabel = new JLabel("카테고리 이름");
		renameCategoryTF = new JTextField(10);
		renameCategoryButton = new JButton("변경");
		renameCategoryButton.addActionListener(this);
		renameCategoryPanel.add(renameCategoryLabel);
		renameCategoryPanel.add(renameCategoryTF);
		renameCategoryPanel.add(renameCategoryButton);
		
		// 카테고리 추가 버튼
		addCategoryButton = new JButton("카테고리 추가");
		addCategoryButton.addActionListener(this);
		
		// 카테고리 삭제 버튼
		deleteCategoryButton = new JButton("카테고리 삭제");
		deleteCategoryButton.addActionListener(this);
		
		// 메뉴 추가 버튼
		addMenuButton = new JButton("메뉴추가");
		addMenuButton.addActionListener(this);
						
		backButton = new JButton("BACK");
		backButton.addActionListener(this);

		this.add(tp);
		this.add(renameCategoryPanel);
		this.add(addCategoryButton);
		this.add(deleteCategoryButton);
		this.add(addMenuButton);
		this.add(backButton);
	}

	private void initTabbedPane() {
		info = new RestaurantInformation();
		tp = new JTabbedPane();
	}

	private void insertCategoryIntoTabbedPane() {
		// 카테고리 추가
		category_list = info.getCategoryList();
		for(int i=0; i<category_list.size(); i++) {
			JSONObject category = (JSONObject)category_list.get(i);
			RestaurantCategory restaurantCategory = new RestaurantCategory(category);	
			JSONArray product_list = (JSONArray)category.get("product_list");
			
			// 카테고리 안에 메뉴 추가
			for(int j=0; j<product_list.size(); j++) {
				JSONObject menu = (JSONObject) product_list.get(j);
				RestaurantMenu menuPanel = new RestaurantMenu(menu, j);
				menuPanel.addMouseListener(new MouseAdapter() { // 음식 클릭 시 삭제
					public void mouseClicked(MouseEvent e) {
						RestaurantMenu delete_target = (RestaurantMenu)e.getSource();
						int menuIndex = delete_target.getIndex();
						int categoryIndex = tp.getSelectedIndex();
						deleteMenu(categoryIndex, menuIndex);
					}
				});
				restaurantCategory.add(menuPanel);
			}
			tp.add(restaurantCategory, restaurantCategory.getCategoryName());
		}
		
		// 세트 카테고리 추가
		set_list = info.getSetList();
		RestaurantSetCategory restaurantSetCategory = new RestaurantSetCategory(set_list);
		for(int i=0; i<set_list.size(); i++) {
			JSONObject set = (JSONObject)set_list.get(i);
			RestaurantSetMenu setMenuPanel = new RestaurantSetMenu(set, i);
			setMenuPanel.addMouseListener(new MouseAdapter() { // 음식 클릭 시 삭제
				public void mouseClicked(MouseEvent e) {
					RestaurantSetMenu delete_target = (RestaurantSetMenu)e.getSource();
					int menuIndex = delete_target.getIndex();
					deleteSetMenu(menuIndex);
				}
			});
			restaurantSetCategory.add(setMenuPanel);
		}
		tp.add(restaurantSetCategory, restaurantSetCategory.getCategoryName());
	}
	
	private void updateCategory() {
		tp.removeAll();
		
		info.update();
		// 카테고리 추가
		category_list = info.getCategoryList();
		for(int i=0; i<category_list.size(); i++) {
			JSONObject category = (JSONObject)category_list.get(i);
			RestaurantCategory restaurantCategory = new RestaurantCategory(category);	
			JSONArray product_list = (JSONArray)category.get("product_list");
			
			// 카테고리 안에 메뉴 추가
			for(int j=0; j<product_list.size(); j++) {
				JSONObject menu = (JSONObject) product_list.get(j);
				RestaurantMenu menuPanel = new RestaurantMenu(menu, j);
				menuPanel.addMouseListener(new MouseAdapter() { // 음식 클릭 시 삭제
					public void mouseClicked(MouseEvent e) {
						RestaurantMenu delete_target = (RestaurantMenu)e.getSource();
						int menuIndex = delete_target.getIndex();
						int categoryIndex = tp.getSelectedIndex();
						deleteMenu(categoryIndex, menuIndex);
					}
				});
				restaurantCategory.add(menuPanel);
			}
			tp.add(restaurantCategory, restaurantCategory.getCategoryName());
		}
		
		// 세트 카테고리 추가
		set_list = info.getSetList();
		RestaurantSetCategory restaurantSetCategory = new RestaurantSetCategory(set_list);
		for(int i=0; i<set_list.size(); i++) {
			JSONObject set = (JSONObject)set_list.get(i);
			RestaurantSetMenu setMenuPanel = new RestaurantSetMenu(set, i);
			setMenuPanel.addMouseListener(new MouseAdapter() { // 음식 클릭 시 삭제
				public void mouseClicked(MouseEvent e) {
					RestaurantSetMenu delete_target = (RestaurantSetMenu)e.getSource();
					int menuIndex = delete_target.getIndex();
					deleteSetMenu(menuIndex);
				}
			});
			restaurantSetCategory.add(setMenuPanel);
		}
		tp.add(restaurantSetCategory, restaurantSetCategory.getCategoryName());
		
	}
	
	private void deleteMenu(int categoryIndex, int menuIndex) { // 메뉴 삭제
		try {
			JSONParser parser = new JSONParser();
			JSONObject whole = (JSONObject)parser.parse(new FileReader("information_restaurant.json"));
			JSONArray category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("category_list");
			JSONObject category = (JSONObject)category_list2.get(categoryIndex);
			JSONArray product_list = (JSONArray)category.get("product_list");
			
			// 삽입
			product_list.remove(menuIndex);
			FileWriter writer = new FileWriter("information_restaurant.json");
			writer.write(whole.toJSONString());
			writer.flush();
			
			updateCategory();
			writer.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void deleteSetMenu(int menuIndex) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject whole = (JSONObject)parser.parse(new FileReader("information_restaurant.json"));
			JSONArray set_list = (JSONArray)((JSONObject)whole.get("message")).get("set_list");
			
			// 삽입
			set_list.remove(menuIndex);
			FileWriter writer = new FileWriter("information_restaurant.json");
			writer.write(whole.toJSONString());
			writer.flush();
			
			updateCategory();
			writer.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backButton) { // Back Button
			cards.show(frame.getContentPane(), "mainPanel");
		} else if(e.getSource() == addMenuButton) { // 메뉴 추가 버튼 클릭
				addMenuDialog = new JDialog(frame, "메뉴 추가", true);
				addMenuDialog.setSize(300,500);
				addMenuDialog.setLayout(new GridLayout(9,1));
				
				chooseFoodLabel = new JLabel();
				imgChooseButton = new JButton("Open");
				menuSaveButton = new JButton("Save");
				nameLabel = new JLabel("이름");
				nameTF = new JTextField(10);
				priceLabel = new JLabel("가격");
				priceTF = new JTextField(10);
				descLabel = new JLabel("설명");
				descriptionTF = new JTextField(10);
				imgChooseButton.addActionListener(this);
				menuSaveButton.addActionListener(this);
				
				addMenuDialog.add(chooseFoodLabel);
				addMenuDialog.add(imgChooseButton);
				addMenuDialog.add(nameLabel);
				addMenuDialog.add(nameTF);
				addMenuDialog.add(priceLabel);
				addMenuDialog.add(priceTF);
				addMenuDialog.add(descLabel);
				addMenuDialog.add(descriptionTF);
				addMenuDialog.add(menuSaveButton);
				
				addMenuDialog.setVisible(true);		
		} else if(e.getSource() == imgChooseButton) { // 메뉴 추가 다이얼로그에서 사진 선택버튼
			fc = new JFileChooser();
			int i= fc.showOpenDialog(this);
			if(i==JFileChooser.APPROVE_OPTION) { 
				File f = fc.getSelectedFile();
				String filepath = f.getPath();				
				ImageIcon icon = new ImageIcon(filepath);
				chooseFoodLabel.setIcon(icon);
			}
		} else if(e.getSource() == menuSaveButton) { // 메뉴 추가 다이얼로그에서 저장버튼
				String name = nameTF.getText();
				int price = Integer.parseInt(priceTF.getText());
				String description = descriptionTF.getText();
		//			String[] arg = fc.getSelectedFile().toString().split("\\\\");
				String img_url = fc.getSelectedFile().toString();
				// json에 저장
				try {
					JSONParser parser = new JSONParser();
					JSONObject whole = (JSONObject)parser.parse(new FileReader("information_restaurant.json"));
					JSONArray category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("category_list");
					JSONObject category = (JSONObject)category_list2.get(tp.getSelectedIndex());
					JSONArray target_product_list = (JSONArray)category.get("product_list");
					
					// 넣을 데이터 구성
					JSONObject inputData = new JSONObject();
					inputData.put("name", name);
					inputData.put("price", price);
					inputData.put("desc", description);
					inputData.put("image", img_url);
					
					// 삽입
					target_product_list.add(inputData);
					FileWriter writer = new FileWriter("information_restaurant.json");
					writer.write(whole.toJSONString());
					writer.flush();
					writer.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateCategory();
				addMenuDialog.dispose();
		} else if(e.getSource() == addCategoryButton) { // 카테고리 + 버튼
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(new FileReader("information_restaurant.json"));
				JSONArray target_category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("category_list");
				
				// 넣을 데이터 구성
				JSONObject inputData = new JSONObject();
				JSONArray product_list = new JSONArray();
				inputData.put("category", "추가");
				inputData.put("product_list", product_list);
				
				// 삽입
				target_category_list2.add(inputData);
				FileWriter writer = new FileWriter("information_restaurant.json");
				writer.write(whole.toJSONString());
				writer.flush();
				writer.close();
				updateCategory();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == renameCategoryButton) { // 카테고리 이름 변경
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(new FileReader("information_restaurant.json"));
				JSONArray category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("category_list");
				JSONObject target_category = (JSONObject)category_list2.get(tp.getSelectedIndex());
				
				// 넣을 데이터 구성
				String inputData = (String)renameCategoryTF.getText();
				
				// 삽입
				target_category.replace("category", inputData);
				FileWriter writer = new FileWriter("information_restaurant.json");
				writer.write(whole.toJSONString());
				writer.flush();
				
				updateCategory();
				writer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if(e.getSource() == deleteCategoryButton) {
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(new FileReader("information_restaurant.json"));
				JSONArray category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("category_list");
				category_list2.remove(tp.getSelectedIndex());
				
				// 삽입
				FileWriter writer = new FileWriter("information_restaurant.json");
				writer.write(whole.toJSONString());
				writer.flush();
				
				updateCategory();
				writer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
