package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pcroom.PosGUIMenuEditPCRoomPanel;
import qr.PosGUIQRPanel;
import restaurant.PosGUIMenuEditRestaurantPanel;
import theater.PosGUIMenuEditTheaterPanel;

public class PosGUIMain extends JFrame implements ActionListener{
	
	private CardLayout cards = new CardLayout();
	private POSServer server;
	
	PosGUIMain(POSServer serv){
		setTitle("U.O.F POS");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.server=serv;
		server.start();
		
		// Card Panel
		Container pane = this.getContentPane();
		pane.setLayout(cards);
		
		JPanel loginPanel = new PosGUILogin(cards, this);
		JPanel mainPanel = new PosGUIMainPanel(cards, this);
		JPanel QRPanel = new PosGUIQRPanel(cards, this);
		JPanel menuEditRestaurantPanel = new PosGUIMenuEditRestaurantPanel(cards, this);
		JPanel menuEditTheaterPanel = new PosGUIMenuEditTheaterPanel(cards, this);
		JPanel menuEditPCRoomPanel = new PosGUIMenuEditPCRoomPanel(cards, this);
		pane.add(loginPanel, "loginPanel");
		pane.add(mainPanel,"mainPanel");
		pane.add(QRPanel,"QRPanel");
		pane.add(menuEditRestaurantPanel, "menuEditRestaurantPanel");
		pane.add(menuEditTheaterPanel, "menuEditTheaterPanel");
		pane.add(menuEditPCRoomPanel, "menuEditPCRoomPanel");
		
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
