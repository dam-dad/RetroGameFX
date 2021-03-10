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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MainController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private Button playButton;

	@FXML
	private Button optionsButton;

	@FXML
	private Button exitButton;

	@FXML
	private Button creditsButton;

	@FXML
	private Text titleText;
	
	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		view.setFocusTraversable(true);
		view.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			switch (key.getCode()) {
			case S:
			case ESCAPE:	onClickExit(null); break;
			case J: 		onClickPlay(null); break;
			case O: 		onClickOptions(null); break;
			case C: 		onClickCredits(null); break;
			default:
			}
		});
	}

	@FXML
	void onClickCredits(ActionEvent event) {
		App.goToCredits();
	}

	@FXML
	void onClickPlay(ActionEvent event) {
		App.goToChooseGame();
	}

	@FXML
	void onClickOptions(ActionEvent event) {
		App.goToSettings();
	}

	@FXML
	void onClickExit(ActionEvent event) {
		App.primaryStage.close();
	}

	public GridPane getView() {
		return view;
	}

}
