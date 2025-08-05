/*
 * -------------------------------------------------------------------------
 * Auteurs           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Vue miniature affichant l’image dans un espace réduit,
 *                    sans tenir compte des transformations de perspective.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Observe ImageModel pour mettre à jour la miniature à chaque chargement.
 *   - Conserve le ratio d’aspect et centre l’image dans le panneau.
 *   - Dépendances : Model.ImageModel, Swing (JPanel, Graphics),
 *     ImageIO pour le chargement d’image.
 * -------------------------------------------------------------------------
 */
package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import Model.ImageModel;

/**
 * Panneau Swing affichant une miniature de l’image chargée.
 * <p>
 * Réagit aux notifications du modèle {@code ImageModel} pour
 * recharger et redessiner l’image en conservant son ratio d’aspect.
 * </p>
 */
public class ThumbnailView extends JPanel implements Observer {
    /** Modèle fournissant le chemin de l’image à afficher */
    private final ImageModel model;
    /** Image chargée et mise à l’échelle */
    private BufferedImage img = null;

    /**
     * Initialise la vue miniature et s’abonne au modèle.
     * Charge immédiatement l’image si disponible.
     *
     * @param model Le modèle d’image à observer
     */
    public ThumbnailView(ImageModel model) {
        this.model = model;
        model.addObserver(this);  // S’abonner aux notifications de changement
        update();                 // Chargement initial de la miniature
    }

    /**
     * Chargement de l’image depuis le chemin indiqué dans le modèle,
     * puis déclenchement d’un repaint.
     */
    @Override
    public void update() {
        String path = model.getFilePath();
        if (path != null && !path.isEmpty()) {
            try {
                img = ImageIO.read(new File(path));  // Charge l’image depuis le fichier
            } catch (Exception ex) {
                img = null;                          // En cas d’erreur, pas de miniature
            }
        } else {
            img = null;  // Pas d’image chargée
        }
        repaint();  // Redessiner la miniature
    }

    /**
     * Dessine la miniature en conservant le ratio d’aspect
     * et en centrant l’image. Si aucune image, affiche un message.
     *
     * @param g Contexte graphique
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (img != null) {
            int w = getWidth();
            int h = getHeight();
            // Calcul du ratio afin de conserver le format de l’image
            double ratio = Math.min((double) w / img.getWidth(),
                    (double) h / img.getHeight());
            int iw = (int) (img.getWidth() * ratio);
            int ih = (int) (img.getHeight() * ratio);
            // Centre l’image dans le panneau
            g.drawImage(img,
                    (w - iw) / 2,
                    (h - ih) / 2,
                    iw, ih,
                    this);
        } else {
            // Message lorsque aucune image n’est chargée
            int x = getWidth()  / 4;
            int y = getHeight() / 2;
            g.drawString("Miniature : aucune image", x, y);
        }
    }
}
