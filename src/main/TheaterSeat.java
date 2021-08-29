package main;

import java.awt.Color;

import javax.swing.JButton;

import org.json.simple.JSONObject;

public class TheaterSeat extends JButton{
	
	private String code;
	private long price;
	private long state; // 0=예약가능, 1=예약완료, 2=예약불가
	private Color availableSeatColor = new Color(255, 102, 164);
	private Color unavailableSeatColor = new Color(214, 214, 214);
	
	public TheaterSeat(int column, int row) {
		state = 0;
		this.setBackground(availableSeatColor);
		this.setOpaque(true);
		
		char a = (char) (column + 'A');
		code = String.valueOf(a)+String.valueOf(row+1);		
	}
	
	public TheaterSeat(JSONObject seat) {
		code = (String)seat.get("code");
		price = (long)seat.get("price");
		state = (long)seat.get("state");
		if(state==0) {
			this.setBackground(availableSeatColor);
		} else if(state==2) {
			this.setBackground(unavailableSeatColor);
		}
	}
	
	public TheaterSeat(char init) {
		if(init=='0')
			state=0;
		else
			state=2;
	}

	public void changeState() {
		if(this.getBackground().equals(availableSeatColor)) {
			this.setBackground(unavailableSeatColor);
			state = 2;
		} else {
			this.setBackground(availableSeatColor);
			state = 0;
		}
	}
	
	public JSONObject getSeatJSON() {
		JSONObject ret = new JSONObject();
		ret.put("code", code);
		ret.put("state", state);
		ret.put("price", price);
		return ret;
	}
}
