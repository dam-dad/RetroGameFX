package dad.javafx.retrogamefx.games.pong;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Sprite {

	private Color color;
	private IntegerProperty score = new SimpleIntegerProperty(0);

	public Player(Color color) {
		super();
		this.color = color;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());// palas
	}

	@Override
	public void update(long diff) {
		// TODO Auto-generated method stub

	}

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

}
