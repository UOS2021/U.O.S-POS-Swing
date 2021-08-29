package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PosGUIMenuEditPCRoomPanel extends JPanel implements ActionListener{
	private CardLayout cards;
	private JFrame frame;
	private JButton backButton;
	
	public PosGUIMenuEditPCRoomPanel(CardLayout cards, JFrame frame) {
		this.cards = cards;
		this.frame = frame;		
		this.setLayout(new BorderLayout());
		
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		this.add(backButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == backButton) {
			cards.show(frame.getContentPane(),"mainPanel");
		}
	}
}
