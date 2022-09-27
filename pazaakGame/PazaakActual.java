package pazaakGame;

import javafx.scene.*;

import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import pazaakMain.Card;
import javafx.scene.control.Label;

public class PazaakActual extends PazaakGameController {
	private int range = 9;

	ArrayList<Node> handPlayer = handCards;
	ArrayList<Node> usedCards = new ArrayList<Node>();
	ArrayList<Card> compHand = new ArrayList<Card>();

	private int score = 0;
	private int compScore = 0;
	private int roundCount = 0;
	private int compRoundCount = 0;
	PazaakAI AI = new PazaakAI(compHand, compScore, roundCount, compRoundCount);
	private boolean isStand = false;
	
    @FXML
    private Button backButton;
	@FXML
	private GridPane cardsPlayed;

	@FXML
	private Label currentPoints;
	@FXML
	private GridPane playerHand;

	@FXML
	private Button standButton;
	@FXML
	private Button endTurn;
	@FXML
	private Label opponentPoints;

	@FXML
	private GridPane computerCardsPlayed;

	@FXML
	private Label gameStatus;
    @FXML
    private GridPane computerRoundCounter;
    @FXML
    private GridPane roundCounter;
    @FXML
    private Button clearButton;

	public void initialize() {
		setFirstTurn();
		generateCompDeck();

	}

	public void setFirstTurn() { // To generate the first turn

		for (int i = 0; i < 4; i++) {

			int rand = (int) (Math.random() * range);
			playerHand.add(handPlayer.get(rand), i, 0);
			handPlayer.remove(rand);

			range -= 1;
			playerHand.getChildren().forEach(this::makedeckClickable);

		}
		hit();

	}

	public void makedeckClickable(Node node) {

		node.setOnMousePressed(e -> {

			for (int row = 0; row <= 2; row++) {
				for (int col = 0; col <= 2; col++) {

					if (getNodeByCoordinate(cardsPlayed, row, col) == null) {
						cardsPlayed.add(node, col, row);
						score += ((Card) node).getValue();
						currentPoints.setText(String.valueOf(score));
						cardsPlayed.getChildren().forEach(this::makeDeckClickable);

						return;
					}
				}

			}

		});

	}

	public void makeDeckClickable(Node node) {

		node.setOnMousePressed(e -> {

			for (int col = 0; col <= 3; col++) {

				if (getNodeByCoordinate(playerHand, 0, col) == null) {

					for (int i = 0; i <= usedCards.size() - 1; i++) { // So you can't move cards in the playedCards
						if ((Card) node == usedCards.get(i)) {
							return;
						}
					}

					playerHand.add(node, col, 0);

					score -= ((Card) node).getValue();
					currentPoints.setText(String.valueOf(score));
					playerHand.getChildren().forEach(this::makedeckClickable);
					return;
				}
			}

		});

	}

	public void hit() { // For your main cards hit
		for (int row = 0; row <= 2; row++) {
			for (int col = 0; col <= 2; col++) {

				if (getNodeByCoordinate(cardsPlayed, row, col) == null) { 
					int mainRand = (int) (Math.random() * 9);
					Card mainCard = new Card(mainCards.get(mainRand).getCard(), mainCards.get(mainRand).getValue());
					usedCards.add(mainCard);
					cardsPlayed.add(mainCard, col, row);
					score += mainCards.get(mainRand).getValue();
					currentPoints.setText(String.valueOf(score));

					return;
				}
			}

		}

	}

	public void computerHit() { // For the computer getting main cards
		for (int row = 0; row <= 2; row++) {
			for (int col = 0; col <= 2; col++) {

				if (getNodeByCoordinate(computerCardsPlayed, row, col) == null) {
					int mainRand = (int) (Math.random() * 10);
					Card mainCard = new Card(mainCards.get(mainRand).getCard(), mainCards.get(mainRand).getValue());
					computerCardsPlayed.add(mainCard, col, row);
					compScore += mainCards.get(mainRand).getValue();
					AI.setPoints(compScore);
					opponentPoints.setText(String.valueOf(compScore));

					return;
				}
			}

		}
	}

	public void compPlay() { // For the AI to find whether or not it can play

		for (int row = 0; row <= 2; row++) {
			for (int col = 0; col <= 2; col++) {

				if (getNodeByCoordinate(computerCardsPlayed, row, col) == null) {
					if (AI.computerPath(score) == null) {
						AI.setCards(compHand);
						AI.setPoints(compScore);
						AI.checkPlayerStand(isStand);
						return;
					} else {
						computerCardsPlayed.add(AI.computerPath(score), col, row);
						compScore+= AI.computerPath(score).getValue();
						
						opponentPoints.setText(String.valueOf(compScore));
						compHand.remove(AI.computerPath(score));
						AI.setCards(compHand);
						AI.setPoints(compScore);
						AI.checkPlayerStand(isStand);
						return;
					}

				}
			}

		}

	}

