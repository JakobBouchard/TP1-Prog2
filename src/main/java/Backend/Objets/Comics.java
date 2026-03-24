package Backend.Objets;

import java.nio.file.Path;
import java.util.Enumeration;

public final class Comics extends Objet implements Descriptible {
    private static final long serialVersionUID = 2L;
    private String dessinateur;
    private String hero;

    /** Constructeur Comics
     * @param type Le type de l'objet
     * @param nom Le nom de l'objet
     * @param prix Le prix de l'objet
     * @param destination La destination
     * @param emplacement L'emplacement
     * @param dessinateur Le dessinateur
     * @param hero Le nom de l'héro
     */
    public Comics(Types type, String nom, double prix, String destination, String emplacement, String dessinateur, String hero) {
        super(type, nom, prix, destination, emplacement);
        this.dessinateur = dessinateur;
        this.hero = hero;
    }

    public String getDessinateur() {
        return this.dessinateur;
    }

    public void setDessinateur(String nom) {
        this.dessinateur = dessinateur;
    }

    public String getHero() {
        return this.hero;
    }

    public void setHero(String nom) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return String.format("Nom: %s, Prix: %.2f$, Dessinateur: %s, Héro(s): %s", getNom(), getPrix(), dessinateur, hero);
    }




}

