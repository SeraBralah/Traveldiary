package carnetdevoyage.carnet.presentation;

import carnetdevoyage.carnet.Pages;

public class PagePresentation extends Pages {
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

    @Override
    public String toString() {
        return "---------------------------------- \n PagePresentation : " + gestionnaire + auteur + presentationCarnet +" ---------------------------------- \n";
    }

    @Override
    public boolean estPresentation() {
        return true;
    }

    @Override
    public boolean estDestination() {
        return false;
    }
}
