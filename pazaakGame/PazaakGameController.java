package pazaakGame;

import pazaakMain.Cards;
import pazaakMain.Card;
import java.util.ArrayList;

import javafx.scene.*;
import javafx.scene.image.Image;
import java.io.File;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.*;

public class PazaakGameController {

	Cards cardClass = new Cards();

	final ArrayList<Integer> cardCount = cardClass.getCardCount(); // For the generation of all cards (A lot unused)
	final ArrayList<Card> plusCards = cardClass.plusCardDeck();

	FileChooser fileChooser = new FileChooser();
	final ArrayList<Card> minusCards = cardClass.minusCardDeck();
	final ArrayList<Card> mainCards = cardClass.mainCards();
	final ArrayList<Card> allCards = cardClass.allCards();
	ArrayList<Card> starterDeck = cardClass.starterDeck();
	ArrayList<Card> playerCards = cardClass.playerDeck();
	ArrayList<Card> loadedCards = new ArrayList<Card>();
	static ArrayList<Node> handCards = new ArrayList<Node>();

	@FXML
	private GridPane playerDeck;
	@FXML
	private GridPane playerCardHand;
	@FXML
	private Button startButton;
	@FXML
	private Label warningLabel;
	@FXML
	private Pane deckChooser;
	@FXML
	private Button loadDeck;

	@FXML
	public void initialize() {
		handCards = new ArrayList<Node>();
	}

	@FXML
	private void onStartPressed(ActionEvent event) throws Exception { // For when you click start to open next scene

		if (playerCardHand.getChildren().size() < 10) {
			warningLabel.setText("Please add more cards");
			return;
		}
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pazaakMain/PazaakGame.fxml"));
		Stage window = (Stage) startButton.getScene().getWindow();
		window.setScene(new Scene(root));

	}

	@FXML
	private void onLoadPress(ActionEvent event) throws Exception { //To load deck files
		if (playerDeck.getChildren().isEmpty() == false) {
			playerDeck.getChildren().clear();
		}
		Stage stage = (Stage) deckChooser.getScene().getWindow();
		fileChooser.setInitialDirectory(new File("MyCardDecks"));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Deck files", "*.deck"));
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			FileInputStream files = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(files);
			boolean keepReading = true;
			try {
				while (keepReading) {

					Card card = new Card(new Image(getClass().getResource(ois.readUTF()).toExternalForm()),
							(int) ois.readObject());
					loadedCards.add(card);

				}

			} catch (EOFException ex) {
				keepReading = false;
			}

			for (int i = 0; i <= loadedCards.size() - 1; i++) {
				Card card = loadedCards.get(i);

				for (int x = 0; x <= cardCount.size() - 1; x++) {
					if (cardCount.get(x) == 2) {
						Card copyCard = new Card(loadedCards.get(i).getCard(), loadedCards.get(i).getValue());

						setCardPos(copyCard);

						playerDeck.getChildren().forEach(this::makedeckClickable);

						break;
					}
				}

				setCardPos(card);
				makedeckClickable(card);
				playerDeck.getChildren().forEach(this::makedeckClickable);
			}

			ois.close();

		}

	}

	public void makedeckClickable(Node node) { // To make everything clickable

		node.setOnMousePressed(e -> {

			for (int row = 0; row <= 4; row++) {
				for (int col = 0; col <= 1; col++) {

					if (getNodeByCoordinate(playerCardHand, row, col) == null) {

						playerCardHand.add(node, col, row);
						playerCardHand.getChildren().forEach(this::makeDeckClickable);
						handCards.add(node);

						return;
					}
				}

			}

		});

	}

	public void makeDeckClickable(Node node) {

		node.setOnMousePressed(e -> {

			handCards.remove(node);
			int value = ((Card) node).getValue();
			switch (value) {
			case -6:
				playerDeck.add(node, 1, 5);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case -5:
				playerDeck.add(node, 1, 4);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case -4:
				playerDeck.add(node, 1, 3);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case -3:
				playerDeck.add(node, 1, 2);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case -2:
				playerDeck.add(node, 1, 1);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case -1:
				playerDeck.add(node, 1, 0);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case 1:
				playerDeck.add(node, 0, 0);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case 2:
				playerDeck.add(node, 0, 1);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case 3:
				playerDeck.add(node, 0, 2);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case 4:
				playerDeck.add(node, 0, 3);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case 5:
				playerDeck.add(node, 0, 4);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;
			case 6:
				playerDeck.add(node, 0, 5);
				playerDeck.getChildren().forEach(this::makedeckClickable);
				return;

			}

			return;

		});

	}

	public Node getNodeByCoordinate(GridPane gridPane, final Integer row, final Integer column) { // For the purpose of
																									// filling
		// in the gridpane wherever
		// it's empty
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return node;
			}
		}
		return null;
	}

	public void setCardPos(Card card) { //For deck generation on left hand side
		int value = card.getValue();
		switch (value) {
		case -6:
			playerDeck.add(card, 1, 5);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case -5:
			playerDeck.add(card, 1, 4);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case -4:
			playerDeck.add(card, 1, 3);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case -3:
			playerDeck.add(card, 1, 2);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case -2:
			playerDeck.add(card, 1, 1);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case -1:
			playerDeck.add(card, 1, 0);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case 1:
			playerDeck.add(card, 0, 0);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case 2:
			playerDeck.add(card, 0, 1);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case 3:
			playerDeck.add(card, 0, 2);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case 4:
			playerDeck.add(card, 0, 3);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case 5:
			playerDeck.add(card, 0, 4);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;
		case 6:
			playerDeck.add(card, 0, 5);
			playerDeck.getChildren().forEach(this::makedeckClickable);
			return;

		}
	}

}
