package controllersTest;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

}
