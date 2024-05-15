package carnetdevoyage.carnet.pages;

/**
 * Constructeur de la classe de description de la destination
 */
public class DescriptionDestination {
    private String titre;
    private String texte;

    /**
     *
     * @param titre
     */
    public DescriptionDestination(String titre){
        this.titre = titre;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }
}
