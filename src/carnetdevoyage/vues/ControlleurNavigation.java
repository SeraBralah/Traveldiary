package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.AuteurCarnet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlleurNavigation {

    @FXML
    private Label nbPage;

    private  Carnet c;
    public ControlleurNavigation(Carnet carnet) {
        this.c=carnet;
    }

    @FXML
    public void initialize() {
        this.nbPage.setText((c.getNumPageCourante()+1)+"/"+(c.getNbPage()));
    }

    @FXML
    void pageSuivante(ActionEvent event) throws IOException {
        if(c.getNumPageCourante()==c.getNbPage()) {
            c.reculerPage();
        } else {
            c.avancerPage();
            if (c.getNumPageCourante() != c.nbPage()) {
                afficherPageDestination(event);
            }
        }
    }

    @FXML
    void pagePrecedente(ActionEvent event) throws IOException {
        if(c.getNumPageCourante()!=0) {
            c.reculerPage();
            if(c.getPageCourante().estPresentation()) afficherPagePresentation(event);
            else afficherPageDestination(event);
        }





    }

    public void afficherPageDestination(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/page_voyage.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic == ControlleurMenu.class) {
                return new ControlleurMenu(this.c);
            } else if (ic == ControlleurPagePresentation.class) {
                return new ControlleurPagePresentation(this.c);
            } else if  (ic == ControlleurNavigation.class ) {
                return new ControlleurNavigation(this.c);
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
    }

    public void afficherPagePresentation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/page_presentation.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic == ControlleurMenu.class) {
                return new ControlleurMenu(this.c);
            } else if (ic == ControlleurPagePresentation.class) {
                return new ControlleurPagePresentation(this.c);
            } else if  (ic == ControlleurNavigation.class ) {
                return new ControlleurNavigation(this.c);
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

    }
}
