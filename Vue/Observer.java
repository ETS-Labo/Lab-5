/*
 * -------------------------------------------------------------------------
 * Auteurs           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Interface définissant le rôle d’un observateur dans
 *                    le patron Observer. Chaque implémentation réagit aux
 *                    notifications d’un objet Observable.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Déclare la méthode update() appelée par les Observable.
 *   - Implémentée par les vues (PerspectiveView, ThumbnailView).
 * -------------------------------------------------------------------------
 */
package Vue;

/**
 * Observateur dans le patron Observer.
 * <p>
 * Les classes implementant cette interface doivent fournir une
 * implémentation de {@link #update()} pour réagir aux changements
 * d’état du modèle observable.
 * </p>
 */
public interface Observer {

    /**
     * Méthode appelée lorsqu’un {@link Model.Observable} notifie
     * ses observers. L’implémentation doit mettre à jour l’affichage
     * ou l’état interne en conséquence.
     */
    void update();
}
