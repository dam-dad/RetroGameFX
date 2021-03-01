package dad.javafx.retrogamesfx.games.laberinto;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.javafx.retrogamefx.base.App;
import dad.javafx.retrogamefx.games.GameScene;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.image.*;

public class Laberinto extends GameScene {

	// variables (objetos)
	private static final int width = 800;// 18x44.44 X
	private static final int height = 600;// 12x42.85 Y
	static int maxFilas = 14;
	static int maxColumnas = 18;
	static int anchoBloque = 44;
	static int altoBloque = 42;
	static Personaje pelota;
	static Mapa mapa = new Mapa();
	Random R;
	Image pared;
	Image camino;
	Image inicio;
	Image fin;
	Image victoria;

	// variables(juego)
	String[] laberinto;
	String[] semilla;
	String[] laberintoTransformado;
	static int x, y, x0, y0, xf, yf,aux, aux2, I;
	static char[] direccionesDisponibles = { ' ', ' ', ' ', ' ' };
	static int[] siguienteDireccion;
	char c, direccion;
	StringBuffer charChange, charChange2;
	boolean goNorth, goSouth, goEast, goWest;

	// view

	@FXML
	private StackPane view;

	@FXML
	private Canvas canvas;

	public Laberinto() {

		super("/fxml/LaberintoView.fxml",800,600);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		R = new Random();
		aux = R.nextInt(4) + 1;
		invocarMapa(aux);
		encontrarI();
		encontrarF();
		crearMapa();
		pelota = new Personaje((x0) * anchoBloque, (y0) * altoBloque, anchoBloque, altoBloque);
		x = x0;
		y = y0;

		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					goNorth(gc);
					break;
				case DOWN:
					goSouth(gc);
					break;
				case LEFT:
					goWest(gc);
					break;
				case RIGHT:
					goEast(gc);
					break;
				case ENTER:
					aux2 = R.nextInt(4) + 1;
System.out.println("aux= "+aux);
					if(aux2==aux) {
						aux=R.nextInt(4) + 1;;
					}
					System.out.println("aux= "+aux);
					invocarMapa(aux);
					encontrarI();
					encontrarF();
					x = x0;
					y = y0;
					if(aux!=1)crearMapa();
					pelota = new Personaje((x0) * anchoBloque, (y0) * altoBloque, anchoBloque, altoBloque);
					run(gc);
					break;
				case ESCAPE:
					App.gotToMain();
					break;

				}
			}

		});
//--------------------Iniciacion de imagenes Para evitar carga lenta-------------------//
		pared = new Image("sprites/pared.png");
		camino = new Image("sprites/camino.png");
		inicio = new Image("sprites/inicio.png");
		fin = new Image("sprites/fin.png");
		victoria = new Image("sprites/victoria.jpg");

//---------------------Preparar el mapa------------------//
		view.requestFocus();
		canvas.setFocusTraversable(true);
		canvas.requestFocus();
		render(gc);
		run(gc);

	}

//--------------Metodos heredados--------------//
	@Override
	public void play() {
		// timer.start();
	}

	

	private void run(GraphicsContext gc) {

		direccionesDisponibles = siguiente(x, y, gc);
		render(gc);
	}

	private void marcaCamino() {
		x = x0;
		y = y0;
		char ultimaDireccion = ' ', proximaDireccion = ' ';
		I = 0;

		do {
			proximaDireccion = recorrer(x, y, ultimaDireccion);
			siguienteDireccion = traducir(proximaDireccion, x, y);
			marcaCruze(x, y);
			x = siguienteDireccion[0];
			y = siguienteDireccion[1];
			ultimaDireccion = proximaDireccion;
			I++;
			if (I > 50)
				break;
		} while (x != xf | y != yf);// x != xf | y != yf
		x = x0;
		y = y0;

	}

	private void bloquearCamino() {
		x = x0;
		y = y0;
		char ultimaDireccion = ' ', proximaDireccion = ' ';
		I = 0;
		System.out.println("G");
		System.out.println("Mapa = " + aux);
		do {
			proximaDireccion = recorrer(x, y, ultimaDireccion);
			siguienteDireccion = traducir(proximaDireccion, x, y);
			cruze(x, y);
			caminos(x, y);

			if (I > 50)
				break;

			x = siguienteDireccion[0];
			y = siguienteDireccion[1];
			ultimaDireccion = proximaDireccion;
			I++;
		} while (x != xf | y != yf);// x != xf | y != yf
		x = x0;
		y = y0;
		System.out.println("H");
		marcaCamino();
		System.out.println("I");

	}

