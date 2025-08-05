/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Commande appliquant un zoom sur un modèle de perspective
 *                    et permettant son annulation.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Conserve l’échelle précédente avant application du zoom.
 *   - execute() multiplie l’échelle par le facteur donné.
 *   - undo() restaure l’ancienne échelle en appliquant l’inverse.
 *   - Dépendances : Model.PerspectiveModel, Controller.Command.
 * -------------------------------------------------------------------------
 */
package Controller;

import Model.PerspectiveModel;

/**
 * Implémente le patron Command pour isoler l’opération de zoom
 * sur une PerspectiveModel. Cette commande stocke l’échelle
 * antérieure afin de pouvoir la rétablir lors d’un undo.
 */
public class ZoomCommand implements Command {
    /** Modèle de perspective ciblé */
    private final PerspectiveModel perspective;
    /** Échelle avant application du zoom */
    private double previousScale;
    /** Facteur de zoom à appliquer */
    private final double factor;

    /**
     * Crée une commande de zoom.
     *
     * @param perspective Le modèle à zoomer
     * @param factor      Facteur par lequel multiplier l’échelle
     */
    public ZoomCommand(PerspectiveModel perspective, double factor) {
        this.perspective = perspective;
        this.factor = factor;
    }

    /**
     * Exécute le zoom :
     * 1) mémorise l’échelle actuelle,
     * 2) applique le facteur de zoom.
     */
    @Override
    public void execute() {
        previousScale = perspective.getScale();
        perspective.zoom(factor);
    }

    /**
     * Annule le zoom en calculant et appliquant le facteur inverse.
     * Inverse = ancienne échelle ÷ échelle courante après execute().
     */
    @Override
    public void undo() {
        double currentScale = perspective.getScale();
        // Applique l’inverse pour revenir à previousScale
        perspective.zoom(previousScale / currentScale);
    }
}
