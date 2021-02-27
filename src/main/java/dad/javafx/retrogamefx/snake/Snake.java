package dad.javafx.retrogamefx.snake;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Snake extends Sprite {
	private Color color;
	private IntegerProperty score = new SimpleIntegerProperty(0);
	public Snake(Color color) {
		super();
		this.color = color;
	}
	
	@Override
	public void update(double diff) {
		
	}
		// TODO Auto-generated method stub
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
	

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());// palas
	}

}
