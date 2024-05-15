package carnetdevoyage.carnet.pages;

public class PageDestination {

    private DescriptionDestination descriptionDestination;
    private LocalisationDestination localisationDestination;
    private ImageDestination imageDestination;

    public PageDestination(){

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
}
