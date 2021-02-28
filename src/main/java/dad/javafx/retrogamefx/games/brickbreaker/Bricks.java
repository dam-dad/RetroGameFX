package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bricks extends Sprite {
	private Color color;
	
	
	public Bricks(Color color) {
		super();
		
		this.color = color;
	}
	@Override
	public void update(double diff) {}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
