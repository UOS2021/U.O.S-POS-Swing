package theater;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.Main;

public class PosGUIMenuEditTheaterPanel implements ActionListener{

	private Main main;
//	private JFrame frame;
	private JPanel panel;
	private JPanel this_panel;
	private JTabbedPane tp;
	private File file;
	private JButton backButton;
	private TheaterInformation info;
	

	// 카테고리 메뉴 영역
	private JButton addCategoryButton;
	private JButton deleteCategoryButton;
	private JButton saveCategoryButton;
	
	// 카테고리 영역
	private ArrayList<TheaterCategory> theaterCategory;
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	public PosGUIMenuEditTheaterPanel(Main main) {
		this.main = main;
//		this.frame = main.getFrame();
		panel = main.getPanel();
		this.file = new File("information_theater.json");
		this_panel = new JPanel();
		panel.add(this_panel);
		this_panel.setBackground(new Color(255, 200, 79));
		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);
		
		initTabbedPane();
	}

	private void initTabbedPane() {
		info = new TheaterInformation();
		theaterCategory = new ArrayList<TheaterCategory>();
		

		tp = new JTabbedPane();
		tp.setBounds(0,0, 500, 400);
		
		
		// 카테고리 추가 버튼
		addCategoryButton = new JButton("카테고리 추가");
		addCategoryButton.addActionListener(this);
		addCategoryButton.setBounds(510, 0, 150, 40);
		
		// 카테고리 저장 버튼
		saveCategoryButton = new JButton("카테고리 저장");
		saveCategoryButton.addActionListener(this);
		saveCategoryButton.setBounds(510, 50, 150, 40);
		
		// 카테고리 삭제 버튼
		deleteCategoryButton = new JButton("카테고리 삭제");
		deleteCategoryButton.addActionListener(this);
		deleteCategoryButton.setBounds(510, 100, 150, 40);
				
		// backButton
		backButton = new JButton("back");
		backButton.addActionListener(this);
		backButton.setBounds(500, 400, 150, 40);
		
		this_panel.add(tp);
		this_panel.add(addCategoryButton);
		this_panel.add(saveCategoryButton);
		this_panel.add(deleteCategoryButton);
		this_panel.add(backButton);
		
		// 영화 데이터
		JSONArray movieList =  (JSONArray)info.getMovieList();
		for(int i=0; i<movieList.size(); i++) {
			JSONObject movie = (JSONObject)movieList.get(i);
			theaterCategory.add(new TheaterCategory(movie, i));	
			tp.add(theaterCategory.get(i), theaterCategory.get(i).getMovieName());
		}
		
		
	}
	
	private void updateCategory() {
		tp.removeAll();
		info.update();
		theaterCategory.clear();
		
		// 영화 추가
		JSONArray movieList =  (JSONArray)info.getMovieList();
		for(int i=0; i<movieList.size(); i++) {
			JSONObject movie = (JSONObject)movieList.get(i);
			theaterCategory.add(new TheaterCategory(movie, i));	
			tp.add(theaterCategory.get(i), theaterCategory.get(i).getMovieName());
		}
		
		// 세트 카테고리 추가
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==backButton) {
			this_panel.setVisible(false);
			main.getSelectOptions().getPanel().setVisible(true);
		} else if(e.getSource() == addCategoryButton) {
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(new FileReader(file));
				JSONArray target_category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("movie_list");
				
				// 넣을 데이터 구성
				JSONObject inputData = new JSONObject();
				JSONArray seat_list = new JSONArray();
				
				inputData.put("movie", "");
				inputData.put("time", "");
				inputData.put("theater", "");
				inputData.put("width", 0);
				inputData.put("height", 0);
				inputData.put("seat_list", seat_list);
				// 카테고리리스트, 세트리스트 추가해야됨.				
				
				// 삽입
				target_category_list2.add(inputData);
				FileWriter writer = new FileWriter(file);
				writer.write(whole.toJSONString());
				writer.flush();
				writer.close();
				updateCategory();
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		} else if(e.getSource() == deleteCategoryButton) {
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(new FileReader(file));
				JSONArray category_list2 = (JSONArray)((JSONObject)whole.get("message")).get("movie_list");
				category_list2.remove(tp.getSelectedIndex());
				
				// 삽입
				FileWriter writer = new FileWriter(file);
				writer.write(whole.toJSONString());
				writer.flush();
				writer.close();
				updateCategory();
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		} else if(e.getSource() == saveCategoryButton) {
			try {
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(new FileReader(file));
				
				JSONObject message = (JSONObject)whole.get("message");
				JSONArray movie_list = (JSONArray)message.get("movie_list");

				movie_list.set(tp.getSelectedIndex(),theaterCategory.get(tp.getSelectedIndex()).getMovie());				

				FileWriter writer = new FileWriter(file);
				writer.write(whole.toJSONString());
				writer.flush();
				updateCategory();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
	
	public JPanel getPanel() {
		return this_panel;
	}
}
