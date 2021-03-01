package dad.javafx.retrogamefx.games.brickbreaker;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import dad.javafx.retrogamefx.games.GameScene;
import dad.javafx.retrogamefx.games.pong.Background;
import dad.javafx.retrogamefx.games.pong.Ball;
import dad.javafx.retrogamefx.games.pong.HorizontalWall;
import dad.javafx.retrogamefx.games.pong.Player;
import dad.javafx.retrogamefx.games.pong.VerticalWall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class BrickBreaker extends GameScene{
	
	private static final double BALL_R = 20;
	private boolean gameStarted;
	//array de las caras
	
	//El propiamente dicho mapa, aqui se guardan los bloques

	
	// model
	private Player player;
	private Background background;
	private Ball ball;
	private VerticalWall topWall;
	private HorizontalWall leftWall, rightWall;
	private Map map;
	private Brick brick;
	static int maxFilas = 10;
	static int maxColumnas = 20;
	static int anchoBloque = 40;
	static int altoBloque = 40;
	
	
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
    
    private ArrayList<Brick> bricks= new ArrayList<Brick>();
	
	public BrickBreaker() {
		super("/fxml/BrickBreaker.fxml", 800, 600);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());
		BricksPack();
//		map= new Map(Color.DEEPSKYBLUE);
//		map.setBounds(getWidth()/6, 20, getWidth()/1.5, getHeight()/4);
		
		//brick = new Bricks(Color.BLUEVIOLET, getWidth()/6, 20, 40, 40);
		//brick.setBounds(getWidth()/6, 20, 40, 40);
		
		topWall = new VerticalWall();
		topWall.setBounds(0, 0, getWidth(), 10);
		
		
		rightWall = new HorizontalWall();
		rightWall.setBounds(getWidth(), 0, getWidth()+10, getHeight());
		
		leftWall = new HorizontalWall();
		leftWall.setBounds(-10, 0, 10, getHeight());
		
		player = new Player(Color.RED);
		player.setBounds(getWidth()/2, getHeight()-10, 200, 10);
		
		ball = new Ball();
		ball.setX(getWidth()/2);
		ball.setY(getHeight()/2);
		ball.setRadio(BALL_R);
		
		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player1LivesLabel.textProperty().bind(player.livesProperty().asString());
		
		
		
		// control de raton
		canvas.setOnMouseMoved(e -> player.setX(e.getX() - player.getWidth() / 2));
		canvas.setOnMouseClicked(e -> gameStarted = true);
	}
	public void BricksPack(){
		int i=0;
		Brick brick;
		for(int fila=0; fila<maxFilas;fila++){
			for(int columna=0; columna<maxColumnas;columna++){
				i++;
				brick=new Brick(Color.AQUA,columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				brick.setBounds(columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
			 bricks.add(brick);
			 if(i==3) {i=0;}
			 }
		}
		
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
		Brick brick;
		background.render(gc);
		ball.render(gc);
		player.render(gc);
		//map.render(gc);
		for (int fila = 0; fila <= maxFilas - 1; fila++) {
			 brick=bricks.get(fila);
			 brick.render(gc);
            }
		
		
		
	}
private void collision() {
		
		// aumenta la velocidad despues de chocar y cambio de direccion
		ball.checkCollision(player);
		ball.checkCollision(topWall);
		
		//-----------------------------
		//Arreglar muros laterales
		ball.checkCollision(leftWall); 
		ball.checkCollision(rightWall);
		//-----------------------------
		ball.checkCollision(brick);
		//ball.checkCollision(brick);              //Implementar Bricks		
	}
private void update(double diff) {

		if (gameStarted) {
			ball.update(diff);
			player.update(diff);
			// Punto para player 
			//			if (ball.getX()) {
			//				player.setScore(player.getScore() + 1);
			//				gameStarted = false;
					//	}
			
			//Player pierde vida(revisar expresion e intentar optimizar)
			 if (ball.getY()> (player.getY()+player.getHeight()) && ball.getX() != player.getX()+(player.getWidth())) {
				 player.setLives(player.getLives()-1);
				 gameStarted = false; 
			 }
		}else {
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
