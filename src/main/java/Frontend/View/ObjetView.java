package Frontend.View;

import Backend.Objets.Objet;
import Backend.Objets.Status;
import Backend.Objets.Types;
import Frontend.GestionObjet.InventaireTableView;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ObjetView extends VBox {

    protected VBox panelDroit;
    protected InventaireTableView inventaireTableView;

    // ComboBox type — visible dans toutes les vues
    protected ComboBox<Types> comboBoxObjet = new ComboBox<>(
            FXCollections.observableArrayList(Types.COMICS, Types.METAUX, Types.PLANETE));

    // Section générale
    Label lblNom = new Label("Nom: ");
    Label lblPrix = new Label("Prix: ");
    Label lblDateAchat = new Label("Date d'achat: ");
    Label lblImage = new Label("Image facture: ");
    Label lblStatus = new Label("Status: ");
    Label lblEmplacement = new Label("Emplacement: ");

    protected TextField  textNom = new TextField();
    protected TextField  textPrix = new TextField();
    protected DatePicker dateAchat = new DatePicker();
    protected Button     imageFacture = new Button("Choisir image");
    protected ComboBox<String> status = new ComboBox<>(FXCollections.observableArrayList(
            Status.DONNE.toString(), Status.ENMAPOSSESSION.toString(),
            Status.PERDU.toString(), Status.PRETE.toString(),
            Status.VENDU.toString(),  Status.VOLE.toString()));
    protected TextField emplacement = new TextField();

    public ObjetView(Objet objet, Action action, VBox panelDroit, InventaireTableView inventaireTableView) {
        super(15);
        this.panelDroit          = panelDroit;
        this.inventaireTableView = inventaireTableView;

        // ── ComboBox type ──────────────────────────────────────────
        HBox choisirType = new HBox(15, new Label("Type d'objet: "), comboBoxObjet);

        // ── Section générale ───────────────────────────────────────
        Label titreGenerale = new Label("Section générale");
        titreGenerale.setStyle("-fx-font-weight: bold;");

        VBox infos = new VBox(20, lblNom, lblPrix, lblDateAchat, lblImage, lblStatus, lblEmplacement);
        VBox texte = new VBox(15, textNom, textPrix, dateAchat, imageFacture, status, emplacement);
        HBox sectionGenerale = new HBox(15, infos, texte);

        this.getChildren().addAll(choisirType, titreGenerale, sectionGenerale);

        // ── Pré-remplir si modification ───────────────────────────
        if (objet != null && action == Action.MODIFIEROBJET) {
            textNom.setText(objet.getNom());
            textPrix.setText(String.format("%.2f", objet.getPrix()));
            if (objet.getDateAchat() != null) dateAchat.setValue(objet.getDateAchat());
            status.setValue(objet.getStatus().toString());
            emplacement.setText(objet.getEmplacement());
        }

        // ── Logique ComboBox ──────────────────────────────────────
        comboBoxObjet.setOnAction(e -> {
            if (comboBoxObjet.getValue() == null) return;
            comboBoxObjet.setDisable(true);

            switch (comboBoxObjet.getValue()) {
                case COMICS -> panelDroit.getChildren().setAll(
                        new ComicsView(null, action, panelDroit, inventaireTableView));
                case METAUX -> panelDroit.getChildren().setAll(
                        new MetauxView(null, action, panelDroit, inventaireTableView));
                case PLANETE -> panelDroit.getChildren().setAll(
                        new PlaneteView(null, action, panelDroit, inventaireTableView));
            }
        });
    }
}