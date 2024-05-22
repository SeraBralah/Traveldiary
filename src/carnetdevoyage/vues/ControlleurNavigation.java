package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControlleurNavigation {

    private  Carnet c;
    public ControlleurNavigation(Carnet carnet) {
        this.c=carnet;
    }

    @FXML
    void pageSuivante(ActionEvent event) {
        System.out.println("pageSuivante");
    }

    @FXML
    void pagePrecedente(ActionEvent event) {
        System.out.println("pagePrecedente");
    }
}
