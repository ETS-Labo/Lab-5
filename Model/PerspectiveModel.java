package Model;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Vue.Observer;

public class PerspectiveModel implements Serializable {
    private double scale = 0.5;
    private double translateX = 0;
    private double translateY = 0;
    private String imagePath = "";
    private List<Observer> observers = new ArrayList<>();

    public void zoom(double factor) {
        scale *= factor;
        notifyObservers();
    }

    public void translate(double dx, double dy) {
        translateX += dx;
        translateY += dy;
        notifyObservers();
    }

    public double getScale() { return scale; }
    public Point getTranslation() { return new Point((int)translateX, (int)translateY); }
    public String getImagePath() { return imagePath; }

    public void setImagePath(String path) {
        imagePath = path;
        scale = 0.5;
        translateX = 0;
        translateY = 0;
        notifyObservers();
    }

    public void setTranslation(double x, double y) {
        this.translateX = x;
        this.translateY = y;
        notifyObservers();
    }

    public void setScale(double s) {
        this.scale = s;
        notifyObservers();
    }

    // Memento support
    public PerspectiveMemento createMemento() {
        return new PerspectiveMemento(scale, translateX, translateY);
    }

    public void restore(PerspectiveMemento memento) {
        this.scale = memento.getScale();
        this.translateX = memento.getTranslateX();
        this.translateY = memento.getTranslateY();
        notifyObservers();
    }

    public PerspectiveMemento createMemento(){
        return new PerspectiveMemento();
    }

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers() { for (Observer o : observers) o.update(); }
}
