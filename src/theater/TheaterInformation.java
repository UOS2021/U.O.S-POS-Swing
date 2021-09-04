package theater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TheaterInformation {
	private File file;
	private JSONObject information;
	private String response_code;
	private JSONObject companyInfo;
	private JSONArray movie_list;
	private JSONArray category_list;
	
	
	public TheaterInformation() {
		update();
	}
	
	public JSONArray getMovieList() {
		return movie_list;
	}

	public String getResponse_code() {
		return response_code;
	}
	public String getCompanyInfoName() {
		return (String)companyInfo.get("name");
	}
	public String getCompanyInfoType() {
		return (String)companyInfo.get("type");
	}
	
	public void update() {
		try {
			file = new File("information_theater.json");
			JSONParser parser = new JSONParser();
			information = (JSONObject)(parser.parse(new FileReader(file)));
			
			response_code = (String)information.get("response_code");
			JSONObject message = ((JSONObject)information.get("message"));
			companyInfo = (JSONObject)message.get("company");
			movie_list = (JSONArray)message.get("movie_list");
			category_list = (JSONArray)message.get("category_list");			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return information.toString();
	}
	
}
