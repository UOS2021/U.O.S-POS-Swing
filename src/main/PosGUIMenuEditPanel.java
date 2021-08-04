package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PosGUIMenuEditPanel extends JPanel implements ActionListener, ChangeListener{
	
	private CardLayout cards;
	private JFrame frame;
	private JButton backButton;
	private JButton addMenuButton;
	private Menu menu;
	private JSONArray menuJson;
	private JTabbedPane tp;
	private List<JPanel> categoryPanel;
	
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
		backButton.addActionListener(this);
		
		add(tp,BorderLayout.SOUTH);
		add(backButton,BorderLayout.NORTH);
	}

	private void initializeCategoryPanel() {
		int categorySize = menu.getCategorySize();
		categoryPanel = new ArrayList<JPanel>();
		
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
			addMenuButton = new JButton("+");
			menuPanel.add(addMenuButton);
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
		} else if(e.getSource()==addMenuButton) { // Menu Add Button
			
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) { // + Tab Click
				
	}

}
