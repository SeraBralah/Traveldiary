package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.PagePresentation;
import carnetdevoyage.carnet.presentation.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ControlleurPagePresentation implements Observateur {


    @FXML
    private Label nomauteur;

    @FXML
    private Label infosauteur;

    @FXML
    private ImageView imageauteur;

    @FXML
    private Label infosoption;

    @FXML
    private Label titreCarnet;

    @FXML
    private Label date;

    @FXML
    private ListView<String> listviewParticipant;

    private Carnet c;

    public ControlleurPagePresentation(Carnet c){
        this.c=c;
        this.c.ajouterObservateur(this);
        this.listviewParticipant=new ListView<String>();
    }

//TEXTINPUTDIALOG

    public void initialize() {
        PagePresentation p = (PagePresentation) c.getPageCourante();
        this.nomauteur.setText(p.getAuteur().getAuteur());
        this.infosauteur.setText(p.getAuteur().getInfos());
        if(p.getAuteur().getImageAuteur()!=null) {
            String imagePath = p.getAuteur().getImageAuteur();
            File file = new File(imagePath);

            if (file.exists()) {
                try {
                    // Convert the file path to a URI and then to a string URL
                    String imageUrl = file.toURI().toString();
                    Image image = new Image(imageUrl);
                    this.imageauteur.setImage(image);
                } catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Problème lors de l'ouverture de l'image");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Problème lors de l'ouverture de l'image");
                alert.showAndWait();
            }
        }

        StringBuilder s = new StringBuilder(p.getPresentationCarnet().getDatedebut() + " - " + p.getPresentationCarnet().getDatefin());
        this.date.setText(s.toString());
        setTitreCarnet();
        setInfosoption();

        ajouterParticipant();
        // si on selectionne un participant dans la listview :
        this.listviewParticipant.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.c.getPagePresentation().getGestionnaire().setParticipantSelectionne(newValue);
                this.c.getPagePresentation().getGestionnaire().setSelection(true);

            } else this.c.getPagePresentation().getGestionnaire().setSelection(false);

        });
    }

    public void setTitreCarnet(){
        this.titreCarnet.setText(c.getPagePresentation().getPresentationCarnet().getTitre());
    }

    public void setInfosoption(){
        this.infosoption.setText(c.getPagePresentation().getAuteur().getInfossupp());
    }

    public void ajouterParticipant(){
        ObservableList<String> noms = FXCollections.observableArrayList();

        for (Participant participant : this.c.getPagePresentation().getGestionnaire()) {
            noms.add(participant.getNom());
        }

        this.listviewParticipant.setItems(noms);
    }

    public void majAuteur(){
        if(c.getPageCourante().estPresentation()) {
            PagePresentation p = (PagePresentation) c.getPageCourante();
            this.nomauteur.setText(p.getAuteur().getAuteur());
            this.infosauteur.setText(p.getAuteur().getInfos());
        }
    }



    @Override
    public void reagir() {
        majAuteur();
        ajouterParticipant();
        setInfosoption();
        setTitreCarnet();
    }
}
