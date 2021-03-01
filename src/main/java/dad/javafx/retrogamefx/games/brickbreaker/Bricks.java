package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bricks extends Sprite {
	private Color color;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Bricks(Color color, double x, double y, double width, double height) {
		super();
		this.x= x;
		this.y= y;
		this.width= width;
		this.height= height;
		this.color = color;
	}
	@Override
	public void update(double diff) {}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		//gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	

}
