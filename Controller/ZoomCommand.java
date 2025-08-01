package Controller;

import Model.PerspectiveModel;
import Model.PerspectiveMemento;

public class ZoomCommand implements Command {
    private final PerspectiveModel perspective;
    private final double factor;
    private final PerspectiveMemento before;

    public ZoomCommand(PerspectiveModel perspective, double factor) {
        this.perspective = perspective;
        this.factor = factor;
        this.before = perspective.createMemento();
    }

    @Override
    public void execute() {
        perspective.zoom(factor);
    }

    @Override
    public void undo() {
        perspective.restore(before);
    }
}
