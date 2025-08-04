package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import Model.ImageModel;

public class ThumbnailView extends JPanel implements Observer {
    private ImageModel model;
    private BufferedImage img = null;

    public ThumbnailView(ImageModel model) {
        this.model = model;
        model.addObserver(this);
        update(); 
    }

    @Override
    public void update() {
        if (model.getFilePath() != null && !model.getFilePath().isEmpty()) {
            try {
                img = ImageIO.read(new File(model.getFilePath()));
            } catch (Exception ex) {
                img = null;
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            int w = getWidth();
            int h = getHeight();
            double ratio = Math.min((double)w/img.getWidth(), (double)h/img.getHeight());
            int iw = (int)(img.getWidth()*ratio);
            int ih = (int)(img.getHeight()*ratio);
            g.drawImage(img, (w-iw)/2, (h-ih)/2, iw, ih, this);
        } else {
            int y = getHeight()/2;
            int x = getWidth()/4;
            g.drawString("Miniature : aucune image", x, y);
        }
    }
}
