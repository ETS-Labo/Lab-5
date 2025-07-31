package Controller;

import Model.PerspectiveModel;

public class ZoomCommand implements Command {
    private PerspectiveModel perspective;
    private double previousScale;
    private double factor;

    public ZoomCommand(PerspectiveModel perspective, double factor) {
        this.perspective = perspective;
        this.factor = factor;
    }

    public void execute() {
        previousScale = perspective.getScale();
        perspective.zoom(factor);
    }

    public void undo() {
        perspective.zoom(previousScale / perspective.getScale());
    }
}
