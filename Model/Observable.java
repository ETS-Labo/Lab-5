package Model;

import Vue.Observer;

public interface Observable {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
