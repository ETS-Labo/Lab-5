package Controller;

import java.awt.event.MouseEvent;

import Model.PerspectiveModel;

public class MouseController{
    private PerspectiveModel perspective;
    private int lastX;
    private int lastY;

    public MouseController (PerspectiveModel perspective){
        this.perspective = perspective;
    }

    public void mousePressed(MouseEvent e){
        lastX = e.getX();
        lastY = e.getY();
    }

    public void mouseDragged(MouseEvent e){

    }

}
