package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.DescriptionDestination;
import carnetdevoyage.carnet.pages.ImageDestination;
import carnetdevoyage.carnet.pages.LocalisationDestination;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.AuteurCarnet;
import carnetdevoyage.carnet.presentation.GestionnaireParticipants;
import carnetdevoyage.carnet.presentation.PagePresentation;
import carnetdevoyage.carnet.presentation.PresentationCarnet;
import carnetdevoyage.exceptions.DateException;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ControlleurCreationCarnet {

    private Carnet c;

    private boolean carnetvalide;
    @FXML
    private TextField nomAuteur;

    @FXML
    private TextArea infoAuteur;

    @FXML
    private TextField datedebut;

    @FXML
    private TextField datefin;
    private AuteurCarnet a;
    private PagePresentation p;


    public ControlleurCreationCarnet(Carnet c,AuteurCarnet a) {
        this.c = c;
        this.a = a;
    }

    @FXML
    void ChercherImage(ActionEvent event) {
        String cheminImage = "../ImagesCarnet";
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"), //filtre pour les images
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File imageSelect = fileChooser.showOpenDialog(new Stage()); //ouverture de l'explorateur de fichier
        if (imageSelect != null) {
            File imageBase = new File(imageSelect.getAbsolutePath());
            try{
                Path destinationFichier = Paths.get(cheminImage);
                if(!Files.exists(destinationFichier)){
                    Files.createDirectories(destinationFichier);
                }
                //on change le chemin :
                Path destinationImage = destinationFichier.resolve(imageSelect.getName());
                //on copie le fichier :
                Files.copy(imageSelect.toPath(),destinationImage, StandardCopyOption.REPLACE_EXISTING);
                this.a.setImageAuteur(destinationImage.toFile().getPath());

            } catch (IOException e){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Erreur");
                a.setHeaderText(null);
                a.setContentText(e.getMessage());
                a.showAndWait();
            }
            //this.a.setImageAuteur(imageSelect.getPath());
        }
    }


    @FXML
    void Exitc(ActionEvent event) {
        //
        this.carnetvalide=false;
        this.p = new PagePresentation();
        p.setAuteur(a);
        p.setGestionnaire(new GestionnaireParticipants());
        ajouterAuteur();
        ajouterInfosAuteur();
        //gérer le nombre de page
        ajouterPages();
        //set l'image
        if(carnetvalide) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        //ajouter les informations
    }

    void ajouterAuteur(){
        a.setAuteur(nomAuteur.getText());
    }

    void ajouterInfosAuteur(){
        a.setInfos(infoAuteur.getText());
    }

    void ajouterPages() {
        String deb = datedebut.getText();
        String fin = datefin.getText();
        PresentationCarnet presentation = new PresentationCarnet("Carnet de voyage");
        try {
            if(presentation.dateValide(deb,fin)) {
                presentation.setDatedebut(deb);
                presentation.setDatefin(fin);
                int nbjour = presentation.nbJourDuVoyage(presentation.getDatedebut(), presentation.getDatefin());
                p.setPresentationCarnet(presentation);
                c.ajouterPagePresentation(p);
                for (int i = 0; i < nbjour; i++) {
                    //ajouter les pages dans le carnet
                    PageDestination pd = new PageDestination();
                    pd.setImageDestination(new ImageDestination());
                    pd.setLocalisationDestination(new LocalisationDestination());
                    pd.setNumPage(i + 1);
                    c.ajouterPageDestination(pd);

                }
                this.carnetvalide=true;
            }
        } catch (DateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur dans les dates");
            alert.setContentText(e.getMessage());
            alert.show();
            PauseTransition p = new PauseTransition(Duration.seconds(5));
            p.setOnFinished(event -> alert.close());
            p.play();
        }

    }

}
