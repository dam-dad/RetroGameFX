package dad.javafx.retrogamefx.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.base.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SelectViewController implements Initializable {
	

    @FXML
    private GridPane View;
    
    @FXML
    private Button volverButton;

    @FXML
    private Button jugar3Button;

    @FXML
    private Button comprar3Button;

    @FXML
    private Button jugar2Button;

    @FXML
    private Button comprar2Button;

    @FXML
    private Button comprar1Button;

    @FXML
    private Button jugar1Button;

    @FXML
    private Button comprar4Button;

    @FXML
    private Button jugar4Button;

    @FXML
    private Text puntosText;

    @FXML
    void onComprar1Action(ActionEvent event) {

    }

    @FXML
    void onComprar2Action(ActionEvent event) {

    }

    @FXML
    void onComprar3Action(ActionEvent event) {

    }

    @FXML
    void onComprar4Action(ActionEvent event) {

    }

    @FXML
    void onJugar1Action(ActionEvent event) {

    }

    @FXML
    void onJugar2Action(ActionEvent event) {

    }

    @FXML
    void onJugar3Action(ActionEvent event) {

    }

    @FXML
    void onJugar4Action(ActionEvent event) {

    }

    @FXML
    void onVolverAction(ActionEvent event) {
    	App.CambiarAMain();
    }

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public GridPane getView() {
		return View;
	}
	public SelectViewController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SelectView.fxml"));
		loader.setController(this);
		loader.load();
		
	}

}
