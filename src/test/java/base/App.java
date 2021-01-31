package base;

import controllersTest.MainViewController;
import controllersTest.OpcionesViewController;
import controllersTest.SelectViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

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
}
