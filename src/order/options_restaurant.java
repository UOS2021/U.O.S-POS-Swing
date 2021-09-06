package order;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gui.options_restaurant_addmenu;
import main.Main;

public class options_restaurant {
	
	public JPanel this_panel;

	private JFrame frame;
	private JPanel panel;
	private JTextField textField_1;
	private JTextField textField;
	
	private Main main;

	public options_restaurant(Main main) {
		this.main = main;
		initialize();
		
	}
	
	private void initialize() {
		
		
		//frame = main.getFrame();
		panel = main.getPanel();
		this_panel = new JPanel();
		//this_panel.setVisible(false);
		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);
		
		panel.add(this_panel);
		this_panel.setBackground(new Color(255, 213, 79));
		
		this_panel.setLayout(null);

		JButton btnNewButton = new JButton("<");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		btnNewButton.setBounds(12, 21, 65, 65);
		this_panel.add(btnNewButton);

		// 주문 목록

		

		
	
		

		JLabel lblNewLabel = new JLabel("\uC74C\uC2DD\uC810 \uCE74\uD14C\uACE0\uB9AC");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblNewLabel.setBounds(108, 21, 220, 23);
		this_panel.add(lblNewLabel);

		JButton btnNewButton_1_1 = new JButton("\uCD94\uAC00");
		btnNewButton_1_1.setBounds(287, 66, 111, 45);
		this_panel.add(btnNewButton_1_1);

		textField_1 = new JTextField();
		textField_1.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField_1.setColumns(10);
		textField_1.setBounds(108, 66, 167, 45);
		this_panel.add(textField_1);

		JButton btnNewButton_1_1_2 = new JButton("\uC774\uB984 \uBCC0\uACBD");
		btnNewButton_1_1_2.setBounds(287, 131, 111, 44);
		this_panel.add(btnNewButton_1_1_2);

		JButton btnNewButton_1_1_1 = new JButton("\uD574\uB2F9 \uCE74\uD14C\uACE0\uB9AC \uC0AD\uC81C");
		btnNewButton_1_1_1.setBounds(410, 66, 190, 109);
		btnNewButton_1_1_1.setBackground(new Color(255, 218, 185));
		this_panel.add(btnNewButton_1_1_1);

		JButton btnNewButton_1_1_2_1 = new JButton("\uBA54\uB274\uCD94\uAC00");
		btnNewButton_1_1_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		btnNewButton_1_1_2_1.setBounds(649, 37, 138, 138);
		btnNewButton_1_1_2_1.setBackground(new Color(250, 250, 210));
		this_panel.add(btnNewButton_1_1_2_1);

		textField = new JTextField();
		textField.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField.setColumns(10);
		textField.setBounds(108, 131, 167, 45);
		this_panel.add(textField);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new select_options();
				//frame.setVisible(false);
				this_panel.setVisible(false);
				main.getSelectOptions().getPanel().setVisible(true);
			}
		});

		btnNewButton_1_1_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new options_restaurant_addmenu();
				frame.setVisible(false);
			}
		});

		//this_panel.setVisible(false);
	}
	
	
	public JPanel getPanel() {
		return this_panel;
	}

}
