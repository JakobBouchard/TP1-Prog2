package Frontend.View;

import Backend.Objets.Planete;
import Backend.Objets.Types;
import Backend.Verification.VerificationPlanete;
import Frontend.GestionObjet.InventaireTableView;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PlaneteView extends ObjetView {

    Label lblSection     = new Label("Section Planète");
    Label lblNbLunes     = new Label("Nb lunes: ");
    Label lblMasse       = new Label("Masse (g): ");
    Label lblAnneaux     = new Label("Anneaux: ");
    Label lblVisitee     = new Label("Visitée: ");
    TextField txtNbLunes = new TextField();
    TextField txtMasse   = new TextField();
    ComboBox<String> cbAnneaux = new ComboBox<>();
    ComboBox<String> cbVisitee = new ComboBox<>();
    Label lblErreur      = new Label();

    public PlaneteView(Planete planete, Action action, VBox panelDroit, InventaireTableView inventaireTableView) {
        super(planete, action, panelDroit, inventaireTableView);

        comboBoxObjet.setValue(Types.PLANETE);
        comboBoxObjet.setDisable(true);

        cbAnneaux.getItems().addAll("Oui", "Non");
        cbVisitee.getItems().addAll("Oui", "Non");

        lblSection.setStyle("-fx-font-weight: bold;");
        lblErreur.setStyle("-fx-text-fill: red; -fx-wrap-text: true;");

        VBox infos = new VBox(20, lblNbLunes, lblMasse, lblAnneaux, lblVisitee);
        VBox texte = new VBox(15, txtNbLunes, txtMasse, cbAnneaux, cbVisitee);
        HBox sectionPlanete = new HBox(15, infos, texte);

        Button btnAdd    = new Button("Ajouter");
        Button btnFermer = new Button("Fermer");
        HBox boutons     = new HBox(10, btnAdd, btnFermer);

        this.getChildren().addAll(lblSection, sectionPlanete, lblErreur, boutons);

        if (planete != null && action == Action.MODIFIEROBJET) {
            txtNbLunes.setText(String.valueOf(planete.getNbLunes()));
            txtMasse.setText(String.valueOf(planete.getMasse()));
            cbAnneaux.setValue(planete.isAnneaux() ? "Oui" : "Non");
            cbVisitee.setValue(planete.isVisitee() ? "Oui" : "Non");
        }

        btnAdd.setOnAction(e -> {
            VerificationPlanete verif = new VerificationPlanete();
            String erreurs = verif.verificationPlanete(
                    textNom.getText(),
                    textPrix.getText(),
                    emplacement.getText(),
                    txtNbLunes.getText(),
                    txtMasse.getText(),
                    cbAnneaux.getValue() != null ? cbAnneaux.getValue().equals("Oui") : null,
                    cbVisitee.getValue() != null ? cbVisitee.getValue().equals("Oui") : null
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
                Planete p = new Planete(
                        Types.PLANETE,
                        textNom.getText(),
                        Double.parseDouble(textPrix.getText()),
                        null,
                        emplacement.getText(),
                        Integer.parseInt(txtNbLunes.getText()),
                        Double.parseDouble(txtMasse.getText()),
                        cbAnneaux.getValue().equals("Oui"),
                        cbVisitee.getValue().equals("Oui")
                );
                inventaireTableView.ajouterObjet(p);

            } else if (action == Action.MODIFIEROBJET) {
                planete.setNom(textNom.getText());
                planete.setPrix(Double.parseDouble(textPrix.getText()));
                planete.setEmplacement(emplacement.getText());
                planete.setNbLunes(Integer.parseInt(txtNbLunes.getText()));
                planete.setMasse(Double.parseDouble(txtMasse.getText()));
                planete.setAnneaux(cbAnneaux.getValue().equals("Oui"));
                planete.setVisite(cbVisitee.getValue().equals("Oui"));
                inventaireTableView.refresh();
            }

            inventaireTableView.getSelectionModel().clearSelection();
        });

        btnFermer.setOnAction(e -> inventaireTableView.getSelectionModel().clearSelection());
    }
}