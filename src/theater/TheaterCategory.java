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
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TheaterCategory extends JPanel implements ActionListener{
	
		private JSONObject movie;
		private int index;
	
		// boxPanel
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
		private JButton seatCreateButton;
		private JButton saveButton;
		
		// seatPanel
		private JPanel seatPanel;
		private TheaterSeat[][] theaterSeat;
		private int seatWidth=0;
		private int seatHeight=0;
		
		public TheaterCategory() {			
			// boxPanel Components
			boxPanel = new JPanel();
			boxPanel.setLayout(new FlowLayout());
			
			movieNameLabel = new JLabel("영화제목");
			movieNameTF = new JTextField(20);
			
			widthLabel = new JLabel("가로");
			widthTF = new JTextField(5);
			widthTF.setText(String.valueOf(seatWidth));

			heightLabel = new JLabel("세로");
			heightTF = new JTextField(5);
			heightTF.setText(String.valueOf(seatHeight));
			
			timeTF = new JTextField(6);
			timeLabel = new JLabel("시간");
			
			theaterTF = new JTextField(5);
			theaterLabel = new JLabel("관");
			
			seatCreateButton = new JButton("Creat");
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
			
			// seatPanel
			seatPanel = new JPanel();
			
			this.add(boxPanel, BorderLayout.NORTH);
			this.add(seatPanel, BorderLayout.CENTER);		
		}

		public TheaterCategory(JSONObject movie2, int idx) {
			movie = movie2;
			index = idx;
			String movieName = (String)movie.get("movie");
			String time = (String)movie.get("time");
			String theater = (String)movie.get("theater");
			seatWidth = getInt((String)movie.get("width"));
			seatHeight = getInt((String)movie.get("height"));
			
			
			// boxPanel Components
			boxPanel = new JPanel();
			boxPanel.setLayout(new FlowLayout());
			
			movieNameLabel = new JLabel("영화 제목");
			movieNameTF = new JTextField(20);
			movieNameTF.setText(movieName);
			
			widthLabel = new JLabel("가로");
			widthTF = new JTextField(5);
			widthTF.setText(String.valueOf(seatWidth));

			heightLabel = new JLabel("세로");
			heightTF = new JTextField(5);
			heightTF.setText(String.valueOf(seatHeight));
			
			timeTF = new JTextField(6);
			timeTF.setText(time);
			timeLabel = new JLabel("시간");
			
			theaterTF = new JTextField(5);
			theaterTF.setText(theater);
			theaterLabel = new JLabel("위치");
			
			seatCreateButton = new JButton("Creat");
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
			
			// seatPanel
			seatPanel = new JPanel();
			if(seatWidth==0 || seatHeight==0){
				this.add(boxPanel, BorderLayout.NORTH);
				this.add(seatPanel, BorderLayout.CENTER);
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
			
			this.add(boxPanel, BorderLayout.NORTH);
			this.add(seatPanel, BorderLayout.CENTER);		
		}

		
		
		public void createSeat(int width, int height) throws FileNotFoundException {			
			seatPanel.removeAll();
			seatPanel.setLayout(new GridLayout(height, width));
			theaterSeat = new TheaterSeat[height][width];
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					theaterSeat[i][j] = new TheaterSeat(i,j);
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
			ret.put("width",widthTF.getText());
			ret.put("height",heightTF.getText());
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
					createSeat(width, height);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch(NumberFormatException e1) {
					e1.printStackTrace();
				}	
			}
		}
		
		
}
