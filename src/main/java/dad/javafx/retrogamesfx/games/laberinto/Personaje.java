package dad.javafx.retrogamesfx.games.laberinto;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Personaje extends Sprite{

	private Color color;

	public Personaje(double x,double y,double ancho,double alto) {
		super();
		this.color = Color.RED;
		setBounds(x,y,ancho,alto);
	}
	

	@Override
	public void render(GraphicsContext gc) {
		Image sprite= new Image("sprites/sprite.png");
		gc.drawImage(sprite,getX(), getY(), getWidth(), getHeight());
		
	}
	@Override
	public void update(double diff) {
		// TODO Auto-generated method stub
		
	}
	

}
