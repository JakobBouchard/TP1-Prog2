package Backend.Verification;

public class VerificationPlanete extends VerificationObjets {

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
