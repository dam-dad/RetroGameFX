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
	private static final double BALL_R = 20;
	private boolean gameStarted;
	

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
		ball.setRadio(BALL_R);

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
		if (((ball.getX() + ball.getRadio(BALL_R) > cpu.getX() + cpu.getWidth()) && ball.getY() >= cpu.getY()
				&& ball.getY() <= cpu.getY() + cpu.getHeight())) {
			ball.setX(ball.getSpeed()+1* Math.signum(ball.getSpeed()));
			ball.setY(ball.getSpeed()+1* Math.signum(ball.getSpeed()));
			ball.setX(ball.getX()*-1);
			ball.setY(ball.getY()*1);
		}
		if (((ball.getX() + ball.getRadio(BALL_R)< player.getX() + player.getWidth()) && ball.getY() >= player.getY()
				&& ball.getY() <= player.getY() + player.getHeight())) {
			ball.setX(ball.getSpeed()+1* Math.signum(ball.getSpeed()));
			ball.setY(ball.getSpeed()+1* Math.signum(ball.getSpeed()));
			ball.setX(ball.getX()*-1);
			ball.setY(ball.getY()*1);
			//ballYSpeed += 1 * Math.signum(ballYSpeed);
			//ballXSpeed += 1 * Math.signum(ballXSpeed);
			//ballXSpeed *= -1; // Cambia de dirrecion la bola
			//ballYSpeed *= 1; // la tira al lado opuesto osea si viene por arriba la dispara por debajo
		}
	}

	private void update() {

		if (gameStarted) {
			// Movimiento de la pelota
			ball.setX(ball.getX()+ball.getSpeed());
			ball.setY(ball.getY()+ball.getSpeed());

			// seguimiento de la pelota IA
			if (ball.getX() < WORLD_WIDTH - WORLD_WIDTH / 4) {
				cpu.setY(ball.getY() - cpu.getHeight() / 2) ;
			} else {// dificultad easy: playerTwoYPos += 1: playerTwoYPos - 1 media: playerTwoYPos
					// += 5: playerTwoYPos - 5 dificil: playerTwoYPos += 10: playerTwoYPos - 10
				if(ball.getY()> cpu.getY() + cpu.getHeight() / 2) {
					 cpu.setY(cpu.getY()+5);
				}else {
					cpu.setY(cpu.getY()-5);
				}
			}
		} else {
			// texto inicio

			// Reset posicion pelota al inicio
			ball.setX(WORLD_WIDTH / 2);
			ball.setY(WORLD_HEIGHT / 2);
			
			// Reseteo de speed de la pelota
			//Revisar posible fallo
			//ball.setSpeed(new Random().nextInt(20) == 0 ? 1 : -1); 
			
			if(new Random().nextInt(20)== 0){
				ball.setSpeed(1);
				
			}else {
				ball.setSpeed(-1);
			}
				
		}

		// ball esta dentro del canvas
		if (ball.getY() > WORLD_HEIGHT || ball.getY() < 0)
			ball.setSpeed(ball.getSpeed()*-1);//Esto esta de prueba xD
		//if (ball.getX() > WORLD_WIDTH || ball.getX() < 0)
			
		// Punto para jugador 2
		if (ball.getX() < player.getX() - cpu.getWidth()) {
			cpu.setScore(cpu.getScore() + 1);
			gameStarted = false;
		}

		// Punto para jugador 1
		if (ball.getX() > cpu.getX() + player.getWidth()) {
			player.setScore(player.getScore() + 1);
			gameStarted = false;
		}
	}

}
