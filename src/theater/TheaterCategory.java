package theater;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TheaterCategory extends JPanel implements ActionListener{
	
		private JSONObject movie;
		private int index;
	
		// boxPanel
		private JScrollPane scrollPane;
		private JPanel boxPanel;
		private JTextField timeTF;
		private JTextField movieNameTF;
		private JTextField widthTF;
		private JTextField heightTF;
		private JTextField theaterTF;
		private JLabel timeLabel;
		private JLabel movieNameLabel;
		private JLabel widthLabel;
		private JLabel heightLabel;
		private JLabel theaterLabel;
		private JLabel priceLabel;
		private JTextField priceTF;
		private JButton seatCreateButton;
		private JButton saveButton;
		
		// seatPanel
		private JPanel seatPanel;
		private TheaterSeat[][] theaterSeat;
		private int seatWidth=0;
		private int seatHeight=0;
		private final int BUTTONSIZE=20;

		public TheaterCategory(JSONObject movie2, int idx) {
			this.setLayout(null);
			movie = movie2;
			index = idx;
			String movieName = (String)movie.get("movie");
			String time = (String)movie.get("time");
			String theater = (String)movie.get("theater");
			seatWidth = ((Long)movie.get("width")).intValue();
			seatHeight = ((Long)movie.get("height")).intValue();
			
			
			// boxPanel Components
			boxPanel = new JPanel();
			boxPanel.setLayout(null);
			boxPanel.setBounds(0,0,500,50);

			theaterTF = new JTextField(5);
			theaterTF.setText(theater);
			theaterTF.setBounds(0,0,50,20);
			theaterLabel = new JLabel("위치");
			theaterLabel.setBounds(55,0,50,20);

			timeTF = new JTextField(6);
			timeTF.setText(time);
			timeTF.setBounds(110,0,50,20);
			timeLabel = new JLabel("시간");
			timeLabel.setBounds(165,0,50,20);
			
			movieNameLabel = new JLabel("영화 제목");
			movieNameLabel.setBounds(220,0,75,20);
			movieNameTF = new JTextField(10);
			movieNameTF.setText(movieName);
			movieNameTF.setBounds(300,0,300,20);
			
			widthLabel = new JLabel("가로");
			widthLabel.setBounds(0,25,30,20);
			widthTF = new JTextField(5);
			widthTF.setText(String.valueOf(seatWidth));
			widthTF.setBounds(35,25,50,20);

			heightLabel = new JLabel("세로");
			heightLabel.setBounds(90,25,30,20);
			heightTF = new JTextField(5);
			heightTF.setText(String.valueOf(seatHeight));
			heightTF.setBounds(125,25,50,20);
			
			priceLabel = new JLabel("가격");
			priceLabel.setBounds(180,25,30,20);
			priceTF = new JTextField(6);
			priceTF.setBounds(215,25,80,20);
			
			seatCreateButton = new JButton("Creat");
			seatCreateButton.setBounds(300,25,70,20);
			seatCreateButton.addActionListener(this);
			
			boxPanel.add(theaterTF);
			boxPanel.add(theaterLabel);
			boxPanel.add(timeTF);
			boxPanel.add(timeLabel);
			boxPanel.add(movieNameLabel);
			boxPanel.add(movieNameTF);
			boxPanel.add(widthLabel);
			boxPanel.add(widthTF);
			boxPanel.add(heightLabel);
			boxPanel.add(heightTF);
			boxPanel.add(seatCreateButton);
			boxPanel.add(priceLabel);
			boxPanel.add(priceTF);
			
			// seatPanel
			seatPanel = new JPanel();
			scrollPane = new JScrollPane(seatPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(0,50,320,320);
			if(seatWidth==0 || seatHeight==0){  // 빈 카테고리 추가
				this.add(boxPanel);
				this.add(scrollPane);
				return;
			}
			seatPanel.setLayout(new GridLayout(seatWidth, seatHeight));
			 
			// 좌석 배치
			theaterSeat = new TheaterSeat[seatHeight][seatWidth];
			JSONArray seatList = (JSONArray)movie.get("seat_list");
			for(int j=0; j<seatList.size(); j++) {
				JSONObject seat = (JSONObject)seatList.get(j);				
				theaterSeat[j/seatWidth][j%seatWidth] = new TheaterSeat(seat);
				theaterSeat[j/seatWidth][j%seatWidth].addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
						((TheaterSeat)e.getSource()).changeState();
					}
				});
				seatPanel.add(theaterSeat[j/seatWidth][j%seatWidth]);
			}
			
			this.add(boxPanel);
			this.add(scrollPane);		
		}

		
		
		public void createSeat(int width, int height, int price) throws FileNotFoundException {			
			seatPanel.removeAll();
			seatPanel.setLayout(new GridLayout(height, width));
			theaterSeat = new TheaterSeat[height][width];
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					theaterSeat[i][j] = new TheaterSeat(i,j,price);
					theaterSeat[i][j].addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent e) {
							((TheaterSeat)e.getSource()).changeState();
						}
					});
					seatPanel.add(theaterSeat[i][j]);
				}
			}
			seatPanel.revalidate();
			seatPanel.repaint();
		}
		
		public String getMovieName() {
			return movieNameTF.getText();
		}
		
		public JSONArray getSeatList() {
			JSONArray arr = new JSONArray();
			for(int i=0; i<Integer.parseInt(heightTF.getText()); i++) {
				for(int j=0; j<Integer.parseInt(widthTF.getText()); j++) {
					arr.add(theaterSeat[i][j].getSeatJSON());
				}
			}
			return arr;
		}

		private void categoryUpdate() {
			String movieName = movieNameTF.getText();
			String time = timeTF.getText();
			String theater = theaterTF.getText();
			String width = widthTF.getText();
			String height = heightTF.getText();
			String price = priceTF.getText();
			JSONArray seat_list = getSeatList();
							
			try {
				FileReader reader = new FileReader("information_theater.json");
				JSONParser parser = new JSONParser();
				JSONObject whole = (JSONObject)parser.parse(reader);
				
				FileWriter writer = new FileWriter("information_theater.json");
				movie.replace("movie", movieName);
				movie.replace("width", width);
				movie.replace("height", height);
				movie.replace("seat_list", seat_list);
				
				JSONObject obj1 = (JSONObject)whole.get("message");
				JSONArray arr = (JSONArray)obj1.get("movie_list");
				arr.set(index, movie);
				writer.write(whole.toJSONString());
				writer.flush();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
		private int getInt(String str) {
			if(!str.equals("")) {
				return Integer.parseInt(str);
			}
			else {
				return 0;
			}
		}
		
		public JSONObject getMovie() {
			JSONObject ret = new JSONObject();
			ret.put("movie", movieNameTF.getText());
			ret.put("time",timeTF.getText());
			ret.put("theater", theaterTF.getText());
			ret.put("width",Integer.parseInt(widthTF.getText()));
			ret.put("height",Integer.parseInt(heightTF.getText()));
			JSONArray addSeatList = new JSONArray();
			for(int i=0; i<Integer.parseInt(heightTF.getText()); i++) {
				for(int j=0; j<Integer.parseInt(widthTF.getText()); j++) {
					addSeatList.add(theaterSeat[i][j].getSeatJSON());
				}
			}
			ret.put("seat_list", addSeatList);
			return ret;	
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == seatCreateButton) { // Create Button
				try {
					int width = Integer.parseInt(widthTF.getText());
					int height= Integer.parseInt(heightTF.getText());	
					int price = Integer.parseInt(priceTF.getText());
					createSeat(width, height, price);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch(NumberFormatException e1) {
					e1.printStackTrace();
				}	
			}
		}
		
		
}
