package pcroom;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONObject;

public class PCRoomMenu extends JPanel {
	private JSONObject menu;
	private int index;
	
	private JLabel productImgLabel;
	private JLabel productNameLabel;
	private JLabel productPriceLabel;
	private JLabel productDescLabel;
	
	public PCRoomMenu(JSONObject menu, int index) {
		this.setLayout(new GridLayout(4,1));
		this.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(Color.BLACK,2)), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		this.menu = menu;
		this.index = index;
		
		productImgLabel = new JLabel(new ImageIcon(
				((new ImageIcon((String)menu.get("image"))).getImage()).getScaledInstance(50, 50, Image.SCALE_SMOOTH)
				));
		productNameLabel = new JLabel((String)menu.get("name"));
		productPriceLabel = new JLabel(String.valueOf((long)menu.get("price")));
		productDescLabel = new JLabel((String)menu.get("desc"));
		
		productImgLabel.setHorizontalAlignment(JLabel.CENTER);
		productNameLabel.setHorizontalAlignment(JLabel.CENTER);
		productPriceLabel.setHorizontalAlignment(JLabel.CENTER);
		productDescLabel.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(productImgLabel);
		this.add(productNameLabel);
		this.add(productPriceLabel);
		this.add(productDescLabel);
	}

	public int getIndex() {
		return this.index;
	}
	
}
