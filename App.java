import javax.swing.SwingUtilities;

import Vue.MainView;

public class App {
    public static void main (String[] args){
        SwingUtilities.invokeLater(() -> {
            MainView window = new MainView();
            window.initComponents();
            window.setVisible(true);
        });
    }
}
