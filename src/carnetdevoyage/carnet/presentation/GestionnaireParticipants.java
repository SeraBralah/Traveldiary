package carnetdevoyage.carnet.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireParticipants implements Iterable<Participant> {
    private ArrayList<Participant> listeparticipants;

    public GestionnaireParticipants(){
        listeparticipants = new ArrayList<>();
    }

    public void ajouterParticipants(Participant... participant){
        this.listeparticipants.addAll(Arrays.asList(participant));
    }

    public void supprimerParticipant(Participant participant){
        this.listeparticipants.remove(participant);
    }


    @Override
    public Iterator<Participant> iterator() {
        return this.listeparticipants.iterator();
    }
}
