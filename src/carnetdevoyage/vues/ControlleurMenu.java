package carnetdevoyage.vues;


import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.PageDestination;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControlleurMenu {

    private Carnet c;

    public ControlleurMenu(Carnet carnet){
        this.c=carnet;
    }
    @FXML
    void AjouterUnePage(ActionEvent event) {
        this.c.ajouterPageDestination(new PageDestination());
    }

    @FXML
    void ModifierLaPage(ActionEvent event) {

    }

    @FXML
    void Sauvegarder(ActionEvent event) {

    }

    @FXML
    void SupprimerUnePage(ActionEvent event) {
        if(this.c.getPageCourante().estDestination()) this.c.supprimerPageDestination(c.getNumPageCourante());
    }

}
