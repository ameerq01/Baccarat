package application;
//Importing javafx and its packages
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.Optional;
public class Main extends Application {
	//Declaring the global arrays and  variables
	private Card[][] c;
	private DeckOfCards d;
	private Label[][] lblCards;
	private Label lblPlayer, lblBanker, lblPTotal, lblBTotal, lblChips, lblResult;
	private TextField txtBet;
	private Button btnDeal, btnBet, btnQuit;
	private int bet, pPoints, bPoints;
	private int totalMoney;
	private boolean pstand, bstand;
	//private ImageView[] imgPlayer, imgBanker;
	public void start(Stage primaryStage) {
		try {		
			//Initializing the array of Card objects
			c = new Card[3][2];
			//Initializing each Card in c
			for(int i =0 ; i<c.length ; i++)
				for(int j =0; j<c[i].length;j++)
					c[i][j] = new Card();
			//Initializing totalMoney, pPoints, and bPoints
			totalMoney= 500;	
			pPoints =0;
			bPoints=0;
			//Declaring and initializing yPos , an int used to place the cards
			int yPos = 175;
			//Initializing a DeckOfCards that shuffles the cards 
			d = new DeckOfCards(true);
			//Declaring and initializing Image and ImageView  for the title
			Image imgTitle = new Image("file:images/baccarat1.png");
			ImageView iviewTitle = new ImageView(imgTitle);
			//Initializing an int for the width of the scene
			final int SCENE_WIDTH = 1000;
			//Declaring and initializing ImageView
			ImageView iviewBack = new ImageView(new Image("file:images/card_table.jpg"));
			//Declaring and initializing lblChips and setting its properties
			lblChips = new Label();
			lblChips.setPrefSize(175, 40);
			lblChips.setAlignment(Pos.CENTER);
			lblChips.setFont(Font.font("System", FontWeight.BOLD, 36));
			lblChips.setTextFill(Color.WHITE);
			lblChips.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, 
					BorderWidths.DEFAULT, new Insets(0, 0, 0, 0))));
			lblChips.setText("$"+totalMoney);
			//Declaring and initializing labels and setting their properties
			lblPlayer = new Label("Player has:");
			lblPlayer.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblPlayer.setAlignment(Pos.CENTER);
			lblPlayer.setPrefSize(150, 40);
			lblPlayer.setTextFill(Color.WHITE);
			lblBanker = new Label("Banker has:");
			lblBanker.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblBanker.setAlignment(Pos.CENTER);
			lblBanker.setPrefSize(150, 40);
			lblBanker.setTextFill(Color.WHITE);
			lblPTotal = new Label();
			lblPTotal.setFont(Font.font("System", FontWeight.BOLD, 28));
			lblPTotal.setAlignment(Pos.CENTER);
			lblPTotal.setTextFill(Color.WHITE);
			lblPTotal.setPrefSize(150, 40);
			lblPTotal.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, 
					BorderWidths.DEFAULT, null)));
			lblBTotal = new Label();
			lblBTotal.setFont(Font.font("System", FontWeight.BOLD, 28));
			lblBTotal.setAlignment(Pos.CENTER);
			lblBTotal.setTextFill(Color.WHITE);
			lblBTotal.setPrefSize(150, 40);
			lblBTotal.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, null)));
			lblResult = new Label();
			lblResult.setPrefSize(200, 40);
			lblResult.setFont(Font.font("System", FontWeight.BOLD, 18));
			lblResult.setTextFill(Color.WHITE);
			lblResult.setText("Place BET to begin!");
			lblResult.setAlignment(Pos.CENTER);
			//Declaring and initializing txtBet and btnBet
			txtBet = new TextField();
			txtBet.setEditable(true);
			txtBet.setAlignment(Pos.CENTER);
			txtBet.setPrefSize(120, 40);
			txtBet.setMaxSize(120, 40);
			btnBet = new Button("PLACE BET");
			btnBet.setPrefSize(120, 40);
			btnBet.setFocusTraversable(false);
			//Setting the setOnAction for btnBet
			btnBet.setOnAction(e->{
				//Try statement to make sure the user inputs an int
				try {
					bet=Integer.parseInt(txtBet.getText());
					//Reinitializing the points, the label
					pPoints=0;
					bPoints=0;
					lblPTotal.setText("");
					lblBTotal.setText("");
					//(re)initializing the booleans that are used to determine whether player or banker need to draw another card
					bstand = false; 
					pstand = false;
					//Resetting all the and cards and labels to default value
					for(int i=0;i<c.length;i++) {
						for(int j =0; j<c[i].length; j++) {
							c[i][j].setDefault();
							lblCards[i][j].setGraphic(c[i][j].getNode());
						}
					}
					//If the user tries to bet more than they have
					if(bet>totalMoney) {
						//Alert them that their input is invalid
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("Invalid Bet!");
						a.setHeaderText(null);
						a.setContentText("Your Bet Cannot Exceed $"+totalMoney+"!");	
						a.showAndWait();
					}
					//If the user tries to bet less than 1
					else if (bet<1){
						//Alert them that their input is valid
						Alert a = new Alert(AlertType.ERROR);
						a.setTitle("Invalid Bet!");
						a.setHeaderText(null);
						a.setContentText("You Must Bet A Minimum of $1!");	
						a.showAndWait();
					}
					//If their bet is valid
					else {
						//Disable btnBet, Enable btnDeal
						btnBet.setDisable(true);
						btnDeal.setDisable(false);
						lblResult.setText("Click DEAL!");
					}
				}
				//If they do not enter an integer
				catch(Exception ex) {
					//Alert them that their valid is integer
					Alert a = new Alert(AlertType.ERROR);
					a.setTitle("Invalid Bet!");
					a.setHeaderText(null);
					a.setContentText("Please Enter An Integer!");	
					a.showAndWait();
				}
			});
			//Initializing btnQuit and btnDeal and setting their properties
			btnQuit = new Button("QUIT");
			btnQuit.setPrefSize(120, 40);
			btnQuit.setFocusTraversable(false);
			btnDeal = new Button("DEAL");
			btnDeal.setPrefSize(120, 40);
			btnDeal.setDisable(true);
			btnDeal.setFocusTraversable(false);
			//Setting the setOnAction for btnDeal
			btnDeal.setOnAction(e->{
				//If the user hasn't dealt yet (they just bet)
				if(btnDeal.getText().equalsIgnoreCase("DEAL")) {
					//Deal the first 2 cards for player and banker
					dealCard();
					//Check if the player draws another card
					checkDraw();	
					//If the player does not have to draw another card
					if (pstand == true && bstand ==false) {
						//Disable the deal button
						btnDeal.setDisable(true);
						//If the banker has 5 points or less
						if(bPoints <=5) {
							//Deal their last card
							c[2][1] = d.dealCard();
							//Set the label
							lblCards[2][1].setGraphic(c[2][1].getNode());
							//Calculate their points (without doing modulo 10)
							bPoints=0;
							for(int i =0; i<c.length;i++) {
								bPoints+=c[i][1].getValue();
							}
						}
						//Now that the banker has either drawn a card or stood, make bstand true
						bstand=true;
					}
				}
				//If the player is trying to draw a card
				else if (btnDeal.getText().equalsIgnoreCase("DRAW CARD") ) 
				{
					//draw a card
					drawPCard();
					//Now make pstand true
					pstand =true;
					//Disable the btnDeal
					btnDeal.setDisable(true);
					//If the banker hasn't stood yet
					if(bstand == false) {
						//Deal a third card to the banker
						c[2][1] = d.dealCard();
						//Update the third card
						lblCards[2][1].setGraphic(c[2][1].getNode());
						//Recalculate the bankers total points (Not doing modulo 10 yet)
						bPoints=0;
						for(int i =0; i<c.length;i++) {
							bPoints+=c[i][1].getValue();
						}
						//Make bstand true now
						bstand =true;
					}
				}
				//Calculate their actual points and update them on the label
				bPoints%=10;
				pPoints%=10;
				lblPTotal.setText(pPoints+"");
				lblBTotal.setText(bPoints+"");
				//If neither the player nor the banker need to draw any more cards
				if(pstand ==true&& bstand ==true) {
					//Set the properties of the alert
					Alert  alertResult = new Alert(AlertType.INFORMATION);
					alertResult.setTitle("Baccarat");
					alertResult.setHeaderText(null);
					//If player has more points
					if(pPoints>bPoints) {
						//Add their bet value to their totalMoney
						totalMoney+=bet;
						//Tell them they win
						alertResult.setContentText("PLAYER Wins!!");
					}
					//If the banker has more points
					else if (bPoints >pPoints) {
						//Tell them that the banker has won
						alertResult.setContentText("BANKER Wins!");
						//Deduct money from the total
						totalMoney-=bet;
					}
					//If they have equal points
					else {
						//Alert them that it is a tie
						alertResult.setContentText("It is a tie!");
					}	
					//Alert them of the result
					alertResult.showAndWait();
					//If they have no more money
					if(totalMoney == 0) {
						//Update the total
						lblChips.setText("$"+totalMoney);
						//Declare and initialize an alert to tell them they are broke and ask them if they want to play again
						Alert alertLose = new Alert(AlertType.CONFIRMATION);
						alertLose.setTitle("Baccarat");
						alertLose.setHeaderText(null);
						alertLose.setContentText("GAME OVER! You have $0 left. \nWould you like to play again?");
						alertLose.getButtonTypes().clear();
						alertLose.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
						Optional <ButtonType> result = alertLose.showAndWait();
						//If they do not want to play again, end the program
						if(result.get()==ButtonType.NO) {
							e.consume();
							System.exit(0);
						}
						//If they do want to play again
						else {
							//Resetting the text of their points
							lblPTotal.setText("");
							lblBTotal.setText("");
							//Reinitialize their totalMoney, pstand, and bstand
							totalMoney = 500;
							pstand = false;
							bstand =false;
							//Set the cards back to default
							for (int rows = 0; rows < lblCards.length; rows++) {
								for (int cols = 0; cols < lblCards[rows].length; cols++) {
									c[rows][cols].setDefault();
									lblCards[rows][cols].setGraphic(c[rows][cols].getNode());
								}
							}
							//Reinitialize the deck of cards and shuffle it again
							d= new DeckOfCards();
							d.shuffle();
						}
					}
					//Display their total money, disable btnBet, enable btnDeak
					lblChips.setText("$"+totalMoney);
					btnBet.setDisable(false);
					btnDeal.setDisable(true);
					btnDeal.setText("DEAL");
					lblResult.setText("Place BET to begin!");
				}
			});

			//Initializing a 2D array of labels to hold the cards
			lblCards = new Label[3][2]; 
			//Initializing each label in the 2D array and setting its graphic to a corresponding card object
			for(int i =0; i< lblCards.length;i++){
				for(int j =0; j<lblCards[i].length;j++){
					lblCards[i][j] = new Label();
					lblCards[i][j].setGraphic(c[i][j].getNode());
				}
			}
			//Adding all the cards to the stack pane
			yPos = 175;	
			for(int j =0; j<3;j++){
				StackPane.setMargin(lblCards[j][0], new Insets(yPos, 0, 0, 250));
				yPos += 75;
			}
			yPos = 175;	
			for(int j =0; j<3;j++){
				StackPane.setMargin(lblCards[j][1], new Insets(yPos, 0, 0, 600));
				yPos += 75;
			}
				btnQuit.setOnAction(e->{
					// declare and init alert of confirmation type
					Alert alertExit = new Alert(AlertType.CONFIRMATION);
					// set properties of alert...
					alertExit.setContentText("Are you sure you want to exit?");
					alertExit.setTitle("Exit");
					alertExit.setHeaderText(null);
					alertExit.getButtonTypes().clear();
					// add yes or no option...
					alertExit.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
					Optional<ButtonType> result = alertExit.showAndWait();
					// if user selected no, close alert
					if (result.get() == ButtonType.NO) {
						e.consume();
					} else if (result.get() == ButtonType.YES) {
						Alert alertMessage = new Alert(AlertType.INFORMATION);
						// set properties of alert...
						alertMessage.setContentText("Thank you for playing Baccarat!");
						alertMessage.setTitle("Baccarat");
						alertMessage.setHeaderText(null);
						// show alert and exit
						alertMessage.showAndWait();
						System.exit(0);
					}
				});
			//Declaring and initializing a StackPane and setting its properties and adding all the components
			StackPane root = new StackPane();
			root.setPadding(new Insets(0, 0, 0, 0));
			root.setAlignment(Pos.TOP_LEFT);
			StackPane.setMargin(iviewTitle, new Insets(0, 0, 0, SCENE_WIDTH / 2 - imgTitle.getWidth() / 2));
			StackPane.setMargin(lblChips, new Insets(175, 0, 0, SCENE_WIDTH / 2 - lblChips.getPrefWidth() / 2));
			StackPane.setMargin(lblPlayer, new Insets(540, 0, 0, 250));
			StackPane.setMargin(lblBanker, new Insets(540, 0, 0, 600));
			StackPane.setMargin(lblPTotal, new Insets(580, 0, 0, 250));
			StackPane.setMargin(lblBTotal, new Insets(580, 0, 0, 600));
			StackPane.setMargin(lblResult, new Insets(495, 0, 0, SCENE_WIDTH / 2 - lblResult.getPrefWidth() / 2));
			StackPane.setMargin(txtBet, new Insets(250, 0, 0, SCENE_WIDTH / 2 - txtBet.getPrefWidth() / 2));
			StackPane.setMargin(btnBet, new Insets(310, 0, 0, SCENE_WIDTH / 2 - btnBet.getPrefWidth() / 2));
			StackPane.setMargin(btnDeal, new Insets(370, 0, 0, SCENE_WIDTH / 2 - btnDeal.getPrefWidth() / 2));
			StackPane.setMargin(btnQuit, new Insets(430, 0, 0, SCENE_WIDTH / 2 - btnQuit.getPrefWidth() / 2));
			root.getChildren().addAll(iviewBack, iviewTitle, lblChips, lblPlayer, lblBanker, lblPTotal,
					lblBTotal, lblResult, txtBet,btnBet, btnDeal, btnQuit, lblCards[0][0], lblCards[1][0], 
					lblCards[2][0],lblCards[0][1], lblCards[1][1], lblCards[2][1]);
			//Declaring and initializing the scene
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Setting the Stage to not be resizable
			primaryStage.setResizable(false);
			//Using an event handler to set an alert for when the user tries to exit the program
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent e) {
					//Declaring and initializing alertExit, a confirmation box
					Alert alertExit = new Alert(AlertType.CONFIRMATION);
					//Setting the properties of alertExit
					alertExit.setContentText("Are you sure you want to exit?");
					alertExit.setTitle("Exit");
					alertExit.setHeaderText(null);
					alertExit.getButtonTypes().clear();
					//Adding yes or no options
					alertExit.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
					//Creating a ButtonType
					Optional<ButtonType> result = alertExit.showAndWait();
					//If the user chose no
					if (result.get() == ButtonType.NO)
					{
						//Close the alertExit
						e.consume();
					}
					//If the user chose yes
					else if(result.get()== ButtonType.YES){
						//Close the program
						System.exit(0);
					}
				}
			});
			//Setting the title of the stage, setting the scene, and showing the stage
			primaryStage.setTitle("Baccarat");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Method to deal the first 2 cards for both player and banker
	private void dealCard(){
		//Deal the first 2 cards for both player and banker & set the graphic of the corresponding label 
		for(int i =0; i<c.length-1;i++) {
			for(int j =0; j< c[i].length;j++) {
				c[i][j] = d.dealCard();
				lblCards[i][j].setGraphic(c[i][j].getNode());
			}
		}
		//Set the points to 0
		pPoints=0;
		bPoints=0;
		//Calculate the player's points and banker's points
		for(int i =0; i<c.length;i++) {
			pPoints+=c[i][0].getValue();
			bPoints +=c[i][1].getValue();
		}
		//Display their total points
		lblPTotal.setText(pPoints+"");
		lblBTotal.setText(bPoints+"");
	}
	//Method to check if the player should draw a third card
	private void checkDraw() {
		//If either player or banker have 8 or 9 points, they both must stand
		if(pPoints%10 == 9 || pPoints%10==8 ||bPoints%10 == 9||bPoints%10==8) {
			pstand=true;
			bstand =true;
		}
		//If the player gets 6 or 7, the player stands
		else if(pPoints%10 ==6 || pPoints%10 ==7) {
			pstand =true;
			//If the banker gets 5or less, the banker draws a third card
			if(bPoints%10<=5) {bstand =false;	}
			//Otherwise the banker stands as well
			else bstand =true;
		}
		//If the player has less than 5 points
		else if(pPoints %10 <=5) {
			//None of them stand yet
			pstand =false;
			bstand =false;
		}
		//If the player doesn'stand, change the text of btnDeal to "DRAW CARD"
		if (pstand ==false ) {
			btnDeal.setText("DRAW CARD");
		}
		//If the player stands, disable the btnDeal
		else btnDeal.setDisable(true);
	}
	//Method to draw a third card for the player
	private void drawPCard() {
		//Reset the player's points to 0
		pPoints=0;
		//Disable the btnDeal now and reset its text
		btnDeal.setDisable(true);
		btnDeal.setText("DEAL");
		//Deal a third card & set the graphic of the corresponding to the label
		c[2][0] = d.dealCard();
		lblCards[2][0].setGraphic(c[2][0].getNode());
		//Recalculate pPoints (without doing %10 yet)
		for(int i =0 ; i< c.length;i++) {
			pPoints+=c[i][0].getValue();
		}
		//If the banker's points are 0-2, they don't stand
		if(bPoints %10 ==0 || bPoints %10 ==1||  bPoints% 10==2) {
			bstand = false;
		}
		//If banker has 3 points while player has 0-7 or 9 after drawing; banker does not stand
		else if(bPoints%10 == 3 && pPoints%10 !=8) bstand = false;
		//else if banker has 4 points and player has 2-7 after drawing, banker does not stand
		else if(bPoints%10 == 4 && pPoints%10 >=2 && pPoints%10 <=7) bstand =false;
		//else if banker has 5 points and player has 4-7 after drawing, banker does not stand
		else if(bPoints%10 == 5 &&pPoints%10 >=4 && pPoints %10<=7)  bstand = false;
		//else if banker has 6 poitns and player has 6-7, banker does not stand
		else if(bPoints%10 == 6 && pPoints%10>=6 && pPoints%10 <=7) 	bstand =false;
		//In other cases, banker stands
		else bstand = true;

	}
	public static void main(String[] args) {
		launch(args);
	}
}