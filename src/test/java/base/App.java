package base;

import controllersTest.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

	private MainViewController controller;
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;
		controller = new MainViewController();
		
		Scene scene = new Scene(controller.getView(), 600, 400);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("RetroGamesFX");
		primaryStage.show();
		primaryStage.setResizable(false);
		//primaryStage.getIcons().add(new Image("/images/classroom-24x24.png"));
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
