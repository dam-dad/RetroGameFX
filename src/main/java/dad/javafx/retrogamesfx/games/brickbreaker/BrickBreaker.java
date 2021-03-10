package dad.javafx.retrogamesfx.games.brickbreaker;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import dad.javafx.retrogamesfx.base.App;
import dad.javafx.retrogamesfx.games.Background;
import dad.javafx.retrogamesfx.games.Player;
import dad.javafx.retrogamesfx.games.Wall;
import dad.javafx.retrogamesfx.games.World;
import dad.javafx.retrogamesfx.reports.HallOfFame;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BrickBreaker extends World {

	private static final double BALL_R = 15;
	private boolean gameStarted;

	private Player<BrickBreaker> player;
	private Background<BrickBreaker> background;
	private Ball ball;
	private Wall<BrickBreaker> topWall;
	private Wall<BrickBreaker> leftWall, rightWall;
	
	static int maxFilas = 10;
	static int maxColumnas = 20;
	static int anchoBloque = 40;
	static int altoBloque = 30;
	
	GraphicsContext gc;

	// view

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;

	@FXML
	private BorderPane hudPane;

	@FXML
	private Label player1ScoreLabel;

	@FXML
	private Label player1LivesLabel;

	private ArrayList<Brick> bricks;

	public BrickBreaker() {
		super("Brick Breaker", "/fxml/BrickBreaker.fxml", 800, 600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();

		ball = new Ball(this);

		bricks = new ArrayList<Brick>();

		background = new Background<>(this, Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());

		topWall = new Wall<>(this, true);
		topWall.setBounds(0, -10, getWidth(), 10);

		rightWall = new Wall<>(this, false);
		rightWall.setBounds(getWidth(), 0, getWidth() + 10, getHeight());

		leftWall = new Wall<>(this, false);
		leftWall.setBounds(-10, 0, 10, getHeight());

		player = new Player<>(this, Color.RED);
		player.setBounds(getWidth() / 2, getHeight() - 10, 200, 10);
		player.scoreProperty().setValue(0);
		player.livesProperty().setValue(5);

		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player1LivesLabel.textProperty().bind(player.livesProperty().asString());
		BricksPack();

		// control de raton
		canvas.setOnMouseMoved(e -> player.setX(e.getX() - player.getWidth() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);
		canvas.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				HallOfFame.Brickscore = player.scoreProperty().getValue();
				App.goToMain();
			}
			if (key.getCode() == KeyCode.ENTER) {

				gameStarted = false;
				player.setBounds(getWidth() / 2, getHeight() - 10, 200, 10);
				player.scoreProperty().setValue(0);
				player.livesProperty().setValue(5);
				bricks = new ArrayList<Brick>();
				BricksPack();

			}
		});
	}

	public void BricksPack() {
		int i = 0;
		for (int fila = 0; fila < maxFilas; fila++) {
			for (int columna = 0; columna < maxColumnas; columna++) {
				i++;
				bricks.add(new Brick(this, i - 1, columna * anchoBloque, fila * altoBloque, anchoBloque - 5, altoBloque - 5));
				if (i == Brick.IMAGES.length) {
					i = 0;
				}
			}
		}
	}

	@Override
	protected void gameLoop(double diff) {
		if (comprobar()) {
			
			if (player.getScore() == (maxColumnas * maxFilas)) {
				gc.setFill(Color.RED);
				gc.setFont(new Font("", 70));
				gc.fillText("WIN", 175, 300);
			} else if (player.livesProperty().getValue() == 0) {
				gc.setFill(Color.RED);
				gc.setFont(new Font("", 50));
				gc.fillText("Game Over", 175, 300);
			}

			gc.setFill(Color.YELLOWGREEN);
			gc.setFont(new Font("", 18));
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

	private boolean comprobar() {
		boolean fin = false;
		if (player.scoreProperty().getValue() == (maxColumnas * maxFilas)) {

			fin = true;
		} else {
			if (player.livesProperty().getValue() == 0) {
				fin = true;
			}
		}
		return fin;
	}

	private void render(GraphicsContext gc) {
		background.render(gc);
		ball.render(gc);
		player.render(gc);
		bricks.stream().forEach(b -> b.render(gc));
	}

	private void collision() {

		// aumenta la velocidad despues de chocar y cambio de direccion

		ball.checkCollision(player);
		ball.checkCollision(topWall);

		// -----------------------------
		// Arreglar muros laterales

		ball.checkCollision(leftWall);
		ball.checkCollision(rightWall);
		// -----------------------------scoreProperty
		new ArrayList<>(bricks).stream().filter(brick -> ball.checkCollision(brick))
				.forEach(brick -> bricks.remove(brick));
		player.setScore(maxFilas * maxColumnas - bricks.toArray().length);
	}

	private void update(double diff) {

		if (gameStarted) {
			ball.update(diff);
			player.update(diff);
			// Punto para player
			// if (ball.getX()) {
			// player.setScore(player.getScore() + 1);
			// gameStarted = false;
			// }

			// Player pierde vida(revisar expresion e intentar optimizar)
			if (ball.getY() > getHeight()) {
				player.setLives(player.getLives() - 1);
				gameStarted = false;
			}
		} else {
			ball.setX(getWidth() / 2);
			ball.setY(getHeight() * 3 / 4);
			ball.setRadio(BALL_R);
			ball.setDirection(new Point2D(1.0, -1.0));
		}
	}
}
