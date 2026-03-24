package Backend.Verification;

public class VerificationMetaux extends VerificationObjets{

    /** Verfication des champs Metaux
     *
     * @param nom le nom ne doit pas être null ou vide
     * @param prix le prix doit être supérieur à zéro
     * @param emplacement l'emplacement de l'objet
     * @param rarete le nb de lunes doit être supérieur à zero
     * @param symbole symbole ne peut pas être null
     * @param pointFusion ne peux pas être inferieur a 30
     * @return Les strings des messages d'erreurs
     **/
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
