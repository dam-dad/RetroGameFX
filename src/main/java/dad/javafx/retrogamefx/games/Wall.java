package dad.javafx.retrogamefx.games;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Wall extends Sprite {

	private boolean horizontal = false;
	
	public Wall(boolean horitzon) {
		super();
	}

	@Override
	public void update(double diff) {

	}

	@Override
	public void render(GraphicsContext gc) {

	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

}
