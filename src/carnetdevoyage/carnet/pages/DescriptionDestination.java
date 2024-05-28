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
        this.texte =" ";
    }

    public void ajouterDescription(String texte) {
        this.texte = texte;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "\n Destination : " + titre + " \n" + texte + "\n";
    }
}
