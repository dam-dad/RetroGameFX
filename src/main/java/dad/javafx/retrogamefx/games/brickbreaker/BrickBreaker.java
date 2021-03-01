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
	private HorizontalWall topWall;
	private VerticalWall leftWall, rightWall;
	private Map map;
	private Brick brick;
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

	private ArrayList<Brick> bricks;
	private ArrayList<ArrayList<Brick>> ArrayBricks;

	public BrickBreaker() {
		super("/fxml/BrickBreaker.fxml", 800, 600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();

		ball = new Ball();
		
		bricks = new ArrayList<Brick>();

		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());

		// map= new Map(Color.DEEPSKYBLUE);
//		map.setBounds(getWidth()/6, 20, getWidth()/1.5, getHeight()/4);

		topWall = new HorizontalWall();
		topWall.setBounds(0, -10, getWidth(), 10);

		rightWall = new VerticalWall();
		rightWall.setBounds(getWidth(), 0, getWidth() + 10, getHeight());

		leftWall = new VerticalWall();
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
		
		Brick brick;
		for (int fila = 0; fila < maxFilas; fila++) {
			for (int columna = 0; columna < maxColumnas; columna++) {
				
				brick = new Brick(Color.AQUA, columna * anchoBloque, fila * altoBloque, anchoBloque-5, altoBloque-5);
				bricks.add(brick);
				
			}
		}
	}

	/*
	 * public void BricksPack() { int i = 0; Brick brick; for (int fila = 0; fila <
	 * maxFilas; fila++) { for (int columna = 0; columna < maxColumnas; columna++) {
	 * i++; brick = new Brick(Color.RED, columna * anchoBloque, fila * altoBloque,
	 * anchoBloque-5, altoBloque-5); if (i == 2) { i = 0; } bricks.add(brick); // }
	 * ArrayBricks.add(bricks); }
	 * 
	 * }
	 */

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
		// -----------------------------
		new ArrayList<>(bricks).stream().filter(brick -> ball.checkCollision(brick)).forEach(brick -> bricks.remove(brick));

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
			ball.setY(getHeight() * 3/4);
			ball.setRadio(BALL_R);
			ball.setDirection(new Point2D(1.0, -1.0));
		}
	}
}
