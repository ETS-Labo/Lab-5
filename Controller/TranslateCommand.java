package Controller;

import Model.PerspectiveModel;
import Model.PerspectiveMemento;

public class TranslateCommand implements Command {
    private final PerspectiveModel perspective;
    private final double dx, dy;
    private final PerspectiveMemento before;

    public TranslateCommand(PerspectiveModel perspective, double dx, double dy) {
        this.perspective = perspective;
        this.dx = dx;
        this.dy = dy;
        this.before = perspective.createMemento();
    }

    @Override
    public void execute() {
        perspective.translate(dx, dy);
    }

    @Override
    public void undo() {
        perspective.restore(before);
    }
}
