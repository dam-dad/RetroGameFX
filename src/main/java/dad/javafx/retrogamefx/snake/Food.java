package dad.javafx.retrogamefx.snake;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Food extends Sprite {
	private Color color;
	int x,y;
	
	public Food(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public double getX() {
	return x;
	}

	public void setX(int x) {
	this.x = x;
}

	public double getY() {
	return y;
	}

	public void setY(int y) {
	this.y = y;
	}
	
	@Override
	public void update(double diff) {
	}

	@Override
	public void render(GraphicsContext gc) {
		color=Color.WHITE;
		gc.setFill(color);
		gc.fillOval(x*30, y*30, 30, 30);
	}

}
