package dad.javafx.retrogamesfx.games.snake;

import java.util.Random;

import dad.javafx.retrogamesfx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Food extends Sprite<SnakeGame> {

	private int x, y;
	private Image apple;
	private Random rnd = new Random();
	
	public Food(SnakeGame world) {
		super(world);
		this.apple = new Image("images/manzanita.png");
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void update(double diff) {
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(apple, x * getWorld().getGridSize(), y * getWorld().getGridSize(), getWorld().getGridSize(), getWorld().getGridSize());
	}

	public void reset() {
		if (getWorld().getGridWidth() <= 0) return;
		setX(rnd.nextInt(getWorld().getGridWidth()));
		setY(rnd.nextInt(getWorld().getGridHeight()));
	}
	
}
