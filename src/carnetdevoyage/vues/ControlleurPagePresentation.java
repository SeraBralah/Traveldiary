package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.PagePresentation;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

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
    private ListView<?> listviewParticipant;

    private Carnet c;

    public ControlleurPagePresentation(Carnet c){
        this.c=c;
        this.c.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        PagePresentation p = (PagePresentation) c.getPageCourante();
        this.nomauteur.setText(p.getAuteur().getAuteur());
        this.infosauteur.setText(p.getAuteur().getInfos());

        System.out.println(p.getPresentationCarnet().getDatedebut());
        System.out.println(p.getPresentationCarnet().getDatefin());
        System.out.println(p.getPresentationCarnet().getTitre());

        StringBuilder s =new StringBuilder(p.getPresentationCarnet().getDatedebut() + " - " + p.getPresentationCarnet().getDatefin());
        this.date.setText(s.toString());
       this.titreCarnet.setText(p.getPresentationCarnet().getTitre());
        this.infosoption.setText(" ");

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
    }
}
