package dad.javafx.retrogamefx.controllers;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import dad.javafx.retrogamefx.base.App;
import dad.javafx.retrogamefx.games.brickbreaker.BrickBreaker;
import dad.javafx.retrogamefx.games.pong.Pong;
import dad.javafx.retrogamefx.games.snake.SnakeGame;
import dad.javafx.retrogamesfx.games.laberinto.Laberinto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ChooseGameController implements Initializable {
	Boolean comprar1=false,comprar2=false,comprar3=false,comprar4=false;

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
    private Text puntos;
    
    private IntegerProperty Puntos = new SimpleIntegerProperty(0);
    public final IntegerProperty puntosProperty() {
		return this.Puntos;
	}
	public ChooseGameController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChooseGameView.fxml"));
		loader.setController(this);
		loader.load();
		
	}
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		puntos.textProperty().bind(puntosProperty().asString());
		puntosProperty().setValue(5);
	}
	
    @FXML
    void onComprar1Action(ActionEvent event) {
    	if(!comprar1) {
    	puntosProperty().setValue(puntosProperty().getValue()-1);
    	comprar1=true;}
    }

    @FXML
    void onComprar2Action(ActionEvent event) {
    	if(!comprar2) {
        	puntosProperty().setValue(puntosProperty().getValue()-1);
        	comprar2=true;
        	}
    }

    @FXML
    void onComprar3Action(ActionEvent event) {
    	if(!comprar3) {
        	puntosProperty().setValue(puntosProperty().getValue()-1);
        	comprar3=true;
        	}
    }

    @FXML
    void onComprar4Action(ActionEvent event) {
    	if(!comprar4) {
        	puntosProperty().setValue(puntosProperty().getValue()-1);
        	comprar4=true;
        	}
    }

    @FXML
    void onJugar1Action(ActionEvent event) {
    	Pong game = new Pong();
    	App.playGame(game);
    	game.play();
    }

    @FXML
    void onJugar2Action(ActionEvent event) {
    	BrickBreaker game = new BrickBreaker();
    	App.playGame(game);
    	game.play();
    }

    @FXML
    void onJugar3Action(ActionEvent event) {
    	SnakeGame game=new SnakeGame();
    	App.playGame(game);
    	game.play();
    }

    @FXML
    void onJugar4Action(ActionEvent event) {
    	Laberinto game =new Laberinto();
    	App.playGame(game);
    	game.play();
    }

    @FXML
    void onVolverAction(ActionEvent event) {
    	App.gotToMain();
    }
	
	public GridPane getView() {
		return View;
	}


}
