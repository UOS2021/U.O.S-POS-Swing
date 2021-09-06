package pcroom;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.Main;
import restaurant.RestaurantMenu;
import restaurant.RestaurantSetCategory;
import restaurant.RestaurantSetMenu;

public class PosGUIMenuEditPCRoomPanel implements ActionListener {

	private Main main;
//	private JFrame frame;
	private JPanel panel;
	private JPanel this_panel;
	private JTabbedPane tp;
	private PCRoomInformation info;
	private JButton backButton;

	// 카테고리 메뉴 영역
	private JSONArray category_list;
	private JButton addCategoryButton;
	private JButton renameCategoryButton;
	private JTextField renameCategoryTF;
	private JButton deleteCategoryButton;
	private JSONArray set_list;

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

	private BufferedReader reader;
	private PrintWriter writer;

	public PosGUIMenuEditPCRoomPanel(Main main) {
		this.main = main;
		panel = this.main.getPanel();
		this_panel = new JPanel();
		panel.add(this_panel,BorderLayout.CENTER);
		this_panel.setBackground(new Color(255, 200, 79));
		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);
		
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
		renameCategoryPanel.setBounds(500, 300, 300, 40);

		// 카테고리 추가 버튼
		addCategoryButton = new JButton("카테고리 추가");
		addCategoryButton.addActionListener(this);
		addCategoryButton.setBounds(510, 0, 150, 40);

		// 카테고리 삭제 버튼
		deleteCategoryButton = new JButton("카테고리 삭제");
		deleteCategoryButton.addActionListener(this);
		deleteCategoryButton.setBounds(510, 50, 150, 40);

		// 메뉴 추가 버튼
		addMenuButton = new JButton("메뉴추가");
		addMenuButton.addActionListener(this);
		addMenuButton.setBounds(650, 350, 150, 40);

		// backButton
		backButton = new JButton("back");
		backButton.addActionListener(this);
		backButton.setBounds(650, 400, 150, 40);

