package dad.javafx.retrogamesfx.games.pong;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamesfx.base.App;
import dad.javafx.retrogamesfx.games.Background;
import dad.javafx.retrogamesfx.games.World;
import dad.javafx.retrogamesfx.games.Player;
import dad.javafx.retrogamesfx.games.Wall;
import dad.javafx.retrogamesfx.reports.HallOfFame;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Pong extends World {

	// variables (objetos)
	private static final double BALL_R = 20;
	private boolean gameStarted;

	// model
	private String scoreWS = "no acabaste la partida";
	private HumanRacket human;
	private HumanRacket ia;
	private Background<Pong> background;
	private Ball ball;
	private Wall<Pong> topWall, bottomWall;
	private GraphicsContext gc;

	// view

	@FXML
	private Label player1ScoreLabel;

	@FXML
	private Label player2ScoreLabel;

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;

	public Pong() {
		super("Pong", "/fxml/Pong.fxml", 800, 600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gc = canvas.getGraphicsContext2D();

		background = new Background<>(this, Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());

		ball = new Ball(this);
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		ball.setRadio(BALL_R);

		topWall = new Wall<>(this, true);
		topWall.setBounds(0, -10, getWidth(), 10);

		bottomWall = new Wall<>(this, true);
		bottomWall.setBounds(0, getHeight(), getWidth(), getHeight() + 10);

		human = new HumanRacket(this, Color.RED);
		human.setBounds(0, getHeight() / 2, 10, 200);

		ia = new IARacket(this, Color.AQUA, ball);
		ia.setBounds(getWidth() - 10, getHeight() / 2, 10, 200);

		player1ScoreLabel.textProperty().bind(human.scoreProperty().asString());
		player2ScoreLabel.textProperty().bind(ia.scoreProperty().asString());

		// control de raton

		canvas.setOnMouseMoved(e -> human.setY(e.getY() - human.getHeight() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);
		canvas.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				HallOfFame.Pongscore = scoreWS;
				App.goToChooseGame();
			}
			if (key.getCode() == KeyCode.ENTER) {
				human.setScore(0);
				ia.setScore(0);
				gameStarted = false;
			}
		});
	}

	@Override
	protected void gameLoop(double diff) {
		
		Player<Pong> winner = null;
		if ((winner = isThereAWinner()) != null) {
			
			if (winner == human) {
				scoreWS = "You win!!!";
				gc.setFill(Color.LIGHTGREEN);
				gc.setFont(Font.font(70));
				gc.fillText("WIN", 175, 300);
			} else {
				scoreWS = "You lose";
				gc.setFill(Color.RED);
				gc.setFont(Font.font(50));
				gc.fillText("Game Over", 175, 300);
			}
			gc.setFill(Color.YELLOWGREEN);
			gc.setFont(Font.font(18));
			gc.fillText("Press Enter to play", 225, 400);
			
		} else {
			
			// actualizar elementos del juego
			update(diff);

			// detectar colisiones
			collision();

			// renderizado (pintar)
			render(gc);
		}

	}

	private Player<Pong> isThereAWinner() {
		if (human.getScore() == 5) {
			return human;
		} 
		if (ia.getScore() == 5) {
			return ia;
		}
		return null;
	}

	private void render(GraphicsContext gc) {

		background.render(gc);
		ball.render(gc);
		human.render(gc);
		ia.render(gc);

	}

	// aumenta la velocidad despues de chocar y cambio de direccion
	private void collision() {

		// el rebote dentro de la pelota va muy a ratos
		if (ball.getY() > 0) {
			ball.setDirection(new Point2D(ball.getDirection().getX(), -ball.getDirection().getY()));
		}

		if (ball.getY() < getHeight()) {
			ball.setDirection(new Point2D(ball.getDirection().getX(), -ball.getDirection().getY()));
		}

		ball.checkCollision(human);
		ball.checkCollision(ia);
		ball.checkCollision(topWall);
		ball.checkCollision(bottomWall);

	}

	private void update(double diff) {

		if (gameStarted) {

			ball.update(diff);
			ia.update(diff);
			human.update(diff);

			// Punto para player (pelota se sale por la derecha)
			if (ball.getX() > getWidth()) {
				human.increaseScore(1);
				gameStarted = false;
			}

			// Punto para cpu (pelota se sale por la izquierda)
			if (ball.getX() < 0) {
				ia.increaseScore(1);
				gameStarted = false;
			}

		} else {

			resetGame();

		}

	}

	private void resetGame() {
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		ball.setSpeed(ball.getSpeed());
		if (new Random().nextBoolean()) {
			ball.setDirection(new Point2D(1.0, -1.0));
		} else {
			ball.setDirection(new Point2D(-1.0, -1.0));
		}
	}

}
