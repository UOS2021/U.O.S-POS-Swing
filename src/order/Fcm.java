package order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

public class Fcm {
    public final static String AUTH_KEY_FCM = "AAAA3aUsP0I:APA91bGfamhYdPu19cB39ODJfDKzt4BRqjFhFhMPz2T8IDXYuvliqF9C79G5F1IAVLdCLd9R0eI6ro2CSf4PT500bIz7H0_bwDVkezu1mTLg5LR7eQNhR6NauT6ntWPDiFUFIVclXtHO";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public static void push(String company_name, int order_number, String token) throws Exception {
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        
        data.put("company_name", company_name);
        data.put("order_number", order_number);

        json.put("to", token);
        json.put("data", data);

        String sendMsg = json.toString();
        OutputStream os = conn.getOutputStream();
        os.write(sendMsg.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + sendMsg);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
    }

} 