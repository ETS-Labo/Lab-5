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

    public void setTranslation(double perspectiveData, double perspectiveData2) {
        this.translateX = perspectiveData;
        this.translateY = perspectiveData2;
        notifyObservers();
    }

    public void setScale(double scale2) {
        this.scale = scale2;
        notifyObservers();
    }

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers() { for (Observer o : observers) o.update(); }
}
