package carnetdevoyage.carnet.pages;

/**
 * Classe localisation de la destination
 */
public class LocalisationDestination {
    private String localisation;

    /**
     * Constructeur de la classe localisation
     */
    public LocalisationDestination(){
        this.localisation=" ";
    }

    /**
     * Set la localisation de la page en cours
     * @param localisation
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLocalisation() {
        return localisation;
    }

    @Override
    public String toString() {
        return "Localisation : " + localisation + '\n' ;
    }
}
