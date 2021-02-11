package dad.javafx.retrogamefx.games;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class GameScene extends Scene implements Initializable {
	
	private AnimationTimer timer;

	public GameScene(String fxml) {
		super(new Pane());
		loadUI(fxml);
		timer = new AnimationTimer() {
			public void handle(long now) {
				gameLoop(now);
			}
		};
	}

	public void play() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	protected abstract void gameLoop(long now);
	
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
