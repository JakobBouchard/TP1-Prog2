package Frontend.View;

import Backend.Objets.Types;
import Frontend.GestionObjet.InventaireTableView;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelObjet extends VBox {

    VBox panelObjet = new VBox(15);
    Label titre1 = new Label("Nouvel item d'inventaire");

    HBox choisirObjets = new HBox(50);
    Label typeObjet = new Label("Type d'objet: ");
    ComboBox<Types> comboBoxObjet = new ComboBox<>(
            FXCollections.observableArrayList(Types.COMICS, Types.METAUX, Types.PLANETE));

    public PanelObjet(ComicsView comicsView, MetauxView metauxView,
                      PlaneteView planeteView, VBox panelDroit,
                      InventaireTableView tableView) {

        choisirObjets.getChildren().addAll(typeObjet, comboBoxObjet);
        panelObjet.getChildren().addAll(titre1, choisirObjets);

        this.getChildren().add(panelObjet);

        comboBoxObjet.setOnAction(e -> {
            comboBoxObjet.setDisable(true);

            switch (comboBoxObjet.getValue()) {
                case COMICS -> panelDroit.getChildren().setAll(
                        new ComicsView(null, Action.AJOUTEROBJET, panelDroit, tableView));

                case METAUX -> panelDroit.getChildren().setAll(
                        new MetauxView(null, Action.AJOUTEROBJET, panelDroit, tableView));

                case PLANETE -> panelDroit.getChildren().setAll(
                        new PlaneteView(null, Action.AJOUTEROBJET, panelDroit, tableView));
            }
        });
    }
}