package Vue;
import javax.swing.JFrame;

public class App extends JFrame{
    public static void main (String[] args){
        
       JFrame window = new JFrame();
       ThumbnailView vue1 = new ThumbnailView();
       PerspectiveView vue2 = new PerspectiveView();
       PerspectiveView vue3 = new PerspectiveView();
        window.add(vue1);
        window.add(vue2);
        window.add(vue3);

    
    }
}
