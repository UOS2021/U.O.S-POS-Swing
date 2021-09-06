package pcroom;

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

public class PCRoomInformation {
	private File file;
	private JSONParser parser;
	
	private JSONObject information;
	private String response_code;
	private JSONObject companyInfo;
	private JSONArray category_list;
	private JSONArray set_list;
	
	
	public PCRoomInformation() {
		file = new File("information_pcroom.json");
		parser = new JSONParser();
		
		update();
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
	
	public JSONArray getCategoryList() {
		return category_list;
	}
	public JSONArray getSetList() {
		return set_list;
	}
	
	public void update() {
		try {
			information = (JSONObject)(parser.parse(new FileReader(file)));
			response_code = (String)information.get("response_code");
			JSONObject message = ((JSONObject)information.get("message"));
			companyInfo = (JSONObject)message.get("company");
			category_list = (JSONArray)message.get("category_list");		
			set_list = (JSONArray)message.get("set_list");	
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
