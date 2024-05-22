package carnetdevoyage.carnet.pages;

import carnetdevoyage.carnet.Pages;

public class PageDestination extends Pages {

    private DescriptionDestination descriptionDestination;
    private LocalisationDestination localisationDestination;
    private ImageDestination imageDestination;
    private int numPage;

    public PageDestination(){
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
}
