package dad.javafx.retrogamefx.games.brickbreaker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import dad.javafx.retrogamefx.games.GameScene;
import dad.javafx.retrogamefx.snake.Corner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BrickBreaker extends GameScene {

	private static final double BALL_R = 20;
	private boolean gameStarted;
	// array de las caras

	// El propiamente dicho mapa, aqui se guardan los bloques

	// model
	private Player player;
	private Background background;
	private Ball ball;
	private VerticalWall topWall;
	private HorizontalWall leftWall, rightWall;
	private Map map;
	private Bricks brick;
	static int maxFilas = 10;
	static int maxColumnas = 20;
	static int anchoBloque = 40;
	static int altoBloque = 40;
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

	private ArrayList<Bricks> bricks;
	private ArrayList<ArrayList<Bricks>> ArrayBricks;

	public BrickBreaker() {
		super("/fxml/BrickBreaker.fxml", 800, 600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
		ball = new Ball();
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		ball.setRadio(BALL_R);
		bricks = new ArrayList<Bricks>();
		ArrayBricks = new ArrayList<ArrayList<Bricks>>();

		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());

		// map= new Map(Color.DEEPSKYBLUE);
//		map.setBounds(getWidth()/6, 20, getWidth()/1.5, getHeight()/4);

		topWall = new VerticalWall();
		topWall.setBounds(0, 0, getWidth(), 10);

		rightWall = new HorizontalWall();
		rightWall.setBounds(getWidth(), 0, getWidth() + 10, getHeight());

		leftWall = new HorizontalWall();
		leftWall.setBounds(-10, 0, 10, getHeight());

		player = new Player(Color.RED);
		player.setBounds(getWidth() / 2, getHeight() - 10, 200, 10);

		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player1LivesLabel.textProperty().bind(player.livesProperty().asString());
		BricksPack();

		// control de raton
		canvas.setOnMouseMoved(e -> player.setX(e.getX() - player.getWidth() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);
	}

	public void BricksPack() {
		int i = 0;
		Bricks brick;
		for (int fila = 0; fila < maxFilas; fila++) {
			for (int columna = 0; columna < maxColumnas; columna++) {
				i++;
				brick = new Bricks(Color.RED, columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				brick.setBounds(columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				if (i == 2) {
					i = 0;
				}
				bricks.add(brick);
				brick = null;
				//
			}
			ArrayBricks.add(bricks);
		}

	}

	@Override
	protected void gameLoop(double diff) {

		// actualizar elementos del juego
		update(diff);

		// detectar colisiones
		collision();

		// renderizado (pintar)
		render(gc);
	}

	private void render(GraphicsContext gc) {
		Bricks brick;
		background.render(gc);
		ball.render(gc);
		player.render(gc);
		// map.render(gc);
		for (int fila = 0; fila < maxFilas; fila++) {
			bricks=ArrayBricks.get(fila);
		for (int columna = 0; columna < maxColumnas; columna++) {
			brick = bricks.get(columna);
			brick.render(gc);
		}
		}
	}

	private void collision() {

		// aumenta la velocidad despues de chocar y cambio de direccion
		ball.setSprite(player);
		ball.checkCollision();
		ball.setSprite(topWall);
		ball.checkCollision();

		// -----------------------------
		// Arreglar muros laterales
		ball.setSprite(leftWall);
		ball.checkCollision();
		ball.setSprite(rightWall);
		ball.checkCollision();
		// -----------------------------
		for (Bricks c : bricks) {
			ball.setSprite(c);
			ball.checkCollision();
		}
		// ball.checkCollision(brick); //Implementar Bricks
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
			if (ball.getY() > (player.getY() + player.getHeight())
					&& ball.getX() != player.getX() + (player.getWidth())) {
				player.setLives(player.getLives() - 1);
				gameStarted = false;
			}
		} else {
			// texto inicio
			// Reset posicion pelota al inicio
			ball.setX(getWidth() / 2);
			ball.setY(getHeight() / 2);
			ball.setDirection(new Point2D(1.0, -1.0));
			if (new Random().nextBoolean()) {
				ball.setSpeed(ball.getSpeed() * -1);
			}
		}
	}
}
