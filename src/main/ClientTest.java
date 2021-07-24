package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class ClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Socket socket = new Socket("localhost",13)){
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			// 서버한테 쓰기
			System.out.println("서버한테 쓰기 시작");
			writer.write("YOU\n");
			writer.flush();
			System.out.println("서버한테 쓰기 끝");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// 서버한테 읽기
			System.out.println("서버한테 읽기 시작");
			for(String line = reader.readLine(); line != null;line = reader.readLine()) {
				System.out.println(line);
			}
			System.out.println("서버한테 읽기 끝");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
