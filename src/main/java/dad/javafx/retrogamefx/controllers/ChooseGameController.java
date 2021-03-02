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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    private Pane Brick;
    
    @FXML
    private Pane Pong;
    
    @FXML
    private Pane Snake;
    
    @FXML
    private Pane Lab;
    
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
		View.setFocusTraversable(true);
		View.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				App.gotToMain();
			}
			if (key.getCode() == KeyCode.NUMPAD1) {
				onJugar1Action(null);
			}
			if (key.getCode() == KeyCode.NUMPAD2) {
				onJugar2Action(null);
			}
			if (key.getCode() == KeyCode.NUMPAD3) {
				onJugar3Action(null);
			}
			if (key.getCode() == KeyCode.NUMPAD4) {
				onJugar4Action(null);
			}
			if (key.getCode() == KeyCode.DIGIT1) {
				onComprar1Action(null);
			}
			if (key.getCode() == KeyCode.DIGIT2) {
				onComprar2Action(null);
			}
			if (key.getCode() == KeyCode.DIGIT3) {
				onComprar3Action(null);
			}
			if (key.getCode() == KeyCode.DIGIT4) {
				onComprar4Action(null);
			}
			});
		
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
    	comprar1=true;
    	Pong.setStyle("-fx-background-image:url(./images/Pong.PNG);-fx-background-size:200px,200px;-fx-background-size:cover,contain;");
    	
    	}
    }

    @FXML
    void onComprar2Action(ActionEvent event) {
    	if(!comprar2) {
        	puntosProperty().setValue(puntosProperty().getValue()-1);
        	comprar2=true;
        	Brick.setStyle("-fx-background-image:url(./images/BrickBreaker.PNG);-fx-background-size:200px,200px;-fx-background-size:cover,contain;");
        	}
    }

    @FXML
    void onComprar3Action(ActionEvent event) {
    	if(!comprar3) {
        	puntosProperty().setValue(puntosProperty().getValue()-1);
        	comprar3=true;
        	Snake.setStyle("-fx-background-image:url(./images/Snake.PNG);-fx-background-size:200px,200px;-fx-background-size:cover,contain;");
        	}
    }

    @FXML
    void onComprar4Action(ActionEvent event) {
    	if(!comprar4) {
        	puntosProperty().setValue(puntosProperty().getValue()-1);
        	comprar4=true;
        	Lab.setStyle("-fx-background-image:url(./images/Laberinto.PNG);-fx-background-size:200px,200px;-fx-background-size:cover,contain;");
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
