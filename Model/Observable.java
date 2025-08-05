/*
 * -------------------------------------------------------------------------
 * Auteur           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Interface définissant le rôle d’un sujet observable
 *                    dans le patron Observer, pour découpler modèles et vues.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Fournit les méthodes d’abonnement et de notification des observateurs.
 *   - Implémentée par les modèles (ImageModel, PerspectiveModel).
 *   - Dépendances : Vue.Observer.
 * -------------------------------------------------------------------------
 */
package Model;

import Vue.Observer;

/**
 * Définit un sujet observable capable de gérer des Observer
 * et de les notifier en cas de changement d’état.
 *
 * <p>Patron : Observer </p>
 */
public interface Observable {

    /**
     * Ajoute un observateur qui sera notifié lors d'une mise à jour du sujet.
     *
     * @param o Observateur à enregistrer
     */
    void addObserver(Observer o);

    /**
     * Retire un observateur pour qu'il ne reçoive plus de notifications.
     *
     * @param o Observateur à désenregistrer
     */
    void removeObserver(Observer o);

    /**
     * Notifie tous les observateurs enregistrés d'un changement d’état.
     */
    void notifyObservers();
}
