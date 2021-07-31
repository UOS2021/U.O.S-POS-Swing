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

public class PosGUIMain extends JFrame implements ActionListener{
	
	private CardLayout cards = new CardLayout();
	
	PosGUIMain(){
		setTitle("U.O.F POS");
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Card Panel
		Container pane = this.getContentPane();
		pane.setLayout(cards);
		
		JPanel mainPanel = new PosGUIMainPanel(cards, this);
		JPanel QRPanel = new PosGUIQRPanel(cards, this);
		pane.add(mainPanel,"main");
		pane.add(QRPanel,"QR");
		
		setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PosGUIMain();
	}

	

}
