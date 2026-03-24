package Backend.Verification;

import java.nio.file.Path;

public class VerificationObjets {

    protected String sommaireVerifObjet(String nom, String prix, String emplacement) {
        return String.format("%s \n %s \n %s \n", verificationNom(nom), verificationPrix(prix), verificationEmplacement(emplacement));
    }

    private String verificationNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            return MsgErreurs.MSGNOM.getMsgErreur();
        }
        return "";
    }

    private String verificationPrix(String prix) {
        if (prix.matches("^[1-9]\\d*(\\.\\d{1,2})?$")) {
            double prixValide = Double.parseDouble(prix);
            if (prixValide <= 0) {
                return MsgErreurs.MSGPRIX.getMsgErreur();

            }

        } else {
            return MsgErreurs.MSGPRIX.getMsgErreur();
        }
        return "";
    }

    private String verificationEmplacement(String emplacement) {
        if (emplacement == null || emplacement.isEmpty()) {
            return MsgErreurs.MSGEMPLACEMENT.getMsgErreur();
        }
        return "";
    }
}
