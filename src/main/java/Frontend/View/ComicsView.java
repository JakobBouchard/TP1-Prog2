package Frontend.View;

import Backend.Objets.Comics;
import Backend.Objets.Types;
import Backend.Verification.VerificationComics;
import Frontend.GestionObjet.InventaireTableView;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ComicsView extends ObjetView {

    HBox sectionComics = new HBox(25);
    Label nomObjet     = new Label("Section Comics");
    VBox infos         = new VBox(25);
    Label lblDess      = new Label("Dessinateur: ");
    Label lblHero      = new Label("Héro(s): ");
    VBox text          = new VBox(25);
    TextField txtDess  = new TextField();
    TextField txtHero  = new TextField();
    Label lblErreur    = new Label();

    public ComicsView(Comics comics, Action action, VBox panelDroit, InventaireTableView inventaireTableView) {
        super(comics, action, panelDroit, inventaireTableView);

        comboBoxObjet.setValue(Types.COMICS);
        comboBoxObjet.setDisable(true);

        nomObjet.setStyle("-fx-font-weight: bold;");
        lblErreur.setStyle("-fx-text-fill: red; -fx-wrap-text: true;");

        infos.getChildren().addAll(lblDess, lblHero);
        text.getChildren().addAll(txtDess, txtHero);
        sectionComics.getChildren().addAll(infos, text);

        Button btnAdd    = new Button("Ajouter");
        Button btnFermer = new Button("Fermer");
        HBox boutons     = new HBox(10, btnAdd, btnFermer);

        this.getChildren().addAll(nomObjet, sectionComics, lblErreur, boutons);

        if (action == Action.MODIFIEROBJET) {
            txtDess.setText(comics.getDessinateur());
            txtHero.setText(comics.getHero());
        }

        btnAdd.setOnAction(e -> {
            VerificationComics verif = new VerificationComics();
            String erreurs = verif.verificationComics(
                    textNom.getText(),
                    textPrix.getText(),
                    emplacement.getText(),
                    txtDess.getText(),
                    txtHero.getText()
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
                Comics c = new Comics(
                        Types.COMICS,
                        textNom.getText(),
                        Double.parseDouble(textPrix.getText()),
                        null,
                        emplacement.getText(),
                        txtDess.getText(),
                        txtHero.getText()
                );
                inventaireTableView.ajouterObjet(c);

            } else if (action == Action.MODIFIEROBJET) {
                comics.setNom(textNom.getText());
                comics.setPrix(Double.parseDouble(textPrix.getText()));
                comics.setEmplacement(emplacement.getText());
                comics.setDessinateur(txtDess.getText());
                comics.setHero(txtHero.getText());
                inventaireTableView.refresh();
            }

            inventaireTableView.getSelectionModel().clearSelection();
        });

        btnFermer.setOnAction(e -> inventaireTableView.getSelectionModel().clearSelection());
    }
}