//--------------------Metodos iniciales------------------------//
	// Busca donde esta el principio
	public void encontrarI() {
		for (int i = 0; i < laberinto.length - 1; i++) {
			c = laberinto[i].charAt(1);
			switch (c) {
			case 'I':
				x0 = 1;
				y0 = i;
				System.out.println(x0 + "," + y0);
				break;
			}
		}
	}

	// Busca donde esta el fin
	public void encontrarF() {
		for (int i = 0; i < laberinto.length - 1; i++) {
			c = laberinto[i].charAt(laberinto[0].length() - 2);
			switch (c) {
			case 'F':
				xf = laberinto[0].length() - 2;
				yf = i;
				System.out.println(xf + "," + yf);
				break;
			}
		}
	}

	public void invocarMapa(int aux) {

		laberinto = mapa.getLaberinto();
		laberintoTransformado = laberinto;
		switch (aux) {
		case 1:
			laberinto = mapa.getLaberinto();
			laberintoTransformado = laberinto;
			break;
		case 2:
			laberinto = mapa.getSemilla(aux);
			laberintoTransformado = laberinto;

			break;
		case 3:
			laberinto = mapa.getSemilla(aux);
			laberintoTransformado = laberinto;

			break;
		case 4:
			laberinto = mapa.getSemilla(aux);
			laberintoTransformado = laberinto;

			break;
		case 5:
			laberinto = mapa.getSemilla(aux);
			laberintoTransformado = laberinto;

			break;

		}
	}

	public void crearMapa() {
		System.out.println("F");
		bloquearCamino();
		System.out.println("T");
		cambio();
		System.out.println("U");
		avanceYSello();
		System.out.println("V");
		cambiarFinal();
		System.out.println("W");

		laberinto = laberintoTransformado;
	}

