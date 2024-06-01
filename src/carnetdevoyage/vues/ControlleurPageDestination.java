package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.PageDestination;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @Override
    public void reagir() {
        titre();
        description();
        localisation();
        image();
    }
}
