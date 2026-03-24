package Backend.Verification;

public class VerificationPlanete extends VerificationObjets {


    /** Verfication des champs planete
     *
     * @param nom le nom ne doit pas être null ou vide
     * @param prix le prix doit être supérieur à zéro
     * @param emplacement l'emplacement de l'objet
     * @param lune le nb de lunes doit être supérieur à zero
     * @param masse masse doit être supérieur à zero
     * @param anneaux ne peux pas être null
     * @param visitee ne peux pas être null
     * @return Les strings des messages d'erreurs
     **/
    public String verificationPlanete(String nom, String prix, String emplacement, String lune, String masse, Boolean anneaux, Boolean visitee) {
        return String.format("%s%s \n %s \n %s \n %s", sommaireVerifObjet(nom, prix, emplacement), verificationNbLune(lune), verificationMasse(masse), verificationAnneaux(anneaux), verificationVisitee(visitee));
    }
    //Vérification des attributs Planète entrés par l'utilisateur
    private String verificationNbLune(String lune) {
        if (!lune.matches("^\\d+$")) {
            return MsgErreurs.MSGNBLUNE.getMsgErreur();

        }
        return "";
    }

    private String verificationMasse(String masse) {
        if (!masse.matches("^[1-9]\\d*(\\.\\d{1,2})?$")) {
            return MsgErreurs.MSGMASSE.getMsgErreur();
        }
        return "";
    }

    private String verificationAnneaux(Boolean anneaux) {
        if (anneaux == null) {
            return MsgErreurs.MSGANNEAUX.getMsgErreur();
        }
        return "";
    }

    private String verificationVisitee(Boolean visitee) {
        if(visitee == null) {
            return MsgErreurs.MSGVISITEE.getMsgErreur();
        }
        return "";
    }
}
