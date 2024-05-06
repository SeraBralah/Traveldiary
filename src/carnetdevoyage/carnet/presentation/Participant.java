package carnetdevoyage.carnet.presentation;

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
     * Lie le chemin d'une image sur l'ordinateur à
     * @param cheminImage
     */
    public void setPhoto(String cheminImage){
        this.photoParticipant=cheminImage;
    }
}

// gérer les images dans les vues avec une sauvegarde vers ressource
