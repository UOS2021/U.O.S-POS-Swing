package order;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

import javax.swing.JFrame;

import java.util.Base64.Encoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.ImgEncoder;




public class POSServer extends Thread{
	
	public final static int PORT=88;
	public POSThread POS_Thread;
	public Object frame;
	public String type;
	
	public ArrayList<String> orderList;
	public ArrayList<Entry> entryList;
	
	
	public POSServer(String type, Object orderReceivedFrame, ArrayList<String> orderList, ArrayList<Entry> entryList) {
		this.frame = orderReceivedFrame;
		this.orderList = orderList;
		this.entryList = entryList;
		this.type = type;
		System.out.println("서버온");
	}
	
	@Override
	public void run() {
		try(ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				Socket connection = server.accept();
				POS_Thread = new POSThread(connection, type, frame, orderList, entryList);
				POS_Thread.start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public POSThread getPOSThread() {
		return this.POS_Thread;
	}
}

class POSThread extends Thread{
	private Socket connection;
	private PrintWriter writer;
	private JSONParser parser;
	public boolean check;
	public boolean whether;
	public Object frame;
	public String type;
	
	public ArrayList<String> orderList;
	public ArrayList<Entry> entryList;
	public boolean order_status;
	public static String save_req = "";
	public static int order_num = 1;
	
	// 지울 거
	public static String company_name = "맘스터치";
	public static String store_type = "Restaurant";
	
	public void setCheckWhether(boolean check, boolean whether) {
		this.check = check;
		this.whether = whether;
		order_status = true;
	}
	
	POSThread(Socket connection, String type, Object frame, ArrayList<String> orderList, ArrayList<Entry> entryList) throws IOException {
		
		parser = new JSONParser();
		this.connection = connection;
		this.check = false;
		this.frame = frame;
		this.type = type;
		this.orderList = orderList;
		this.entryList = entryList;
		writer = new PrintWriter((this.connection).getOutputStream(), true);	

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
                    //System.out.println("receive: "+ recvmsg);
                    System.out.println(recvmsg);
                    obj = (JSONObject)parser.parse(recvmsg);
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
					JSONObject reponse = new JSONObject();
                    
                    switch ((String)obj.get("request_code")) {
					case "0000":
						
						reponse.put("response_code", "0000");
						reponse.put("message", "none");
				        out.println(reponse.toString());
				        
						break;
						
					case "0011":
						//code0011(obj);
						reponseMaker(connection, "0009");
						Scanner sc = new Scanner(System.in);
						System.out.println("["  + ((JSONObject)obj.get("message")).get("id") + "] y/n");
						
						
						
						((select_options)frame).whetherDialog(obj);
						while(this.check == false) {
							System.out.println("확인 안 함");
							if(order_status == false) {
								break;
							}
						}
						
						
						/*
						// 지울 거
						sleep(3000);
						whether = true;
						*/
						
						if(whether == true) {
							int total_price = totalPrice(obj);
							System.out.println("total price : " + total_price);
							
							save_req = ((obj.toString()).replace("0011", "000A")).replace("\"card\":", ("\"company_name\": \"" + company_name + "\", \"total_price\": \"" + total_price +"\", \"card\":"));
							
							reponseMaker(connection, "0011");
							
						}
						else {
							reponseMaker(connection, "0012");
						}
						break;
					case "0012":
						if( ((boolean)((JSONObject)obj.get("message")).get("cancel")) == false ) {
							
							
							// DB에 주문내역 저장
							String reponse_to_me = contectExternalServer(save_req);
							JSONParser jsonParse = new JSONParser();
							JSONObject reponse_to_me_obj = (JSONObject) jsonParse.parse(reponse_to_me);
							
							
							switch (((String)reponse_to_me_obj.get("response_code"))) {
							case "A000":
								reponse.put("response_code", "0030");
								
								JSONObject order_number = new JSONObject();
								
								// mysql로 order_number 외부서버에서 받아오기
								order_number.put("order_number", Integer.parseInt(String.valueOf(((JSONObject) reponse_to_me_obj.get("message")).get("num"))) );
								
								reponse.put("message", order_number);
								
								
								System.out.println("최종 보내는 거 : " + reponse.toString());
								
						        out.println(reponse.toString());
						        System.out.println("결제 성공");
						        
						        
								JSONObject save_req_json = (JSONObject) jsonParse.parse(save_req);
						        String listData = findTheMostExpensive(save_req_json);
						        orderList.add(listData);
						        
						        if(frame instanceof OrderReceivedFrame) {
						        	((OrderReceivedFrame)frame).refreshScrollList();
						        }
						        else if(frame instanceof select_options) {
						        	((select_options)frame).refreshScrollList();
						        }
						        
						        
						        
						        entryList.add(new Entry(store_type, save_req_json));
						        
								break;
								
							case "B000":
								reponse.put("response_code", "0032");
								out.println(reponse.toString());
								break;
								
							case "C000":
								reponse.put("response_code", "0031");
								out.println(reponse.toString());
								break;
								
							case "D000":
								reponse.put("response_code", "0032");
								out.println(reponse.toString());
								break;

							default:
								break;
							}
										
							
					        
						}
						else if( ((boolean)((JSONObject)obj.get("message")).get("cancel")) == true ) {
							JSONObject req = new JSONObject();
							
							reponse.put("response_code", "0026");
					        out.println(reponse.toString());
					        System.out.println("고객 주문취소");
						}
						else {
							reponse.put("response_code", "0027");
					        out.println(reponse.toString());
					        System.out.println("주문취소결과-실패");
						}
						break;
						
					case "0014": // 매장정보 및 주문가능목록
						code0014();
						break;
						
					case "0015": // QR코드 이미지 요청
						code0015();
						break;
						
					default:
						break;
					}
                    out.flush();

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
	
	public int totalPrice(JSONObject obj) {
		JSONObject message = (JSONObject) obj.get("message");
		JSONArray order = (JSONArray) message.get("order");
		int total_price = 0;
		for(Object o : order) {
			JSONObject or = (JSONObject)o;
			total_price += Integer.parseInt(String.valueOf(or.get("price"))) * Integer.parseInt(String.valueOf(or.get("count")));
		}
		
		
		return total_price;
	}

	public String findTheMostExpensive(JSONObject obj) {
		JSONObject message = (JSONObject) obj.get("message");
		System.out.println(message.toJSONString());
		JSONArray order = (JSONArray)message.get("order");
		int most_expensive = -1;
		String most_menu = "";
		int cnt = 0;
		for(Object o : order) {
			JSONObject or = (JSONObject)o;
			if( Integer.parseInt(String.valueOf(or.get("price"))) > most_expensive ){
				most_menu = (String)or.get("menu");
				most_expensive = Integer.parseInt(String.valueOf(or.get("price")));
			}
			cnt++;
		}
		
		String return_string = "";
		if(cnt == 1) {
			return_string = order_num + ". " + most_menu;
		}
		else {
			return_string = order_num + ". " + most_menu + " 외 " + (cnt-1) + "개";
		}
		order_num++;
			
		
		return return_string;
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
		int type = Integer.parseInt(String.valueOf(order1.get("type")));
		String menu = (String) order1.get("menu");
		String submenu = (String) order1.get("submenu");
		int count = Integer.parseInt(String.valueOf(order1.get("count")));
		int price = Integer.parseInt(String.valueOf(order1.get("price")));

		// 필요한 것들
		String id = (String) message.get("id");
		// Card card = new Card(card_num, card_pw, card_cvc, card_due_date);
		// Order order = new Order(order_name, order_count);
		
		

	}


	public void code0014() throws FileNotFoundException, IOException, ParseException { // 매장정보 및 주문가능목록
		
		JSONObject whole = null;
		switch (type) {
		case "패스트푸드/프랜차이즈":
			whole = (JSONObject)(parser.parse(new FileReader("information_restaurant.json")));
			break;

		case "영화관":
			whole = (JSONObject)(parser.parse(new FileReader("information_theater.json")));
			break;

		case "피시방":
			whole = (JSONObject)(parser.parse(new FileReader("information_pcroom.json")));
			break;
		}
		//JSONObject whole = (JSONObject)(parser.parse(new FileReader("information_theater.json")));
		JSONArray categroy_list = ((JSONArray)((JSONObject)whole.get("message")).get("category_list"));
		
		// 이미지 주소 -> 이미지 데이터 변경
		for(int i=0;i<categroy_list.size();i++) {
			JSONObject category = (JSONObject)categroy_list.get(i);
			JSONArray product_list = (JSONArray)category.get("product_list");
			for(int j=0; j<product_list.size(); j++) {
				JSONObject product = (JSONObject)product_list.get(j);
				String imagePath = (String)product.get("image");
				byte[] imageBytes = ImgEncoder.extractBytes(imagePath);
				if(imageBytes == null) {
					product.replace("image", "");
					return;
				}
				byte[] baseEncodingBytes = ImgEncoder.encodingBase64(imageBytes);
				String  encodedImg = new String(baseEncodingBytes);
				product.replace("image", encodedImg);
			}
		}
		writer.println(whole.toJSONString());
	}
	
	private void code0015() throws IOException {
		JSONObject data = new JSONObject();		
		JSONObject message = new JSONObject();
		File QRImage = new File("QRCODE.jpg");
		
		byte[] imageBytes = ImgEncoder.extractBytes("QRCODE.jpg");
		byte[] baseEncodingBytes = ImgEncoder.encodingBase64(imageBytes);
		
		message.put("image", new String(baseEncodingBytes));
		
		data.put("response_code", "0028");
		data.put("message",message);
		
		System.out.println(data.toJSONString());
		writer.println(data.toJSONString());
	}	
	
	public String contectExternalServer(String json) throws Exception {
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
		return obj.toString();
	}

}
