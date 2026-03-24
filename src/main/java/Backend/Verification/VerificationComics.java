package Backend.Verification;

public class VerificationComics extends VerificationObjets{

    public String verificationComics(String nom, String prix, String emplacement, String dessinateur, String hero) {
        return String.format("%s%s \n %s", sommaireVerifObjet(nom, prix, emplacement), verificationDessinateur(dessinateur), verificationHero(hero));
    }

    //Vérification des attributs Comics entrés par l'utilisateur
    private String verificationDessinateur(String dessinateur) {
        if (dessinateur == null || dessinateur.isEmpty()) {
            return MsgErreurs.MSGDESSINATEUR.getMsgErreur();
        }
        return "";
    }

    private String verificationHero(String hero) {
        if (hero == null || hero.isEmpty()) {
            return MsgErreurs.MSGHERO.getMsgErreur();
        }
        return "";
    }
}
