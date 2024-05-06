package carnetdevoyage.carnet.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Classe gestionnaire de participants
 */
public class GestionnaireParticipants implements Iterable<Participant> {
    private ArrayList<Participant> listeparticipants;

    /**
     * Constructeur du gestionnaire de Participants
     */
    public GestionnaireParticipants(){
        listeparticipants = new ArrayList<>();
    }

    /**
     * Ajoute un participant à la collection
     * @param participant
     */
    public void ajouterParticipants(Participant... participant){
        this.listeparticipants.addAll(Arrays.asList(participant));
    }

    /**
     * Supprime un participant de la collection
     * @param participant
     */
    public void supprimerParticipant(Participant participant){
        this.listeparticipants.remove(participant);
    }


    @Override
    public Iterator<Participant> iterator() {
        return this.listeparticipants.iterator();
    }
}
