package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PosGUILogin extends JPanel implements ActionListener{
	private CardLayout cards;
	private JFrame frame;
	
	private JLabel IDLabel;
	private JLabel PWLabel;
	private JTextField IDTF;
	private JTextField PWTF;
	private JButton loginButton;
	
	public PosGUILogin(CardLayout cards, JFrame frame) { // public PosGUILogin(CardLayout cards, JFrame frame) {
		this.cards = cards;
		this.frame = frame;
		this.setLayout(new FlowLayout());
		
		IDLabel = new JLabel("ID");
		PWLabel = new JLabel("PW");
		
		IDTF = new JTextField(10);
		PWTF = new JTextField(10);
		
		loginButton = new JButton("LOGIN");
		loginButton.addActionListener(this);
		
		this.add(IDLabel);
		this.add(IDTF);
		this.add(PWLabel);
		this.add(PWTF);
		this.add(loginButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			// 로그인 버튼 클릭
			cards.show(frame.getContentPane(), "mainPanel");
		}
	}
	
}
