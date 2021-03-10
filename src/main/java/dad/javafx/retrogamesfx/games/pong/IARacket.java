package dad.javafx.retrogamesfx.games.pong;

import javafx.scene.paint.Color;

public class IARacket extends HumanRacket {

	private Ball ball;

	public IARacket(Pong world, Color color, Ball ball) {
		super(world, color);
		this.ball = ball;
		setWorld(world);
	}

	@Override
	public void update(double diff) {

		if (ball.getX() < getWorld().getWidth() - getWorld().getWidth() / 4) {
			setY(ball.getY() - getHeight() / 2);
		} else {
			if (ball.getY() > getY() + getHeight() / 2) {
				setY(getY() + 5);
			} else {
				setY(getY() - 5);
			}
		}

	}

}
