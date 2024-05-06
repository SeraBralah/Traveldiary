package carnetdevoyage.carnet;

import carnetdevoyage.carnet.presentation.PagePresentation;

public class Carnet {
    private PagePresentation pagePresentation;

    /**
     * Constructeur du carnet
     */
    public Carnet(){
    }

    /**
     * Set la page de présentation du carnet
     * @param pagePresentation
     */
    public void setPagePresentation(PagePresentation pagePresentation) {
        this.pagePresentation = pagePresentation;
    }
}
