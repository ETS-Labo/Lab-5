package Controller;

import Model.PerspectiveModel;
import Model.PerspectiveMemento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Caretaker qui maintient des piles undo/redo pour chaque PerspectiveModel.
 * Permet de sauvegarder explicitement des états (checkpoints) et de revenir en arrière
 * ou avancer dans l'historique indépendamment du Command pattern.
 */
public class MementoManager {
    private final Map<PerspectiveModel, Deque<PerspectiveMemento>> undoStacks = new HashMap<>();
    private final Map<PerspectiveModel, Deque<PerspectiveMemento>> redoStacks = new HashMap<>();
    private final int maxHistorySize;

    public MementoManager() {
        this.maxHistorySize = 50; // par défaut limiter la profondeur
    }

    public MementoManager(int maxHistorySize) {
        this.maxHistorySize = Math.max(1, maxHistorySize);
    }

    /**
     * Sauvegarde l'état courant du modèle dans la pile d'undo et vide la redo stack.
     */
    public void save(PerspectiveModel model) {
        Deque<PerspectiveMemento> undo = undoStacks.computeIfAbsent(model, k -> new ArrayDeque<>());
        undo.push(model.createMemento());
        trimIfNeeded(undo);
        // reset redo stack
        Deque<PerspectiveMemento> redo = redoStacks.computeIfAbsent(model, k -> new ArrayDeque<>());
        redo.clear();
    }

    /**
     * Annule : restaure l'état précédent si disponible.
     */
    public void undo(PerspectiveModel model) {
        Deque<PerspectiveMemento> undo = undoStacks.get(model);
        if (undo == null || undo.isEmpty()) return;

        // sauvegarder l'état courant dans redo
        Deque<PerspectiveMemento> redo = redoStacks.computeIfAbsent(model, k -> new ArrayDeque<>());
        redo.push(model.createMemento());
        trimIfNeeded(redo);

        // restaurer le précédent
        PerspectiveMemento previous = undo.pop();
        model.restore(previous);
    }

    /**
     * Rétablit l'état annulé si disponible.
     */
    public void redo(PerspectiveModel model) {
        Deque<PerspectiveMemento> redo = redoStacks.get(model);
        if (redo == null || redo.isEmpty()) return;

        Deque<PerspectiveMemento> undo = undoStacks.computeIfAbsent(model, k -> new ArrayDeque<>());
        undo.push(model.createMemento());
        trimIfNeeded(undo);

        PerspectiveMemento next = redo.pop();
        model.restore(next);
    }

    public boolean canUndo(PerspectiveModel model) {
        Deque<PerspectiveMemento> undo = undoStacks.get(model);
        return undo != null && !undo.isEmpty();
    }

    public boolean canRedo(PerspectiveModel model) {
        Deque<PerspectiveMemento> redo = redoStacks.get(model);
        return redo != null && !redo.isEmpty();
    }

    /**
     * Vide l'historique pour un modèle donné.
     */
    public void clearHistory(PerspectiveModel model) {
        Deque<PerspectiveMemento> undo = undoStacks.get(model);
        if (undo != null) undo.clear();
        Deque<PerspectiveMemento> redo = redoStacks.get(model);
        if (redo != null) redo.clear();
    }

    private void trimIfNeeded(Deque<PerspectiveMemento> stack) {
        while (stack.size() > maxHistorySize) {
            // supprimer l'élément le plus ancien (à la base)
            if (stack instanceof ArrayDeque) {
                ((ArrayDeque<PerspectiveMemento>) stack).removeLast();
            } else {
                // fallback général
                while (stack.size() > maxHistorySize) stack.removeLast();
            }
        }
    }
}
