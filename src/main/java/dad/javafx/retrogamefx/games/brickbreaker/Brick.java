package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Brick extends Sprite {
	private Color color;

	public Brick(Color color, double x, double y, double width, double height) {
		super();
		this.color = color;
		this.setBounds(x,  y,  width,  height);
	}

	@Override
	public void update(double diff) {
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
