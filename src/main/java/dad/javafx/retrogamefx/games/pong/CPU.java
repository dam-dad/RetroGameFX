package dad.javafx.retrogamefx.games.pong;

import dad.javafx.retrogamefx.games.GameScene;
import javafx.scene.paint.Color;

public class CPU extends Player {

	private Ball ball;
	private GameScene world;

	public CPU(Color color, Ball ball, GameScene world) {
		super(color);
		this.ball = ball;
		this.world = world;
	}

	@Override
	public void update(double diff) {

		if (ball.getX() < world.getWidth() - world.getWidth() / 4) {
			setY(ball.getY() - getHeight() / 2);
		} else {// dificultad easy: playerTwoYPos += 1: playerTwoYPos - 1 media: playerTwoYPos
				// += 5: playerTwoYPos - 5 dificil: playerTwoYPos += 10: playerTwoYPos - 10
			if (ball.getY() > getY() + getHeight() / 2) {
				setY(getY() + 5);
			} else {
				setY(getY() - 5);
			}
		}

	}

}
