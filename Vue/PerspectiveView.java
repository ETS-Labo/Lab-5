package Vue;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

import Controller.MouseController;
import Model.PerspectiveModel;
import java.awt.event.MouseAdapter;


public class PerspectiveView extends JPanel implements Observer{
    private PerspectiveModel image;
     private MouseController mouseController;

     public PerspectiveView(PerspectiveModel model) {
        mouseController = new MouseController(model);

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseController.mousePressed(e);;
                }
            }); 
    }
    
    
    public void update(){
        repaint();
    }
}
