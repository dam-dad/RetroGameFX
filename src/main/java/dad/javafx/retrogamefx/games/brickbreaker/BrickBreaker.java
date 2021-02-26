package dad.javafx.retrogamefx.games.brickbreaker;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import dad.javafx.retrogamefx.games.GameScene;
import dad.javafx.retrogamefx.games.pong.Background;
import dad.javafx.retrogamefx.games.pong.Ball;
import dad.javafx.retrogamefx.games.pong.Player;
import dad.javafx.retrogamefx.games.pong.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BrickBreaker extends GameScene{
	
	private static final double BALL_R = 20;
	private boolean gameStarted;
	
	private Player player;
	private Background background;
	private Ball ball;
	private Wall topWall, leftWall, rightWall;
    @FXML
    private StackPane view;

    @FXML
    private Canvas canvas;

    @FXML
    private BorderPane hudPane;

    @FXML
    private Label player1ScoreLabel;
	
	public BrickBreaker() {
		super("/fxml/BrickBreaker.fxml", 800, 600);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());
		
		ball = new Ball();
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		ball.setRadio(BALL_R);
		
		player = new Player(Color.RED);
		player.setBounds(getWidth()/2, getHeight()-10, 200, 10);
		
		canvas.setOnMouseMoved(e -> player.setX(e.getX() - player.getWidth() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);
		
		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
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
		ball.render(gc);
		player.render(gc);

	}
private void collision() {
		
		// aumenta la velocidad despues de chocar y cambio de dirreccion
		ball.checkCollision(player);
		ball.checkCollision(topWall);
		ball.checkCollision(leftWall);
		ball.checkCollision(rightWall);
	//	ball.checkCollision(brick);              //Implementar Bricks		
	}
private void update(double diff) {

		if (gameStarted) {
			ball.update(diff);
			player.update(diff);
			// Punto para player 
			//			if (ball.getX()) {
			//				player.setScore(player.getScore() + 1);
			//				gameStarted = false;
					//	}
		}else {
			// texto inicio

			// Reset posicion pelota al inicio
			ball.setX(getWidth() / 2);
			ball.setY(getWidth() / 2);
			ball.setDirection(new Point2D(1.0, -1.0));			
			if(new Random().nextBoolean()){
				ball.setSpeed(ball.getSpeed() * -1);
			}
		}
	}
}
