package Backend.Objets;

import java.nio.file.Path;

public final class Metaux extends Objet implements Descriptible {
    private static final long serialVersionUID = 3L;
    private int rarete;
    private String symbole;
    private double pointFusion;

    /** Constructeur Comics
     * @param type Le type de l'objet
     * @param nom Le nom de l'objet
     * @param prix Le prix de l'objet
     * @param destination La destination
     * @param emplacement L'emplacement
     * @param rarete Le niveau de rarete
     * @param symbole Le nom du symbole
     * @param pointFusion le point de fusion
     */

    public Metaux(Types type,String nom, double prix, String destination, String emplacement, int rarete, String symbole, int pointFusion) {
        super(type,nom, prix, destination, emplacement);
        this.rarete = rarete;
        this.symbole = symbole;
        this.pointFusion = pointFusion;
    }

    public int getRarete() {
        return rarete;
    }

    public void setRarete(int rarete) {
        this.rarete = rarete;
    }

    public String getSymbole() {
        return symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public double getPointFusion() {
        return pointFusion;
    }

    public void setPointFusion(double pointFusion) {
        this.pointFusion = pointFusion;
    }

    @Override
    public String toString() {
        return String.format("Nom: %s, Prix: %.2f$, Rareté: %d, Symbole: %s, Point de fusion: %d degré Celsius", getNom(), getPrix(), this.rarete, this.symbole, this.pointFusion);
    }
}
