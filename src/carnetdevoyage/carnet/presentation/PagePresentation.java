package carnetdevoyage.carnet.presentation;

public class PagePresentation {
    private GestionnaireParticipants gestionnaire;
    private AuteurCarnet auteur;
    private PresentationCarnet presentationCarnet;

    public PagePresentation(){}

    public void setAuteur(AuteurCarnet auteur) {
        this.auteur = auteur;
    }

    public void setGestionnaire(GestionnaireParticipants gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    public void setPresentationCarnet(PresentationCarnet presentationCarnet) {
        this.presentationCarnet = presentationCarnet;
    }
}
