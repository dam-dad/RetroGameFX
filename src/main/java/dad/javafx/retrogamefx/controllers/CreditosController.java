package dad.javafx.retrogamefx.controllers;

import java.io.IOException;

import dad.javafx.retrogamefx.base.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class CreditosController {
	

	

	    @FXML
	    private GridPane View;

	    @FXML
	    private Button Volver;

	    @FXML
	    void onClickReturn(ActionEvent event) {
	    	App.gotToMain();
	    }
	    
	    public GridPane getView() {
			return View;
		}
	    public CreditosController() throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Creditos.fxml"));
			loader.setController(this);
			loader.load();
			View.setFocusTraversable(true);
			View.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
				if (key.getCode() == KeyCode.ESCAPE) {
					App.gotToMain();
				}
				});
			
		}

	

	
	

}
