package Backend.Objets;

import java.nio.file.Path;

public final class Planete extends Objet implements Descriptible {
    private static final long serialVersionUID = 4L;
    private int nbLunes;
    private double masse;
    private Boolean anneaux;
    private Boolean visitee;

    /** Constructeur Comics
     * @param type Le type de l'objet
     * @param nom Le nom de l'objet
     * @param prix Le prix de l'objet
     * @param destination La destination
     * @param emplacement L'emplacement
     * @param masse La masse de la planète
     * @param anneaux oui ou non
     * @param nbLunes le nombre de lunes
     * @param visitee visiter ou non
     */

    public Planete(Types type,String nom, double prix, String destination, String emplacement, int nbLunes, double masse, Boolean anneaux, Boolean visitee) {
        super(type, nom, prix, destination, emplacement);
        this.nbLunes = nbLunes;
        this.masse = masse;
        this.anneaux = anneaux;
        this.visitee = visitee;
    }

    public int getNbLunes() {
        return this.nbLunes;
    }

    public void setNbLunes(int nbLunes) {
        this.nbLunes = nbLunes;
    }

    public double getMasse() {
        return masse;
    }

    public void setMasse(double masse) {
        this.masse = masse;
    }

    public Boolean isAnneaux() {
        return anneaux;
    }

    public void setAnneaux(Boolean anneaux) {
        this.anneaux = anneaux;
    }

    public Boolean isVisitee() {
        return visitee;
    }

    public void setVisite(Boolean visitee) {
        this.visitee = visitee;
    }

    @Override
    public String toString() {
        return String.format("Nom: %s, Prix: %.2f$, Nombres de lunes: %d, Masse: %.2f grammes, Anneaux: %b, Visitée: %b", getNom(), getPrix(), this.nbLunes, this.masse, this.anneaux, this.visitee);

    }

}
