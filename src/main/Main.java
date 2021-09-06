package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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


import order.login;
import order.options_restaurant;
import order.select_options;

import java.awt.Toolkit;

import pcroom.PosGUIMenuEditPCRoomPanel;
import qr.PosGUIQRPanel;
import restaurant.PosGUIMenuEditRestaurantPanel;
import theater.PosGUIMenuEditTheaterPanel;

public class Main{
	
	
	private JFrame frame;
	private JPanel panel;
	
	PosGUIMainPanel mainPanel;
	PosGUIQRPanel QRPanel;
	PosGUIMenuEditRestaurantPanel menuEditRestaurantPanel;
	PosGUIMenuEditTheaterPanel menuEditTheaterPanel;
	PosGUIMenuEditPCRoomPanel menuEditPCRoomPanel;
	private login login;
	private options_restaurant options_restaurant;
	private select_options select_options;
	private String company_type;
	private String company_name;
	
	public static void main(String[] args) {
		new Main();	
	}
	
	Main(){
		this.frame = new JFrame();
		this.frame.setTitle("U.O.F POS");
		
		
		this.frame.setBackground(Color.WHITE);
//		this.frame.setBounds(100, 100, 818, 532);
		this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.frame.setUndecorated(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel = new JPanel();
		this.panel.setBackground(new Color(255, 213, 79));
		this.panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.panel.setBounds(0, 0,(int)screenSize.getWidth(),(int)screenSize.getHeight() );
		
		this.frame.add(this.panel);
	
		
		
		this.login = new login(this);
		this.options_restaurant = new options_restaurant(this);
		this.select_options = new select_options(this);
		
		
		
		//mainPanel = new PosGUIMainPanel(this);
		
		QRPanel = new PosGUIQRPanel(this);
		menuEditRestaurantPanel = new PosGUIMenuEditRestaurantPanel(this);
		menuEditTheaterPanel = new PosGUIMenuEditTheaterPanel(this);
		menuEditPCRoomPanel = new PosGUIMenuEditPCRoomPanel(this);
		
		
		/* 순서 */
		
		login.getPanel().setVisible(true);
		options_restaurant.getPanel().setVisible(false);
		select_options.getPanel().setVisible(false);
		
		
		//mainPanel.getPanel().setVisible(true);
		
		QRPanel.getPanel().setVisible(false);
		menuEditRestaurantPanel.getPanel().setVisible(false);
		menuEditTheaterPanel.getPanel().setVisible(false);
		menuEditPCRoomPanel.getPanel().setVisible(false);
		
		this.frame.setVisible(true);
		this.panel.setVisible(true);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	public JFrame getFrame() {
		return this.frame;
	}
	
	public PosGUIMainPanel getMainPanel() {
		return this.mainPanel;
	}
	public PosGUIQRPanel getQRPanel() {
		return this.QRPanel;
	}
	public PosGUIMenuEditRestaurantPanel getMenuEditRestaurantPanel() {
		return this.menuEditRestaurantPanel;
	}
	
	public PosGUIMenuEditTheaterPanel getMenuEditTheater() {
		return this.menuEditTheaterPanel;
	}
	public PosGUIMenuEditPCRoomPanel getMenuEditPCRoomPanel() {
		return this.menuEditPCRoomPanel;
	}
	
	public login getLogin() {
		return this.login;
	}
	
	public options_restaurant getOptionsRestaurant() {
		return this.options_restaurant;
	}
	
	public select_options getSelectOptions() {
		return this.select_options;
	}
	
	
	public String getCompanyType() {
		return this.company_type;
	}
	
	public String getCompanyName() {
		return this.company_name;
	}
	
	public void setCompanyType(String company_type) {
		this.company_type = company_type;
	}
	
	public void setCompanyName(String company_name) {
		this.company_name = company_name;
	}
	

}
