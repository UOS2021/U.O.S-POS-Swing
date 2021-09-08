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

public class options_pcroom {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					options_pcroom window = new options_pcroom();
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
	public options_pcroom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 818, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(255, 213, 79));
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		btnNewButton.setBounds(12, 21, 65, 65);
		frame.getContentPane().add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(28, 196, 762, 287);
		frame.getContentPane().add(tabbedPane);
		
		JLabel lblNewLabel = new JLabel("PC\uBC29 \uCE74\uD14C\uACE0\uB9AC");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblNewLabel.setBounds(108, 21, 177, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1_1 = new JButton("\uCD94\uAC00");
		btnNewButton_1_1.setBounds(287, 66, 111, 45);
		frame.getContentPane().add(btnNewButton_1_1);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField_1.setColumns(10);
		textField_1.setBounds(108, 66, 167, 45);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton_1_1_2 = new JButton("\uC774\uB984 \uBCC0\uACBD");
		btnNewButton_1_1_2.setBounds(287, 131, 111, 44);
		frame.getContentPane().add(btnNewButton_1_1_2);
		
		JButton btnNewButton_1_1_1 = new JButton("\uD574\uB2F9 \uCE74\uD14C\uACE0\uB9AC \uC0AD\uC81C");
		btnNewButton_1_1_1.setBounds(410, 66, 190, 109);
		btnNewButton_1_1_1.setBackground(new Color(255, 218, 185));
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_2_1 = new JButton("\uBA54\uB274\uCD94\uAC00");
		btnNewButton_1_1_2_1.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		btnNewButton_1_1_2_1.setBounds(649, 37, 138, 138);
		btnNewButton_1_1_2_1.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(btnNewButton_1_1_2_1);
		
		textField = new JTextField();
		textField.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField.setColumns(10);
		textField.setBounds(108, 131, 167, 45);
		frame.getContentPane().add(textField);
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new select_options();
                frame.setVisible(false);
            }
        });
		
		btnNewButton_1_1_2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new options_pcroom_addmenu();
                frame.setVisible(false);
            }
        });

        frame.setVisible(true);
	}
}
