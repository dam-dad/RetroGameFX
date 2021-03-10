package dad.javafx.retrogamesfx.games;

import javafx.scene.canvas.GraphicsContext;

public class Wall<T extends World> extends Sprite<T> {

	private boolean horizontal = false;
	
	public Wall(T world, boolean horizontal) {
		super(world);
		this.horizontal = horizontal;
	}

	@Override
	public void update(double diff) {}

	@Override
	public void render(GraphicsContext gc) {}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

}
