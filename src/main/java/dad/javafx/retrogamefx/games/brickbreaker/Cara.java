package dad.javafx.retrogamefx.games.brickbreaker;

import java.util.ArrayList;

import dad.javafx.retrogamefx.games.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Cara extends Sprite {
 public double x,y,w,h;
 public boolean d;
 public int fila,columna;
 private Map map;
 private ArrayList<Cara> Caras= new ArrayList<Cara>();
 public Cara(double X,double Y,double W,double H,boolean D,int Fila,int Columna) {
	 x=X;
	 y=Y;
	 w=W;
	 h=H;
	 d=D;
	 fila=Fila;
	 columna=Columna;
	 
 }
	@Override
	public void update(double diff) {
		// TODO Auto-generated method stub
	}



	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
