package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class POSServer extends Thread{
	
	public final static int PORT=13;
	
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
	private BufferedWriter writer;
	
	private StoreData storeData;
	private UserData userData;
	
	POSThread(Socket connection){
		this.connection = connection;
	}
	
	@Override
	public void run() {
		System.out.println("연결 완료");
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			
			// 클라이언트한테 읽기
//			System.out.println("클라이언트한테 읽기 시작");
//			System.out.println(reader.readLine());
//			System.out.println("클라이언트한테 읽기 끝");

			// 클리이언트한테 쓰기
			 
//			System.out.println("클라이언트한테 쓰기 시작");
//			writer.write("To Client From Server\n");
//			writer.flush();
//			System.out.println("클라이언트한테 쓰기 끝");
			
			QRCommunicate();
			OrderWaiting();
			Paying();
			
			reader.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	private void QRCommunicate() throws IOException {
		// TODO Auto-generated method stub
		String userPhoneNumber = reader.readLine();
		userData.setPhoneNumber(userPhoneNumber);
		writer.write(storeData.getStoreName());
		writer.write(storeData.getStoreLocation());
		writer.write(storeData.getMenu());
		writer.flush();
	}
	
	private void OrderWaiting() throws IOException {
		// TODO Auto-generated method stub
		String order = reader.readLine();
		
	}
	
	private void Paying() throws IOException {
		String order = reader.readLine();
		
		// order 처리
		

		// 주문 성공
		writer.write(0);
	}
}