package Model;

import java.io.Serializable;
import java.util.List;

import Vue.Observer;
import Vue.PerspectiveView;


public class ImageModel implements Observable, Serializable{
    private String filePath;
    private List<PerspectiveView> perspectives;

    public void loadImage(String path){

    }

    public String getFilePath(){
        return null;
    }

    public void addPerspective(PerspectiveModel p){
        
    }

    public PerspectiveModel getPerspective(){
        return null;
    }


    public void addObserver(Observer o){

    }

    public void removeObserver(Observer o){

    }

    public void notifyObservers(){
        for (PerspectiveView perspective : perspectives){
            perspective.update();
        }
    }
}
