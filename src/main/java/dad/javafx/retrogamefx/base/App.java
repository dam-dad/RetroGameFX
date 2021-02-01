package dad.javafx.retrogamefx.base;

import java.net.URISyntaxException;

import java.net.URL;

import dad.javafx.retrogamefx.controllers.MainViewController;
import dad.javafx.retrogamefx.controllers.OpcionesViewController;
import dad.javafx.retrogamefx.controllers.SelectViewController;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class App extends Application {

	// Musica
	private static MusicThread music;
	private static double volume = 1;
	// Escenas
	private MainViewController controller;
	private SelectViewController selectViewController;
	private OpcionesViewController opcionesViewController;
	static Scene mainScene;
	static Scene chooseGameScene;
	static Scene optionsScene;
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		controller = new MainViewController();
		selectViewController = new SelectViewController();
		opcionesViewController = new OpcionesViewController();

		mainScene = new Scene(controller.getView(), 600, 400);
		chooseGameScene = new Scene(selectViewController.getView(), 600, 400);
		optionsScene = new Scene(opcionesViewController.getView(), 600, 400);

		primaryStage.setScene(mainScene);
		primaryStage.setTitle("RetroGamesFX");
		primaryStage.show();
		primaryStage.setResizable(false);
		// primaryStage.getIcons().add(new Image("/images/classroom-24x24.png"));
//		playMusica("MainSong");
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Cambio de vetana a Main
	public static void CambiarAMain() {
		primaryStage.setScene(mainScene);
	}

	// Cambio de ventana a Selection
	public static void CambiarASeleccion() {
		primaryStage.setScene(chooseGameScene);
	}

	// Cambio de ventana a Opciones
	public static void CambiarAOpciones() {
		primaryStage.setScene(optionsScene);
	}

	// Musica inacabado
	public static double getVolumenMusica() {
		return volume;
	}

	public static void setVolumenMusica(double volumenMusica) {
		App.volume = volumenMusica;
	}

	public static void playMusica(String file) {
		music = new MusicThread(file);
		music.play();
	}

	public static void stopMusica() {
		// if cambiar a juego
		music.parar();
	}

}

class MusicThread extends Thread {
	String file;
	MediaPlayer player;

	public MusicThread(String file) {
		this.file = file;
	}

	public void play() {
		URL path = getClass().getResource("/sounds/" + file + ".mp3");
		Media media;
		try {
			media = new Media(path.toURI().toString());
			player = new MediaPlayer(media);
			player.setVolume(App.getVolumenMusica());
			player.setCycleCount(Transition.INDEFINITE);
			player.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void parar() {
		try {
			player.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
