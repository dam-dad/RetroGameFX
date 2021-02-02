package dad.javafx.retrogamefx.base;

import dad.javafx.retrogamefx.controllers.ChooseGameController;
import dad.javafx.retrogamefx.controllers.MainController;
import dad.javafx.retrogamefx.controllers.SettingsController;
import dad.javafx.retrogamefx.games.GameScene;
import dad.javafx.retrogamefx.music.MusicThread;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

	// Musica
	private static MusicThread music;
	private static DoubleProperty volume = new SimpleDoubleProperty(1);
	
	// Controladores
	private MainController controller;
	private ChooseGameController chooseGameController;
	private SettingsController settingsController;
	
	// Escenas
	static Scene mainScene;
	static Scene chooseGameScene;
	static Scene settingsScene;
	
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		controller = new MainController();
		chooseGameController = new ChooseGameController();
		settingsController = new SettingsController();

		mainScene = new Scene(controller.getView(), 600, 400);
		chooseGameScene = new Scene(chooseGameController.getView(), 600, 400);
		settingsScene = new Scene(settingsController.getView(), 600, 400);

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
	public static void gotToMain() {
		primaryStage.setScene(mainScene);
	}

	// Cambio de ventana a Selection
	public static void goToChooseGame() {
		primaryStage.setScene(chooseGameScene);
	}

	// Cambio de ventana a Opciones
	public static void goToSettings() {
		primaryStage.setScene(settingsScene);
	}

	// Cambio de ventana a Opciones
	public static void playGame(GameScene gameScene) {
		primaryStage.setScene(gameScene);
	}
	
	// Musica inacabado


	public static void playMusic(String file) {
		music = new MusicThread(file);
		music.play();
	}

	public static void stopMusic() {
		// if cambiar a juego
		music.pause();
	}

	public static final DoubleProperty volumeProperty() {
		return App.volume;
	}	

	public static final double getVolume() {
		return App.volumeProperty().get();
	}

	public static final void setVolume(final double volume) {
		App.volumeProperty().set(volume);
	}

}
