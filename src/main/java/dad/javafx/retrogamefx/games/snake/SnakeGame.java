package dad.javafx.retrogamefx.games.snake;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.base.App;
import dad.javafx.retrogamefx.formulario.Myscores;
import dad.javafx.retrogamefx.games.GameScene;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeGame extends GameScene{
	private static IntegerProperty score = new SimpleIntegerProperty(0);

	public final int getScore() {
		return this.scoreProperty().get();
	}

	public final void setScore(final int score) {
		this.scoreProperty().set(score);
	}
	public final IntegerProperty scoreProperty() {
		return this.score;
	}
	static int width=20;
	static int height=20;
	static int cornersize=30;
	static List<Corner> snakes= new ArrayList<>();
	static List<Corner> snakes2= new ArrayList<>();
	static Dir direction=Dir.left;
	static double speed=0.000001;
	static Random rand=new Random();
	static boolean gameOver=false;
	private Background background;
	private static Food food= new Food(0,0);
	GraphicsContext gc;
	
	//view
	@FXML
    private StackPane view;

    @FXML
    private Canvas canvas;

    @FXML
    private BorderPane hudPane;

    @FXML
    private Label player1ScoreLabel;
    
    
	public SnakeGame() {
		super("/fxml/Snake.fxml", 600, 600,1);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc= canvas.getGraphicsContext2D();
		cuerpoReset();
		canvas.setFocusTraversable(true);
		canvas.addEventFilter(KeyEvent.KEY_PRESSED,key->{
			
			if(key.getCode()==KeyCode.W) {
				direction=Dir.up;
			}
			if(key.getCode()==KeyCode.UP) {
				direction=Dir.up;
			}
			if(key.getCode()==KeyCode.A) {
				direction=Dir.left;
			}
			if(key.getCode()==KeyCode.LEFT) {
				direction=Dir.left;
			}
			if(key.getCode()==KeyCode.D) {
				direction=Dir.right;
			}
			if(key.getCode()==KeyCode.RIGHT) {
				direction=Dir.right;
			}
			if(key.getCode()==KeyCode.S) {
				direction=Dir.down;
			}
			if(key.getCode()==KeyCode.DOWN) {
				direction=Dir.down;
			}
			if(key.getCode()==KeyCode.ENTER) {
				
				if(gameOver==true) {
				score.setValue(0);
				gameOver=false;
				snakes=snakes2;
				cuerpoReset();
				newFood();}
			}
			if(key.getCode()==KeyCode.ESCAPE) {
				Myscores.Snakescore=scoreProperty().getValue(); 
				App.gotToMain();;	
			}

		});
		Cuerpo();
		newFood();
		background = new Background(Color.BLACK);
		background.setBounds(0, 0, 600, 600);
		
		player1ScoreLabel.textProperty().bind(scoreProperty().asString());
	}

	private void cuerpoReset() {
		snakes2=new ArrayList<>();
		snakes2.add(new Corner(width/2,height/2));
		snakes2.add(new Corner(width/2,height/2));
		snakes2.add(new Corner(width/2,height/2));
	}

	@Override
	protected void gameLoop(double diff) {
		if(gameOver) {
			gc.setFill(Color.RED);
			gc.setFont(new Font("",50));
			gc.fillText("Game Over", 175, 300);
			gc.setFill(Color.YELLOWGREEN);
			gc.setFont(new Font("",18));
			gc.fillText("Press Enter to play", 225, 400);
			return;
		}else {
			// actualizar elementos del juego
			update();

			// renderizado (pintar)
			render(gc);
			// detectar colisiones
			collision(gc);
		}
	}


	private void render(GraphicsContext gc) {
		background.render(gc);
		food.render(gc);
		pintarsnake(gc);
	
	}
	private void collision(GraphicsContext gc) {
		selfDestroy();
		eatFood(gc);
	}

	private void update() {
		partsfollow();
		switch(direction) {
		case up:
			snakes.get(0).y--;
			if(snakes.get(0).y<0) {
				gameOver=true;
			}
			break;
		case down:
			snakes.get(0).y++;
			if(snakes.get(0).y+1>height) {
				gameOver=true;
			}
			break;
		case left:
			snakes.get(0).x--;
			if(snakes.get(0).x<0) {
				gameOver=true;
			}
			break;
		case right:
			snakes.get(0).x++;
			if(snakes.get(0).x+1>width) {
				gameOver=true;
			}
			break;
		}
	}
	private static void Cuerpo() {
		snakes.add(new Corner(width/2,height/2));
		snakes.add(new Corner(width/2,height/2));
		snakes.add(new Corner(width/2,height/2));
	}
	public static void newFood() {	
		food.setX(rand.nextInt(width));
		food.setY(rand.nextInt(height));			
			speed++;			
	}
		
	private static void pintarsnake(GraphicsContext gc) {
		for(Corner c:snakes) {
			Image serpen=new Image("images/serpentilla.png");
			gc.drawImage(serpen,c.x*cornersize, c.y*cornersize, cornersize-1, cornersize-1);
			//gc.setFill(Color.LIGHTGREEN);
			//gc.fillOval(c.x*cornersize, c.y*cornersize, cornersize-1, cornersize-1);
			//capa para seguridad
			//gc.setFill(Color.LIGHTGREEN);
			gc.drawImage(serpen,c.x*cornersize, c.y*cornersize, cornersize-2, cornersize-2);
		}
	}
	
	private static void partsfollow() {
		for(int i=snakes.size()-1;i>=1;i--) {
			snakes.get(i).x=snakes.get(i-1).x;
			snakes.get(i).y=snakes.get(i-1).y;
		}}
	//al comerte una comida se aumenta el tamaño del snake
	private static void eatFood(GraphicsContext gc) {
		if(food.getX()==snakes.get(0).x && food.getY()==snakes.get(0).y) {
			snakes.add(new Corner(-1,-1));
			newFood();
			food.render(gc);
			score.setValue(score.getValue()+1);;
		}
	}
	//te comes a ti mismo
	private static void selfDestroy() {
		for(int i=1;i<snakes.size();i++) {
			if(snakes.get(0).x==snakes.get(i).x &&snakes.get(0).y==snakes.get(i).y) {
				gameOver=true;
			}
		}
	}
}
