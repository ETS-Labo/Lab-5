/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Vue graphique pour afficher une perspective d’image.
 *                    Gère l’affichage, le zoom et la translation via la souris.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Observe un PerspectiveModel pour refléter ses changements.
 *   - Configure MouseController pour drag/zoom (clic, molette).
 *   - Dépendances : Model.PerspectiveModel, Controller.MouseController,
 *     Controller.ZoomCommand, Controller.CommandManager, Swing, AWT.
 * -------------------------------------------------------------------------
 */
package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Controller.Command;
import Controller.MouseController;
import Controller.ZoomCommand;
import Controller.CommandManager;
import Model.PerspectiveModel;

/**
 * Panneau Swing affichant une perspective d’image, avec support
 * de l’observation du modèle et des interactions utilisateur
 * pour zoom et translation.
 */
public class PerspectiveView extends JPanel implements Observer {

    /** Modèle représentant l’état de la perspective (zoom, translation). */
    private final PerspectiveModel model;

    /** Contrôleur spécialisé pour interpréter les événements souris. */
    private final MouseController mouseController;

    /** Image chargée depuis le modèle (rafraîchie à chaque update()). */
    private BufferedImage img = null;

    /**
     * Initialise la vue de perspective et l’enregistre comme observateur
     * du modèle. Configure les listeners pour gérer drag, clic et molette.
     *
     * @param model Le modèle de perspective à afficher et contrôler
     */
    public PerspectiveView(PerspectiveModel model) {
        this.model = model;
        model.addObserver(this);                       // S’abonne aux notifications
        this.mouseController = new MouseController(model);

        // Listener pour les événements de souris principaux
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseController.mousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                mouseController.mouseReleased(e);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseController.mouseClicked(e);
            }
        });

        // Listener pour le drag (translation)
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseController.mouseDragged(e);
            }
        });

        // Listener pour la molette (zoom)
        addMouseWheelListener(e -> {
            double factor = (e.getWheelRotation() < 0) ? 1.1 : 0.9;
            Command zoomCmd = new ZoomCommand(model, factor);
            CommandManager.getInstance().executeCommand(zoomCmd);
        });
    }

    /**
     * Récupère l’image depuis le modèle (si un chemin est défini),
     * puis déclenche un repaint pour mettre à jour l’affichage.
     */
    @Override
    public void update() {
        String path = model.getImagePath();
        if (path != null && !path.isEmpty()) {
            try {
                img = ImageIO.read(new File(path));    // Charge l’image depuis le fichier
            } catch (Exception ex) {
                img = null;                            // En cas d’erreur, aucune image
            }
        }
        repaint();                                     // Redessine le composant
    }

    /**
     * Dessine l’image en appliquant la translation et le zoom du modèle.
     * Si aucune image n’est disponible, affiche un message centré.
     *
     * @param g Contexte graphique
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform saved = g2.getTransform();    // Sauvegarde la transformation

            // Applique translation et zoom avant de dessiner l’image
            g2.translate(model.getTranslation().x, model.getTranslation().y);
            g2.scale(model.getScale(), model.getScale());
            g2.drawImage(img, 0, 0, this);

            g2.setTransform(saved);                       // Restaure la transformation
        } else {
            // Si pas d’image, affiche un texte centré
            int x = getWidth() / 2 - 50;
            int y = getHeight() / 2;
            g.drawString("Aucune image", x, y);
        }
    }
}
