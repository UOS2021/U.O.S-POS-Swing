package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PosGUIMenuEditTheaterPanel extends JPanel implements ActionListener{

	private CardLayout cards;
	private JFrame frame;
	private JTabbedPane tp;
	private File seatFile;
	private JButton backButton;
	private TheaterInformation info;
	
	// boxPanel
	private JPanel boxPanel;
	private JTextField widthTF;
	private JTextField heightTF;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JButton seatCreateButton;
	
	// seatPanel
	private JPanel seatPanel;
	private TheaterSeat[][] theaterSeat;
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	public PosGUIMenuEditTheaterPanel(CardLayout cards, JFrame frame) {
		this.cards = cards;
		this.frame = frame;		
		this.setLayout(new BorderLayout());

		initTabbedPane();
		
		// backButton
		backButton = new JButton("back");
		backButton.addActionListener(this);
		
		this.add(tp);
		this.add(backButton, BorderLayout.SOUTH);
	}

	private void initTabbedPane() {
		tp = new JTabbedPane();
		info = new TheaterInformation();
		
		// 영화 추가
		JPanel addPanel = new JPanel();
	    addPanel.setOpaque (false);
		JButton addTab = new JButton("+");
		addTab.setOpaque (false); //
	    addTab.setBorder (null);
	    addTab.setContentAreaFilled (false);
	    addTab.setFocusPainted (false);
		addPanel.add(addTab);
		addTab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tp.addTab("추가", new TheaterCategory());
			}
		});
		addTab.setFocusable (false);
		tp.add(addTab, "+");
		
		JSONArray movieList =  (JSONArray)info.getMovieList();
		for(int i=0; i<movieList.size(); i++) {
			JSONObject movie = (JSONObject)movieList.get(i);
			TheaterCategory theaterCategory = new TheaterCategory(movie, i);	
			tp.add(theaterCategory, theaterCategory.getMovieName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==backButton) {
				cards.show(frame.getContentPane(),"mainPanel");
		}
	}	
}
