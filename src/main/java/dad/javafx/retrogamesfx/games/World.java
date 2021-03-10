package dad.javafx.retrogamesfx.games;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class World extends Scene implements Initializable {

	private static final double NANO_TO_SEC = 1 / 1000000000.0;

	private StringProperty title = new SimpleStringProperty();
	private AnimationTimer timer;

	public World(String title, String fxml, double width, double height) {
		super(new Pane(), width, height);
		setTitle(title);
		timer = new AnimationTimer() {
			private long last = System.nanoTime();
			public void handle(long now) {
				gameLoop((now - last) * NANO_TO_SEC);
				last = now;
			}
		};
		loadUI(fxml);
		windowProperty().addListener((o, ov, nv) -> {
			if (nv != null)
				play();
			else
				stop();
		});
	}

	public AnimationTimer getTimer() {
		return timer;
	}

	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}

	public void play() {
		System.out.println("Playing " + getTitle());
		timer.start();
	}

	public void stop() {
		System.out.println("Stopping " + getTitle());
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

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}

}
