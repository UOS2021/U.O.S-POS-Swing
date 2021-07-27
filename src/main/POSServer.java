package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class POSServer{
	
	public final static int PORT=13;

	public static void main(String[] args) {
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
	POSThread(Socket connection){
		this.connection = connection;
	}
	
	@Override
	public void run() {
		System.out.println("연결 완료");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
			// 클라이언트한테 읽기
			System.out.println("클라이언트한테 읽기 시작");
			System.out.println(reader.readLine());
			System.out.println("클라이언트한테 읽기 끝");
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			// 클리이언트한테 쓰기
			System.out.println("클라이언트한테 쓰기 시작");
			writer.write("To Client From Server\n");
			writer.flush();
			System.out.println("클라이언트한테 쓰기 끝");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}