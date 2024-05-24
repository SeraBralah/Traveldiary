package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.AuteurCarnet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlleurNavigation {

    private  Carnet c;
    public ControlleurNavigation(){}

    public ControlleurNavigation(Carnet carnet) {
        this.c=carnet;
    }

    @FXML
    void pageSuivante(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/page_voyage.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic == ControlleurMenu.class) {
                return new ControlleurMenu(this.c);
            } else if (ic == ControlleurPagePresentation.class) {
                return new ControlleurPagePresentation(this.c);
            } else {
                try {
                    return ic.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Parent root = loader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        System.out.println("pageSuivante");
    }

    @FXML
    void pagePrecedente(ActionEvent event) {
        System.out.println("pagePrecedente");
    }
}
