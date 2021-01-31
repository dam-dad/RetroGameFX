package controllersTest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import base.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class OpcionesViewController implements Initializable {

    @FXML
    private GridPane View;
    
    @FXML
    private Slider slider_musc;

    @FXML
    private Slider slider_soni;

    @FXML
    private ComboBox<?> comboIdioma;

    @FXML
    private Button butVolv;

    @FXML
    private Button butAcept;

    @FXML
    void butOnAcept(ActionEvent event) {

    }

    @FXML
    void butOnVolv(ActionEvent event) {
    	App.CambiarAMain();
    }

    @FXML
    void comboOnIdioma(ActionEvent event) {

    }

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public GridPane getView() {
		return View;
	}
	public OpcionesViewController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sound.fxml"));
		loader.setController(this);
		loader.load();
		
	}

}
