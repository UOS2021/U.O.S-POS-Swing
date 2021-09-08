package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import order.options_restaurant;

import java.awt.SystemColor;

public class options_restaurant_addmenu {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					options_restaurant_addmenu window = new options_restaurant_addmenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public options_restaurant_addmenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 213, 79));
		frame.setBounds(100, 100, 818, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnNewButton.setBounds(12, 11, 54, 54);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\uBA54\uB274 \uCD94\uAC00");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		lblNewLabel.setBounds(339, 12, 118, 80);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("\uC5F4\uAE30");
		btnNewButton_1.setBounds(132, 112, 65, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("\uBA54\uB274\uC774\uBBF8\uC9C0");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(20, 114, 103, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(31, 144, 388, 340);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uBA54\uB274\uC774\uB984 : ");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(431, 163, 103, 23);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(555, 159, 174, 40);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uBA54\uB274\uAC00\uACA9 : ");
		lblNewLabel_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(433, 237, 103, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(555, 233, 174, 40);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\uBA54\uB274\uC124\uBA85 : ");
		lblNewLabel_1_1_2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(434, 298, 103, 23);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(555, 290, 174, 71);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton_2 = new JButton("\uCD94\uAC00\uD558\uAE30");
		btnNewButton_2.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		btnNewButton_2.setBounds(462, 380, 276, 56);
		btnNewButton_2.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC6D0");
		lblNewLabel_3.setBounds(733, 245, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new options_restaurant();
                frame.setVisible(false);
            }
        });
		
		btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new options_restaurant();
                frame.setVisible(false);
            }
        });
		

        frame.setVisible(true);
	}
}
