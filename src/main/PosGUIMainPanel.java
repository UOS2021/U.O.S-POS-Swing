package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PosGUIMainPanel extends JPanel implements ActionListener{
	
	private JButton cancelButton;
	private JButton QRButton;
	private JButton menuEditRestaurantButton;
	private JButton orderButton;
	private JButton menuEditTheaterButton;
	private JButton menuEditPCRoomButton;
	private CardLayout cards;
	private JFrame frame;
	
	PosGUIMainPanel(CardLayout cards, JFrame frame){
		this.cards = cards;
		this.frame = frame;
		// Top Panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
				
		ImageIcon logoIcon = new ImageIcon("images/logo.png");
		JLabel logoLabel = new JLabel(logoIcon);
		cancelButton = new JButton("X");
		northPanel.add(logoLabel,BorderLayout.WEST);
		northPanel.add(cancelButton,BorderLayout.EAST);
		add(northPanel);
				
		// Center Panel	
		JPanel centerPanel = new JPanel();
		QRButton = new JButton("QR");
		QRButton.addActionListener(this);
		
		menuEditRestaurantButton = new JButton("皋春包府(澜侥痢)");
		menuEditRestaurantButton.addActionListener(this);
		
		orderButton = new JButton("林巩包府");
		orderButton.addActionListener(this);
		
		menuEditTheaterButton = new JButton("皋春包府(康拳包)");
		menuEditTheaterButton.addActionListener(this);
		
		menuEditPCRoomButton = new JButton("皋春包府(PC规)");
		menuEditPCRoomButton.addActionListener(this);
		
		centerPanel.add(QRButton);
		centerPanel.add(menuEditRestaurantButton);
		centerPanel.add(orderButton);
		centerPanel.add(menuEditTheaterButton);
		centerPanel.add(menuEditPCRoomButton);
		add(centerPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==QRButton) {
			cards.show(frame.getContentPane(), "QRPanel");
		} else if(e.getSource()==menuEditRestaurantButton) {
			cards.show(frame.getContentPane(), "menuEditRestaurantPanel");
		} else if(e.getSource()==menuEditTheaterButton) {
			cards.show(frame.getContentPane(), "menuEditTheaterPanel");
		} else if(e.getSource() == menuEditPCRoomButton) {
			cards.show(frame.getContentPane(), "menuEditPCRoomPanel");
		}
	}
}