		this_panel.add(tp);
		this_panel.add(renameCategoryPanel);
		this_panel.add(addCategoryButton);
		this_panel.add(deleteCategoryButton);
		this_panel.add(addMenuButton);
		this_panel.add(backButton);
	}

	private void initTabbedPane() {
		info = new PCRoomInformation();
		tp = new JTabbedPane();
		tp.setBounds(0,0, 500, 400);
	}

	private void insertCategoryIntoTabbedPane() {
		// 카테고리 추가
		category_list = info.getCategoryList();
		for (int i = 0; i < category_list.size(); i++) {
			JSONObject category = (JSONObject) category_list.get(i);
			PCRoomCategory pcRoomCategory = new PCRoomCategory(category);
			JSONArray product_list = (JSONArray) category.get("product_list");

			// 카테고리 안에 메뉴 추가
			for (int j = 0; j < product_list.size(); j++) {
				JSONObject menu = (JSONObject) product_list.get(j);
				PCRoomMenu menuPanel = new PCRoomMenu(menu, j);
				menuPanel.addMouseListener(new MouseAdapter() { // 음식 클릭 시 삭제
					public void mouseClicked(MouseEvent e) {
						PCRoomMenu delete_target = (PCRoomMenu) e.getSource();
						int menuIndex = delete_target.getIndex();
						int categoryIndex = tp.getSelectedIndex();
						deleteMenu(categoryIndex, menuIndex);
					}
				});
				pcRoomCategory.add(menuPanel);
			}
			JScrollPane scrollPane = new JScrollPane(pcRoomCategory, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			tp.add(scrollPane, pcRoomCategory.getCategoryName());
		}
	}

	private void deleteMenu(int categoryIndex, int menuIndex) { // 메뉴 삭제
		try {
			JSONParser parser = new JSONParser();
			JSONObject whole = (JSONObject) parser.parse(new FileReader("information_pcroom.json"));
			JSONArray category_list2 = (JSONArray) ((JSONObject) whole.get("message")).get("category_list");
			JSONObject category = (JSONObject) category_list2.get(categoryIndex);
			JSONArray product_list = (JSONArray) category.get("product_list");

			// 삽입
			product_list.remove(menuIndex);
			FileWriter writer = new FileWriter("information_pcroom.json");
			writer.write(whole.toJSONString());
			writer.flush();

			updateCategory();
			writer.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void updateCategory() {
		tp.removeAll();

		info.update();
		// 카테고리 추가
		category_list = info.getCategoryList();
		for (int i = 0; i < category_list.size(); i++) {
			JSONObject category = (JSONObject) category_list.get(i);
			PCRoomCategory pcRoomCategory = new PCRoomCategory(category);
			JSONArray product_list = (JSONArray) category.get("product_list");

			// 카테고리 안에 메뉴 추가
			for (int j = 0; j < product_list.size(); j++) {
				JSONObject menu = (JSONObject) product_list.get(j);
				PCRoomMenu menuPanel = new PCRoomMenu(menu, j);
				menuPanel.addMouseListener(new MouseAdapter() { // 음식 클릭 시 삭제
					public void mouseClicked(MouseEvent e) {
						PCRoomMenu delete_target = (PCRoomMenu) e.getSource();
						int menuIndex = delete_target.getIndex();
						int categoryIndex = tp.getSelectedIndex();
						deleteMenu(categoryIndex, menuIndex);
					}
				});
				pcRoomCategory.add(menuPanel);
				
			}
			JScrollPane scrollPane = new JScrollPane(pcRoomCategory, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			tp.add(scrollPane, pcRoomCategory.getCategoryName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) { // Back 버튼 클릭
			this_panel.setVisible(false);
			main.getSelectOptions().getPanel().setVisible(true);
		} else if (e.getSource() == addMenuButton) { // 메뉴 추가 버튼 클릭
			addMenuDialog = new JDialog(main.getFrame(), "메뉴 추가", true);
			addMenuDialog.setSize(300, 500);
			addMenuDialog.setSize(300,610);
			addMenuDialog.setLayout(null);
			
			chooseFoodLabel = new JLabel();
			chooseFoodLabel.setBounds(0,0,300,200);
			imgChooseButton = new JButton("Open");
			imgChooseButton.setBounds(0,200,300,50);
			imgChooseButton.addActionListener(this);
			nameLabel = new JLabel("이름");
			nameLabel.setBounds(0,250,300,30);
			nameTF = new JTextField(10);
			nameTF.setBounds(0,280,300,60);
			priceLabel = new JLabel("가격");
			priceLabel.setBounds(0,340,300,30);
			priceTF = new JTextField(10);
			priceTF.setBounds(0,370,300,60);
			descLabel = new JLabel("설명");
			descLabel.setBounds(0,430,300,30);
			descriptionTF = new JTextField(10);
			descriptionTF.setBounds(0,460,300,60);
			menuSaveButton = new JButton("Save");
			menuSaveButton.setBounds(0,520,300,50);
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
		} else if (e.getSource() == imgChooseButton) { // 메뉴 추가 다이얼로그에서 사진 선택버튼
			fc = new JFileChooser();
			int i = fc.showOpenDialog(this_panel);
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				String filepath = f.getPath();
				ImageIcon icon = new ImageIcon(((new ImageIcon(filepath)).getImage()).getScaledInstance(300, 200, Image.SCALE_SMOOTH));
				chooseFoodLabel.setIcon(icon);
			}
		} else if (e.getSource() == menuSaveButton) { // 메뉴 추가 다이얼로그에서 저장버튼
			String name = nameTF.getText();
			String price = priceTF.getText();
			String description = descriptionTF.getText();
//			String[] arg = fc.getSelectedFile().toString().split("\\\\");
			String img_url = fc.getSelectedFile().toString();
			// json에 저장
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject) parser.parse(new FileReader("information_pcroom.json"));
				JSONArray category_list2 = (JSONArray) ((JSONObject) whole.get("message")).get("category_list");
				JSONObject category = (JSONObject) category_list2.get(tp.getSelectedIndex());
				JSONArray target_product_list = (JSONArray) category.get("product_list");

				// 넣을 데이터 구성
				JSONObject inputData = new JSONObject();
				inputData.put("name", name);
				inputData.put("price", Long.parseLong(price));
				inputData.put("desc", description);
				inputData.put("image", img_url);

				// 삽입
				target_product_list.add(inputData);
				FileWriter writer = new FileWriter("information_pcroom.json");
				writer.write(whole.toJSONString());
				writer.flush();
				writer.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			updateCategory();
			addMenuDialog.dispose();
		} else if (e.getSource() == addCategoryButton) { // 카테고리 + 버튼
			// json 파일에 빈 자리 생성
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject) parser.parse(new FileReader("information_pcroom.json"));
				JSONArray target_category_list2 = (JSONArray) ((JSONObject) whole.get("message")).get("category_list");

				// 넣을 데이터 구성
				JSONObject inputData = new JSONObject();
				JSONArray product_list = new JSONArray();
				inputData.put("category", "추가");
				inputData.put("product_list", product_list);

				// 삽입
				target_category_list2.add(inputData);
				FileWriter writer = new FileWriter("information_pcroom.json");
				writer.write(whole.toJSONString());
				writer.flush();
				writer.close();
				updateCategory();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == renameCategoryButton) { // 카테고리 이름 변경
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject) parser.parse(new FileReader("information_pcroom.json"));
				JSONArray category_list2 = (JSONArray) ((JSONObject) whole.get("message")).get("category_list");
				JSONObject target_category = (JSONObject) category_list2.get(tp.getSelectedIndex());

				// 넣을 데이터 구성
				String inputData = (String) renameCategoryTF.getText();

				// 삽입
				target_category.replace("category", inputData);
				FileWriter writer = new FileWriter("information_pcroom.json");
				writer.write(whole.toJSONString());
				writer.flush();

				updateCategory();
				writer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == deleteCategoryButton) {
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject) parser.parse(new FileReader("information_pcroom.json"));
				JSONArray category_list2 = (JSONArray) ((JSONObject) whole.get("message")).get("category_list");
				category_list2.remove(tp.getSelectedIndex());

				// 삽입
				FileWriter writer = new FileWriter("information_pcroom.json");
				writer.write(whole.toJSONString());
				writer.flush();

				updateCategory();
				writer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public JPanel getPanel() {
		return this_panel;
	}
	
}
