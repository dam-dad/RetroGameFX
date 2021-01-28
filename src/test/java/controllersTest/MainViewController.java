package controllersTest;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainViewController {

	@FXML
	Pane View;
	
	@FXML
	Button Jugar;
	
	@FXML
	Button Opciones;
	
	@FXML
	Button Salir;
	
	@FXML
	Button Creditos;
	
	@FXML
	Text Titulo;
	
	Image image = new Image(new FileInputStream("/Images/Fondo.gif"));
	ImageView IV = new ImageView(image);
	
	public Pane getView() {
		return View;
	}
	
	public MainViewController() throws IOException {
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
	loader.setController(this);
	loader.load();
}
}
