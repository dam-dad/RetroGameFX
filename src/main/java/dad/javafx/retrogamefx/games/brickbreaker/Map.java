package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Map extends Sprite{
	private double maxFilas = 10;
	private double maxColumnas = 20;
	private Bricks[][] Bloques;
	
	@Override
	public void update(double diff) {

		
	}
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}

public Bricks[][] getBloques() {
	return Bloques;
}
public void setBloques(Bricks[][] bloques) {
	Bloques = bloques;
}

public double getMaxFilas() {
	return maxFilas;
}

public void setMaxFilas(double maxFilas) {
	this.maxFilas = maxFilas;
}


public double getMaxColumnas() {
	return maxColumnas;
}

public void setMaxColumnas(double maxColumnas) {
	this.maxColumnas = maxColumnas;
}

}

