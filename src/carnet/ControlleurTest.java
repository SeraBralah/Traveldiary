package carnet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ControlleurTest {

    @FXML
    private void handleButtonClick() {
        System.out.println("Test de bouton");

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bouton cliqué!", ButtonType.OK);
        alert.showAndWait();
    }
}
