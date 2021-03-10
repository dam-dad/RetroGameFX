package dad.javafx.retrogamesfx.games.brickbreaker;

import dad.javafx.retrogamesfx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick extends Sprite<BrickBreaker> {

	private int imageNum;
	
	private static final Image RED = new Image("images/rojo.png");
	private static final Image BLUE = new Image("images/bluesmar.png");
	private static final Image JADE = new Image("images/green.png");
	private static final Image ORANGE = new Image("images/orange.png");
	private static final Image PINK = new Image("images/pink.png");
	private static final Image GREEN = new Image("images/veerde.png");
	private static final Image YELLOW = new Image("images/yellow.png");
	
	public static final Image [] IMAGES = { RED, BLUE, JADE, ORANGE, PINK, GREEN, YELLOW };

	public Brick(BrickBreaker world, int imageNum, double x, double y, double width, double height) {
		super(world);
		this.imageNum = imageNum;
		setBounds(x, y, width, height);
	}

	@Override
	public void update(double diff) {
	}

	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(IMAGES[imageNum], getX(), getY(), getWidth(), getHeight());
	}

}
