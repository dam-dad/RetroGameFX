package dad.javafx.retrogamefx.games.pong;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.base.App;
import dad.javafx.retrogamefx.formulario.Myscores;
import dad.javafx.retrogamefx.games.GameScene;
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

public class Pong extends GameScene {

	// variables (objetos)
	private static final double BALL_R = 20;
	private boolean gameStarted;

	// model
	private String scoreWS="no acabaste la partida";
	private Player player;
	private Player cpu;
	private Background background;
	private Ball ball;
	private VerticalWall topWall, bottomWall;
	private boolean gameOver = false;
	GraphicsContext gc;
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
		gc=canvas.getGraphicsContext2D();
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());

	
		ball = new Ball();
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		ball.setRadio(BALL_R);
		
		topWall = new VerticalWall();
		topWall.setBounds(0, -10, getWidth(), 10);

		bottomWall = new VerticalWall();
		bottomWall.setBounds(0, getHeight(), getWidth(), getHeight() + 10);

		player = new Player(Color.RED);
		player.setBounds(0, getHeight() / 2, 10, 200);

		cpu = new CPU(Color.AQUA, ball, this);
		cpu.setBounds(getWidth() - 10, getHeight() / 2, 10, 200);

		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player2ScoreLabel.textProperty().bind(cpu.scoreProperty().asString());

		// control de raton
		
		canvas.setOnMouseMoved(e -> player.setY(e.getY() - player.getHeight() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);
		canvas.setFocusTraversable(true);
		canvas.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			if (key.getCode() == KeyCode.ESCAPE) {
				Myscores.Pongscore=scoreWS; 
				App.gotToMain();
			}
			if (key.getCode() == KeyCode.ENTER) {
				player.setScore(0);
				cpu.setScore(0);
				gameStarted=false;
			}
		});
	}

	@Override
	protected void gameLoop(double diff) {
		if (comprobar()) {
		if (player.scoreProperty().getValue() == 5) {
			scoreWS="Win";
			gc.setFill(Color.RED);
			gc.setFont(new Font("", 70));
			gc.fillText("WIN", 175, 300);
			gc.setFill(Color.YELLOWGREEN);
			gc.setFont(new Font("", 18));
			gc.fillText("Press Enter to play", 225, 400);
		} else {if (cpu.scoreProperty().getValue() == 5) {
			scoreWS="Lose";
			gc.setFill(Color.RED);
			gc.setFont(new Font("", 50));
			gc.fillText("Game Over", 175, 300);
			gc.setFill(Color.YELLOWGREEN);
			gc.setFont(new Font("", 18));
			gc.fillText("Press Enter to play", 225, 400);
			}}}else {
			// actualizar elementos del juego
			update(diff);

			// detectar colisiones
			collision();

			// renderizado (pintar)
			render(gc);}

	}
		private boolean comprobar() {
			boolean fin = false;
			if (player.scoreProperty().getValue() == 5) {

				fin = true;
			} else {
				if (cpu.scoreProperty().getValue() == 5) {
					fin = true;
				}
			}
			return fin;
		}

	private void render(GraphicsContext gc) {

		background.render(gc);
		ball.render(gc);
		player.render(gc);
		cpu.render(gc);
		
		
	}
	// aumenta la velocidad despues de chocar y cambio de direccion
	private void collision() {

		//el rebote dentro de la pelota va muy a ratos
		if(ball.getY()>0) {
			ball.setDirection(new Point2D(ball.getDirection().getX(), -ball.getDirection().getY()));}
		
		if(ball.getY()<getHeight()) {
		ball.setDirection(new Point2D(ball.getDirection().getX(), -ball.getDirection().getY()));}
		
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
			if (new Random().nextBoolean()) {
				ball.setSpeed(ball.getSpeed() * -1);
			}

		}

	}

}
