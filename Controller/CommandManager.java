/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Gestionnaire centralisant l’exécution et l’annulation
 *                    des commandes. Implémente le patron Singleton pour
 *                    garantir un point d’accès unique à l’historique.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Maintient une liste de commandes exécutées (historique).
 *   - Fournit executeCommand() pour appliquer et mémoriser une commande.
 *   - Fournit undoLast() pour annuler la dernière commande exécutée.
 *   - Thread-safe via synchronisation de getInstance().
 * -------------------------------------------------------------------------
 */

package Controller;

import java.util.List;
import java.util.ArrayList;

/**
 * Singleton responsable de la gestion de l’historique des commandes.
 * Seul point d’accès pour exécuter ou annuler des actions de type {@link Command}.
 *
 * <p>Patron : Singleton</p>
 */
public class CommandManager {
    /** Instance unique du gestionnaire */
    private static CommandManager instance;

    /** Historique des commandes exécutées, dans l’ordre d’exécution */
    private final List<Command> history = new ArrayList<>();

    /**
     * Constructeur privé pour interdire l’instanciation externe.
     */
    private CommandManager() {}

    /**
     * Retourne l’instance unique du CommandManager de manière thread-safe.
     *
     * @return instance globale de CommandManager
     */
    public static synchronized CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    /**
     * Exécute la commande fournie, puis l’ajoute à l’historique.
     *
     * @param c Commande à exécuter
     */
    public void executeCommand(Command c) {
        // Appliquer l’opération encapsulée
        c.execute();
        // Mémoriser pour pouvoir undo plus tard
        history.add(c);
    }

    /**
     * Annule la dernière commande exécutée en appelant sa méthode undo()
     * et la retire de l’historique.
     */
    public void undoLast() {
        if (!history.isEmpty()) {
            // Récupérer et supprimer la dernière commande
            Command last = history.remove(history.size() - 1);
            // Effectuer l’annulation
            last.undo();
        }
    }
}
