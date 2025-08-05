/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Modèle unique gérant le chargement d'image et la liste
 *                    des perspectives. Implémente Observable pour notifier
 *                    les vues et Serializable pour la persistance.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Singleton : assure une seule instance de l’application.
 *   - gère la liste de PerspectiveModel et notifie les Observer.
 *   - Dépendances : Model.PerspectiveModel, Vue.Observer, java.io.Serializable
 * -------------------------------------------------------------------------
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Vue.Observer;

/**
 * Modèle représentant une image et ses perspectives associées.
 *
 * <p>Partie du patron Observer :
 * - ConcreteSubject pour ImageModel.
 * - Permet d’abonner des Observer et de les notifier.
 *
 * <p>Singleton :
 * - Une seule instance partagée par toute l’application.
 */
public class ImageModel implements Observable, Serializable {
    /** Instance unique (singleton) */
    private static ImageModel instance;
    /** Chemin du fichier image courant */
    private String filePath;
    /** Liste des perspectives associées à l’image */
    private final List<PerspectiveModel> perspectives = new ArrayList<>();
    /** Liste des observateurs à notifier */
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Constructeur privé pour empêcher l’instanciation externe.
     */
    private ImageModel() {}

    /**
     * Retourne l’instance unique de ImageModel (singleton).
     *
     * @return instance globale de ImageModel
     */
    public static synchronized ImageModel getInstance() {
        if (instance == null) {
            instance = new ImageModel();
        }
        return instance;
    }

    /**
     * Charge une nouvelle image et met à jour le chemin.
     * Met à jour chaque PerspectiveModel avec le nouveau chemin
     * puis notifie tous les observateurs.
     *
     * @param path Chemin du fichier image à charger
     */
    public void loadImage(String path) {
        this.filePath = path;
        for (PerspectiveModel p : perspectives) {
            p.setImagePath(path);
        }
        notifyObservers();
    }

    /**
     * Retourne le chemin du fichier image courant.
     *
     * @return chemin de l’image
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Ajoute une nouvelle perspective et notifie les observateurs
     * pour mettre à jour la vue miniature si nécessaire.
     *
     * @param p PerspectiveModel à ajouter
     */
    public void addPerspective(PerspectiveModel p) {
        perspectives.add(p);
        notifyObservers();
    }

    /**
     * @return liste des perspectives associées
     */
    public List<PerspectiveModel> getPerspectives() {
        return perspectives;
    }

    /**
     * Abonne un observateur pour recevoir les notifications de changement.
     *
     * @param o observateur à ajouter
     */
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Désabonne un observateur.
     *
     * @param o observateur à retirer
     */
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifie tous les observateurs que le modèle a changé.
     */
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}