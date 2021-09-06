package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class options_qrcode {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					options_qrcode window = new options_qrcode();
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
	public options_qrcode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 213, 79));
		frame.setBounds(100, 100, 818, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		btnNewButton.setBounds(12, 21, 65, 65);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("QR\uCF54\uB4DC \uC0DD\uC131\uD558\uAE30");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(565, 190, 205, 54);
		btnNewButton_1.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("QR\uCF54\uB4DC \uC0AD\uC81C\uD558\uAE30");
		btnNewButton_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(567, 285, 205, 54);
		btnNewButton_1_1.setBackground(new Color(255, 218, 185));
		frame.getContentPane().add(btnNewButton_1_1);
		
		JLabel lblNewLabel = new JLabel("\uC774\uBBF8\uC9C0 \uB4E4\uC5B4\uAC08 \uC790\uB9AC");
		lblNewLabel.setBounds(114, 87, 376, 376);
		frame.getContentPane().add(lblNewLabel);
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // new select_options();
                frame.setVisible(false);
            }
        });
		
		
		

        frame.setVisible(true);
	}

}
