package dad.javafx.retrogamefx.games.brickbreaker;

import java.util.ArrayList;

import dad.javafx.retrogamefx.games.Sprite;
import dad.javafx.retrogamefx.games.pong.Ball;
import javafx.scene.canvas.GraphicsContext;

public class Cara extends Sprite {
 public double x,y,w,h;
 public boolean d;
 public int fila,columna;
 private Map map;
 private ArrayList<Cara> Caras;
 Cara = new ArrayList();
 public Cara(double X,double Y,double W,double H,boolean D,int Fila,int Columna) {
	 x=X;
	 y=Y;
	 w=W;
	 h=H;
	 d=D;
	 fila=Fila;
	 columna=Columna;
	 
 }
 
 public ArrayList<Cara> getCaras() {
		return Caras;
	}



	public void setCaras(ArrayList<Cara> caras) {
		Caras = caras;
	}

 
//comprueba que los lados de los bricks sean null
	public void esNull(Bricks bloque,int fila, int columna) {
		if (izquierda(fila, columna)) {
			Caras.add(new Cara(bloque.getX(),bloque.getY(), bloque.getWidth(), bloque.getHeight(), true, fila, columna));
		}
		if (derecha(fila, columna)) {
			Caras.add(new Cara(bloque.getX() + bloque.getHeight(),bloque.getY(), bloque.getWidth(), bloque.getHeight(), true, fila, columna));
		}
		if (arriba(fila, columna)) {
			Caras.add(new Cara(bloque.getX(),bloque.getY(), bloque.getWidth(), bloque.getHeight(), false, fila, columna));
		}
		if (abajo(fila, columna)) {
			Caras.add(new Cara(bloque.getX(),bloque.getY()+bloque.getHeight(), bloque.getWidth(), bloque.getHeight(), false, fila, columna));
		}
		
		
	}
	
	//compara las caras con cada uno de los puntos de la pelota
	public int[] recorrerCaras(ArrayList<Cara> caras, Ball ball) {
		int[] aux = { -1, -1 };
		for (Cara x : caras) {
			// falta añadir colisiones de otros 3 puntos de la pelota y/o 
			//cambiar los puntos de colision de la pelota a su N,S,E,W
			if (comparadorCaraPunto(x, ball.getX(),ball.getY())) {
				aux[0] = x.fila;
				aux[1] = x.columna;
			}
		}
		return aux;
	}



	@Override
	public void update(double diff) {
		// TODO Auto-generated method stub
		//recorre la array de bricks para encontrar lados sin proteger de los bloques y llama a colision
			for (int fila = 1; fila <= map.getMaxFilas() - 2; fila++) {
				for (int columna = 1; columna <= map.getMaxColumnas() - 2; columna++) {
					if(map.getBloques()[fila][columna]!=null)esNull(map.getBloques()[fila][columna],fila,columna);
				}
			}
			//colision(Caras,ball);	
	}



	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}//UPDATE BLOQUES CERCANOS

//comprueba si tiene encima un bloque null
public boolean izquierda(int fila, int columna) {
	if (Bloques[fila][columna - 1] == null)
		return true;
	else
		return false;
}

//comprueba si tiene debajo un bloque null
public boolean derecha(int fila, int columna) {
	if (Bloques[fila][columna + 1] == null)
		return true;
	else
		return false;
}

//comprueba si tiene a la izquierda un bloque null
public boolean arriba(int fila, int columna) {
	if (Bloques[fila - 1][columna] == null)
		return true;
	else
		return false;
}

//comprueba si tiene a la derecha un bloque null
public boolean abajo(int fila, int columna) {
	if (Bloques[fila + 1][columna] == null)
		return true;
	else
		return false;
}
Bloques = new Bricks[maxFilas + 2][maxColumnas + 2];
