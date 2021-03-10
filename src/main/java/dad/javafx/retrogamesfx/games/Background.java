package dad.javafx.retrogamesfx.games;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Background<T extends World> extends Sprite<T> {
	
	private Color color;

	public Background(T world, Color color) {
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
