/**
 * Class name: PazaakMain
 * @author elija
 * Purpose: To create the application
 */
package pazaakMain;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PazaakMain extends Application{
	Cards cardClass = new Cards();
	final ArrayList<Integer> cardCount = cardClass.getCardCount();
	final ArrayList<Card> plusCards = cardClass.plusCardDeck();
	final ArrayList<Card> minusCards = cardClass.minusCardDeck();
	final ArrayList<Card> starterDeck = cardClass.starterDeck();

	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Pazaak.fxml"));
		Scene scene = new Scene(root); // attach scene graph to scene
		stage.setTitle("Pazaak"); // displayed in window's title bar
		stage.setScene(scene); // attach scene to stage
		stage.show(); // display the stage
		
	}

	public static void main(String[] args) {
		// create the car payment calculator
		launch(args);
	}
}
