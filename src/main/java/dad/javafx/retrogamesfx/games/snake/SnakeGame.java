package dad.javafx.retrogamesfx.games.snake;

import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.retrogamesfx.base.App;
import dad.javafx.retrogamesfx.games.Background;
import dad.javafx.retrogamesfx.games.World;
import dad.javafx.retrogamesfx.reports.HallOfFame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeGame extends World {

	private int gridWidth = 20;
	private int gridHeight = 20;
	private int gridSize = 30;
	
	boolean gameOver;

	private Snake snake;
	private Background<SnakeGame> background;
	private Food food;
	
	private GraphicsContext gc;

	// view
	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;

	@FXML
	private Label scoreLabel;

	public SnakeGame() {
		super("Snake", "/fxml/Snake.fxml", 600, 600);
	}
	
	public int getGridWidth() {
		return gridWidth;
	}
	
	public int getGridHeight() {
		return gridHeight;
	}
	
	public int getGridSize() {
		return gridSize;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gridWidth = 20;
		gridHeight = 20;
		gridSize = 30;
		
		gc = canvas.getGraphicsContext2D();

		canvas.addEventFilter(KeyEvent.KEY_PRESSED, key -> {

			switch(key.getCode()) {
			case W:
			case UP:
				snake.setDirection(Direction.UP); break;
			case A:
			case LEFT:
				snake.setDirection(Direction.LEFT); break;
			case D:
			case RIGHT:
				snake.setDirection(Direction.RIGHT); break;
			case S:
			case DOWN:
				snake.setDirection(Direction.DOWN); break;
			case ENTER:
				resetGame(); break;
			case ESCAPE:
				HallOfFame.Snakescore = snake.getScore();
				App.goToMain();
				break;
			default:
			}
		});
		
		snake = new Snake(this);

		food = new Food(this);

		background = new Background<>(this, Color.BLACK);
		background.setBounds(0, 0, 600, 600);

		scoreLabel.textProperty().bind(snake.scoreProperty().asString());
		
		resetGame();
		
	}

	private void resetGame() {
		gameOver = false;
		snake.setScore(0);
		snake.resetBody();
		food.reset();
	}

	@Override
	protected void gameLoop(double diff) {
		if (gameOver) {
			gc.setFill(Color.RED);
			gc.setFont(new Font("", 50));
			gc.fillText("Game Over", 175, 300);
			gc.setFill(Color.YELLOWGREEN);
			gc.setFont(new Font("", 18));
			gc.fillText("Press Enter to play", 225, 400);
		} else {
			update(diff);
			collision(gc);
			render(gc);
		}
	}

	private void render(GraphicsContext gc) {
		background.render(gc);
		food.render(gc);
		snake.render(gc);
	}

	private void collision(GraphicsContext gc) {
		
		if (snake.eat(snake)) {
			gameOver = true;
		}
		
		if (snake.eat(food)) {
			snake.grow();
			snake.increaseScore(1);
			snake.increaseSpeed(0.25);
			food.reset();
		}
		
		if (snake.getHead().x < 0) snake.getHead().x = getGridWidth() - 1;
		if (snake.getHead().x >= getGridWidth()) snake.getHead().x = 0;
		if (snake.getHead().y < 0) snake.getHead().y = getGridHeight() - 1;
		if (snake.getHead().y >= getGridHeight()) snake.getHead().y = 0;
		
	}

	private void update(double diff) {
		snake.update(diff);
	}

}
