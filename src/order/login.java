package order;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.Main;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	public JPanel this_panel,panel;
	public Main main;


	public login(Main main) {
		this.main = main;
		initialize();
	}

	private void initialize() {
		frame = main.getFrame();
		
		this_panel = new JPanel();
		panel = main.getPanel();
		
		panel.add(this_panel);
		this_panel.setBackground(new Color(255, 213, 79));
		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);

		JLabel lblNewLabel = new JLabel("U.O.F POS");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 49));
		lblNewLabel.setBounds(267, 64, 273, 94);
		this_panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디 : ");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(199, 212, 127, 36);
		this_panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("비밀번호 :");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(174, 276, 127, 36);
		this_panel.add(lblNewLabel_1_1);

		// ID 입력란
		textField = new JTextField();
		textField.setBounds(298, 206, 187, 43);
		this_panel.add(textField);
		textField.setColumns(10);

		// PW 입력란
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(298, 275, 186, 43);
		this_panel.add(textField_1);

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setBackground(new Color(255, 255, 224));
		btnNewButton.setBounds(507, 206, 97, 113);
		this_panel.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// new select_options();
				//if()
				
				JSONObject request = new JSONObject();
				request.put("request_code", "0003");

				JSONObject message = new JSONObject();
				message.put("id", textField.getText());
				message.put("pw", textField_1.getText());
				message.put("type", "uofpartner");

				request.put("message", message);

				try {
					JSONObject response = contectExternalServer(request.toString());
					String reponse_code = (String) response.get("response_code");

					switch (reponse_code) {
					case "0003":
						System.out.println("로그인 성공!");
						this_panel.setVisible(false);
						
						main.getSelectOptions().initialize();
						main.getSelectOptions().getPanel().setVisible(true);
						
						break;

					case "0005":
						System.out.println("없는 아이디입니다.");
						
						break;
						
					case "0006":
						System.out.println("비밀번호가 틀렸습니다.");
						break;

					default:
						break;
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		
			}
		});
	}
	
	public JPanel getPanel() {
		return this_panel;
	}

	public JSONObject contectExternalServer(String json) throws Exception {
		URL url = new URL("http://211.217.202.55:8080/post"); // 호출할 url

		byte[] out = json.getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);

		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		http.connect();
		try (OutputStream os = http.getOutputStream()) {
			os.write(out);
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String testString = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null) { // response 출력
			System.out.println("response :" + inputLine);
			testString = inputLine;
		}

		in.close();

		testString = testString.replace("\"{", "{");
		testString = testString.replace("}\"", "}");
		testString = testString.replace("\\", "");

		// String to Json
		JSONParser jsonParse = new JSONParser();
		JSONObject obj = (JSONObject) jsonParse.parse(testString);
		System.out.println("JsonObject 결과 값 :: " + obj);
		
		if(((String)obj.get("response_code")).equals("0003")) {
			JSONObject message = (JSONObject) obj.get("message");
			main.setCompanyType((String)message.get("company_type"));
			main.setCompanyName((String)message.get("company_name"));
		}
		
		return obj;
	}

}
