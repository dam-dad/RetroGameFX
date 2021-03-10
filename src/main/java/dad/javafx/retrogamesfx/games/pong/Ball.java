package dad.javafx.retrogamesfx.games.pong;

import dad.javafx.retrogamesfx.games.Sprite;
import dad.javafx.retrogamesfx.games.Wall;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Ball extends Sprite<Pong> {

	private Point2D direction;
	private double speed = 500.0;
	private Color color;
	private double radio;

	public Ball(Pong world) {
		super(world);
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

	public boolean checkCollision(Sprite<Pong> sprite) {
		if (intersects(sprite)) {
			// Collision player cambiar para diferentes juegos
			if (sprite instanceof HumanRacket) {

				System.out.println("colisión con player");
				setDirection(new Point2D(-getDirection().getX(), getDirection().getY()));
				setSpeed(getSpeed() + 100);
				
			}
			// Collision muros implementar choques laterales
			else if ((sprite instanceof Wall) && ((Wall<Pong>)sprite).isHorizontal()) {
				
				System.out.println("colisión con muro horizontal");
				setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
				
			}
			return true;
		}
		return false;
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
