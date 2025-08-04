package Controller;

import Model.PerspectiveModel;
import java.io.FileWriter;
import java.io.IOException;

public class SavePerspectiveCommand implements Command {
    private final PerspectiveModel model;
    private final String outputPath;

    public SavePerspectiveCommand(PerspectiveModel model, String outputPath) {
        this.model = model;
        this.outputPath = outputPath;
    }

    @Override
    public void execute() {
        try (FileWriter fw = new FileWriter(outputPath)) {
            fw.write("imagePath=" + model.getImagePath() + "\n");
            fw.write("scale=" + model.getScale() + "\n");
            fw.write("translateX=" + model.getTranslation().x + "\n");
            fw.write("translateY=" + model.getTranslation().y + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void undo() {
    }
}
