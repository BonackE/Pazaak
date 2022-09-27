/**
 * Class name: PazaakMainController
 * @author Elijah Bonack
 * Purpose: To open up next scene on button press
 */
package pazaakMain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;


public class PazaakMainController {
	
	
    @FXML
    private Button start;


    @FXML
    private void onStartPressed (ActionEvent event) throws Exception{


    		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("pazaakMain/PazaakCard.fxml"));
    		Stage window = (Stage) start.getScene().getWindow();
    		window.setScene(new Scene(root));
    		

    	
    	}
    @FXML
    private void onExitButtonPressed(ActionEvent event) {
    	System.exit(0);
    }
    }

