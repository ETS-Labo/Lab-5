package Controller;

/* Ici je vais mettre un test unitaire
Donc prenez pas en compte ce qu'il y a ici car il sera modifié et migré vers un autre dossier.
 */

import static org.junit.jupiter.api.Assertions.*;

import Model.PerspectiveModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TranslateCommandMementoTest {

    private PerspectiveModel model;

    @BeforeEach
    void setUp() throws Exception {
        // Reset singleton CommandManager so tests are isolated
        java.lang.reflect.Field instanceField = CommandManager.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        model = new PerspectiveModel();
    }

    @Test
    void testExecuteAndUndo_withMemento() {
        CommandManager cm = CommandManager.getInstance();
        TranslateCommand cmd = new TranslateCommand(model, 10, 15);

        cm.executeCommand(cmd);
        assertEquals(10, model.getTranslation().x, "Translation X should be applied");
        assertEquals(15, model.getTranslation().y, "Translation Y should be applied");

        cm.undoLast();
        assertEquals(0, model.getTranslation().x, "After undo, X should revert");
        assertEquals(0, model.getTranslation().y, "After undo, Y should revert");
    }
}

