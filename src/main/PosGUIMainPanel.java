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
	private JButton menuEditButton;
	private JButton orderButton;
	private JButton test;
	private CardLayout cards;
	private JFrame frame;
	
	PosGUIMainPanel(CardLayout cards, JFrame frame){
		this.cards = cards;
		this.frame = frame;
		this.setLayout(null);
		// Top Panel
		JPanel northPanel = new JPanel();
		northPanel.setBounds(0,0,800,20);
		northPanel.setLayout(new BorderLayout());
				
		ImageIcon logoIcon = new ImageIcon("images/logo.png");
		JLabel logoLabel = new JLabel(logoIcon);
		cancelButton = new JButton("X");
		northPanel.add(logoLabel,BorderLayout.WEST);
		northPanel.add(cancelButton,BorderLayout.EAST);
		add(northPanel);
				
		// Center Panel	
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBounds(50,70,700,400);
		int x=0,y=0,width=100,height=100,hgap=10,vgap=10;
		QRButton = new JButton("QR");
		QRButton.addActionListener(this);
		QRButton.setBounds(x,y,width,height);
		
		x+=width+hgap;
		menuEditButton = new JButton("皋春包府");
		menuEditButton.setBounds(x, y, width, height);
		menuEditButton.addActionListener(this);
		
		x+=width+hgap;
		orderButton = new JButton("林巩包府");
		orderButton.setBounds(x, y, width, height);
		orderButton.addActionListener(this);
		
		x=0;
		y+=height+vgap;
		test = new JButton("TEST");
		test.setBounds(x,y,width,height);
		test.addActionListener(this);
		
		centerPanel.add(QRButton);
		centerPanel.add(menuEditButton);
		centerPanel.add(orderButton);
		centerPanel.add(test);
		add(centerPanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==QRButton) {
			cards.next(frame.getContentPane());		
		}
	}
}
