package main;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PosGUI extends JFrame{
	
	private JButton QRButton = new JButton("QR");
	private JButton MenuEditButton = new JButton("皋春包府");
	private JButton OrderButton = new JButton("林巩包府");
	
	PosGUI(){
		setTitle("U.O.F POS");
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container pane = this.getContentPane();
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ImageIcon logoIcon = new ImageIcon("../../images/logo.png");
		JLabel logoLabel = new JLabel(logoIcon);
		northPanel.add(logoLabel);
		
		pane.add(northPanel);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PosGUI();
	}

}
