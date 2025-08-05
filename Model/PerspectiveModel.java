/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Modèle représentant la perspective d’une image
 *                    (zoom, translation, chemin de l’image).
 *                    Implémente le patron Observer pour notifier
 *                    automatiquement les vues de toute modification.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Attributs : scale (facteur de zoom), translateX/Y (déplacements),
 *     imagePath (chemin du fichier).
 *   - Méthodes : zoom(), translate(), getters/setters,
 *     gestion des observateurs (add/remove/notify).
 *   - Dépendances : java.awt.Point, java.io.Serializable, Vue.Observer.
 * -------------------------------------------------------------------------
 */
package Model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Vue.Observer;

/**
 * Représente l’état d’une perspective : facteur d’échelle,
 * translations X/Y et chemin de l’image associée.
 * Notifie ses observers à chaque changement d’état.
 */
public class PerspectiveModel implements Serializable {
    /** Facteur de zoom initial */
    private double scale = 0.5;
    /** Déplacement horizontal */
    private double translateX = 0;
    /** Déplacement vertical */
    private double translateY = 0;
    /** Chemin du fichier image */
    private String imagePath = "";
    /** Liste des observers abonnés */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Applique un zoom en multipliant l’échelle par le facteur spécifié.
     *
     * @param factor Facteur de zoom
     */
    public void zoom(double factor) {
        scale *= factor;
        notifyObservers(); // Notifie les vues du nouveau zoom
    }

    /**
     * Applique une translation en ajoutant dx/dy aux coordonnées actuelles.
     *
     * @param dx Déplacement en X
     * @param dy Déplacement en Y
     */
    public void translate(double dx, double dy) {
        translateX += dx;
        translateY += dy;
        notifyObservers(); // Notifie les vues du nouveau déplacement
    }

    /**
     * Retourne le facteur de zoom actuel.
     *
     * @return le facteur d’échelle
     */
    public double getScale() {
        return scale;
    }

    /**
     * Retourne la translation courante sous forme de Point (int pour l’affichage).
     *
     * @return un Point représentant (translateX, translateY)
     */
    public Point getTranslation() {
        return new Point((int) translateX, (int) translateY);
    }

    /**
     * Retourne le chemin du fichier image associé.
     *
     * @return le chemin de l’image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Modifie le chemin de l’image et réinitialise zoom et translation
     * aux valeurs par défaut. Notifie ensuite les observers.
     *
     * @param path le nouveau chemin d’image
     */
    public void setImagePath(String path) {
        imagePath = path;
        scale = 0.5;        // Valeur par défaut
        translateX = 0;
        translateY = 0;
        notifyObservers();  // Notifie les vues du changement d’image
    }

    /**
     * Définit directement la translation et notifie les observers.
     *
     * @param x nouvelle coordonnée X
     * @param y nouvelle coordonnée Y
     */
    public void setTranslation(double x, double y) {
        this.translateX = x;
        this.translateY = y;
        notifyObservers();
    }

    /**
     * Définit directement le facteur d’échelle et notifie les observers.
     *
     * @param scale2 nouveau facteur de zoom
     */
    public void setScale(double scale2) {
        this.scale = scale2;
        notifyObservers();
    }

    /**
     * Abonne un observer pour qu’il reçoive les notifications de changement.
     *
     * @param o observer à ajouter
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Désabonne un observer pour qu’il n’écoute plus les notifications.
     *
     * @param o observer à retirer
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Parcourt tous les observers abonnés et appelle leur méthode update().
     * Sert à informer les vues que le modèle a changé.
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
