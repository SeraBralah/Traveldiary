package carnetdevoyage.carnet.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Classe gestionnaire de participants
 */
public class GestionnaireParticipants implements Iterable<Participant> {
    private ArrayList<Participant> listeparticipants;
    private Participant participantSelectionne;
    private boolean selection;

    /**
     * Constructeur du gestionnaire de Participants
     */
    public GestionnaireParticipants(){
        listeparticipants = new ArrayList<>();
        this.selection=false;
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

    public ArrayList<Participant> getListeparticipants() {
        return listeparticipants;
    }

    @Override
    public Iterator<Participant> iterator() {
        return this.listeparticipants.iterator();
    }

    public void setParticipantSelectionne(String participantSelectionne) {
        for(Participant p : listeparticipants) {
            if (p.getNom().equals(participantSelectionne)) {
                this.participantSelectionne = p;
            }
        }
    }

    public void setSelection(boolean selection) {
        this.selection = selection;
    }

    public Participant getParticipantSelectionne() {
        return participantSelectionne;
    }

    public boolean getSelection() {
        return this.selection;
    }

    @Override
    public String toString() {
        return "\n Participants :" + listeparticipants +
                '\n';
    }
}
