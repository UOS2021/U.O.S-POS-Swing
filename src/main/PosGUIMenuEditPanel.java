package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PosGUIMenuEditPanel extends JPanel implements ActionListener, ChangeListener{
	
	private CardLayout cards;
	private JFrame frame;
	private JButton backButton;
	private JButton imgChooseButton;
	private JButton menuSaveButton;
	private List<JButton> addMenuButtonList;
	private int addMenuButtonIdx;
	
	private Menu menu;
	private JSONArray menuJson;
	private JTabbedPane tp;
	private List<JPanel> categoryPanel;
	private JDialog addMenuDialog;
	private JLabel chooseFoodLabel;
	
	private JTextArea nameTextArea;
	private JTextArea descriptionTextArea;
	private JTextArea priceTextArea;
	private JFileChooser fc;
	
	public PosGUIMenuEditPanel(CardLayout cards, JFrame frame) {
		this.setLayout(null);
		this.cards = cards;
		this.frame = frame;
		menu = new Menu();
		menuJson = menu.getMenuJSON();
		tp = new JTabbedPane();
		initializeCategoryPanel();
		
		tp.setBounds(50,50,400,400);
		insertCategoryPanelIntoTabbedPanel();
				
		backButton = new JButton("BACK");
		backButton.setBounds(200, 500, 100, 40);
		backButton.addActionListener(this);

		add(tp);
		add(backButton);
	}

	private void initializeCategoryPanel() {
		int categorySize = menu.getCategorySize();
		categoryPanel = new ArrayList<JPanel>();
		addMenuButtonList = new ArrayList<JButton>();
		
		for(int i=0;i<categorySize;i++) {
			JPanel menuPanel = new JPanel();
			JSONObject obj = (JSONObject) menuJson.get(i);
			JSONArray menuArr = (JSONArray)obj.get("menu");
			for(int j=0;j<menuArr.size();j++) {
				JPanel foodPanel = new JPanel();
				foodPanel.setLayout(new GridLayout(3,1));
				JSONObject food = (JSONObject)menuArr.get(j);
				
				ImageIcon foodIcon = new ImageIcon((String)food.get("img_url"));
				ImageIcon foodImg = new ImageIcon(
						(foodIcon.getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
						);
				JLabel foodLabel = new JLabel(foodImg);				
				JLabel nameLabel = new JLabel((String)food.get("name"));
				JLabel priceLabel = new JLabel((String)food.get("price"));
				foodPanel.add(foodLabel);
				foodPanel.add(nameLabel);
				foodPanel.add(priceLabel);				
											
				menuPanel.add(foodPanel);
			}
			JButton addMenuButton = new JButton("+");
			addMenuButton.addActionListener(this);
			addMenuButtonList.add(addMenuButton);
			menuPanel.add(addMenuButtonList.get(i));
			categoryPanel.add(menuPanel);
		}
	}

	public void insertCategoryPanelIntoTabbedPanel() {
		int categorySize = menu.getCategorySize();
		List<String> categoryList = menu.getCategories();
		for(int i=0;i<categorySize;i++) {
			tp.add(categoryList.get(i),categoryPanel.get(i));
		}
		JPanel plusPanel = new JPanel();
		tp.add("+",plusPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backButton) { // Back Button
			cards.show(frame.getContentPane(), "mainPanel");
		} else if(addMenuButtonList.indexOf(e.getSource())>=0) {
			addMenuButtonIdx = addMenuButtonList.indexOf(e.getSource());
			addMenuDialog = new JDialog(this.frame, "메뉴 추가", true);
			addMenuDialog.setSize(300,500);
			addMenuDialog.setLayout(new GridLayout(5,1));

			
			chooseFoodLabel = new JLabel();
			imgChooseButton = new JButton("Open");
			imgChooseButton.addActionListener(this);
			menuSaveButton = new JButton("Save");
			menuSaveButton.addActionListener(this);
			
			nameTextArea = new JTextArea(2, 10);
			descriptionTextArea = new JTextArea(2,10);
			priceTextArea = new JTextArea(2,10);
			
			JPanel foodPanel = new JPanel();
			foodPanel.setLayout(new BorderLayout());
			foodPanel.add(chooseFoodLabel);
			foodPanel.add(imgChooseButton,BorderLayout.SOUTH);
			
			JPanel namePanel = new JPanel();
			namePanel.setLayout(new FlowLayout());
			namePanel.add(new JLabel("이름"),FlowLayout.LEFT);
			namePanel.add(nameTextArea);
			
			JPanel pricePanel = new JPanel();
			pricePanel.add(new JLabel("가격"));
			pricePanel.add(priceTextArea);
			
			JPanel descriptionPanel = new JPanel();
			descriptionPanel.add(new JLabel("설명"),FlowLayout.LEFT);
			descriptionPanel.add(descriptionTextArea);
			
			addMenuDialog.add(foodPanel);
			addMenuDialog.add(namePanel);
			addMenuDialog.add(pricePanel);
			addMenuDialog.add(descriptionPanel);
			addMenuDialog.add(menuSaveButton);
			
			addMenuDialog.setVisible(true);
		}  else if(e.getSource()==imgChooseButton) { 
			fc = new JFileChooser();
			int i= fc.showOpenDialog(this);
			if(i==JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				String filepath = f.getPath();				
				ImageIcon icon = new ImageIcon(filepath);
				chooseFoodLabel.setIcon(icon);
			}
		} else if(e.getSource()==menuSaveButton) {
			String name = nameTextArea.getText();
			String price = priceTextArea.getText();
			String description = descriptionTextArea.getText();
			String[] arg = fc.getSelectedFile().toString().split("\\\\");
			String img_url = "images\\"+arg[arg.length-1];
			JSONObject addMenuJSON = menu.makeMenu(name, price, description, img_url);
			menu.addMenu(addMenuJSON, addMenuButtonIdx);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) { // + Tab Click
				
	}

}
