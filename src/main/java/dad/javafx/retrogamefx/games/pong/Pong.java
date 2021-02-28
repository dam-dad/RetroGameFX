package dad.javafx.retrogamefx.games.pong;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.games.GameScene;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Pong extends GameScene {

	// variables (objetos)
	private static final double BALL_R = 20;
	private boolean gameStarted;

	// model

	private Player player;
	private Player cpu;
	private Background background;
	private Ball ball;
	private VerticalWall topWall, bottomWall;

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
		super("/fxml/Pong.fxml", 800, 600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());
		
		topWall = new VerticalWall();
		topWall.setBounds(0, -10, getWidth(), 10);

		bottomWall = new VerticalWall();
		bottomWall.setBounds(0, getHeight(), getWidth(), getHeight() + 10);

		ball = new Ball();
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		ball.setRadio(BALL_R);

		player = new Player(Color.RED);
		player.setBounds(0, getHeight() / 2, 10, 200);
		
		cpu = new CPU(Color.AQUA, ball, this);
		cpu.setBounds(getWidth() - 10, getHeight() / 2, 10, 200);

		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player2ScoreLabel.textProperty().bind(cpu.scoreProperty().asString());

		// control de raton
		canvas.setOnMouseMoved(e -> player.setY(e.getY() - player.getHeight() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);

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
		cpu.render(gc);

	}

	private void collision() {
		
		// aumenta la velocidad despues de chocar y cambio de direccion
		ball.checkCollision(player);
		ball.checkCollision(cpu);
		ball.checkCollision(topWall);
		ball.checkCollision(bottomWall);
		
	}

	private void update(double diff) {

		if (gameStarted) {

			ball.update(diff);
			cpu.update(diff);
			player.update(diff);

			// seguimiento de la pelota IA
//			if (ball.getX() < getWidth() - getWidth() / 4) {
//				cpu.setY(ball.getY() - cpu.getHeight() / 2) ;
//			} else {// dificultad easy: playerTwoYPos += 1: playerTwoYPos - 1 media: playerTwoYPos
//					// += 5: playerTwoYPos - 5 dificil: playerTwoYPos += 10: playerTwoYPos - 10
//				if(ball.getY()> cpu.getY() + cpu.getHeight() / 2) {
//					 cpu.setY(cpu.getY()+5);
//				}else {
//					cpu.setY(cpu.getY()-5);
//				}
//			}
			
			// Punto para player (pelota se sale por la derecha)
			if (ball.getX() > cpu.getX()) {
				player.setScore(player.getScore() + 1);
				gameStarted = false;
			}

			// Punto para cpu (pelota se sale por la izquierda)
			if (ball.getX() < player.getX()) {
				cpu.setScore(cpu.getScore() + 1);
				gameStarted = false;
			}
			
		} else {
			// texto inicio

			// Reset posicion pelota al inicio
			ball.setX(getWidth() / 2);
			ball.setY(getHeight() / 2);
			ball.setDirection(new Point2D(1.0, -1.0));			
			if(new Random().nextBoolean()){
				ball.setSpeed(ball.getSpeed() * -1);
			}
				
		}


	}

}
