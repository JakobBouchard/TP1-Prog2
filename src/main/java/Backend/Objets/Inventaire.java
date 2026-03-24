package Backend.Objets;

import java.time.LocalDate;

/** Constructeur Inventaire **/

public final class Inventaire {
    private Objet[] inventaire;

    public Inventaire() {
        this.inventaire = new Objet[10];

    }

    public Objet[] getInventaire() {
        return inventaire;
    }

    /*
    public void afficherInventaire() {
        for (Objet objet : inventaire) {
            if (objet != null) {
                System.out.println(objet);
            }
        }
    } */

    public void ajouterObjet(Objet nouvelObjet) {
        for (int i = 0; i < inventaire.length; i++) {
            if (inventaire[i] == null) {
                inventaire[i] = nouvelObjet;
                break;
            } else if (inventaire.length - 1 == i) {
                agrandirInventaire();
            }
        }
    }

    public void agrandirInventaire() {
        Objet[] nouvelInventaire = new Objet[inventaire.length + 10];
        for (int i = 0; i < inventaire.length; i++) {
            if (inventaire[i] != null) {
                nouvelInventaire[i] = inventaire[i];
            }
            nouvelInventaire = inventaire;
        }
    }

    public void supprimerObjet(Objet enleverObjet) {
        for (int i = 0; i < inventaire.length; i++) {
            if (inventaire[i] == enleverObjet) {
                inventaire[i] = null;
            }
            if (inventaire[i] == null && i != inventaire.length - 1) {
                inventaire[i] = inventaire[i + 1];
                inventaire[i + 1] = null;
            }
        }

    }
}
/* setVisible(false)
    public Objet[] filtreComics(Objet[] inventaire) {
        int i = 0;
        Objet[] tableauComics = new Objet[inventaire.length];
        for (Objet objet : inventaire) {
            if(objet == null) {
                break;
            } else if (objet.getType() == Types.COMICS) {
                tableauComics[i] = objet;
                i++;

            }
        }
        return tableauComics;

    }

    public Objet[] filtreMetaux(Objet[] inventaire) {
        int i = 0;
        Objet[] tableauMetaux = new Objet[inventaire.length];
        for (Objet objet : inventaire) {
            if(objet == null) {
                break;
            } else if (objet.getType() == Types.METAUX) {
                tableauMetaux[i] = objet;
                i++;

            }
        }
        return tableauMetaux;

    }

    public Objet[] filtrePlanete(Objet[] inventaire) {
        int i = 0;
        Objet[] tableauPlanete = new Objet[inventaire.length];
        for (Objet objet : inventaire) {
            if(objet == null) {
                break;
            } else if (objet.getType() == Types.PLANETE) {
                tableauPlanete[i] = objet;
                i++;

            }
        }
        return tableauPlanete;

    }

    public Objet[] filtreDateAchat(Objet[] inventaire, LocalDate dateAchatMin, LocalDate dateAchatMax) {
        int i = 0;
        Objet[] tableauDate = new Objet[inventaire.length];
        for(Objet objet: inventaire) {
            if(objet.getDateAchat().isAfter(dateAchatMin) && objet.getDateAchat().isBefore(dateAchatMax)) {
                tableauDate[i] = objet;
            }
        }
        return tableauDate;
    }

    public Objet[] filtrePrix(Objet[] inventaire, double prixMin, double prixMax) {
        int i = 0;
        Objet[] tableauPrix = new Objet[inventaire.length];
        for(Objet objet : inventaire) {
            if(objet.getPrix() < prixMin && objet.getPrix() > prixMax) {

                tableauPrix[i] = objet;

            }
        }
        return tableauPrix;

    }

    public Objet[] filtreStatus(Objet[] inventaire, Status status) {
        int i = 0;
        Objet[] tableauStatus = new Objet[inventaire.length];

        for(Objet objet : inventaire) {
            if(objet.getStatus() == status) {
                tableauStatus[i] = objet;
            }
        }

        return tableauStatus;
    }

 */
