package Backend.Objets;

import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDate;

public abstract class Objet implements Serializable {
    private static final long serialVersionUID = 1L;
    private Types type;
    private String nom;
    private double prix;
    private String destination;
    private final LocalDate dateAchat;
    private Status status;
    private String emplacement;

    //Constructeur

    /** Constructeur Comics
     * @param type Le type de l'objet
     * @param nom Le nom de l'objet
     * @param prix Le prix de l'objet
     * @param destination La destination
     * @param emplacement L'emplacement
     */
    public Objet(Types type,String nom, double prix, String destination, String emplacement) {
        this.type = type;
        this.nom = nom;
        this.prix = prix;
        this.destination = destination;
        this.emplacement = emplacement;
        this.dateAchat = LocalDate.now();
        this.status = Status.ENMAPOSSESSION;

    }

    //Accesseurs
    public String getNom() {
        return this.nom;
    }

    public double getPrix() {
        return prix;
    }

    public String getStatus() {
        return String.format("%s", status);
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public String getDestination() {
        return destination;
    }

    public Types getType() {
        return this.type;
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }



}