	public void onTurnEnd() {
		
		for (int i = 0; i <= cardsPlayed.getChildren().size() - 1; i++) {
			usedCards.add(cardsPlayed.getChildren().get(i));
		}
		AI.setPoints(compScore);
		if (AI.isStand == true) { // When computer is at 20, you only hit
			
			if(score > 20) {
				onStand();
			}
			hit();
			checkWinner();
		} else if (compScore < 20 && score < 20) { // When both scores are under 20
			computerHit();
			compPlay();
			
			hit();
			checkWinner();
		}else if(score > 20 ) {
			onStand();
		}else if(compScore >= 20 && score <= 20) {
			computerHit();
			compPlay();
			
			hit();
			checkWinner();
		}
		
		

	}
	public void onStand() {
		isStand = true;
		compPlay();
		while(AI.isStand == false) {
			AI.setPoints(compScore);
			
			if(compScore == score) {
				AI.isStand = true;
			
				break;
			}
			if(score > 20) {
				AI.isStand = true;
			
				break;
			}


			
			if(compScore == 20) {
				break;
			}
			if(compScore <= 20 || compScore >= 20) {
				
			
				computerHit();
				
				compPlay();
				if(compScore > 20) {
					AI.isStand = true;
					break;
				}
			}
			
			
			
			
		}
		checkWinner();
	}

	public void generateCompDeck() { // For computer deck generation
		for (int i = 0; i <= 3; i++) {
			int rand = (int) (Math.random() * 12);
			Card compCard = new Card(starterDeck.get(rand).getCard(), starterDeck.get(rand).getValue());
			compHand.add(compCard);
			
			
		}
		
	}
	public void checkWinner() {

		if(compScore > score && isStand == true && compScore <= 20 && AI.isStand == true||  score > 20 && compScore <= 20 && isStand == true) {
			gameStatus.setText("Loser");
			endTurn.setVisible(false);
			standButton.setVisible(false);
			playerHand.setVisible(false);
			clearButton.setVisible(true);
			Circle roundPoint = new Circle();
			roundPoint.setRadius(16);
			computerRoundCounter.add(roundPoint,compRoundCount,0);
			
			compRoundCount++;
			AI.setRoundCounts(roundCount, compRoundCount);
	

			
			
		}else if(score > compScore && AI.isStand == true &&  score <= 20 && isStand == true || compScore > 20 &&  score <= 20 && AI.isStand == true) {
			gameStatus.setText("Winner");
			endTurn.setVisible(false);
			standButton.setVisible(false);
			playerHand.setVisible(false);
			clearButton.setVisible(true);
			Circle roundPoint = new Circle();
			roundPoint.setRadius(16);
			
			roundCounter.add(roundPoint,roundCount,0);
			
			roundCount++;
			AI.setRoundCounts(roundCount, compRoundCount);
			
			
	
			
		}else if (compScore == score  &&  isStand == true && AI.isStand == true || compScore > 20 && score > 20) {
			gameStatus.setText("Tie");
			endTurn.setVisible(false);
			standButton.setVisible(false);
			playerHand.setVisible(false);
			clearButton.setVisible(true);


			
		}
		if(compRoundCount == 3 || roundCount == 3) {
			if(compRoundCount == 3) {
				gameStatus.setText("Computer wins!");
				endTurn.setVisible(false);
				clearButton.setVisible(false);
				standButton.setVisible(false);
				playerHand.setVisible(false);
				backButton.setVisible(true);
				return;
			}else if(roundCount == 3) {
				gameStatus.setText("Player wins!");
				endTurn.setVisible(false);
				clearButton.setVisible(false);
				standButton.setVisible(false);
				playerHand.setVisible(false);
				backButton.setVisible(true);
				return;
			}
		}
	}
	public void resetTable(){ //So everything gets cleared
		AI.isStand = false;
		isStand = false;
			score = 0;
		compScore = 0;
		AI.setRoundCounts(roundCount, compRoundCount);
		currentPoints.setText(String.valueOf(score));
		opponentPoints.setText(String.valueOf(compScore));
		AI.setPoints(compScore);
		cardsPlayed.getChildren().clear();
		computerCardsPlayed.getChildren().clear();
		endTurn.setVisible(true);
		standButton.setVisible(true);
		playerHand.setVisible(true);
		clearButton.setVisible(false);
		gameStatus.setText("");
		hit();

		
		
	}
	public void backButtonPressed(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pazaakMain/PazaakCard.fxml"));
		Stage window = (Stage) backButton.getScene().getWindow();
		window.setScene(new Scene(root));
		
	}

}
