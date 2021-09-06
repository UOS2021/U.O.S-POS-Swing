package order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fcm {
    public static void push(String companyName, String orderNumber, String token) throws Exception{
        final String apiKey = "AAAA3aUsP0I:APA91bGfamhYdPu19cB39ODJfDKzt4BRqjFhFhMPz2T8IDXYuvliqF9C79G5F1IAVLdCLd9R0eI6ro2CSf4PT500bIz7H0_bwDVkezu1mTLg5LR7eQNhR6NauT6ntWPDiFUFIVclXtHO";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);
        conn.setDoOutput(true);

        String input = "{\"data\" : {\"company_name\" : \""+ companyName +"\", \"order_number\" : \""+ orderNumber +"\"}, \"to\":\""+ token +"\"}";
        OutputStream os = conn.getOutputStream();

        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();
    }
}
