/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Commande pour sauvegarder l'état courant d'une perspective
 *                    dans un fichier texte.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Écrit les attributs de PerspectiveModel dans un fichier spécifié.
 *   - Peut être étendu ultérieurement pour gérer l'undo via un Memento.
 *   - Dépendances : Model.PerspectiveModel, java.io.FileWriter, java.io.IOException
 * -------------------------------------------------------------------------
 */
package Controller;

import Model.PerspectiveModel;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Commande qui sauvegarde l'état d'une PerspectiveModel dans un fichier texte.
 *
 * <p>Concrètement, on y écrit :
 * <p>Implémente le patron Command pour intégrer cette opération dans
 * la gestion d'historique (undo) via CommandManager.
 */
public class SavePerspectiveCommand implements Command {
    /** Le modèle dont on sauvegarde l'état */
    private final PerspectiveModel model;
    /** Chemin du fichier de sortie où l'état sera écrit */
    private final String outputPath;

    /**
     * Initialise la commande avec le modèle cible et le chemin de fichier.
     *
     * @param model      PerspectiveModel à sauvegarder
     * @param outputPath Chemin du fichier de sortie
     */
    public SavePerspectiveCommand(PerspectiveModel model, String outputPath) {
        this.model = model;
        this.outputPath = outputPath;
    }

    /**
     * Exécute la sauvegarde en écrivant chaque propriété du modèle
     * dans le fichier spécifié.
     */
    @Override
    public void execute() {
        try (FileWriter fw = new FileWriter(outputPath)) {
            fw.write("imagePath=" + model.getImagePath() + "\n");
            fw.write("scale=" + model.getScale() + "\n");
            fw.write("translateX=" + model.getTranslation().x + "\n");
            fw.write("translateY=" + model.getTranslation().y + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Cette commande n'implémente pas encore d'annulation.
     * Pour gérer l'undo, on pourrait utiliser un Memento pour restaurer l'état.
     */
    @Override
    public void undo() {
        // Pas de logique d'annulation pour la sauvegarde actuellement
    }
}
