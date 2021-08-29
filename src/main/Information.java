package main;

import java.io.BufferedReader;

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

public class Information {
	private JSONObject information;
	
	public Menu menu;
	private String response_code;
	private JSONObject companyInfo;
	
	public Information() {
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
	
	public void update() {
		try {
			JSONParser parser = new JSONParser();
			information = (JSONObject)(parser.parse(new FileReader("information.json")));
			
			response_code = (String)information.get("response_code");
			
			JSONObject message = ((JSONObject)information.get("message"));
			companyInfo = (JSONObject)message.get("name");
			menu = new Menu((JSONArray)message.get("category_list"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return information.toString();
	}
	
}
