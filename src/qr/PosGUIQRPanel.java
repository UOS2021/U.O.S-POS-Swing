package qr;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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

import main.Main;

public class PosGUIQRPanel implements ActionListener{

	private CardLayout cards;
	private JPanel panel;
	private JLabel QRLabel;
	private JButton backButton;
	private JButton createButton;
	private JButton deleteButton;
	private QrCode QrCode;
	private ImageIcon QRImage;
	private Main main;
	public JPanel this_panel;
	
	public PosGUIQRPanel(Main main){
		this.main = main;
		panel = main.getPanel();
		this_panel = new JPanel();
		panel.add(this_panel,BorderLayout.CENTER);

		this_panel.setBackground(new Color(255, 200, 79));
		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);
		
		QrCode = new QrCode();
		
		if(!QrCode.isExist()) {
			QrCode.create();
		}
		
		QRImage = new ImageIcon("QRCODE.jpg");
		QRLabel = new JLabel(QRImage);
		QRLabel.setBounds(10, 10, 400, 400);
		
		backButton = new JButton("BACK");
		backButton.addActionListener(this);
		backButton.setBounds(500, 0, 200, 100);
		
		createButton = new JButton("CREATE");
		createButton.addActionListener(this);
		createButton.setBounds(500, 150, 200, 100);
		
		deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(this);
		deleteButton.setBounds(500, 300, 200, 100);
		
		this_panel.add(QRLabel);
		this_panel.add(backButton);
		this_panel.add(createButton);
		this_panel.add(deleteButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton) {
			this_panel.setVisible(false);
			main.getSelectOptions().getPanel().setVisible(true);
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
	
	public JPanel getPanel() {
		return this_panel;
	}
}
