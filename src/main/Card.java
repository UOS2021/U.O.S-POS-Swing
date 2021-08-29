package main;

public class Card {
	
	private String cardPW;
	private String cardNumber;
	private String cardCVC;
	private String cardExpiration;
	
	public Card(String card_num, String card_pw, String card_cvc, String card_due_date) {
		cardNumber = card_num;
		cardPW = card_pw;
		cardCVC = card_cvc;
		cardExpiration = card_due_date;
	}

	public String getCardPW() {
		return cardPW;
	}

	public String getCardCVC() {
		return cardCVC;
	}

	public String getCardExpiration() {
		return cardExpiration;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	
	
}
