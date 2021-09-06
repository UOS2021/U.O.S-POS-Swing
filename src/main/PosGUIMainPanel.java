package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PosGUIMainPanel implements ActionListener{
	
	private JButton cancelButton;
	private JButton QRButton;
	private JButton menuEditRestaurantButton;
	private JButton orderButton;
	private JButton menuEditTheaterButton;
	private JButton menuEditPCRoomButton;
	private JPanel panel;
	private Main main;
	
	private JPanel this_panel;
	
	public PosGUIMainPanel(Main main){
		this.main = main;
		panel = this.main.getPanel();
		this_panel = new JPanel();
		this_panel.setBackground(new Color(255, 200, 79));
		
		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);
		
		panel.add(this_panel);

		
				
		ImageIcon logoIcon = new ImageIcon("images/logo.png");
		JLabel logoLabel = new JLabel(logoIcon);
		logoLabel.setBounds(0, 0, 50, 50);
		cancelButton = new JButton("X");
		cancelButton.setBounds(100, 0, 50, 50);
		this_panel.add(logoLabel);
		this_panel.add(cancelButton);
				
		// Center Panel	
		QRButton = new JButton("QR");
		QRButton.addActionListener(this);
		QRButton.setBounds(600, 0, 100, 100);
		
		menuEditRestaurantButton = new JButton("메뉴관리(음식점)");
		menuEditRestaurantButton.addActionListener(this);
		menuEditRestaurantButton.setBounds(100, 150, 200, 100);
		
		orderButton = new JButton("주문관리");
		orderButton.addActionListener(this);
		orderButton.setBounds(400, 150, 200, 100);
		
		menuEditTheaterButton = new JButton("메뉴관리(영화관)");
		menuEditTheaterButton.addActionListener(this);
		menuEditTheaterButton.setBounds(100, 300, 200, 100);
		
		menuEditPCRoomButton = new JButton("메뉴관리(PC방)");
		menuEditPCRoomButton.addActionListener(this);
		menuEditPCRoomButton.setBounds(400, 300, 200, 100);
		
		this_panel.add(QRButton);
		this_panel.add(menuEditRestaurantButton);
		this_panel.add(orderButton);
		this_panel.add(menuEditTheaterButton);
		this_panel.add(menuEditPCRoomButton);
		
		this_panel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==QRButton) {
			this_panel.setVisible(false);
			main.getQRPanel().getPanel().setVisible(true);
		} else if(e.getSource()==menuEditRestaurantButton) {
			this_panel.setVisible(false);
			main.getMenuEditRestaurantPanel().getPanel().setVisible(true);
		} else if(e.getSource()==menuEditTheaterButton) {
			this_panel.setVisible(false);
			main.getMenuEditTheater().getPanel().setVisible(true);
		} else if(e.getSource() == menuEditPCRoomButton) {
			this_panel.setVisible(false);
			main.getMenuEditPCRoomPanel().getPanel().setVisible(true);
		}
	}
	
	public JPanel getPanel() {
		return this_panel;
	}
}
