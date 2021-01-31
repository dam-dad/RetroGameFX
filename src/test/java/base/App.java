package base;

import java.io.File;
import java.net.MalformedURLException;
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
import javafx.scene.media.*;

public class App extends Application{
	private static HiloMusica hiloMusica;
	private static double volumenMusica = 0.3;
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
		playMusica("MainSong.mp3");
	}
	public static void main(String[] args) {
		launch(args);
	}
	public static void CambiarAMain(){
		primaryStage.setScene(mainScene);
	}
	public static void CambiarASeleccion(){
		primaryStage.setScene(seleccionScene);
	}
	public static void CambiarAOpciones(){
		primaryStage.setScene(opcionesScene);
	}
	public static double getVolumenMusica() {
		return volumenMusica;
	}
	public static void setVolumenMusica(double volumenMusic) {
		App.volumenMusica = volumenMusic;
	}
	
	public static void playMusica(String file) {
		hiloMusica = new HiloMusica(file);
		hiloMusica.run();
	}
	
}
class HiloMusica extends Thread{
	String file;
	MediaPlayer player;
	
	public HiloMusica(String file) {
		this.file =file;
		
	}
	public void play() {
	URL path= getClass().getResource(file);
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
	}
