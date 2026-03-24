package Backend.Gestion;

import Backend.Objets.Objet;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class GestionFichier {

    private File fichierCourant;

    // ── Nouveau ───────────────────────────────────────────────────────────
    public List<Objet> nouveau() {
        fichierCourant = null;
        return new java.util.ArrayList<>();
    }

    // ── Ouvrir ────────────────────────────────────────────────────────────
    public List<Objet> ouvrir(File fichier) throws IOException, ClassNotFoundException {
        fichierCourant = fichier;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))) {
            return (List<Objet>) ois.readObject();
        }
    }

    // ── Sauvegarder ───────────────────────────────────────────────────────
    public void sauvegarder(List<Objet> objets) throws IOException {
        if (fichierCourant == null) {
            throw new IOException("Aucun fichier courant — utilisez Sauvegarder sous.");
        }
        ecrireFichier(fichierCourant, objets);
    }

    // ── Sauvegarder sous ──────────────────────────────────────────────────
    public void sauvegarderSous(File fichier, List<Objet> objets) throws IOException {
        fichierCourant = fichier;
        ecrireFichier(fichier, objets);
    }

    // ── Exporter JSON ─────────────────────────────────────────────────────
    public void exporterJSON(File fichier, List<Objet> objets) throws IOException {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < objets.size(); i++) {
            json.append("  {\n");
            json.append("    \"type\": \"").append(objets.get(i).getType()).append("\",\n");
            json.append("    \"nom\": \"").append(objets.get(i).getNom()).append("\",\n");
            json.append("    \"prix\": ").append(objets.get(i).getPrix()).append(",\n");
            json.append("    \"description\": \"").append(objets.get(i).toString()).append("\"\n");
            json.append("  }");
            if (i < objets.size() - 1) json.append(",");
            json.append("\n");
        }
        json.append("]");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(json.toString());
        }
    }

    // ── Copier image ──────────────────────────────────────────────────────
    public String copierImage(File image) throws IOException {
        if (fichierCourant == null) {
            throw new IOException("Sauvegardez le fichier avant d'ajouter des images.");
        }
        File dossierImages = new File(fichierCourant.getParent(), "images");
        if (!dossierImages.exists()) dossierImages.mkdirs();

        File destination = new File(dossierImages, image.getName());
        Files.copy(image.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return destination.getAbsolutePath();
    }

    // ── Helper ────────────────────────────────────────────────────────────
    private void ecrireFichier(File fichier, List<Objet> objets) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))) {
            oos.writeObject(objets);
        }
    }

    // ── Getter ────────────────────────────────────────────────────────────
    public File getFichierCourant() { return fichierCourant; }
}


//Ne pas oublier de try-catch la méthode!!