//-------------------Metodos de movimiento-----------------------//
	// devuelve las siguientes coordenadas caminables
	public char[] siguiente(int X1, int Y1, GraphicsContext gc) {
		char[] direcciones = { ' ', ' ', ' ', ' ' };
		if (x != xf | y != yf) {

			// determinamos los caminos posibles para avanzar

			// Comprueba el encima
			if (laberinto[Y1 - 1].charAt(X1) == 'C') {
				direcciones[0] = 'N';
			}
			// Comprueba el debajo
			if (laberinto[Y1 + 1].charAt(X1) == 'C') {
				direcciones[1] = 'S';
			}
			// Comprueba el lado derecho
			if (laberinto[Y1].charAt(X1 + 1) == 'C' | laberinto[Y1].charAt(X1 + 1) == 'F') {
				direcciones[2] = 'E';
			}
			// Comprueba el lado izquierdo
			if (laberinto[Y1].charAt(X1 - 1) == 'C') {
				direcciones[3] = 'W';
			}
		} else {
			gc.drawImage(victoria, 0, 0, width, height);
		}

		return direcciones;
	}

	public char[] secundarios(int X1, int Y1) {
		char[] direcciones = { ' ', ' ', ' ', ' ' };

		// determinamos los caminos posibles para avanzar

		// Comprueba el encima
		if (laberinto[Y1 - 1].charAt(X1) == '.') {
			direcciones[0] = 'N';
		}
		// Comprueba el debajo
		if (laberinto[Y1 + 1].charAt(X1) == '.') {
			direcciones[1] = 'S';
		}
		// Comprueba el lado derecho
		if (laberinto[Y1].charAt(X1 + 1) == '.') {
			direcciones[2] = 'E';
		}
		// Comprueba el lado izquierdo
		if (laberinto[Y1].charAt(X1 - 1) == '.') {
			direcciones[3] = 'W';
		}

		return direcciones;

	}

	public char recorrer(int X1, int Y1, char direccion2) {
		char direccion3, direccion1 = ' ';
		direccion2 = invertir(direccion2);

		// determinamos los caminos posibles para avanzar

		// Comprueba el encima
		if (laberinto[Y1 - 1].charAt(X1) == 'C') {
			direccion3 = 'N';
			if (direccion3 != direccion2)
				direccion1 = 'N';
		}
		// Comprueba el debajo
		if (laberinto[Y1 + 1].charAt(X1) == 'C') {
			direccion3 = 'S';
			if (direccion3 != direccion2)
				direccion1 = 'S';
		}
		// Comprueba el lado derecho
		if ((laberinto[Y1].charAt(X1 + 1) == 'C' | laberinto[Y1].charAt(X1 + 1) == 'F')) {
			direccion3 = 'E';
			if (direccion3 != direccion2)
				direccion1 = 'E';
		}
		// Comprueba el lado izquierdo
		if (laberinto[Y1].charAt(X1 - 1) == 'C') {
			direccion3 = 'W';
			if (direccion3 != direccion2)
				direccion1 = 'W';
		}
		return direccion1;

	}

	// cuenta las casillas de camino cercanas
	public int recuento(int X1, int Y1) {

		int aux = 0;
		// determinamos cercanos que molesten a la creacion
		// Comprueba el encima
		if (laberintoTransformado[Y1 - 1].charAt(X1) == 'C' | laberintoTransformado[Y1 - 1].charAt(X1) == '1') {
			aux++;
		}
		// Comprueba el encima izquierda
		if (laberintoTransformado[Y1 - 1].charAt(X1 - 1) == 'C' | laberintoTransformado[Y1 - 1].charAt(X1 - 1) == '1') {
			aux++;
		}
		// Comprueba el encima derecha
		if (laberintoTransformado[Y1 - 1].charAt(X1 + 1) == 'C' | laberintoTransformado[Y1 - 1].charAt(X1 + 1) == '1') {
			aux++;
		}
		// Comprueba el debajo
		if (laberintoTransformado[Y1 + 1].charAt(X1) == 'C' | laberintoTransformado[Y1 + 1].charAt(X1) == '1') {
			aux++;
		}
		// Comprueba el debajo izquierda
		if (laberintoTransformado[Y1 + 1].charAt(X1 - 1) == 'C' | laberintoTransformado[Y1 + 1].charAt(X1 - 1) == '1') {
			aux++;
		}
		// Comprueba el debajo derecha
		if (laberintoTransformado[Y1 + 1].charAt(X1 + 1) == 'C' | laberintoTransformado[Y1 + 1].charAt(X1 + 1) == '1') {
			aux++;
		}
		// Comprueba el lado derecho
		if (laberintoTransformado[Y1].charAt(X1 + 1) == 'C' | laberintoTransformado[Y1].charAt(X1 + 1) == '1') {
			aux++;
		}
		// Comprueba el lado izquierdo
		if (laberintoTransformado[Y1].charAt(X1 - 1) == 'C' | laberintoTransformado[Y1].charAt(X1 - 1) == '1') {
			aux++;
		}
		if (recuento2(X1, Y1))
			aux = 9;

		return aux;
	}

	public boolean recuento2(int X1, int Y1) {
		boolean aux = false;
		if (laberintoTransformado[Y1 - 1].charAt(X1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1 - 1].charAt(X1 - 1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1 - 1].charAt(X1 + 1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1 + 1].charAt(X1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1 + 1].charAt(X1 - 1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1 + 1].charAt(X1 + 1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1].charAt(X1 + 1) == '2') {
			aux = true;
		}
		if (laberintoTransformado[Y1].charAt(X1 - 1) == '2') {
			aux = true;
		}
		return aux;
	}

	public void cambio() {
		for (int fila = 0; fila <= maxFilas - 1; fila++) {
			for (int columna = 0; columna <= maxColumnas - 1; columna++) {
				if (laberintoTransformado[fila].charAt(columna) == 'M') {

					direccionesDisponibles = secundarios(columna, fila);
					avance(columna, fila);
					cruze(x, y);
					caminos(x, y);

				}
			}
		}

	}

	public void cambiar12() {
		for (int fila = 1; fila <= maxFilas - 1; fila++) {
			for (int columna = 1; columna <= maxColumnas - 1; columna++) {
				if (laberintoTransformado[fila].charAt(columna) == '1') {
					charChange = new StringBuffer(laberintoTransformado[fila]);
					charChange.setCharAt(columna, 'C');
					laberintoTransformado[fila] = new String(charChange.toString());
				}
				if (laberintoTransformado[fila].charAt(columna) == '2') {
					charChange = new StringBuffer(laberintoTransformado[fila]);
					charChange.setCharAt(columna, '1');
					laberintoTransformado[fila] = new String(charChange.toString());
				}
			}
		}

	}

	public void cambiarFinal() {
		for (int fila = 1; fila <= maxFilas - 1; fila++) {
			for (int columna = 1; columna <= maxColumnas - 1; columna++) {
				if (laberintoTransformado[fila].charAt(columna) == '.') {
					charChange = new StringBuffer(laberintoTransformado[fila]);
					charChange.setCharAt(columna, 'P');
					laberintoTransformado[fila] = new String(charChange.toString());
				}

			}
		}

	}

	public void avanceYSello() {
		boolean terminar;

		int i = 0;
		do {

			terminar = true;
			for (int fila = 1; fila <= maxFilas - 1; fila++) {
				for (int columna = 1; columna <= maxColumnas - 1; columna++) {
					if (laberintoTransformado[fila].charAt(columna) == '1') {

						direccionesDisponibles = secundarios(columna, fila);

						checkAvance(columna, fila, direccion);

						terminar = false;
					}
				}
			}
			cambiar12();
			i++;

		} while (!terminar);
	}

	public void avance(int columna, int fila) {
		int cantidad = 3, aux2 = check();
		if (aux2 == 0) {
			insert(laberintoTransformado, columna, fila, 'P');

		}
		if (direccionesDisponibles[0] == 'N') {

			direccion = 'N';
			if (recuento(columna, fila) < cantidad) {
				insert(laberintoTransformado, columna, fila, '1');

			} else {
				insert(laberintoTransformado, columna, fila, 'P');

			}

		}
		if (direccionesDisponibles[1] == 'S') {

			direccion = 'S';
			if (recuento(columna, fila) < cantidad) {
				insert(laberintoTransformado, columna, fila, '1');

			} else {
				insert(laberintoTransformado, columna, fila, 'P');

			}
		}
		if (direccionesDisponibles[2] == 'E') {

			direccion = 'E';
			if (recuento(columna, fila) < cantidad) {
				insert(laberintoTransformado, columna, fila, '1');

			} else {
				insert(laberintoTransformado, columna, fila, 'P');

			}

		}
		if (direccionesDisponibles[3] == 'W') {

			direccion = 'W';
			if (recuento(columna, fila) < cantidad) {
				insert(laberintoTransformado, columna, fila, '1');

			} else {
				insert(laberintoTransformado, columna, fila, 'P');

			}

		}

	}

	public int check() {
		int aux = 0, aux2 = 0;
		if (direccionesDisponibles[0] == 'N') {
			aux2++;
		}
		if (direccionesDisponibles[1] == 'S') {
			aux2++;
		}
		if (direccionesDisponibles[2] == 'E') {
			aux2++;
		}
		if (direccionesDisponibles[3] == 'W') {
			aux2++;
		}
		if (aux2 == 0)
			aux = 0;
		if (aux2 == 1)
			aux = 1;
		if (aux2 == 2)
			aux = RNG2();
		if (aux2 == 3)
			aux = RNG3();
		return aux;
	}

	public void checkAvance(int columna, int fila, char direccion2) {
		int aux = 0, aux2 = check(), aux3 = 1, Fila = fila, Columna = columna, cantidad = 0;
		// direccion = ' ';
		direccion2 = invertir(direccion2);
		boolean Realizado = false;
		if (direccion == direccion2)
			cantidad = 2;
		else
			cantidad = 3;
		if (aux3 == 0) {
			insert(laberintoTransformado, columna, fila, 'C');
		}
		if (direccionesDisponibles[0] == 'N') {
			if (aux2 == aux3) {
				fila--;
				direccion = 'N';
				if (recuento(columna, fila) < cantidad) {
					insert(laberintoTransformado, columna, fila, '2');

				} else {
					insert(laberintoTransformado, columna, fila, 'P');

				}
			} else
				aux3++;
		}
		if (direccionesDisponibles[1] == 'S') {
			if (aux2 == aux3) {
				fila++;
				direccion = 'S';
				if (recuento(columna, fila) < cantidad) {
					insert(laberintoTransformado, columna, fila, '2');

				} else {
					insert(laberintoTransformado, columna, fila, 'P');

				}
			} else
				aux3++;
		}
		if (direccionesDisponibles[2] == 'E') {
			if (aux2 == aux3) {
				columna++;
				direccion = 'E';
				if (recuento(columna, fila) < cantidad) {
					insert(laberintoTransformado, columna, fila, '2');

				} else {
					insert(laberintoTransformado, columna, fila, 'P');

				}
			} else
				aux3++;
		}
		if (direccionesDisponibles[3] == 'W') {
			if (aux2 == aux3) {
				columna--;
				direccion = 'W';
				if (recuento(columna, fila) < cantidad) {
					insert(laberintoTransformado, columna, fila, '2');
					recuento2(columna, fila);
				} else {
					insert(laberintoTransformado, columna, fila, 'P');

				}
			} else
				aux3++;
		}

	}

