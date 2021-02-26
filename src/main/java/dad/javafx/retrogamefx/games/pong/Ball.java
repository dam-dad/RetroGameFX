package dad.javafx.retrogamefx.games.pong;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Ball extends Sprite {

	private Point2D direction;
	private double speed = 500.0;
	private Color color;
	private double radio;

	public Ball() {
		super();
		this.color = Color.WHITE;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setRadio(double radio) {
		setWidth(radio * 2);
		setHeight(radio * 2);
		this.radio = radio;
	}

	public double getRadio() {
		return radio;
	}

	public Point2D getDirection() {
		return direction;
	}

	public void setDirection(Point2D direction) {
		this.direction = direction;
	}
	
	@Override
	public Shape getShape() {
		return new Ellipse(getX(), getY(), getRadio(), getRadio());
	}

	public void checkCollision(Sprite sprite) {
		if (intersects(sprite)) {
			
			if (sprite instanceof Player) {
				System.out.println("colisión con player");
				setDirection(new Point2D(-getDirection().getX(), getDirection().getY()));
				setSpeed(getSpeed() + 1);
			}
			else if (sprite instanceof Wall) {
				System.out.println("colisión con muro");
				setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
			}

			
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(getX() - getRadio(), getY() - getRadio(), getWidth(), getHeight());
	}

	@Override
	public void update(double diff) {

		setX(getX() + direction.getX() * getSpeed() * diff);
		setY(getY() + direction.getY() * getSpeed() * diff);

	}
}
