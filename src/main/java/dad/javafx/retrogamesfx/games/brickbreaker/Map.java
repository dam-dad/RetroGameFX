package dad.javafx.retrogamesfx.games.brickbreaker;

import dad.javafx.retrogamesfx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Map extends Sprite<BrickBreaker> {

	private Color color;

	public Map(BrickBreaker world, Color color) {
		super(world);
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
