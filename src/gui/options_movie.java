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
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.UIManager;

public class options_movie {

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					options_movie window = new options_movie();
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
	public options_movie() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 818, 532);
		frame.getContentPane().setBackground(new Color(255, 213, 79));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		btnNewButton.setBounds(12, 21, 65, 65);
		frame.getContentPane().add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(343, 85, 393, 57);
		frame.getContentPane().add(tabbedPane);
		
		JLabel lblNewLabel = new JLabel("\uC601\uD654");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblNewLabel.setBounds(99, 102, 177, 23);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField_1.setColumns(10);
		textField_1.setBounds(120, 147, 167, 37);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton_1_1_1 = new JButton("\uC601\uD654 \uCD94\uAC00");
		btnNewButton_1_1_1.setBackground(new Color(250, 250, 210));
		btnNewButton_1_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		btnNewButton_1_1_1.setBounds(26, 410, 261, 45);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(343, 226, 436, 257);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("\uC88C\uC11D\uD45C");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblNewLabel_1.setBounds(343, 189, 126, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC601\uD654\uBAA9\uB85D");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblNewLabel_2.setBounds(343, 33, 177, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC601\uD654 \uC704\uCE58");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(18, 155, 90, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField.setColumns(10);
		textField.setBounds(121, 217, 167, 37);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_3_1 = new JLabel("\uC601\uD654 \uC2DC\uAC04");
		lblNewLabel_3_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(17, 222, 87, 23);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("\uC601\uD654 \uC81C\uBAA9");
		lblNewLabel_3_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_3_1_1.setBounds(18, 290, 87, 23);
		frame.getContentPane().add(lblNewLabel_3_1_1);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField_2.setColumns(10);
		textField_2.setBounds(122, 285, 167, 37);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("\uAC00\uB85C \uBC0F \uC138\uB85C");
		lblNewLabel_3_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_3_1_1_1.setBounds(10, 345, 120, 23);
		frame.getContentPane().add(lblNewLabel_3_1_1_1);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField_3.setColumns(10);
		textField_3.setBounds(142, 340, 54, 37);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setToolTipText("\uCD94\uAC00\uD560 \uCE74\uD14C\uACE0\uB9AC \uC774\uB984");
		textField_4.setColumns(10);
		textField_4.setBounds(226, 341, 54, 37);
		frame.getContentPane().add(textField_4);
		
		JButton btnNewButton_1_1_1_1 = new JButton("\uC120\uD0DD\uD55C \uC601\uD654 \uC0AD\uC81C");
		btnNewButton_1_1_1_1.setBackground(new Color(255, 218, 185));
		btnNewButton_1_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton_1_1_1_1.setBounds(466, 30, 167, 37);
		frame.getContentPane().add(btnNewButton_1_1_1_1);
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new select_options();
                frame.setVisible(false);
            }
        });

        frame.setVisible(true);
	}
}
