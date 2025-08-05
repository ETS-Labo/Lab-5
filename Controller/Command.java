/*
 * -------------------------------------------------------------------------
 * Auteurs           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Interface Command définissant les opérations
 *                    exécutables et annulables pour le patron Command.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Partie intégrante du patron Command (GoF).
 *   - Utilisée par CommandManager (invoker) et les contrôleurs
 *     pour découpler l’émission et l’exécution des requêtes.
 *   - Méthodes : execute() pour appliquer l’action, undo() pour y revenir.
 * -------------------------------------------------------------------------
 */

package Controller;

/**
 * Représente une action exécutable dans l’application, avec la possibilité
 * de l’annuler. Chaque commande encapsule une opération sur
 * le modèle (zoom, translation, sauvegarde, etc.) et en fournit l’inverse.
 *
 * <p>Patron : Command
 * Rôle :
 * <ul>
 *   <li>Déclare l’interface d’exécution {@code execute()}.</li>
 *   <li>Fournit une méthode {@code undo()} pour revenir en arrière.</li>
 * </ul>
 *
 * <p>Contexte : utilisé par {@link CommandManager} (invoker) et par
 * les contrôleurs pour découpler l’émission et l’exécution des requêtes.
 */
public interface Command {

    /**
     * Exécute l’opération encapsulée par cette commande.
     */
    void execute();

    /**
     * Annule l’opération précédemment exécutée.
     * Doit ramener le modèle à son état avant {@link #execute()}.
     */
    void undo();
}
