package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import dad.javafx.retrogamefx.games.Wall;
import dad.javafx.retrogamefx.games.brickbreaker.Brick;
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
	private Sprite sprite;

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

	public boolean checkCollision(Sprite sprite) {

		if (intersects(sprite)) {
			// Collision player cambiar para diferentes juegos
			if (sprite instanceof Player) {

				System.out.println("colisión con player");
				setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
				setSpeed(getSpeed() + 1);

			}
			// Collision muros implementar choques laterales
			else if (sprite instanceof VerticalWall) {
				System.out.println("colisión con muro vertical");
				setDirection(new Point2D(-getDirection().getX(), getDirection().getY()));

			} else if (sprite instanceof HorizontalWall) {
				System.out.println("colisión con muro horizontal");
				setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
			} else if (sprite instanceof Brick) {
				System.out.println("colisión con brick");
				Brick brick = (Brick) sprite;

				// cocha por la Right y Left
				if (getX() + 19 <= brick.getX() || getX() + 1 >= brick.getX() + brick.getWidth()) {
					setDirection(new Point2D(-getDirection().getX(), getDirection().getY()));
				} else {// cocha por Top y Botton
					setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
				}

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

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
