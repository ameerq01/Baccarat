package application;

import java.util.Random;
import javafx.scene.image.Image;

public class DeckOfCards {
//Declaring class variables and arrays
	private Card[] deck;
	private String[] suits;
	private Random rnd;
	private int cardIndex;
	/**
	 * A constructor that takes a boolean. It will create just a default deck of cards if a false
	 * value is passed. If a true value is passed, it will also shuffle the deck after generating it
	 * @param shuf - a boolean value that determines whether or not the deck is to be shuffled when generated
	 */
	public DeckOfCards(boolean shuf){
		createDeck();
		if(shuf == true){
			shuffle();
		}
	}
	/**
	 * A constructor that generates a default deck of cards, in order
	 */
	public DeckOfCards(){
		createDeck();
	}
	/**
	 * Creates a default sorted deck of cards. Can only be used within the class 
	 */
	private void createDeck(){
		deck = new Card[52];
		cardIndex=-1;
		suits = new String[]{"CLUBS","DIAMONDS","HEARTS","SPADES"};
		int index= 0;
		rnd = new Random();
		for(int i=2; i<=14;i++){
			for(int j = 0; j<4;j++){
				deck[index]=new Card(new Image("file:images/"+i+suits[j].charAt(0)+".png"),i,suits[j]);
				if(i<=10){
					deck[index].setName(i+" of "+ suits[j]);
				}
				else if(i==11){
					deck[index].setName("JACK of "+ suits[j]);
				}
				else if(i==12){
					deck[index].setName("QUEEN OF "+suits[j]);
				}
				else if(i==13){
					deck[index].setName("KING OF "+suits[j]);
				}
				else {
					deck[index].setName("ACE OF" +suits[j]);
				}
				if(i>=2&&i<=9) {
					deck[index].setValue(i);
				}
				else if(i==14) {
					deck[index].setValue(1);
				}
				else {
					deck[index].setValue(0); 
				}
				index++;
			}
		}
	}
	/**
	 * Method that shuffles the deck of cards. It changes the order in which the cards are present in the array of Cards
	 */
	public void shuffle(){
		int maxN = 52;
		while(maxN >1){
			int k = rnd.nextInt(maxN);
			maxN--;
			//swap
			Card temp = deck[maxN];
			deck[maxN] = deck[k];
			deck[k]=temp;
		}
		cardIndex=-1;
	}
	/**
	 * Method that deals a card. It deals the card at the cardIndex. It shuffles the deck once there are less than 6 cards left
	 * @return a Card object at the cardIndex
	 */
	public Card dealCard(){
		cardIndex++;
		if(52 - cardIndex < 6) {
			createDeck();
			shuffle();
			cardIndex=0;
		}
		return deck[cardIndex];
	}
}