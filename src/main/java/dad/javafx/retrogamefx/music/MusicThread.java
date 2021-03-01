package dad.javafx.retrogamefx.music;

import java.net.URISyntaxException;
import java.net.URL;

import dad.javafx.retrogamefx.base.App;
import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicThread extends Thread {

	private String file;
	private MediaPlayer player;

	public MusicThread(String file) {
		this.file = file;
	}

	public void play() {
		URL path = getClass().getResource("/sounds/" + file + ".mp3");
		Media media;
		try {
			media = new Media(path.toURI().toString());
			player = new MediaPlayer(media);
			player.volumeProperty().bind(App.volumeProperty());
			player.setCycleCount(Transition.INDEFINITE);
			player.play();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		try {
			player.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
