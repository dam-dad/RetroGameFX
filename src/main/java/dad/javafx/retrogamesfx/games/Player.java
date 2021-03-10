package dad.javafx.retrogamesfx.games;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player<T extends World> extends Sprite<T> {

	private Color color;
	private IntegerProperty score = new SimpleIntegerProperty(0);
	private IntegerProperty lives = new SimpleIntegerProperty(0);

	public Player(T world, Color color) {
		super(world);
		this.color = color;
	}
	
	public Player(T world) {
		this(world, Color.WHITE);
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(double diff) {}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public final IntegerProperty scoreProperty() {
		return this.score;
	}

	public final int getScore() {
		return this.scoreProperty().get();
	}

	public final void setScore(final int score) {
		this.scoreProperty().set(score);
	}
	
	public final IntegerProperty livesProperty() {
		return this.lives;
	}
	
	public final int getLives() {
		return this.livesProperty().get();
	}

	public final void setLives(final int lives) {
		this.livesProperty().set(lives);
	}
	
	public int increaseScore(int increase) {
		setScore(getScore() + increase);
		return getScore();
	}

	public int decreaseLives(int lives) {
		setLives(getLives() - lives);
		return getLives();
	}

	
}
