package dad.javafx.retrogamesfx.base;

import dad.javafx.retrogamesfx.controllers.ChooseGameController;
import dad.javafx.retrogamesfx.controllers.CreditsController;
import dad.javafx.retrogamesfx.controllers.MainController;
import dad.javafx.retrogamesfx.controllers.SettingsController;
import dad.javafx.retrogamesfx.games.World;
import dad.javafx.retrogamesfx.music.MusicThread;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {

	// Musica
	private static MusicThread music;
	private static DoubleProperty volume = new SimpleDoubleProperty(1);
	
	// Controladores
	private MainController controller;
	private ChooseGameController chooseGameController;
	private SettingsController settingsController;
	private CreditsController creditosController;
	
	// Escenas
	static Scene mainScene;
	static Scene chooseGameScene;
	static Scene settingsScene;
	static Scene creditsScene;
	
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		controller = new MainController();
		chooseGameController = new ChooseGameController();
		settingsController = new SettingsController();
		creditosController= new CreditsController();

		mainScene = new Scene(controller.getView(), 600, 400);
		chooseGameScene = new Scene(chooseGameController.getView(), 600, 400);
		settingsScene = new Scene(settingsController.getView(), 600, 400);
		creditsScene = new Scene(creditosController.getView(), 600, 400);
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("RetroGamesFX");
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("/images/arcadeico.png"));
		
		playMusic("MainSong");
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Cambio de vetana a Main
	public static void goToMain() {
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
	public static void goToCredits() {
		primaryStage.setScene(creditsScene);
	}

	// Cambio de ventana de Juego
	public static void playGame(World gameScene) {
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
