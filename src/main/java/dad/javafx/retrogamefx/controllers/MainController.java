package dad.javafx.retrogamefx.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

import dad.javafx.retrogamefx.base.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {

    
	@FXML
	GridPane View;
	
	@FXML
	Button Jugar;
	
	@FXML
	Button Opciones;
	
	@FXML
	Button Salir;
	
	@FXML
	Button Creditos;
	
	@FXML
	Text Titulo;

	
    @FXML
    void onClickCreditos(ActionEvent event) {
    	App.goToCredits();
    }

    @FXML
    void onClickJugar(ActionEvent event) throws IOException {
		App.goToChooseGame();
		
    }

    @FXML
    void onClickOpciones(ActionEvent event) {
    	App.goToSettings();
    }

    @FXML
    void onClickSalir(ActionEvent event) {
    	Stage stage = (Stage) Salir.getScene().getWindow();
        stage.close();
    }
	public GridPane getView() {
		return View;
	}
	
	public MainController() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
	loader.setController(this);
	loader.load();
	View.setFocusTraversable(true);
	View.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
		if (key.getCode() == KeyCode.ESCAPE) {
			onClickSalir(null);
		}
		if (key.getCode() == KeyCode.J) {
			try{onClickJugar(null);}catch(Exception e){}
		}
		if (key.getCode() == KeyCode.O) {
			try{onClickOpciones(null);}catch(Exception e){}
		}
		if (key.getCode() == KeyCode.C) {
			try{onClickOpciones(null);}catch(Exception e){}
		}
		if (key.getCode() == KeyCode.S) {
			onClickSalir(null);
		}
		});
	
}
}
