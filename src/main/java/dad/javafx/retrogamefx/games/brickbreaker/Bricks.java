package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bricks extends Sprite {
	private Color color;
	private Map map;
	private double anchoBloque = width / map.getMaxColumnas();
	private double altoBloque = height / map.getMaxFilas();
	public Bricks() {
		super();
		this.color = Color.DEEPSKYBLUE;
	}
	@Override
	public void update(double diff) {

	}

	@Override
	public void render(GraphicsContext gc) {
		
	}

}
