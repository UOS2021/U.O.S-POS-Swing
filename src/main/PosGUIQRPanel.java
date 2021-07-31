package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PosGUIQRPanel extends JPanel implements ActionListener{

	private CardLayout cards;
	private JFrame frame;
	private JButton backButton;
	
	PosGUIQRPanel(CardLayout cards, JFrame frame){
		this.cards = cards;
		this.frame = frame;
		this.setLayout(new BorderLayout());
		
		ImageIcon QRImage = new ImageIcon("QRCODE.jpg");
		JLabel QRLabel = new JLabel(QRImage);
		
		backButton = new JButton("BACK");
		backButton.addActionListener(this);
		
		add(QRLabel,BorderLayout.CENTER);
		add(backButton, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton) {
			cards.next(frame.getContentPane());
		}
	}
}
