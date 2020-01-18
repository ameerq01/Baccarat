package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
//Declaring class variables
	private double width, height;
	private int value;
	private String suit, name;
	private Image imgCard;
	private ImageView ivCard;
	/**
	 * Constructor that makes a Card object with default values for the class variables (back of a card)
	 */
	public Card(){
		imgCard = new Image("file:images/back_blue.png");
		ivCard = new ImageView(imgCard);
		width = imgCard.getWidth();
		height = imgCard.getHeight();
		suit ="";
		name = "";
		value = 0;
	}
	/**
	 * Constructor that makes a Card with default values for the class variables (back of a card) but lets 
	 * the name be specified
	 * @param n - a String representing the name of the card
	 */
	public Card (String n){
		imgCard = new Image("file:images/back_blue.png");
		ivCard = new ImageView(imgCard);
		width = imgCard.getWidth();
		height = imgCard.getHeight();
		suit ="";
		name = n;
		value = 0;	
	}
	/**
	 * A constructor that allows the image, the value, and the suit to be specified
	 * @param i - an Image that specifies the image to be used for the Card
	 * @param v - an int that specifies the value of the Card
	 * @param suit - a String that specifies what the suit of the Card should be called
	 */
	public Card(Image i, int v, String suit){
		this.suit= suit;
		imgCard = i;
		ivCard = new ImageView(imgCard);
		width = imgCard.getWidth();
		height = imgCard.getHeight();
		this.suit=suit;
		name = "";
		value = 0;			
	}
	/**
	 * Method that allows the image to be set
	 * @param img - an Image object that will be the image of the Card object 
	 */
	public void setImage(Image img){
		imgCard = img;
		ivCard = new ImageView(imgCard);
	}
	/**
	 * Method that allows the ImageView of the Card object to be set
	 * @param iv - an ImageView that will be the ImageView of the card
	 */
	public void setNode(ImageView iv){
		ivCard = iv;
	}
	/**
	 * Method that returns the ImageView of the Card object
	 * @return the ImageView of the Card object
	 */
	public ImageView getNode(){
		return ivCard;
	}
	/**
	 * Method that takes an int that will represent the height of the card
	 * @param h - an int representing the desired height of the card
	 */
	public void setHeight(int h){
		height = h;
		ivCard.setFitHeight(h);
	}
	/**
	 * Method that gets the height of the Card object
	 * @return an int representing the height of the Card object
	 */
	public double getHeight(){
		return height;
	}
	/**
	 * Method that takes an int that represents the desired height of the card
	 * @param w - an int representing the desired height of the card
	 */
	public void setWidh(double w){
		width = w;
		ivCard.setFitWidth(w);
	}
	/**
	 * Method that changes the name of the Card object
	 * @param n - a String representing the desired name of the Card object
	 */
	public void setName(String n){
		name = n;
	}
	/**
	 * Method that returns the name of the Card
	 * @return a String representing the name of the Card object
	 */
	public String getName(){
		return name;
	}
	/**
	 * Method that changes what the suit of the Card is called
	 * @param s - a String representing what the suit of the card should be called
	 */
	public void setSuit(String s){
		suit =s;
	}
/**
 * Method that returns what the suit of the card is
 * @return A String representing the suit of the card
 */
	public String getSuit(){
		return suit;
	}
	/**
	 * Method that sets the value of the Card
	 * @param v - an Int representing the value of the Card object
	 */
	public void setValue(int v){
		value =v;
	}
/**
 * Method that resets the class variables to default values. This will make the Card a default card(back of the Card)
 */
	public void setDefault() {
		imgCard = new Image("file:images/back_blue.png");
		ivCard = new ImageView(imgCard);
		width = imgCard.getWidth();
		height = imgCard.getHeight();
		suit ="";
		name = "";
		value = 0;	
	}
/**
 * Method that returns the value of the Card object
 * @return an int representing the value of the Card object
 */
	public int getValue(){
		return value;
	}
}

