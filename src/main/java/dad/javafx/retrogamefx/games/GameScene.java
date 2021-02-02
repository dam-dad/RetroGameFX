package dad.javafx.retrogamefx.games;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class GameScene extends Scene implements Initializable {

	public GameScene(String fxml) {
		super(new Pane());
		loadUI(fxml);
	}

	public abstract void play();
	public abstract void quit();
	
	private void loadUI(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			loader.setController(this);
			setRoot(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
