/*
 * -------------------------------------------------------------------------
 * Auteurs           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Contrôleur gérant les interactions souris pour
 *                    appliquer zoom et translation sur une perspective.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Interprète les événements mousePressed, mouseDragged, mouseReleased
 *     et mouseClicked.
 *   - Convertit un glissé (drag) en commande de translation.
 *   - Convertit un clic gauche en commande de zoom, avec facteur
 *     dépendant de la touche Ctrl.
 *   - Dépendances : Model.PerspectiveModel, Controller.Command,
 *     Controller.TranslateCommand, Controller.ZoomCommand,
 *     Controller.CommandManager.
 * -------------------------------------------------------------------------
 */

package Controller;

import java.awt.event.MouseEvent;
import Model.PerspectiveModel;

/**
 * Gère les événements de la souris et génère les commandes correspondantes
 * (translation et zoom) à exécuter via {@link CommandManager}.
 *
 * <p>Partie du patron Command : le contrôleur agit comme client qui crée
 * et envoie les commandes à l’Invoker ({@code CommandManager}).</p>
 */
public class MouseController {
    /** Le modèle manipulé par ce contrôleur */
    private final PerspectiveModel perspective;
    /** Coordonnées de la souris lors du dernier press/drag */
    private int lastX, lastY;
    /** Accumulateur de déplacement durant le drag */
    private double fx, fy;
    /** Indique si un drag est en cours */
    private boolean dragging = false;

    /**
     * Initialise le contrôleur avec la perspective cible.
     *
     * @param perspective Le modèle de perspective sur lequel agir
     */
    public MouseController(PerspectiveModel perspective) {
        this.perspective = perspective;
    }

    /**
     * Enregistre la position initiale du drag et démarre le mode dragging.
     *
     * @param e Événement de souris contenant les coordonnées initiales
     */
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
        fx = 0;
        fy = 0;
        dragging = true;
    }

    /**
     * À la fin du drag, crée et exécute une commande de translation
     * avec le déplacement accumulé, puis termine le mode dragging.
     *
     * @param e Événement de souris (non utilisé ici)
     */
    public void mouseReleased(MouseEvent e) {
        // Encapsule le déplacement total en commande
        Command trCmd = new TranslateCommand(perspective, fx, fy);
        CommandManager.getInstance().executeCommand(trCmd);
        dragging = false;
    }

    /**
     * Pendant le drag, calcule et accumule le delta de déplacement
     * sans exécuter immédiatement la commande (optimisation UX).
     *
     * @param e Événement de souris contenant les nouvelles coordonnées
     */
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            int x = e.getX();
            int y = e.getY();
            // Calcul du déplacement depuis la dernière position
            double dx = x - lastX;
            double dy = y - lastY;
            fx += dx;
            fy += dy;
            // Mise à jour des dernières coordonnées
            lastX = x;
            lastY = y;
        }
    }

    /**
     * Lors d’un clic gauche, crée et exécute une commande de zoom.
     * Le facteur est inversé si la touche Ctrl est maintenue.
     *
     * @param e Événement de souris indiquant le bouton et l’état Ctrl
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Zoom avant ou arrière selon Ctrl
            double factor = e.isControlDown() ? 0.9 : 1.1;
            Command zoomCmd = new ZoomCommand(perspective, factor);
            CommandManager.getInstance().executeCommand(zoomCmd);
        }
    }
}
