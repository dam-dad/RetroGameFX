package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Brick extends Sprite {
	private int color;

	public Brick(int color, double x, double y, double width, double height) {
		super();
		this.color = color;
		setBounds(x, y, width, height);
	}

	@Override
	public void update(double diff) {
	}

	Image red = new Image("images/rojo.png");
	Image blue = new Image("images/bluesmar.png");
	Image jade = new Image("images/green.png");
	Image orange = new Image("images/orange.png");
	Image pink = new Image("images/pink.png");
	Image verde = new Image("images/veerde.png");
	Image yellow = new Image("images/yellow.png");

	@Override
	public void render(GraphicsContext gc) {
		// gc.setFill(Color.RED);
		// gc.fillRect(getX(), getY(), getWidth(), getHeight());
		switch (color) {
		case 1:
			gc.drawImage(pink, getX(), getY(), getWidth(), getHeight());
			break;
		case 2:
			gc.drawImage(red, getX(), getY(), getWidth(), getHeight());
			break;
		case 3:
			gc.drawImage(orange, getX(), getY(), getWidth(), getHeight());
			break;
		case 4:
			gc.drawImage(yellow, getX(), getY(), getWidth(), getHeight());
			break;
		case 5:
			gc.drawImage(verde, getX(), getY(), getWidth(), getHeight());
			break;
		case 6:
			gc.drawImage(jade, getX(), getY(), getWidth(), getHeight());
			break;
		case 7:
			gc.drawImage(blue, getX(), getY(), getWidth(), getHeight());
			break;
		}
	}

}
