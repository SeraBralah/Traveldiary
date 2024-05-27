package carnetdevoyage.vues;


import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlleurMenu {

    @FXML
    private TextField nomParticipant;


    private Carnet c;


    private Stage stage;


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


    @FXML
    void ajouterParticipant(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouter_participant.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic == ControlleurMenu.class) {
                return new ControlleurMenu(this.c); // Passez les paramètres nécessaires
            } else {
                try {
                    return ic.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Parent root = loader.load();
        this.stage = new Stage();
        stage.setTitle("Carnet de voyage");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void ValiderParticipant(ActionEvent event) {
        this.c.getPagePresentation().getGestionnaire().ajouterParticipants(new Participant(this.nomParticipant.getText()));
        System.out.println(this.c.getPagePresentation().getGestionnaire());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
