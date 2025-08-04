package Controller;

import java.awt.event.MouseEvent;

import Model.PerspectiveModel;

public class MouseController {
    private PerspectiveModel perspective;
    private int lastX, lastY;
    private double fx, fy;
    private boolean dragging = false;

    public MouseController(PerspectiveModel perspective){
        this.perspective = perspective;
    }

    public void mousePressed(MouseEvent e){
        lastX = e.getX();
        lastY = e.getY();
        fx = 0;
        fy = 0;
        dragging = true;
    }

    public void mouseReleased(MouseEvent e){
        Command trCmd = new TranslateCommand(perspective, fx, fy);
        CommandManager.getInstance().executeCommand(trCmd);
        dragging = false;
    }

    public void mouseDragged(MouseEvent e){
        if (dragging) {
            int x = e.getX();
            int y = e.getY();
            double dx = x - lastX;
            double dy = y - lastY;
            fx += dx;
            fy += dy;
            lastX = x;
            lastY = y;
        }
    }

    public void mouseClicked(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1) {
            double factor = e.isControlDown() ? 0.9 : 1.1;
            Command zoomCmd = new ZoomCommand(perspective, factor);
            CommandManager.getInstance().executeCommand(zoomCmd);
        }
    }
}
