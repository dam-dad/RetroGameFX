package dad.javafx.retrogamefx.games.brickbreaker;

import java.util.ArrayList;
import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Map extends Sprite{
	 private Bricks brick;
	 private Color color;
	 private ArrayList<Bricks> bricks= new ArrayList<Bricks>();
	 
	 public Map(Color color) { 
				super();
				this.color = color;
	 }
	
	 
	 @Override
	public void update(double diff) {

		
	}
	
	 @Override
	public void render(GraphicsContext gc) {
			    fillMap();
			    gc.strokeRect(getX(), getY(), getWidth(), getHeight());
				gc.setFill(color);
				gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}
		public void fillMap() {
			 for(int i=0; i>20;i++){
				 bricks.add(new Bricks(color));
			 }
	  
	}
	}

