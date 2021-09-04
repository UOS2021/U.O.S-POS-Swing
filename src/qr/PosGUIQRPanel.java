package qr;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.zxing.qrcode.encoder.QRCode;

public class PosGUIQRPanel extends JPanel implements ActionListener{

	private CardLayout cards;
	private JFrame frame;
	private JLabel QRLabel;
	private JButton backButton;
	private JButton createButton;
	private JButton deleteButton;
	private QrCode QrCode;
	private ImageIcon QRImage;
	
	public PosGUIQRPanel(CardLayout cards, JFrame frame){
		this.cards = cards;
		this.frame = frame;
		QrCode = new QrCode();
		
		if(!QrCode.isExist()) {
			QrCode.create();
		}
		
		QRImage = new ImageIcon("QRCODE.jpg");
		QRLabel = new JLabel(QRImage);
		
		backButton = new JButton("BACK");
		backButton.addActionListener(this);
		createButton = new JButton("CREATE");
		createButton.addActionListener(this);
		deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(this);
		
		add(QRLabel);
		add(backButton);
		add(createButton);
		add(deleteButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton) {
			cards.show(frame.getContentPane(), "mainPanel");
		} else if(e.getSource()==deleteButton) {
			QrCode.delete();
			QRLabel.setText("Deleted");
			QRLabel.setIcon(null);
		} else if(e.getSource()==createButton) {
			QrCode.create();
			QRLabel.setText("");
			QRLabel.setIcon(QRImage);
		}
	}
}
