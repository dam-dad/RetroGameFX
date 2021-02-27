package dad.javafx.retrogamefx.snake;

import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.games.GameScene;
import dad.javafx.retrogamefx.games.pong.Background;
import dad.javafx.retrogamefx.games.pong.Player;
import dad.javafx.retrogamefx.games.pong.Wall;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class SnakeGame extends GameScene{

	private Snake snake;
	private Background background;
	private Wall topWall, bottomWall, leftWall, rightWall;
	
	//view
	@FXML
    private StackPane view;

    @FXML
    private Canvas canvas;

    @FXML
    private BorderPane hudPane;

    @FXML
    private Label player1ScoreLabel;
    
    
	public SnakeGame() {
		super("/fxml/Snake.fxml", 800, 600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());
		//Muros horizontales
		topWall = new Wall();
		topWall.setBounds(0, 0, getWidth(), 10);
		
		bottomWall = new Wall();
		bottomWall.setBounds(0, getHeight(), getWidth(), getHeight() + 10);
		
		//-------------------------------------------------------------
		//Arreglar muros laterales
		rightWall = new Wall();
		rightWall.setBounds(getWidth(), 0, getWidth()+10, getHeight());
		
		leftWall = new Wall();
		leftWall.setBounds(-10, 0, 10, getHeight());
		//-------------------------------------------------------------
		
		snake = new Snake(Color.GREEN);
		snake.setBounds(getWidth()/2, getHeight()-10, 200, 10);
		
		player1ScoreLabel.textProperty().bind(snake.scoreProperty().asString());
	}

	@Override
	protected void gameLoop(double diff) {
		
		// actualizar elementos del juego
		update(diff);
		
		// detectar colisiones
		collision();
		
		// renderizado (pintar)
		render(canvas.getGraphicsContext2D());
	}


	private void render(GraphicsContext gc) {

		background.render(gc);
		snake.render(gc);

	}
	private void collision() {
		
		
	}

	private void update(double diff) {
		
		
	}

}
