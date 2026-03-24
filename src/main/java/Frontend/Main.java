package Frontend;

import Backend.Gestion.GestionFichier;
import Backend.Objets.*;
import Frontend.GestionObjet.InventaireTableView;
import Frontend.View.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        SplitPane mainApp = new SplitPane();
        InventaireTableView tableView = new InventaireTableView();
        VBox panelDroit = new VBox();
        GestionFichier gestionFichier = new GestionFichier();

        mainApp.getItems().add(tableView);

        // ── Sélection ───────────────────────────────────────────────
        tableView.getSelectionModel().selectedItemProperty()
                .addListener((obs, ancien, nouveau) -> {
                    if (nouveau == null) {
                        mainApp.getItems().remove(panelDroit);
                        return;
                    }

                    if (!mainApp.getItems().contains(panelDroit)) {
                        mainApp.getItems().add(panelDroit);
                        mainApp.setDividerPosition(0, 0.6);
                    }

                    if (nouveau instanceof Comics c) {
                        panelDroit.getChildren().setAll(
                                new ComicsView(c, Action.MODIFIEROBJET, panelDroit, tableView));
                    } else if (nouveau instanceof Metaux m) {
                        panelDroit.getChildren().setAll(
                                new MetauxView(m, Action.MODIFIEROBJET, panelDroit, tableView));
                    } else if (nouveau instanceof Planete p) {
                        panelDroit.getChildren().setAll(
                                new PlaneteView(p, Action.MODIFIEROBJET, panelDroit, tableView));
                    }
                });

        // ── FileChooser ─────────────────────────────────────────────
        FileChooser fileChooserDat = new FileChooser();
        fileChooserDat.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichier DAT", "*.dat"));

        // ── MENU FICHIER ────────────────────────────────────────────
        MenuItem menuNouveau = new MenuItem("Nouveau");
        MenuItem menuOuvrir = new MenuItem("Ouvrir");
        MenuItem menuSauvegarder = new MenuItem("Sauvegarder");
        MenuItem menuSauvegarderSous = new MenuItem("Sauvegarder sous");
        MenuItem menuExporter = new MenuItem("Exporter");

        Menu menuFichier = new Menu("Fichier");
        menuFichier.getItems().addAll(
                menuNouveau, menuOuvrir,
                new SeparatorMenuItem(),
                menuSauvegarder, menuSauvegarderSous,
                new SeparatorMenuItem(),
                menuExporter
        );

        // ── MENU ÉDITION ────────────────────────────────────────────
        MenuItem menuAjouter = new MenuItem("Ajouter");
        MenuItem menuModifier = new MenuItem("Modifier");
        MenuItem menuSupprimer = new MenuItem("Supprimer");

        menuAjouter.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        menuModifier.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
        menuSupprimer.setAccelerator(KeyCombination.keyCombination("Delete"));

        Menu menuEdition = new Menu("Édition");
        menuEdition.getItems().addAll(
                menuAjouter,
                menuModifier,
                new SeparatorMenuItem(),
                menuSupprimer
        );

        MenuBar menuBar = new MenuBar(menuFichier, menuEdition);

        // ── ACTIONS FICHIER ─────────────────────────────────────────

        menuNouveau.setOnAction(e -> {
            gestionFichier.nouveau();
            tableView.viderTable();
            mainApp.getItems().remove(panelDroit);
        });

        menuOuvrir.setOnAction(e -> {
            File fichier = fileChooserDat.showOpenDialog(stage);
            if (fichier != null) {
                try {
                    List<Objet> objets = gestionFichier.ouvrir(fichier);
                    tableView.viderTable();
                    objets.forEach(tableView::ajouterObjet);
                } catch (IOException | ClassNotFoundException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                }
            }
        });

        menuSauvegarder.setOnAction(e -> {
            try {
                if (gestionFichier.getFichierCourant() == null) {
                    File fichier = fileChooserDat.showSaveDialog(stage);
                    if (fichier != null) {
                        gestionFichier.sauvegarderSous(fichier, tableView.getObjetsList());
                    }
                } else {
                    gestionFichier.sauvegarder(tableView.getObjetsList());
                }
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        menuSauvegarderSous.setOnAction(e -> {
            File fichier = fileChooserDat.showSaveDialog(stage);
            if (fichier != null) {
                try {
                    gestionFichier.sauvegarderSous(fichier, tableView.getObjetsList());
                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                }
            }
        });

        menuExporter.setOnAction(e -> {
            FileChooser fcJson = new FileChooser();
            fcJson.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Fichier JSON", "*.json"));

            File fichier = fcJson.showSaveDialog(stage);
            if (fichier != null) {
                try {
                    gestionFichier.exporterJSON(fichier, tableView.getObjetsList());
                } catch (IOException ex) {
                    new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
                }
            }
        });

        // ── ACTIONS ÉDITION ─────────────────────────────────────────

        menuAjouter.setOnAction(e -> {
            if (!mainApp.getItems().contains(panelDroit)) {
                mainApp.getItems().add(panelDroit);
                mainApp.setDividerPosition(0, 0.6);
            }

            panelDroit.getChildren().setAll(new PanelObjet(
                    new ComicsView(null, Action.AJOUTEROBJET, panelDroit, tableView),
                    new MetauxView(null, Action.AJOUTEROBJET, panelDroit, tableView),
                    new PlaneteView(null, Action.AJOUTEROBJET, panelDroit, tableView),
                    panelDroit, tableView
            ));
        });

        menuModifier.setOnAction(e -> {
            Objet selection = tableView.getSelectionModel().getSelectedItem();

            if (selection == null) {
                new Alert(Alert.AlertType.WARNING, "Aucun objet sélectionné.").showAndWait();
                return;
            }

            if (!mainApp.getItems().contains(panelDroit)) {
                mainApp.getItems().add(panelDroit);
                mainApp.setDividerPosition(0, 0.6);
            }

            if (selection instanceof Comics c) {
                panelDroit.getChildren().setAll(
                        new ComicsView(c, Action.MODIFIEROBJET, panelDroit, tableView));
            } else if (selection instanceof Metaux m) {
                panelDroit.getChildren().setAll(
                        new MetauxView(m, Action.MODIFIEROBJET, panelDroit, tableView));
            } else if (selection instanceof Planete p) {
                panelDroit.getChildren().setAll(
                        new PlaneteView(p, Action.MODIFIEROBJET, panelDroit, tableView));
            }
        });

        menuSupprimer.setOnAction(e -> {
            Objet selection = tableView.getSelectionModel().getSelectedItem();

            if (selection != null) {
                tableView.supprimerObjet(selection);
            } else {
                new Alert(Alert.AlertType.WARNING, "Aucun objet sélectionné.").showAndWait();
            }
        });

        // ── LAYOUT ──────────────────────────────────────────────────
        VBox root = new VBox(menuBar, mainApp);
        VBox.setVgrow(mainApp, Priority.ALWAYS);

        stage.setTitle("Gestionnaire d'inventaire");
        stage.setScene(new Scene(root, 1400, 600));
        stage.show();
    }
}