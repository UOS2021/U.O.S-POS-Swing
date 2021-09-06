package order;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OrderReceivedClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Socket socket = new Socket("127.0.0.1",88)){
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 서버한테 쓰기
			System.out.println("서버한테 쓰기 시작");
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("request_code", "0011");	
			
			
			JSONObject card = new JSONObject();
			card.put("num", "0000-0000-0000-0000");
			card.put("cvc", "000");
			card.put("pw", "cardpw");
			card.put("due_date", "00/00");
			
			JSONObject order1 = new JSONObject();
			order1.put("menu", "productname");
			order1.put("submenu", "submenu");
			order1.put("count", "3");
			order1.put("price", "1000");
			
			JSONArray order_arr = new JSONArray();
			order_arr.add(order1);
			
			JSONObject message = new JSONObject();
			
			message.put("id", "inu");
			message.put("card", card);
			message.put("order", order_arr);
			
			jsonObject.put("message", message);
			
			System.out.println(jsonObject.toString());
			writer.write(jsonObject.toString());
			writer.flush();
			System.out.println("서버한테 쓰기 끝");

			BufferedReader reader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 서버한테 읽기
			System.out.println("서버한테 읽기 시작");
			
			System.out.println(reader1.readLine());
			
			System.out.println("서버한테 읽기 끝");
			
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(reader2.readLine());
			System.out.println("서버한테 읽기 진짜 끝");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
