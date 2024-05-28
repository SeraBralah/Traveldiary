package carnetdevoyage.vues;


import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.PagePresentation;
import carnetdevoyage.carnet.presentation.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ControlleurMenu implements Observateur{

    @FXML
    private TextField nomParticipant;


    private Carnet c;



    @FXML
    private Menu editions;
    @FXML
    private Menu modifier;

    private Stage stage;

    @FXML
    private MenuItem supprimerPage;

    @FXML
    private MenuItem supprimerParticipant;

    @FXML
    private MenuItem titreCarnet;

    @FXML
    private MenuItem infosSuppCarnet;

    @FXML
    private MenuItem ajoutImage;

    @FXML
    private MenuItem infosJournee;

    @FXML
    private MenuItem localDest;

    @FXML
    private MenuItem titreDest;

    @FXML
    void InfosJourneeDest(ActionEvent event) {
        if(this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            p.getDescriptionDestination().setTexte(boiteDialogue("Modifier les informations du la journée :"));
            this.c.notifierObservateurs();
        }
    }

    @FXML
    void ModifLocalDest(ActionEvent event) {
        if(this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            p.getLocalisationDestination().setLocalisation(boiteDialogue("Modifier la destination :"));
            this.c.notifierObservateurs();
        }
    }


    @FXML
    void ajouterImageDestination(ActionEvent event) {
        if (this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            String cheminImage = "../ImagesCarnet";
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );

            File imageSelect = fileChooser.showOpenDialog(new Stage());
            if (imageSelect != null) {
                try {
                    Path destinationFichier = Paths.get(cheminImage);
                    if (!Files.exists(destinationFichier)) {
                        Files.createDirectories(destinationFichier);
                    }
                    Path destinationImage = destinationFichier.resolve(imageSelect.getName());
                    Files.copy(imageSelect.toPath(), destinationImage, StandardCopyOption.REPLACE_EXISTING);
                    Image image = new Image(destinationImage.toUri().toString());
                    p.getImageDestination().ajouterImage(image);
                } catch (IOException e) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Erreur");
                    a.setHeaderText(null);
                    a.setContentText(e.getMessage());
                    a.showAndWait();
                }
            }
        }
        this.c.notifierObservateurs();
    }



    @FXML
    void infosSuppCarnet(ActionEvent event) {
        if(this.c.getPageCourante().estPresentation()) {
            PagePresentation p = (PagePresentation) this.c.getPageCourante();
            p.getAuteur().setInfossupp((boiteDialogue("Ajoutez quelques informations supplémentaires : ")));
            this.c.notifierObservateurs();
        }
    }

    @FXML
    void titreCarnet(ActionEvent event) {
        if(this.c.getPageCourante().estPresentation()) {
            PagePresentation p = (PagePresentation) this.c.getPageCourante();
            p.getPresentationCarnet().setTitre(boiteDialogue("Donnez un titre personnalisez à votre carnet : "));
            this.c.notifierObservateurs();
        }
    }

    @FXML
    void titreDest(ActionEvent event) {
        if(this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            p.getDescriptionDestination().setTitre(boiteDialogue("Modifier le titre de la destination"));
            this.c.notifierObservateurs();
        }
    }



    public ControlleurMenu(Carnet carnet){
        this.c=carnet;
    }

    @FXML
    public void initialize() {
        //disable les boutons inutiles ici
        if (this.c.getNbPage() != 0) {
            if (this.c.getPageCourante().estPresentation()) {
                this.ajoutImage.setVisible(false);
                this.infosJournee.setVisible(false);
                this.localDest.setVisible(false);
                this.titreDest.setVisible(false);

            } else if (this.c.getPageCourante().estDestination()) {
                this.infosSuppCarnet.setVisible(false);
                this.titreCarnet.setVisible(false);

            }
        } else {
            this.editions.setVisible(false);
        }
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
    void Quitter(ActionEvent event) {

    }


    @FXML
    void SupprimerParticipant(ActionEvent event) {
        if(this.c.getPagePresentation().getGestionnaire().getSelection()){
            this.c.getPagePresentation().getGestionnaire().supprimerParticipant(this.c.getPagePresentation().getGestionnaire().getParticipantSelectionne());
        }
        this.c.notifierObservateurs();
    }

    @FXML
    void SupprimerUnePage(ActionEvent event) {
        if(this.c.getPageCourante().estDestination()) this.c.supprimerPageDestination(this.c.getNumPageCourante());
    }


    @FXML
    void ajouterParticipant(ActionEvent event) throws IOException {
        if(this.c.getPageCourante().estPresentation()) {
            PagePresentation p = (PagePresentation) this.c.getPageCourante();
            p.getGestionnaire().ajouterParticipants(new Participant(boiteDialogue("Ajoutez un participant : ")));
            this.c.notifierObservateurs();
        }
    }

    @FXML
    void ValiderParticipant(ActionEvent event) {
        this.c.getPagePresentation().getGestionnaire().ajouterParticipants(new Participant(this.nomParticipant.getText()));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
        this.c.notifierObservateurs();
    }

    public String boiteDialogue(String demande){
        AtomicReference<String> res =  new AtomicReference<>();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Editions");
        dialog.setHeaderText(demande);

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(text -> res.set(text));
        return res.toString();
    }


    @Override
    public void reagir() {

    }
}
