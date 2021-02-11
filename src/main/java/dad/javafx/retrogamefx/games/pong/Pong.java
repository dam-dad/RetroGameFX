package dad.javafx.retrogamefx.games.pong;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.games.GameScene;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Pong extends GameScene {

	// variables (objetos)
	private static final int width = 800;
	private static final int height = 600;
	private static final int PLAYER_HEIGHT = 200;
	private static final int PLAYER_WIDTH = 10;
	private static final double BALL_R = 40;
	private int ballYSpeed = 4;
	private int ballXSpeed = 4;
	private double playerOneYPos = height / 2;
	private double playerTwoYPos = height / 2;
	private double ballXPos = width / 2;
	private double ballYPos = height / 2;
	private boolean gameStarted;
	private int playerOneXPos = 0;
	private double playerTwoXPos = width - PLAYER_WIDTH;

//	private Timeline timeline;
	private AnimationTimer timer;
	
	// model
	
	private IntegerProperty player1Score;
	private IntegerProperty player2Score;
	
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
		
		player1Score = new SimpleIntegerProperty(0);
		player2Score = new SimpleIntegerProperty(0);
		
		player1ScoreLabel.textProperty().bind(player1Score.asString());
		player2ScoreLabel.textProperty().bind(player2Score.asString());

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Frames por segundos
		timer = new AnimationTimer() {
			public void handle(long now) {
				run();
				// update() --- actualizar elementos del juego 
				update();
				// collisions() --- detectar colisiones
				collision();
				// redner() --- renderizado (pintar) --> gc
				render(gc);
			}
		};
		
		// control de raton
		canvas.setOnMouseMoved(e -> playerOneYPos = e.getY()-(PLAYER_HEIGHT/2));
		canvas.setOnMouseClicked(e -> gameStarted = true);	
		
	}

	@Override
	public void play() {
		timer.start();
	}
	
	@Override
	public void quit() {
		timer.stop();
	}
	private void render(GraphicsContext gc) {
		GraphicsContext text=gc;
		GraphicsContext ball=gc;
		GraphicsContext player1=gc;
		GraphicsContext player2=gc;
		// set color fondo
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);
		
		// set texto
		text.setFill(Color.WHITE);
		text.setFont(Font.font(25));
		// Creando pelota
		ball.setFill(Color.WHITE);
		ball.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);
		//Render
		player1.setFill(Color.RED); // p1
		player1.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);// palas

		player2.setFill(Color.AQUA);// p2
		player2.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);// palas
				
	}


	private void run() {
		
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
