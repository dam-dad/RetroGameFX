package Pong;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

	public class Pong extends Application {
		
		//variables
		private static final int width = 1900;
		private static final int height = 1000;
		private static final int PLAYER_HEIGHT = 200;
		private static final int PLAYER_WIDTH = 20;
		private static final double BALL_R = 40;
		private int ballYSpeed = 4;
		private int ballXSpeed = 4;
		private double playerOneYPos = height / 2;
		private double playerTwoYPos = height / 2;
		private double ballXPos = width / 2;
		private double ballYPos = height / 2;
		private int scoreP1 = 0;
		private int scoreP2 = 0;
		private boolean gameStarted;
		private int playerOneXPos = 0;
		private double playerTwoXPos = width - PLAYER_WIDTH;
		
			
		public void start(Stage stage) throws Exception {
			stage.setTitle("P O N G");
			
			Canvas canvas = new Canvas(width, height);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			//Frames por segundos
			Timeline tl = new Timeline(new KeyFrame(Duration.millis(5), e -> run(gc)));
			//number of cycles in animation INDEFINITE = repeat indefinitely
			tl.setCycleCount(Timeline.INDEFINITE);
			
			//control de raton
			canvas.setOnMouseMoved(e ->  playerOneYPos  = e.getY());
			
			canvas.setOnMouseClicked(e ->  gameStarted = true);
			stage.setScene(new Scene(new StackPane(canvas)));
			stage.show();
			tl.play();
		}

		private void run(GraphicsContext gc) {
			//set color fondo
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, width, height);
			
			//set texto
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font(25));
			
			if(gameStarted) {
				//Movimiento de la pelota
				ballXPos+=ballXSpeed;
				ballYPos+=ballYSpeed;
				
				//seguimiento de la pelota IA
				if(ballXPos < width - width  / 4) {
					playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
				}  else {//dificultad easy: playerTwoYPos += 1: playerTwoYPos - 1 media: playerTwoYPos += 5: playerTwoYPos - 5 dificil: playerTwoYPos += 10: playerTwoYPos - 10	 	
					playerTwoYPos =  ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ?playerTwoYPos += 5: playerTwoYPos - 5;
				}
				//Creando pelota
				gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);
				
			} else {
				//texto inicio
				gc.setStroke(Color.WHITE);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.strokeText("Click para empezar", width / 2, height / 2);
				
				//Reset posicion pelota al inicio 
				ballXPos = width / 2;
				ballYPos = height / 2;
				
				//Reseteo de speed de la pelota
				ballXSpeed = new Random().nextInt(4) == 0 ? 1: -1;
				ballYSpeed = new Random().nextInt(4) == 0 ? 1: -1;
			}
			
			//ball esta dentro del canvas
			if(ballYPos > height || ballYPos < 0) ballYSpeed *=-1;
			
			//Punto para jugador 2
			if(ballXPos < playerOneXPos - PLAYER_WIDTH) {
				scoreP2++;
				gameStarted = false;
			}
			
			//Punto para jugador 1
			if(ballXPos > playerTwoXPos + PLAYER_WIDTH) {  
				scoreP1++;
				gameStarted = false;
			}
		
			//aumenta la velocidad despues de chocar y cambio de dirreccion
			if( ((ballXPos + BALL_R > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT))
				{
				ballYSpeed += 1 * Math.signum(ballYSpeed);
				ballXSpeed += 1 * Math.signum(ballXSpeed);
				ballXSpeed *= -1;
				ballYSpeed *= 1;
			}
			if(((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
				ballYSpeed += 1 * Math.signum(ballYSpeed);
				ballXSpeed += 1 * Math.signum(ballXSpeed);	
				ballXSpeed *= -1; //Cambia de dirrecion la bola
				ballYSpeed *= 1; //la tira al lado opuesto osea si viene por arriba la dispara por debajo
				}
		
			
			//score
			gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t\t\t\t" + scoreP2, width / 2, 100);
			
			gc.setFill(Color.RED); //p1
			//gc.fillOval(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
			//gc.drawImage(imag, playerTwoXPos,  playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT); intento de poner cosas
			gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);//palas
			//gc.fillRoundRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT, 10, 20);
			gc.setFill(Color.AQUA);//p2
			gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);//palas
			//gc.fillRoundRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT, 20, 10);			
		}
			public static void main(String[] args) {
			launch(args);
			}
	}
