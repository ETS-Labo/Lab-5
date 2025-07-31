package Model;

import java.awt.Point;
import java.io.Serializable;

import Vue.Observer;

public class PerspectiveModel implements Serializable{

    private double scale;
    private double translateX;
    private double translateY;


    public void zoom(double factor){

    }

    public void translate(double dx, double dy){

    }

    public <PerspectiveData> PerspectiveData getViewData(){
        return null;
    }

    public double getScale(){
        return this.scale;
    }

    public Point getTranslation(){
        return null;
    }


    public void addObserver(Observer o){

    }

    public void removeObserver(Observer o){

    }

}   
