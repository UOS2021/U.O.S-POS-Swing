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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class POSServer extends Thread{
	
	public final static int PORT=10003;
	
	@Override
	public void run() {
		try(ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				Socket connection = server.accept();
				new POSThread(connection).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class POSThread extends Thread{
	private Socket connection;
	private BufferedReader reader;
	private PrintWriter writer;
	private JSONParser parser;
	
	private UserData userData;
	
	POSThread(Socket connection){
		this.connection = connection;
	}
	
	@Override
	public void run() {
		System.out.println("연결 완료");	
		try {
			parser = new JSONParser();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"EUC-KR"));
			writer = new PrintWriter(connection.getOutputStream(), true);	

			System.out.println("서버 읽기 시작");
			String line;
			while(true) {
				line = reader.readLine();
				if(line==null)
					continue;
				JSONObject obj = (JSONObject)parser.parse(line);
				String code = (String) obj.get("request_code");
				System.out.println(code);
				System.out.println("서버 읽기 끝");
				
				switch(code) {
				case "0000":
					code0000();
					break;
				case "0011":
					code0011(obj); // 주문 요청
					break;
				case "0012":
					code0012(); // 주문 취소
					break;
				case "0014": // 매장정보 및 주문가능목록
					code0014();
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void code0000() {}
	
	private void code0011(JSONObject obj) { // 주문 요청
		JSONObject message = (JSONObject)obj.get("message");
		JSONObject cardJSON = (JSONObject) message.get("card");
		String card_num = (String) cardJSON.get("num");
		String card_cvc = (String) cardJSON.get("cvc");
		String card_pw = (String) cardJSON.get("pw");
		String card_due_date = (String) cardJSON.get("due_date");
		JSONObject orderJSON = (JSONObject) message.get("order");
		String order_name = (String) orderJSON.get("name");
		String order_count = (String) orderJSON.get("count");
		
		// 필요한 것들
		String id = (String) message.get("id");
		String order_number = (String) message.get("android_key_value");
		Card card = new Card(card_num, card_pw, card_cvc, card_due_date);
		Order order = new Order(order_name, order_count);
	}

	private void code0012() { // 주문 취소
		
	}

	
	public void code0014() throws FileNotFoundException, IOException, ParseException { // 매장정보 및 주문가능목록
		sendOrderList();
	}
	
	public void sendOrderList() throws FileNotFoundException, IOException, ParseException { // response_code 0007
		JSONObject information = (JSONObject)(parser.parse(new FileReader("information.json")));
		information.put("response_code", "0007");
		writer.println(information.toJSONString());
	}
	
	
}