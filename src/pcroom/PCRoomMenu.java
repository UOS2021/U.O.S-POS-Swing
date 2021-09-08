package pcroom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONObject;

public class PCRoomMenu extends JPanel {
	private JSONObject menu;
	private int index;
	
	private JLabel productImgLabel;
	private JTextArea productNameLabel;
	private JLabel productPriceLabel;
	private JTextArea productDescLabel;
	
	public PCRoomMenu(JSONObject menu, int index) {
		this.setPreferredSize(new Dimension(100,345));
		this.setLayout(null);
		this.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(Color.BLACK,2)), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		this.menu = menu;
		this.index = index;
		
		productImgLabel = new JLabel(new ImageIcon(
				((new ImageIcon((String)menu.get("image"))).getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
				));
		productImgLabel.setBounds(3,5,94,50);
		productNameLabel = new JTextArea((String)menu.get("name"),5,2);
		productNameLabel.setDisabledTextColor(Color.black);
		productNameLabel.setLineWrap(true);
		productNameLabel.disable();
		productNameLabel.setBounds(3,55,94,40);
		
		
		productPriceLabel = new JLabel(new DecimalFormat("###,###").format((long)menu.get("price"))+"원");
		productPriceLabel.setBounds(3,95,94,20);
		
		productDescLabel  = new JTextArea((String)menu.get("desc"),5,50);
		productDescLabel.setLineWrap(true);
		productDescLabel.setDisabledTextColor(Color.black);
		productDescLabel.disable();
		productDescLabel.setBounds(3,115,94,225);
		
		this.add(productImgLabel);
		this.add(productNameLabel);
		this.add(productPriceLabel);
		this.add(productDescLabel);
	}
	
	public int getIndex() {
		return this.index;
	}
	
}
