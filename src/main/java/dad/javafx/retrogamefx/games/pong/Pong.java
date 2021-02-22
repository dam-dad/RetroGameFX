package dad.javafx.retrogamefx.games.pong;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.games.GameScene;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Pong extends GameScene {

	// variables (objetos)
	private static final int WORLD_WIDTH = 800;
	private static final int WORLD_HEIGHT = 600;
	
	private static final double BALL_R = 40;
	
	private int ballYSpeed = 4;
	private int ballXSpeed = 4;
	
	private double ballXPos = width / 2;
	private double ballYPos = height / 2;
	private boolean gameStarted;
	private int playerOneXPos = 0;
	private double playerTwoXPos = width - PLAYER_WIDTH;

	// model

	private Player player;
	private Player cpu;
	private Background background;
	private Ball ball;

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
		super("/fxml/Pong.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, WORLD_WIDTH, WORLD_HEIGHT);

		player = new Player(Color.RED);
		player.setBounds(0, WORLD_HEIGHT / 2, 10, 200);
		
		cpu = new Player(Color.AQUA);
		cpu.setBounds(WORLD_WIDTH - 10, WORLD_HEIGHT / 2, 10, 200);
		
		ball = new Ball();
		ball.setX(WORLD_WIDTH / 2);
		ball.setY(WORLD_HEIGHT / 2);
		ball.setRadio(40);

		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player2ScoreLabel.textProperty().bind(cpu.scoreProperty().asString());

		// control de raton
		canvas.setOnMouseMoved(e -> player.setY(e.getY() - player.getHeight() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);

	}

	@Override
	protected void gameLoop(long now) {

		// actualizar elementos del juego
		update();
		
		// detectar colisiones
		collision();
		
		// renderizado (pintar)
		render(canvas.getGraphicsContext2D());
		
	}

	private void render(GraphicsContext gc) {

		background.render(gc);
		ball.render(gc);
		player.render(gc);
		cpu.render(gc);

	}

	private void collision() {
		// aumenta la velocidad despues de chocar y cambio de dirreccion
		if (((ballXPos + BALL_R > playerTwoXPos) && ballYPos >= playerTwoYPos
				&& ballYPos <= playerTwoYPos + PLAYER_HEIGHT)) {
			ballYSpeed += 1 * Math.signum(ballYSpeed);
			ballXSpeed += 1 * Math.signum(ballXSpeed);
			ballXSpeed *= -1;
			ballYSpeed *= 1;
		}
		if (((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos
				&& ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
			ballYSpeed += 1 * Math.signum(ballYSpeed);
			ballXSpeed += 1 * Math.signum(ballXSpeed);
			ballXSpeed *= -1; // Cambia de dirrecion la bola
			ballYSpeed *= 1; // la tira al lado opuesto osea si viene por arriba la dispara por debajo
		}
	}

	private void update() {

		if (gameStarted) {
			// Movimiento de la pelota
			ballXPos += ballXSpeed;
			ballYPos += ballYSpeed;

			// seguimiento de la pelota IA
			if (ballXPos < width - width / 4) {
				playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
			} else {// dificultad easy: playerTwoYPos += 1: playerTwoYPos - 1 media: playerTwoYPos
					// += 5: playerTwoYPos - 5 dificil: playerTwoYPos += 10: playerTwoYPos - 10
				playerTwoYPos = ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ? playerTwoYPos += 5 : playerTwoYPos - 5;
			}
		} else {
			// texto inicio

			// Reset posicion pelota al inicio
			ballXPos = width / 2;
			ballYPos = height / 2;

			// Reseteo de speed de la pelota
			ballXSpeed = new Random().nextInt(20) == 0 ? 1 : -1;
			ballYSpeed = new Random().nextInt(20) == 0 ? 1 : -1;
		}

		// ball esta dentro del canvas
		if (ballYPos > height || ballYPos < 0)
			ballYSpeed *= -1;
		
		// Punto para jugador 2
		if (ballXPos < playerOneXPos - PLAYER_WIDTH) {
			player2Score.set(player2Score.get() + 1);
			gameStarted = false;
		}

		// Punto para jugador 1
		if (ballXPos > playerTwoXPos + PLAYER_WIDTH) {
			player1Score.set(player1Score.get() + 1);
			gameStarted = false;
		}
	}

}
