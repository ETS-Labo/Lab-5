package Controller;

import java.awt.event.MouseEvent;

import Model.PerspectiveModel;

public class MouseController {
    private PerspectiveModel perspective;
    private int lastX, lastY;
    private boolean dragging = false;

    public MouseController(PerspectiveModel perspective){
        this.perspective = perspective;
    }

    public void mousePressed(MouseEvent e){
        lastX = e.getX();
        lastY = e.getY();
        dragging = true;
    }

    public void mouseReleased(MouseEvent e){
        dragging = false;
    }

    public void mouseDragged(MouseEvent e){
        if (dragging) {
            int x = e.getX();
            int y = e.getY();
            double dx = x - lastX;
            double dy = y - lastY;
            Command trCmd = new TranslateCommand(perspective, dx, dy);
            CommandManager.getInstance().executeCommand(trCmd);
            lastX = x;
            lastY = y;
        }
    }

    public void mouseClicked(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1) {
            // Clic gauche = zoom avant ; clic droit = zoom arrière
            double factor = e.isControlDown() ? 0.9 : 1.1;
            Command zoomCmd = new ZoomCommand(perspective, factor);
            CommandManager.getInstance().executeCommand(zoomCmd);
        }
    }
}
