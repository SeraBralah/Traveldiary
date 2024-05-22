package carnetdevoyage.carnet.presentation;

import carnetdevoyage.carnet.Pages;
import carnetdevoyage.exceptions.DateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe Presentation du carnet
 */
public class PresentationCarnet {

    private String datedebut;
    private String datefin;
    private String titre;
    private static final DateTimeFormatter modeledate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * Constructeur de la classe Presentation du carnet
     * @param titre
     */
    public PresentationCarnet(String titre) {
        this.titre = titre;
    }

    /**
     * Fixe la date de début du voyage
     * @param datedebut
     * @throws DateException
     */
    public void setDatedebut(String datedebut) throws DateException {
        try {
            LocalDate date = LocalDate.parse(datedebut, modeledate);
            boolean dateverifier = verifDate(datedebut);
            if(!dateverifier) throw new DateException("La date est invalide");
            this.datedebut = datedebut;
        } catch (DateTimeParseException e) {
            throw new DateException("La date est incorrecte");
        }
    }

    /**
     * S'assure que la date rentrée est valide par rapport au calendrier
     * @param date
     * @return
     * @throws DateException
     */
    public boolean verifDate(String date) throws DateException {
        String[] datesplit = date.split("/");
        int jour = Integer.parseInt(datesplit[0]);
        int mois = Integer.parseInt(datesplit[1]);
        int annee = Integer.parseInt(datesplit[2]);
        if(1900>=annee) throw new DateException("Vous n'êtes pas si vieux !");
        if(annee>LocalDate.now().getYear()) throw new DateException("Vous possédez un tardis ?");
        if(mois==2) {
            if (annee % 4 == 0) {
                if (0 >= jour || jour >= 30) {
                    System.out.println("Mois de février en année 4");
                    return false;
                }
            } else if (0 >= jour || jour >= 29) return false;
        }
        if(mois%2==0 ){
            if(0>=jour || jour>=31){ //jour plus petit que 0 ou plus grand que 31
                System.out.println("jour plus petit que 0 ou plus grand que 31");
                return false;
            }
        } else { //mois impair, février et août
            if (0 >= jour || jour >= 32) {
                System.out.println("mois impair, février et août");

                return false;
            }

        }
return true;
    }

    /**
     * Vérifie que la date de début et la date de fin sont bien conformes
     * @param datedebut
     * @param datefin
     * @return
     * @throws DateException
     */
    public boolean dateValide(String datedebut, String datefin) throws DateException {
        try {

            LocalDate debut = LocalDate.parse(datedebut, modeledate);
            LocalDate fin = LocalDate.parse(datefin, modeledate);
            return fin.isAfter(debut);
        } catch (DateTimeParseException e) {
            throw new DateException("La fin est avant le début");
        }
    }

    /**
     * Fixe la date de la fin du voyage
     * @param datefin
     * @throws DateException
     */
    public void setDatefin(String datefin) throws DateException{
        try{
            System.out.println(datefin);
            LocalDate fin = LocalDate.parse(datefin, modeledate);
            boolean dateverifier = verifDate(datefin);
            if(!dateverifier) throw new DateException("La date est invalide");
            this.datefin = datefin;
        } catch (DateTimeParseException e) {
            throw new DateException("La date est incorrecte");
        }
    }

    public String getDatedebut() {
        return datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public String getTitre() {
        return titre;
    }

    @Override
    public String toString() {
        return " Presentation du carnet : " +
                "début : "+  datedebut +"fin : "+ datefin + titre +
                "\n";
    }

}
