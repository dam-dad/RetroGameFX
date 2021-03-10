package dad.javafx.retrogamesfx.games.snake;

import java.util.ArrayList;
import java.util.List;

import dad.javafx.retrogamesfx.games.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Snake extends Player<SnakeGame> {

	private double totalDiff = 0.0;
	
	private boolean selfEatable = false;
	private List<SnakePiece> pieces = new ArrayList<>();
	private Direction direction = Direction.LEFT;
	private Image image;
	private double speed = 2.0;

	public Snake(SnakeGame world) {
		super(world);
		this.image = new Image("images/serpentilla.png");
		resetBody();
	}

	public void resetBody() {
		selfEatable = false;
		pieces.clear();
		for (int i = 0; i < 3; i++)
			pieces.add(new SnakePiece(getWorld().getGridWidth() / 2, getWorld().getGridHeight() / 2));
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public List<SnakePiece> getPieces() {
		return pieces;
	}

	@Override
	public void render(GraphicsContext gc) {
		for (SnakePiece p : pieces) {
			gc.drawImage(image, p.x * getWorld().getGridSize(), p.y * getWorld().getGridSize(), getWorld().getGridSize() - 2, getWorld().getGridSize() - 2);
		}
	}
	
	public SnakePiece getHead() {
		return pieces.get(0);
	}

	@Override
	public void update(double diff) {
		if (totalDiff > 1/speed) { 
			partsFollow();
			switch (direction) {
			case UP:	getHead().y--; break;
			case DOWN:	getHead().y++; break;
			case LEFT:	getHead().x--; break;
			case RIGHT: getHead().x++; break;
			}
			selfEatable = true;
			totalDiff = 0.0;
		}
		totalDiff += diff;
	}
	
	private void partsFollow() {
		for (int i = pieces.size() - 1; i >= 1; i--) {
			pieces.get(i).x = pieces.get(i - 1).x;
			pieces.get(i).y = pieces.get(i - 1).y;
		}
	}

	public void grow() {
		pieces.add(new SnakePiece());
	}
	
	public boolean eat(Food food) {
		return pieces.stream().anyMatch(p -> p.collide(food));
	}
	
	public boolean eat(Snake snake) {
		return selfEatable ? snake.getPieces().stream().filter(p -> p != this.pieces.get(0)).anyMatch(p -> this.pieces.get(0).collide(p)) : false;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void increaseSpeed(double inc) {
		setSpeed(getSpeed() + inc);
	}

}
