package dad.javafx.retrogamefx.games.pong;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends Sprite {

	private double speed = 4.0;
	private Color color;
	private double radio;

	public Ball() {
		super();
		this.color = Color.WHITE;
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update(long diff) {
		// TODO Auto-generated method stub

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

	public boolean checkCollision(Player player) {
		return (((getX() + getRadio() > player.getX() + player.getWidth()) && getY() >= player.getY()
				&& getY() <= player.getY() + player.getHeight()));
	}

	public void collision(Player player) {
		if (checkCollision(player)) {
			setX(getSpeed() + 1 * Math.signum(getSpeed()));
			setY(getSpeed() + 1 * Math.signum(getSpeed()));
			setX(getX() * -1);
			// setY(getY()*1);
		}
	}
}
