package base;


import java.net.URISyntaxException;
import java.net.URL;

import controllersTest.MainViewController;
import controllersTest.OpcionesViewController;
import controllersTest.SelectViewController;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class App extends Application{
	
	//Musica
	private static HiloMusica hiloMusica;
	private static double volumenMusica = 1;
	//Escenas
	private MainViewController controller;
	private SelectViewController SelectViewController;
	private OpcionesViewController OpcionesViewController;
	static Scene mainScene;
	static Scene seleccionScene;
	static Scene opcionesScene;
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;
		controller = new MainViewController();
		SelectViewController= new SelectViewController();
		OpcionesViewController= new OpcionesViewController();
		
		mainScene = new Scene(controller.getView(), 600, 400);
		seleccionScene = new Scene(SelectViewController.getView(), 600, 400);
		opcionesScene = new Scene(OpcionesViewController.getView(), 600, 400);
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("RetroGamesFX");
		primaryStage.show();
		primaryStage.setResizable(false);
		//primaryStage.getIcons().add(new Image("/images/classroom-24x24.png"));
		playMusica("MainSong");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	//Cambio de vetana a Main
	public static void CambiarAMain(){
		primaryStage.setScene(mainScene);
	}
	//Cambio de ventana a Selection
	public static void CambiarASeleccion(){
		primaryStage.setScene(seleccionScene);
	}
	//Cambio de ventana a Opciones
	public static void CambiarAOpciones(){
		primaryStage.setScene(opcionesScene);
	}
	
	//Musica inacabado
	public static double getVolumenMusica() {
		return volumenMusica;
	}
	
	public static void setVolumenMusica(double volumenMusica) {
		App.volumenMusica = volumenMusica;
	}
	
	
	public static void playMusica(String file) {
		hiloMusica = new HiloMusica(file);
		hiloMusica.play();
	}
	public static void stopMusica() {
	//	if cambiar a juego
		hiloMusica.parar();
	}
	
}
class HiloMusica extends Thread{
	String file;
	MediaPlayer player;
	
	public HiloMusica(String file) {
		this.file = file;
		
	}
	public void play() {
	URL path= getClass().getResource("/MusicotaRicota/" + file + ".mp3");
	Media media;
	try {
		media= new Media(path.toURI().toString());
		player=new MediaPlayer(media);
		player.setVolume(App.getVolumenMusica());
		player.setCycleCount(Transition.INDEFINITE);
		player.play();
	}catch(URISyntaxException e) {
		e.printStackTrace();
	}
	}
		public void parar() {
			try {
				player.stop();
			} catch(Exception e) {};
		}
	
	
	}
