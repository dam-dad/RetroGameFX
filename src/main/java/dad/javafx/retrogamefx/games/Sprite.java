package dad.javafx.retrogamefx.games;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class Sprite {

	private double x, y;
	private double width, height;

	public abstract void update(double diff);

	public abstract void render(GraphicsContext gc);

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

	public void setBounds(double x, double y, double width, double height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	public Shape getShape() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public boolean intersects(Sprite sprite) {
		return getShape().intersects(sprite.getShape().getBoundsInParent());
	}

}
