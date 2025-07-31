package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Vue.Observer;
import Vue.PerspectiveView;

public class ImageModel implements Observable, Serializable {
    private static ImageModel instance;
    private String filePath;
    private List<PerspectiveModel> perspectives = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private ImageModel() {}

    public static synchronized ImageModel getInstance() {
        if (instance == null) {
            instance = new ImageModel();
        }
        return instance;
    }

    public void loadImage(String path) {
        this.filePath = path;
        for (PerspectiveModel p : perspectives) {
            p.setImagePath(path);
        }
        notifyObservers();
    }

    public String getFilePath() {
        return filePath;
    }

    public void addPerspective(PerspectiveModel p) {
        perspectives.add(p);
        notifyObservers();
    }

    public List<PerspectiveModel> getPerspectives() {
        return perspectives;
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
