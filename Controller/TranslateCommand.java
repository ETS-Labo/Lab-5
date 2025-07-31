package Controller;

import Model.PerspectiveModel;

public class TranslateCommand implements Command {
    private PerspectiveModel perspective;
    private double prevX, prevY;
    private double dx, dy;

    public TranslateCommand(PerspectiveModel perspective, double dx, double dy) {
        this.perspective = perspective;
        this.dx = dx;
        this.dy = dy;
    }

    public void execute() {
        prevX = perspective.getTranslation().getX();
        prevY = perspective.getTranslation().getY();
        perspective.translate(dx, dy);
    }

    public void undo() {
        perspective.translate(prevX - perspective.getTranslation().getX(),
                              prevY - perspective.getTranslation().getY());
    }
}
