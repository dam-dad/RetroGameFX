package dad.javafx.retrogamefx.games.brickbreaker;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bricks extends Sprite {
	private Color color;
	private int Color;
	private double x;
	private double y;
	private double width;
	private double height;
	
	public Bricks(int color, double x, double y, double width, double height) {
		super();
		Color=color;
		this.x= x;
		this.y= y;
		this.width= width;
		this.height= height;
	}
	@Override
	public void update(double diff) {}

	@Override
	public void render(GraphicsContext gc) {
		gc.setFill(color);

		Image red= new Image("sprites/red.png");
		Image green= new Image("sprites/green.png");
		
		switch(Color) {
		case 1:gc.drawImage(red,getX(), getY(), getWidth(), getHeight());
			break;
		case 2:gc.drawImage(green,getX(), getY(), getWidth(), getHeight());
			break;
		case 3: //gc.drawImage(,getX(), getY(), getWidth(), getHeight());
			break;
		case 4:	//gc.drawImage(,getX(), getY(), getWidth(), getHeight());
		
		}
		//gc.drawImage(,getX(), getY(), getWidth(), getHeight());
		//gc.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	

}
