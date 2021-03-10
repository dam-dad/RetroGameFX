package dad.javafx.retrogamesfx.games.labyrinth;

import dad.javafx.retrogamesfx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Sprite<Labyrinth> {

	private Image character;

	public Player(Labyrinth world, double x, double y, double ancho, double alto) {
		super(world);
		setBounds(x, y, ancho, alto);
		character = new Image("images/character.png");
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(character, getX(), getY(), getWidth(), getHeight());

	}

	@Override
	public void update(double diff) {}

}
