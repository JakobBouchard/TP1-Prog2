package Frontend.GestionObjet;

import Backend.Objets.Objet;
import Backend.Objets.Status;
import Backend.Objets.Types;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InventaireTableView extends TableView<Objet> {

    private ObservableList<Objet> items = FXCollections.observableArrayList();
    private FilteredList<Objet> filteredItems = new FilteredList<>(items, p -> true);

    public InventaireTableView() {

        TableColumn<Objet, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getType().toString()));

        TableColumn<Objet, String> colNom = new TableColumn<>("Nom");
        colNom.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Objet, String> colPrix = new TableColumn<>("Prix");
        colPrix.setCellValueFactory(data ->
                new SimpleStringProperty(String.format("%.2f$", data.getValue().getPrix())));

        TableColumn<Objet, String> colDate = new TableColumn<>("Date d'achat");
        colDate.setCellValueFactory(data -> {
            var date = data.getValue().getDateAchat();
            return new SimpleStringProperty(date == null ? "" : date.toString());
        });

        TableColumn<Objet, String> colEtat = new TableColumn<>("État");
        colEtat.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStatus()));

        this.getColumns().addAll(colType, colNom, colPrix, colDate, colEtat);
        this.setItems(filteredItems);
        this.setPlaceholder(new Label("Aucun contenu dans la table"));
    }

    // ── Filtre combiné ────────────────────────────────────────────────────
    public void filtrer(String recherche, String type, String etat,
                        String prixMin, String prixMax,
                        LocalDate dateMin, LocalDate dateMax) {

        filteredItems.setPredicate(obj -> {

            // ── Recherche mot-clé ─────────────────────────────────────
            if (recherche != null && !recherche.isBlank()) {
                if (!obj.toString().toLowerCase().contains(recherche.toLowerCase())) {
                    return false;
                }
            }

            // ── Filtre type ───────────────────────────────────────────
            if (type != null && !type.equals("Tous")) {
                if (!obj.getType().toString().equals(type)) {
                    return false;
                }
            }

            // ── Filtre état ───────────────────────────────────────────
            if (etat != null && !etat.equals("Tous")) {
                if (!obj.getStatus().equals(etat)) {
                    return false;
                }
            }

            // ── Filtre prix min ───────────────────────────────────────
            if (prixMin != null && !prixMin.isBlank()) {
                try {
                    double min = Double.parseDouble(prixMin);
                    if (obj.getPrix() < min) return false;
                } catch (NumberFormatException ignored) {}
            }

            // ── Filtre prix max ───────────────────────────────────────
            if (prixMax != null && !prixMax.isBlank()) {
                try {
                    double max = Double.parseDouble(prixMax);
                    if (obj.getPrix() > max) return false;
                } catch (NumberFormatException ignored) {}
            }

            // ── Filtre date min ───────────────────────────────────────
            if (dateMin != null && obj.getDateAchat() != null) {
                if (obj.getDateAchat().isBefore(dateMin)) return false;
            }

            // ── Filtre date max ───────────────────────────────────────
            if (dateMax != null && obj.getDateAchat() != null) {
                if (obj.getDateAchat().isAfter(dateMax)) return false;
            }

            return true;
        });
    }

    // ── Filtre par type seulement ─────────────────────────────────────────
    public void filtrerParType(Types type) {
        filteredItems.setPredicate(o -> o.getType() == type);
    }

    public void afficherTout() {
        filteredItems.setPredicate(o -> true);
    }

    public void ajouterObjet(Objet o) { items.add(o); }
    public void supprimerObjet(Objet o) { items.remove(o); }

    public List<Objet> getObjetsList() {
        return new ArrayList<>(getItems());
    }

    public void viderTable() {
        this.getItems().clear();
    }
}