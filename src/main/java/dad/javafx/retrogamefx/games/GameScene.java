package dad.javafx.retrogamefx.games;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class GameScene extends Scene implements Initializable {
	
	private AnimationTimer timer;
	private Double Speed=1.0;
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
	public GameScene(String fxml, double width, double height, int in) {
		super(new Pane(), width, height);
		loadUI(fxml);
		timer=new AnimationTimer() {
			long lastTick=0;
			
			public void handle(long now) {
				if(lastTick==0) {
					lastTick=now;
					gameLoop(1);
					return;
				}
				if(now-lastTick>1000000000/5) {
					lastTick=now;
					gameLoop(1);
				}
			}};;}
	
	public AnimationTimer getTimer() {
		return timer;
	}

	public void setTimer(AnimationTimer timer) {
		this.timer = timer;
	}

	public Double getSpeed() {
		return Speed;
	}

	public void setSpeed(Double speed) {
		Speed = speed;
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
