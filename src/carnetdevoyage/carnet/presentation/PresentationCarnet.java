package carnetdevoyage.carnet.presentation;

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

    public void setDatedebut(String datedebut) throws DateException {
        try {
            LocalDate date = LocalDate.parse(datedebut, modeledate);
            this.datedebut = datedebut;
        } catch (DateTimeParseException e) {
            throw new DateException("La date est incorrecte");
        }
    }

    public boolean dateValide(String datedebut, String datefin) throws DateException {
        try {
            LocalDate debut = LocalDate.parse(datedebut, modeledate);
            LocalDate fin = LocalDate.parse(datefin, modeledate);
            return fin.isAfter(debut);
        } catch (DateTimeParseException e) {
            throw new DateException("La fin est avant le début");
        }
    }

    public void setDatefin(String datefin) throws DateException{
        try{
            LocalDate fin = LocalDate.parse(datefin, modeledate);
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
}
