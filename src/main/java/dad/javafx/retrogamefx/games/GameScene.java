package dad.javafx.retrogamefx.games;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class GameScene extends Scene implements Initializable {
	
	private AnimationTimer timer;

	public GameScene(String fxml, double width, double height) {
		super(new Pane(), width, height);
		loadUI(fxml);
		timer = new AnimationTimer() {
			private long last = System.nanoTime();
			public void handle(long now) {
				gameLoop((now - last) / 1000000000.0);
				last = now;
			}
		};
	}

	public void play() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	protected abstract void gameLoop(double diff);
	
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
