package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PosGUIMenuEditPanel extends JPanel implements ActionListener{
	
	private CardLayout cards;
	private JFrame frame;
	private JButton backButton;
	private Menu menu;
	private JSONArray menuJson;
	private JTabbedPane tp;
	private JPanel[] categoryPanel;
	
	public PosGUIMenuEditPanel(CardLayout cards, JFrame frame) {
		this.setLayout(null);
		this.cards = cards;
		this.frame = frame;
		menu = new Menu();
		menuJson = menu.getMenuJSON();
		tp = new JTabbedPane();
		initializeCategoryPanel();
		
		tp.setBounds(50,50,400,400);
		insertCategoryPanelIntoTabbedyPanel();
				
		backButton = new JButton("BACK");
		backButton.addActionListener(this);
		
		add(tp,BorderLayout.SOUTH);
		add(backButton,BorderLayout.NORTH);
	}

	private void initializeCategoryPanel() {
		int categorySize = menu.getCategorySize();
		categoryPanel = new JPanel[categorySize];
		
		for(int i=0;i<categorySize;i++) {
			categoryPanel[i] = new JPanel();
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
											
				categoryPanel[i].add(foodPanel);
			}
		}
	}

	public void insertCategoryPanelIntoTabbedyPanel() {
		int categorySize = menu.getCategorySize();
		List<String> categoryList = menu.getCategories();
		for(int i=0;i<categorySize;i++) {
			tp.add(categoryList.get(i),categoryPanel[i]);
		}
		JPanel plusPanel = new JPanel();
		tp.add("+",plusPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton) {
			cards.show(frame.getContentPane(), "mainPanel");
		}
	}

}
