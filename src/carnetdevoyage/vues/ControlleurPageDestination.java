package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.PageDestination;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class ControlleurPageDestination implements Observateur{

    @FXML
    private Label titre;

    @FXML
    private Label description;

    @FXML
    private Label localisation;

    @FXML
    private ImageView imageLongue;

    @FXML
    private ImageView imageCarre;

    @FXML
    private ImageView imageLongue2;

    private Carnet c;
    private PageDestination p;

    public ControlleurPageDestination(Carnet c) {
        this.c = c;
        this.p = (PageDestination) c.getPageCourante();
        this.c.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        titre();
        description();
        localisation();
        image();
    }

    public void titre(){
        this.titre.setText(p.getDescriptionDestination().getTitre());
    }

    public void description(){
        this.description.setText(p.getDescriptionDestination().getTexte());
    }

    public void localisation(){
        this.localisation.setText(p.getLocalisationDestination().getLocalisation());
    }

    public void image(){
        if(this.p.getImageDestination().getImage(0)!=null) {
            Image image1 = new Image(this.p.getImageDestination().getImage(0));
            this.imageCarre.setImage(image1);
        }
        if(this.p.getImageDestination().getImage(1)!=null) {
            Image image2 = new Image(this.p.getImageDestination().getImage(1));
            this.imageLongue.setImage(image2);
        }
        if(this.p.getImageDestination().getImage(2)!=null) {
            Image image3 = new Image(this.p.getImageDestination().getImage(2));
            this.imageLongue2.setImage(image3);
        }
    }


    @FXML
    void changerDescription(MouseEvent event) {
        /*String s = boiteDialogue("Description de la journée : ");
        this.p.getDescriptionDestination().setTexte(s);
        this.description.setText(s);*/
        dialogueTexte();
        this.c.notifierObservateurs();
    }

    @FXML
    void changerLocalisation(MouseEvent event) {
        String s = boiteDialogue("Localisation de la journée : ");
        this.p.getLocalisationDestination().setLocalisation(s);
        this.localisation.setText(s);
        this.c.notifierObservateurs();
    }

    @FXML
    void changerTitre(MouseEvent event) {
        String s = boiteDialogue("Titre de la journée : ");
        this.p.getDescriptionDestination().setTitre(s);
        this.titre.setText(s);
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

    public void dialogueTexte(){
        Stage stage = new Stage();
        stage.setTitle("Description de ta journée :");
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);
        Button printButton = new Button("Valider");
        printButton.setOnAction(event -> {
            this.description.setText(textArea.getText());
            this.p.getDescriptionDestination().setTexte(textArea.getText());
            stage.close();
        });
        VBox vbox = new VBox(10, textArea, printButton);
        vbox.setPadding(new Insets(20));
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void reagir() {
        titre();
        description();
        localisation();
        image();
    }
}
