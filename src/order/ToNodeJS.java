package order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ToNodeJS {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://211.217.202.55:8080/post"); // 호출할 url

		byte[] out = "{\"request_code\":\"0013\",\"message\":{\"id\":\"testid12\"}}".getBytes(StandardCharsets.UTF_8);
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
			System.out.println(inputLine);
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

	}

}
