package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;

public class ClientTest {
	private PrintWriter writer;
	private BufferedReader reader;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClientTest().Do();
	}
	
	public void Do() {
		try(Socket socket = new Socket("112.214.137.70",10003)){
			writer = new PrintWriter(socket.getOutputStream(), true);
			
			// 서버한테 쓰기
			System.out.println("서버한테 쓰기 시작");
			String str = "{\"request_code\" : \"0014\", \"fuck\" : \"0003\"}";
			writer.println(str);
			System.out.println("서버한테 쓰기 끝");

			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("서버한테 읽기 끝");
			System.out.println(reader.readLine());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String read() throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=reader.readLine())!=null) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

}
