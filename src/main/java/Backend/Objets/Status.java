package Backend.Objets;

public enum Status {
    /*En ma possession, Prêté, Perdu, Vendu, Volé, Doné*/
    ENMAPOSSESSION("En ma posssession"),
    PRETE("Prêté"),
    PERDU("Perdu"),
    VENDU("Vendu"),
    VOLE("Volé"),
    DONNE("Donné");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        return status;
    }

}
