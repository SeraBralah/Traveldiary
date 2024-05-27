package carnetdevoyage.carnet.presentation;

/**
 * Classe participant
 */
public class Participant {
    private String nom;
    private String photoParticipant;

    /**
     * Constructeur de participant
     * @param nom
     */
    public Participant(String nom){
        this.nom=nom;
    }

    /**
     * Lie le chemin d'une image sur l'ordinateur au participant
     * @param cheminImage
     */
    public void setPhoto(String cheminImage){
        this.photoParticipant=cheminImage;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "\n Participant : " + nom +
                ", photo :'" + photoParticipant +
                '\n';
    }
}

// gérer les images dans les vues avec une sauvegarde vers ressource
