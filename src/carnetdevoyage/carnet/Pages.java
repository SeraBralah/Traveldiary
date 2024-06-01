package carnetdevoyage.carnet;

import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.PagePresentation;

public abstract class Pages {
    private PagePresentation pagePresentation;
    private PageDestination pageDestination;


    public abstract boolean estPresentation();
    public abstract boolean estDestination();



}
