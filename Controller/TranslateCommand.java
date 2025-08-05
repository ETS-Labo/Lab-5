/*
 * -------------------------------------------------------------------------
 * Auteurs           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Commande pour appliquer une translation sur un modèle
 *                    de perspective et permettre son annulation.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Stocke le déplacement (dx, dy) et la position précédente du modèle.
 *   - execute() applique la translation, undo() restaure la position antérieure.
 *   - Dépendances : Model.PerspectiveModel, Controller.Command.
 * -------------------------------------------------------------------------
 */
package Controller;

import Model.PerspectiveModel;

/**
 * Implémente le patron Command pour isoler une opération de translation
 * sur la PerspectiveModel. Cette commande conserve l’état avant
 * exécution afin de pouvoir effectuer un undo.
 */
public class TranslateCommand implements Command {
    /** Modèle cible de la translation */
    private final PerspectiveModel perspective;
    /** Coordonnées précédentes avant exécution */
    private double prevX, prevY;
    /** Déplacements à appliquer */
    private final double dx, dy;

    /**
     * Initialise la commande de translation.
     *
     * @param perspective Le modèle sur lequel appliquer la translation
     * @param dx          Distance à déplacer en abscisse
     * @param dy          Distance à déplacer en ordonnée
     */
    public TranslateCommand(PerspectiveModel perspective, double dx, double dy) {
        this.perspective = perspective;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Exécute la translation :
     * 1) mémorise la position courante,
     * 2) applique le déplacement.
     */
    @Override
    public void execute() {
        prevX = perspective.getTranslation().getX();
        prevY = perspective.getTranslation().getY();
        perspective.translate(dx, dy);
    }

    /**
     * Annule la translation en calculant le mouvement inverse
     * et en l’appliquant au modèle.
     */
    @Override
    public void undo() {
        double currentX = perspective.getTranslation().getX();
        double currentY = perspective.getTranslation().getY();
        perspective.translate(prevX - currentX, prevY - currentY);
    }
}
