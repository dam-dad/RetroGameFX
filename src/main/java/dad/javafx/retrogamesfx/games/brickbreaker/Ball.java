package dad.javafx.retrogamesfx.games.brickbreaker;

import dad.javafx.retrogamesfx.games.Player;
import dad.javafx.retrogamesfx.games.Sprite;
import dad.javafx.retrogamesfx.games.Wall;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Ball extends Sprite<BrickBreaker> {

	private Point2D direction;
	private double speed = 500.0;
	private Color color;
	private double radio;
	private Sprite<BrickBreaker> sprite;

	public Ball(BrickBreaker world) {
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

	public boolean checkCollision(Sprite<BrickBreaker> sprite) {

		if (intersects(sprite)) {
			
			// colisión con player
			if (sprite instanceof Player) {

				System.out.println("colisión con player");
				setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
				setSpeed(getSpeed() + 10);

			}
			
			// colisión muros laterales
			else if (sprite instanceof Wall && !((Wall<?>)sprite).isHorizontal()) {
				
				System.out.println("colisión con muro lateral");
				setDirection(new Point2D(-getDirection().getX(), getDirection().getY()));

			} 
			
			// colisión muro superior
			else if (sprite instanceof Wall && ((Wall<?>)sprite).isHorizontal()) {
				
				System.out.println("colisión con muro superior");
				setDirection(new Point2D(getDirection().getX(), -getDirection().getY()));
				
			} 
			
			// colisión con un bloque
			else if (sprite instanceof Brick) {
				
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

	public Sprite<BrickBreaker> getSprite() {
		return sprite;
	}

	public void setSprite(Sprite<BrickBreaker> sprite) {
		this.sprite = sprite;
	}
}
