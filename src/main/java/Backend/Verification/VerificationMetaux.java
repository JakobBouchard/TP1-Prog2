package Backend.Verification;

public class VerificationMetaux extends VerificationObjets{

    public String verificationMetaux(String nom, String prix, String emplacement, String rarete, String symbole, String pointFusion) {
        return String.format("%s%s \n %s \n %s \n", sommaireVerifObjet(nom, prix, emplacement), verificationRarete(rarete), verificationSymbole(symbole), verificationPointFusion(pointFusion));
    }
    //Vérification des attributs Métaux entrés par l'utilisateur
    private String verificationRarete(String rarete) {
        if (rarete.matches("^\\d+$")) {
            int rareteValide = Integer.parseInt(rarete);
            if(rareteValide < 0 || rareteValide > 100 ) {
                return MsgErreurs.MSGRARETE.getMsgErreur();
            }
        }
        return "";

    }

    private String verificationSymbole(String symbole) {
        if (symbole == null || symbole.isEmpty()) {
            return MsgErreurs.MSGSYMBOLE1.getMsgErreur();
        } else if (symbole.length() > 3) {
            return MsgErreurs.MSGSYMBOLE2.getMsgErreur();
        }
        return "";
    }

    private String verificationPointFusion(String pointFusion) {
        if (pointFusion.matches("^[1-9]\\d*(\\.\\d{1,2})?$")) {
            double pointFusionValide = Double.parseDouble(pointFusion);
            if (pointFusionValide < 30.00) {
                return MsgErreurs.MSGPOINTDEFUSION.getMsgErreur();
            }
        }
        return "";
    }
}
