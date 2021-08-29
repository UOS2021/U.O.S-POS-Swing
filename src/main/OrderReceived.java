package main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OrderReceived extends Thread {

	public final static int PORT = 10003;

	@Override
	public void run() {
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				Socket connection = server.accept();
				new POSThread(connection).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class POSThread extends Thread {
	private Socket connection;
	private PrintWriter writer;
	private JSONParser parser;

	// private UserData userData;

	POSThread(Socket connection) {
		this.connection = connection;
	}

	@Override
	public void run() {
		JSONParser parser = new JSONParser();
		JSONObject obj;
		System.out.println("연결 완료");
		try {

			while (true) {
                InputStream input = connection.getInputStream();
                if (input.available() != 0) {
                    byte[] byteArray = new byte[input.available()];
                    int size = input.read(byteArray);

                    if (size == -1) {
                        break;
                    }
                    String recvmsg = new String(byteArray, 0, size, StandardCharsets.UTF_8); // JSON -> String
                    System.out.println("receive: "+ recvmsg);
                    obj = (JSONObject)parser.parse(recvmsg);
                    
                    switch ((String)obj.get("request_code")) {
					case "0000":
						PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
						JSONObject reponse = new JSONObject();
						reponse.put("response_code", "0000");
						reponse.put("message", "none");
				        out.println(reponse.toString());
				        out.flush();
						break;
						
					case "0011":
						code0011(obj);
						reponseMaker(connection, "0009");
						Scanner sc = new Scanner(System.in);
						System.out.println("["  + ((JSONObject)obj.get("message")).get("id") + "] y/n");
						String inputdata = sc.nextLine();
						if(inputdata.equals("y")) {
							reponseMaker(connection, "0011");
						}
						if(inputdata.equals("n")) {
							reponseMaker(connection, "0012");
						}
						break;
						
					default:
						break;
					}
                    
                    code0011(obj);
                    
                    
                    
                    
                    

                }
            }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[server]클라이언트가 퇴장했습니다.");
		}
	}

	private void reponseMaker(Socket con, String reponse_code) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter out = new PrintWriter(new OutputStreamWriter(con.getOutputStream()));
		JSONObject reponse = new JSONObject();
		reponse.put("response_code", reponse_code);
        out.println(reponse.toString());
        out.flush();
	}

	public void code0000() {
	}

	private void code0011(JSONObject obj) { // 주문 요청
		JSONObject message = (JSONObject) obj.get("message");
		JSONObject card = (JSONObject) message.get("card");
		String num = (String) card.get("num");
		String cvc = (String) card.get("cvc");
		String pw = (String) card.get("pw");
		String due_date = (String) card.get("due_date");
		JSONArray order = (JSONArray) message.get("order");
		JSONObject order1 = (JSONObject) order.get(0);
		String menu = (String) order1.get("menu");
		String submenu = (String) order1.get("submenu");
		String count = (String) order1.get("count");
		String price = (String) order1.get("price");

		// 필요한 것들
		String id = (String) message.get("id");
		// Card card = new Card(card_num, card_pw, card_cvc, card_due_date);
		// Order order = new Order(order_name, order_count);

	}

	private void code0012() { // 주문 취소

	}

	public void code0014() throws FileNotFoundException, IOException, ParseException { // 매장정보 및 주문가능목록
		sendOrderList();
	}

	public void sendOrderList() throws FileNotFoundException, IOException, ParseException { // response_code 0007
		JSONObject information = (JSONObject) (parser.parse(new FileReader("information.json")));
		information.put("response_code", "0009");
		writer.println(information.toJSONString());
	}

}