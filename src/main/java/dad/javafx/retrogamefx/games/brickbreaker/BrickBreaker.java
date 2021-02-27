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
import dad.javafx.retrogamefx.games.pong.Background;
import dad.javafx.retrogamefx.games.pong.Ball;
import dad.javafx.retrogamefx.games.pong.Player;
import dad.javafx.retrogamefx.games.pong.Wall;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class BrickBreaker extends GameScene{
	
	private static final double BALL_R = 20;
	private boolean gameStarted;
	static int maxFilas = 10;
	static int maxColumnas = 20;
	private static final int width = 800;
	private static final int height = 600;
	static int anchoBloque = width / maxColumnas;
	static int altoBloque = height / maxFilas;
	ArrayList<Cara> Caras;
	Bricks[][] Bloques;
	
	// model
	private Player player;
	private Background background;
	private Ball ball;
	private Wall topWall, leftWall, rightWall;
	private Bricks brick;
	
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
	
	public BrickBreaker() {
		super("/fxml/BrickBreaker.fxml", width, height);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Bloques = new Bricks[maxFilas + 2][maxColumnas + 2];
		 Caras = new ArrayList();
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, getWidth(), getHeight());
		
		topWall = new Wall();
		topWall.setBounds(0, 0, getWidth(), 10);
		
		//-------------------------------------------------------------
		//Arreglar muros laterales
		rightWall = new Wall();
		rightWall.setBounds(getWidth(), 0, getWidth()+10, getHeight());
		
		leftWall = new Wall();
		leftWall.setBounds(-10, 0, 10, getHeight());
		//-------------------------------------------------------------
		
		player = new Player(Color.RED);
		player.setBounds(getWidth()/2, getHeight()-10, 200, 10);
		
		ball = new Ball();
		ball.setX(getWidth()/2);
		ball.setY(getHeight()/2);
		ball.setRadio(BALL_R);
		
		//Añadir mapeo de bricks
		
		player1ScoreLabel.textProperty().bind(player.scoreProperty().asString());
		player1LivesLabel.textProperty().bind(player.livesProperty().asString());
		
		// control de raton
		canvas.setOnMouseMoved(e -> player.setX(e.getX() - player.getWidth() / 2));
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
//------------------------Colisiones by cosme




public void update(Bricks[][] bloques) {
	for (int fila = 1; fila <= maxFilas - 2; fila++) {
		for (int columna = 1; columna <= maxColumnas - 2; columna++) {
			esNull(bloques[fila][columna],fila,columna);
		}
	}
	colision(Caras,ball);
}

public void esNull(Bricks bloque,int fila, int columna) {
	if (izquierda(fila, columna)) {
		Caras.add(new Cara(bloque.getX(),bloque.getY(), bloque.getWidth(), bloque.getHeight(), true, fila, columna));
	}
	if (derecha(fila, columna)) {
		Caras.add(new Cara(bloque.getX() + bloque.getHeight(),bloque.getY(), bloque.getWidth(), bloque.getHeight(), true, fila, columna));
	}
	if (arriba(fila, columna)) {
		Caras.add(new Cara(bloque.getX(),bloque.getY(), bloque.getWidth(), bloque.getHeight(), false, fila, columna));
	}
	if (abajo(fila, columna)) {
		Caras.add(new Cara(bloque.getX(),bloque.getY()+bloque.getHeight(), bloque.getWidth(), bloque.getHeight(), false, fila, columna));
	}

}

// comprueba si tiene encima un bloque normal o protegido
public boolean izquierda(int fila, int columna) {
	if (Bloques[fila][columna - 1] == null)
		return true;
	else
		return false;
}

// comprueba si tiene debajo un bloque normal o protegido
public boolean derecha(int fila, int columna) {
	if (Bloques[fila][columna + 1] == null)
		return true;
	else
		return false;
}

// comprueba si tiene a la izquierda un bloque normal o protegido
public boolean arriba(int fila, int columna) {
	if (Bloques[fila - 1][columna] == null)
		return true;
	else
		return false;
}

// comprueba si tiene a la derecha un bloque normal o protegido
public boolean abajo(int fila, int columna) {
	if (Bloques[fila + 1][columna] == null)
		return true;
	else
		return false;
}

public void colision(ArrayList<Cara> caras, Ball ball) {
	if (recorrerCaras(caras, ball)[0] != -1) {
		Bloques[recorrerCaras(caras, ball)[0]][recorrerCaras(caras, ball)[1]] = null;
		player.setScore(player.getScore()+1);
	}
}

public int[] recorrerCaras(ArrayList<Cara> caras, Ball ball) {
	int[] aux = { -1, -1 };
	for (Cara x : caras) {
		// añadir colisiones de otros 3 puntos de la pelota
		if (comparadorCaraPunto(x, ball)) {
			aux[0] = x.fila;
			aux[1] = x.columna;
		}
	}
	return aux;
}

//colision de 1 punto de la pelota hace falta poner 4 puntos a la pelota
public boolean comparadorCaraPunto(Cara cara, Ball ball) {
	boolean aux = false;
	if (cara.d) {
		// linea horizontal misma Y, contiene X
		double X = cara.x, Y = cara.y, W = cara.w, Xb = ball.getX(), Yb = ball.getY();
		if (Y == Yb & X >= Xb & Xb >= (X + W)) {
			aux = true;
		}
	} else {
		// linea vertical misma X, contiene Y
		double X = cara.x, Y = cara.y, H = cara.h, Xb = ball.getX(), Yb = ball.getY();
		if (X == Xb & Y >= Yb & Yb >= (Y + H)) {
			aux = true;
		}

	}
	return aux;

}
}
