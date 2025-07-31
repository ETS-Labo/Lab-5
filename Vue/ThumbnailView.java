package Vue;
import javax.swing.JPanel;

import Model.ImageModel;

public class ThumbnailView extends JPanel{
    private ImageModel image;
    
    public ThumbnailView(ImageModel image){
        this.image = image;
    }

    public void update(){
        this.repaint();
    }
}
