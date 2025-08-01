/*

Test à modifier et à migrer, je l'ai mis seulement pour ne pas me perdre

 */


package Controller;

import static org.junit.jupiter.api.Assertions.*;

import Model.PerspectiveModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZoomCommandMementoTest {

    private PerspectiveModel model;

    @BeforeEach
    void setUp() throws Exception {
        java.lang.reflect.Field instanceField = CommandManager.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        model = new PerspectiveModel();
    }

    @Test
    void testExecuteAndUndo_withMemento() {
        CommandManager cm = CommandManager.getInstance();
        double initialScale = model.getScale();
        ZoomCommand cmd = new ZoomCommand(model, 2.0);

        cm.executeCommand(cmd);
        assertEquals(initialScale * 2.0, model.getScale(), 1e-9, "Scale should be doubled after zoom");

        cm.undoLast();
        assertEquals(initialScale, model.getScale(), 1e-9, "After undo, scale should revert to initial");
    }
}
