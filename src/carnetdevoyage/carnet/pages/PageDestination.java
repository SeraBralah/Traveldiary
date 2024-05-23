package carnetdevoyage.carnet.pages;

import carnetdevoyage.carnet.Pages;

public class PageDestination extends Pages {

    private DescriptionDestination descriptionDestination;
    private LocalisationDestination localisationDestination;
    private ImageDestination imageDestination;
    private int numPage;

    /**
     * COnstructeur de la page de destination
     */
    public PageDestination(){
    this.localisationDestination = new LocalisationDestination();
    this.descriptionDestination = new DescriptionDestination("Notre destination");
    this.descriptionDestination.ajouterDescription("C'était chouette ? \n Expliquez nous pourquoi.");
    this.numPage=0;
    }

    public void setDescriptionDestination(DescriptionDestination descriptionDestination) {
        this.descriptionDestination = descriptionDestination;
    }

    public void setImageDestination(ImageDestination imageDestination) {
        this.imageDestination = imageDestination;
    }

    public void setLocalisationDestination(LocalisationDestination localisationDestination) {
        this.localisationDestination = localisationDestination;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    @Override
    public String toString() {
        return "---------------------------------- \n PageDestination : \n" + descriptionDestination + localisationDestination +
                imageDestination +
                "---------------------------------- \n";
    }

    @Override
    public boolean estPresentation() {
        return false;
    }

    @Override
    public boolean estDestination() {
        return true;
    }
}
