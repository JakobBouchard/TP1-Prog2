package Frontend.View;

import Backend.Objets.Metaux;
import Backend.Objets.Types;
import Backend.Verification.VerificationMetaux;
import Frontend.GestionObjet.InventaireTableView;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MetauxView extends ObjetView {

    Label lblSection      = new Label("Section Métaux");
    Label lblRarete       = new Label("Rareté (0-100): ");
    Label lblSymbole      = new Label("Symbole: ");
    Label lblPointFusion  = new Label("Point de fusion (°C): ");
    TextField txtRarete      = new TextField();
    TextField txtSymbole     = new TextField();
    TextField txtPointFusion = new TextField();
    Label lblErreur = new Label();

    public MetauxView(Metaux metaux, Action action, VBox panelDroit, InventaireTableView inventaireTableView) {
        super(metaux, action, panelDroit, inventaireTableView);

        comboBoxObjet.setValue(Types.METAUX);
        comboBoxObjet.setDisable(true);

        lblSection.setStyle("-fx-font-weight: bold;");
        VBox infos = new VBox(20, lblRarete, lblSymbole, lblPointFusion);
        VBox texte = new VBox(15, txtRarete, txtSymbole, txtPointFusion);
        HBox sectionMetaux = new HBox(15, infos, texte);

        Button btnAdd    = new Button("Ajouter");
        Button btnFermer = new Button("Fermer");
        HBox boutons     = new HBox(10, btnAdd, btnFermer);

        this.getChildren().addAll(lblSection, sectionMetaux, boutons);

        if (metaux != null && action == Action.MODIFIEROBJET) {
            txtRarete.setText(String.valueOf(metaux.getRarete()));
            txtSymbole.setText(metaux.getSymbole());
            txtPointFusion.setText(String.valueOf(metaux.getPointFusion()));
        }

        btnAdd.setOnAction(e -> {
            System.out.println("btnAdd cliqué!");

            VerificationMetaux verif = new VerificationMetaux();
            String erreurs = verif.verificationMetaux(
                    textNom.getText(),
                    textPrix.getText(),
                    emplacement.getText(),
                    txtRarete.getText(),
                    txtSymbole.getText(),
                    txtPointFusion.getText()
            );

            if (erreurs != null && !erreurs.replace("\n", "").replace(" ", "").isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Veuillez corriger les erreurs suivantes :");
                alert.setContentText(erreurs);
                alert.showAndWait();
                return;
            }

            if (action == Action.AJOUTEROBJET) {
                Metaux m = new Metaux(
                        Types.METAUX,
                        textNom.getText(),
                        Double.parseDouble(textPrix.getText()),
                        null,
                        emplacement.getText(),
                        Integer.parseInt(txtRarete.getText()),
                        txtSymbole.getText(),
                        (int) Double.parseDouble(txtPointFusion.getText())
                );
                inventaireTableView.ajouterObjet(m);

            } else if (action == Action.MODIFIEROBJET) {
                metaux.setNom(textNom.getText());
                metaux.setPrix(Double.parseDouble(textPrix.getText()));
                metaux.setEmplacement(emplacement.getText());
                metaux.setRarete(Integer.parseInt(txtRarete.getText()));
                metaux.setSymbole(txtSymbole.getText());
                metaux.setPointFusion((int) Double.parseDouble(txtPointFusion.getText()));
                inventaireTableView.refresh();
            }

            panelDroit.getChildren().clear();
            inventaireTableView.getSelectionModel().clearSelection();
        });
    }
}
