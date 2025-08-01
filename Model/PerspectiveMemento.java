package Model;

/**
 * Snapshot immuable de l'état d'une PerspectiveModel (scale + translation).
 */
public class PerspectiveMemento {
    private final double scale;
    private final double translateX;
    private final double translateY;

    public PerspectiveMemento(double scale, double translateX, double translateY) {
        this.scale = scale;
        this.translateX = translateX;
        this.translateY = translateY;
    }

    public double getScale() {
        return scale;
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }
}
