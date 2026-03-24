package Backend.Verification;

public enum MsgErreurs {
    MSGNOM("Le champ nom ne peut pas être vide."),
    MSGPRIX("Le champ prix ne peut pas être inférieur ou égal à zéro."),
    MSGFACTURE("La destination n'a pas été selectionnée ou elle est corrompue."),
    MSGEMPLACEMENT("Le champ emplacement ne peut pas être vide."),
    //Messages d'erreurs pour les attributs de la classe Comics
    MSGDESSINATEUR("Le champ dessinateur ne peut pas être vide."),
    MSGHERO("Le champ héro(s) ne peut pas être vide."),
    //Messages d'erreurs pour les attributs de la classe Métaux
    MSGRARETE("Le niveau de rareté doit se situer entre zéro et 100."),
    MSGSYMBOLE1("Le champ symbole ne peut pas être vide."),
    MSGSYMBOLE2("Le champ symbole ne peut pas contenir plus de trois caractères."),
    MSGPOINTDEFUSION("Le point de fusion doit être au dessus de 30.00 degré Celsius."),
    //Messages d'erreurs pour les attributs de la classe Planète
    MSGNBLUNE("Le nombre de lune doit être supérieur à zéro."),
    MSGMASSE("La masse doit être supérieure à zéro"),
    MSGANNEAUX("Un choix doit être fait."),
    MSGVISITEE("Un choix doit être fait.");


    private final String MsgErreur;

    MsgErreurs(String MsgErreur) {
        this.MsgErreur = MsgErreur;
    }

    public String getMsgErreur() {
        return this.MsgErreur;
    }
}