//----------------------------movimiento por keys----------------------------------//
	private void goNorth(GraphicsContext gc) {
		if (direccionesDisponibles[0] == 'N') {// a�adir transicion
			x += 0;
			y -= 1;
			pelota.setX((x) * anchoBloque);
			pelota.setY((y) * altoBloque);

		}
		render(gc);
		direccionesDisponibles = siguiente(x, y, gc);
	}

	private void goSouth(GraphicsContext gc) {
		if (direccionesDisponibles[1] == 'S') {// a�adir transicion
			x += 0;
			y += 1;
			pelota.setX((x) * anchoBloque);
			pelota.setY((y) * altoBloque);

		}
		render(gc);
		direccionesDisponibles = siguiente(x, y, gc);

	}

	private void goEast(GraphicsContext gc) {
		if (direccionesDisponibles[2] == 'E') {// a�adir transicion
			x += 1;
			y -= 0;
			pelota.setX((x) * anchoBloque);
			pelota.setY((y) * altoBloque);
		}

		render(gc);
		direccionesDisponibles = siguiente(x, y, gc);
	}

	private void goWest(GraphicsContext gc) {
		if (direccionesDisponibles[3] == 'W') {// a�adir transicion
			x -= 1;
			y -= 0;
			pelota.setX((x) * anchoBloque);
			pelota.setY((y) * altoBloque);
		}
		render(gc);
		direccionesDisponibles = siguiente(x, y, gc);
	}

