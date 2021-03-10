package dad.javafx.retrogamesfx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.retrogamesfx.base.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class CreditsController implements Initializable {

	@FXML
	private GridPane View;

	@FXML
	private Button Volver;

	@FXML
	void onClickReturn(ActionEvent event) {
		App.goToMain();
	}

	public GridPane getView() {
		return View;
	}

	public CreditsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Credits.fxml"));
		loader.setController(this);
		loader.load();

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		View.setFocusTraversable(true);
		View.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				App.goToMain();
			}
		});		
	}

}
