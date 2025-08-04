package Vue;

import javax.swing.*;

import Controller.Command;
import Controller.MouseController;
import Controller.ZoomCommand;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import Model.PerspectiveModel;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


public class PerspectiveView extends JPanel implements Observer{
    private PerspectiveModel model;
    private MouseController mouseController;
    private BufferedImage img = null;

    public PerspectiveView(PerspectiveModel model) {
        this.model = model;
        model.addObserver(this);
        mouseController = new MouseController(model);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { mouseController.mousePressed(e); }
            @Override
            public void mouseReleased(MouseEvent e) { mouseController.mouseReleased(e); }
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseController.mouseClicked(e);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                mouseController.mouseDragged(e);
            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double factor = (e.getWheelRotation() < 0) ? 1.1 : 0.9;
                Command zoomCmd = new ZoomCommand(model, factor);
                Controller.CommandManager.getInstance().executeCommand(zoomCmd);
            }
        });
    }

    @Override
    public void update(){
        if (model.getImagePath() != null && !model.getImagePath().isEmpty()) {
            try {
                img = ImageIO.read(new File(model.getImagePath()));
            } catch(Exception ex) {
                img = null;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (img != null) {
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform saved = g2.getTransform();

            g2.translate(model.getTranslation().x, model.getTranslation().y);
            g2.scale(model.getScale(), model.getScale());
            g2.drawImage(img, 0, 0, this);

            g2.setTransform(saved);

        } else {
            int y = getHeight()/2;
            int x = getWidth()/3;
            g.drawString("Aucune image", x, y);
        }
    }
}