//---------------------------Dibujado del mapa-------------------------------------//
	public void render(GraphicsContext gc) {
		for (int fila = 0; fila <= maxFilas - 1; fila++) {
			for (int columna = 0; columna <= maxColumnas - 1; columna++) {
				// pintarCuadrado(gc);
				if (laberinto[fila].charAt(columna) == 'P') {
					gc.setFill(Color.DARKGREEN);
					gc.drawImage(pared, columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				}
				if (laberinto[fila].charAt(columna) == '+') {
					gc.setFill(Color.DARKGREEN);
					gc.fillRect(columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				}
				if (laberinto[fila].charAt(columna) == 'C' | laberinto[fila].charAt(columna) == '1') {
					gc.setFill(Color.LIGHTBLUE);
					gc.drawImage(camino, columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				}
				if (laberinto[fila].charAt(columna) == 'I') {
					gc.setFill(Color.GREEN);
					gc.drawImage(inicio, columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				}
				if (laberinto[fila].charAt(columna) == 'F') {
					gc.setFill(Color.RED);
					gc.drawImage(fin, columna * anchoBloque, fila * altoBloque, anchoBloque, altoBloque);
				}
			}
		}
		pelota.render(gc);
	}

//----------------------utiles varios----------------------//
	public int[] traducir(char C, int X1, int Y1) {
		switch (C) {
		case 'N':
			Y1--;
			break;
		case 'S':
			Y1++;
			break;
		case 'E':
			X1++;
			break;
		case 'W':
			X1--;
			break;
		}
		int[] aux = { X1, Y1 };
		return aux;
	}

	public boolean RNG(int n) {
		boolean aux = false;
		int aux2 = 1;
		if ((aux2 + R.nextInt(99)) > (100 - n)) {
			aux = true;
		}
		return aux;
	}

	public int RNG3() {
		int aux2 = 1;
		aux2 += R.nextInt(99);
		if (aux2 > 67) {
			aux2 = 1;
		}
		if (67 >= aux2 & aux2 > 33) {
			aux2 = 2;
		}
		if (33 > aux2) {
			aux2 = 3;
		}
		return aux2;
	}

	public int RNG2() {
		int aux2 = 1;
		aux2 += R.nextInt(99);
		if (aux2 > 50) {
			aux2 = 1;
		} else {
			aux2 = 2;
		}
		return aux2;
	}

	public void insert(String[] array, int X1, int Y1, char C) {
		charChange = new StringBuffer(array[Y1]);
		charChange.setCharAt(X1, C);
		array[Y1] = new String(charChange.toString());
	}

//----------Herramientas Bloqueo-----------------------//
	// Bloquea caminos horizontales y verticales
	public void caminos(int X1, int Y1) {
		if ((X1 != x0 | Y1 != y0) & (X1 != xf | Y1 != yf)) {
			if (esVertical(X1, Y1)) {
				protejeHorizontal(X1, Y1);
			} else if (esHorizontal(X1, Y1)) {
				protejeVertical(X1, Y1);
			}
		}
	}

	// compreba si encima y debajo hay bloques normales o protegidos
	public boolean esHorizontal(int X1, int Y1) {

		if (arriba(X1, Y1) & abajo(X1, Y1))
			return true;
		else
			return false;

	}

	// Comprueba el lado izquierdo y derecho
	public boolean esVertical(int X1, int Y1) {
		if (izquierda(X1, Y1) & derecha(X1, Y1))
			return true;
		else
			return false;
	}

	// bloquea cruzes y los marca
	private void cruze(int X1, int Y1) {
		if (NW(X1, Y1) | NE(X1, Y1) | SW(X1, Y1) | SE(X1, Y1)) {

			insert(laberintoTransformado, X1, Y1, 'C');
			protejeCruze(X1, Y1);

			//

		}
	}

	// --------------Herramientas direccionales--------------//
	public void marcaCruze(int X1, int Y1) {

		if (laberintoTransformado[Y1 - 1].charAt(X1) == '.') {
			insert(laberintoTransformado, X1, Y1 - 1, 'M');

		}
		if (laberintoTransformado[Y1 + 1].charAt(X1) == '.') {
			insert(laberintoTransformado, X1, Y1 + 1, 'M');
		}
		if (laberintoTransformado[Y1].charAt(X1 - 1) == '.') {
			insert(laberintoTransformado, X1 - 1, Y1, 'M');
		}
		if (laberintoTransformado[Y1].charAt(X1 + 1) == '.') {
			insert(laberintoTransformado, X1 + 1, Y1, 'M');
		}

	}

	// comprueba si tiene encima un bloque normal o protegido
	public boolean arriba(int X1, int Y1) {
		if (laberintoTransformado[Y1 - 1].charAt(X1) == '.')
			return true;
		else
			return false;
	}

	// comprueba si tiene debajo un bloque normal o protegido
	public boolean abajo(int X1, int Y1) {
		if (laberintoTransformado[Y1 + 1].charAt(X1) == '.')
			return true;
		else
			return false;
	}

	// comprueba si tiene a la izquierda un bloque normal o protegido
	public boolean izquierda(int X1, int Y1) {
		if (laberintoTransformado[Y1].charAt(X1 - 1) == '.')
			return true;
		else
			return false;
	}

	// comprueba si tiene a la derecha un bloque normal o protegido
	public boolean derecha(int X1, int Y1) {
		if (laberintoTransformado[Y1].charAt(X1 + 1) == '.')
			return true;
		else
			return false;
	}

	// revisa North-West para revisar si puede haber cruze
	public boolean NW(int X1, int Y1) {
		if (izquierda(X1, Y1) & arriba(X1, Y1))
			return true;
		else
			return false;
	}

	// revisa North-East para revisar si puede haber cruze
	public boolean NE(int X1, int Y1) {
		if (derecha(X1, Y1) & arriba(X1, Y1))
			return true;
		else
			return false;
	}

	// revisa South-West para revisar si puede haber cruze
	public boolean SW(int X1, int Y1) {
		if (izquierda(X1, Y1) & abajo(X1, Y1))
			return true;
		else
			return false;
	}

	// revisa South-East para revisar si puede haber cruze
	public boolean SE(int X1, int Y1) {
		if (derecha(X1, Y1) & abajo(X1, Y1))
			return true;
		else
			return false;
	}

	// Protege los bloques correspondientes a un cruze
	private void protejeCruze(int X1, int Y1) {
		// 1 * 2
		// * H *
		// 3 * 4

		// protege la casilla arriba izquierda 1
		if (laberintoTransformado[Y1 - 1].charAt(X1 - 1) == '.') {
			insert(laberintoTransformado, X1 - 1, Y1 - 1, 'P');
		}

		// protege la casilla arriba derecha 2
		if (laberintoTransformado[Y1 - 1].charAt(X1 + 1) == '.') {
			insert(laberintoTransformado, X1 + 1, Y1 - 1, 'P');
		}

		// protege la casilla abajo izquierda 3
		if (laberintoTransformado[Y1 + 1].charAt(X1 - 1) == '.') {
			insert(laberintoTransformado, X1 - 1, Y1 + 1, 'P');
		}

		// protege la casilla abajo derecha 4
		if (laberintoTransformado[Y1 + 1].charAt(X1 + 1) == '.') {
			insert(laberintoTransformado, X1 + 1, Y1 + 1, 'P');
		}

	}

	// Protege los bloques correspondientes a un camino Ascendente
	private void protejeHorizontal(int X1, int Y1) {
		// 1 H 2

		// Protege el lado izquierdo 1
		if (laberintoTransformado[Y1].charAt(X1 - 1) == '.') {
			insert(laberintoTransformado, X1 - 1, Y1, 'P');
		}
		// Protege el lado derecho 2
		if (laberintoTransformado[Y1].charAt(X1 + 1) == '.') {
			insert(laberintoTransformado, X1 + 1, Y1, 'P');
		}
	}

	// Protege los bloques correspondientes a un camino Lateral
	private void protejeVertical(int X1, int Y1) {
		// 1
		// H
		// 2

		// Protege encima 1
		if (laberintoTransformado[Y1 - 1].charAt(X1) == '.') {
			insert(laberintoTransformado, X1, Y1 - 1, 'P');
		}
		// Protege debajo 2
		if (laberintoTransformado[Y1 + 1].charAt(X1) == '.') {
			insert(laberintoTransformado, X1, Y1 + 1, 'P');
		}

	}

	// Invierte la letra de Direccion que se le de
	public char invertir(char C) {
		switch (C) {
		case 'N':
			C = 'S';
			break;
		case 'S':
			C = 'N';
			break;
		case 'E':
			C = 'W';
			break;
		case 'W':
			C = 'E';
			break;
		}
		return C;
	}

	@Override
	protected void gameLoop(double diff) {
		// TODO Auto-generated method stub
		
	}
}
