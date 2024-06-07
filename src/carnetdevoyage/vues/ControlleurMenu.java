package carnetdevoyage.vues;


import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.PagePresentation;
import carnetdevoyage.carnet.presentation.Participant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
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

    private Stage stage;

    @FXML
    private MenuItem ajouterParticipant;

    @FXML
    private Menu editions;
    @FXML
    private Menu modifier;

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
    private MenuItem itemCharger;

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
                this.supprimerParticipant.setVisible(false);
                this.ajouterParticipant.setVisible(false);
                this.itemCharger.setVisible(false);
            }
        } else {
            this.editions.setVisible(false);
        }
    }



    @FXML
    void Charger(ActionEvent event) throws IOException {
        int page = 0;
        if (c.getPageCourante().estDestination()) {
            page = 1;
        }
        final Stage dialog = new Stage();
        dialog.setTitle("Chargement d'une sauvegarde");
        FileChooser choixfichier = new FileChooser();
        choixfichier.setTitle("Quel fichier charger ?");
        choixfichier.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("json", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = choixfichier.showOpenDialog(dialog);
        if (selectedFile != null) {
            c.chargement(selectedFile);
            if (page == 1) c.avancerPage();
        }
        this.c.notifierObservateurs();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    void InfosJourneeDest(ActionEvent event) {
        if(this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            //p.getDescriptionDestination().setTexte(boiteDialogue("Modifier les informations du la journée :"));
            dialogueTexte();
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
        // récupérer l'image
        if (this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            String cheminImage = "../ImagesCarnet";
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );

            File imageSelect = fileChooser.showOpenDialog(new Stage());
            if (imageSelect != null) { //on traite l'image selectionné
                try {
                    Path destinationFichier = Paths.get(cheminImage);
                    if (!Files.exists(destinationFichier)) {
                        Files.createDirectories(destinationFichier);
                    }
                    Path destinationImage = destinationFichier.resolve(imageSelect.getName());
                    Files.copy(imageSelect.toPath(), destinationImage, StandardCopyOption.REPLACE_EXISTING);
                    p.getImageDestination().ajouterImage(destinationImage.toUri().toString());
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




    @FXML
    void AjouterUnePage(ActionEvent event) {
        this.c.ajouterPageDestination(new PageDestination());
    }

    @FXML
    void Sauvegarder(ActionEvent event) {
        File emplacement = obtenirCheminSauvegarde();
        if (emplacement != null) {
            this.c.sauvegarde(emplacement);
        }
    }

    private File obtenirCheminSauvegarde() {
        Stage stage1 = new Stage();
        File file = creerRepertoireSauvegarde(stage1);

        if (file != null) {
            String nomcarnet = "Carnet_de_" + this.c.getPagePresentation().getAuteur().getAuteur();
            nomcarnet = nomcarnet.replaceAll(" ", "_");
            Path cheminSauvegarde = file.toPath().resolve(nomcarnet);
            try {
                Files.createDirectories(cheminSauvegarde);
                String nomfichier = "sauvegarde.json";
                Path cheminfinal = cheminSauvegarde.resolve(nomfichier);
                return cheminfinal.toFile();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erreur lors de la création du dossier de sauvegarde : " + e.getMessage());
                alert.showAndWait();
            }
        }
        return null;
    }


    public File creerRepertoireSauvegarde(Stage stage1) {
        DirectoryChooser repertoire = new DirectoryChooser();
        repertoire.setTitle("Choisir un dossier pour sauvegarder le carnet : ");
        return repertoire.showDialog(stage1);
    }


    @FXML
    void Quitter(ActionEvent event) {
        Platform.exit();
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
        this.c.notifierObservateurs();
    }


    @FXML
    void ajouterParticipant(ActionEvent event) throws IOException {
        if(this.c.getPageCourante().estPresentation()) {
            PagePresentation p = (PagePresentation) this.c.getPageCourante();
            p.getGestionnaire().ajouterParticipants(new Participant(boiteDialogue("Ajoutez un participant : ")));
            this.c.notifierObservateurs();
        }
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

    public void dialogueTexte(){
        Stage stage = new Stage();
        if(this.c.getPageCourante().estDestination()) {
            PageDestination p = (PageDestination) this.c.getPageCourante();
            stage.setTitle("Description de ta journée :");
            TextArea textArea = new TextArea();
            textArea.setWrapText(true);
            Button printButton = new Button("Valider");
            printButton.setOnAction(event -> {
                p.getDescriptionDestination().setTexte(textArea.getText());
                stage.close();
            });
            VBox vbox = new VBox(10, textArea, printButton);
            vbox.setPadding(new Insets(20));
            Scene scene = new Scene(vbox, 400, 300);
            stage.setScene(scene);
            stage.show();
        }
    }


    @Override
    public void reagir() {

    }
}
