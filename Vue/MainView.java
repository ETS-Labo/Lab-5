package Vue;

import javax.swing.*;

import Controller.CommandManager;

import java.awt.*;
import java.awt.event.*;

import Model.ImageModel;
import Model.PerspectiveModel;

public class MainView extends JFrame {
    private PerspectiveView perspectiveView1;
    private PerspectiveView perspectiveView2;
    private ThumbnailView thumbnailView; 
    private PerspectiveModel model1;
    private PerspectiveModel model2;

    public void initComponents() {
        // Modèles : Singleton pour la miniature, deux modifiables
        ImageModel imgModel = ImageModel.getInstance();
        model1 = new PerspectiveModel();
        model2 = new PerspectiveModel();
        imgModel.addPerspective(model1);
        imgModel.addPerspective(model2);

        perspectiveView1 = new PerspectiveView(model1);
        perspectiveView2 = new PerspectiveView(model2);
        thumbnailView = new ThumbnailView(imgModel);

        thumbnailView.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        perspectiveView1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        perspectiveView2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        setLayout(new BorderLayout());

        // BARRE DE MENU complète
        JMenuBar menuBar = new JMenuBar();

        // === Menu Fichier ===
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem loadItem = new JMenuItem("Ouvrir une image...");
        loadItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                imgModel.loadImage(path);
                model1.setImagePath(path);
                model2.setImagePath(path);
            }
        });
        menuFichier.add(loadItem);

        // === Menu Édition ===
        JMenu menuEdition = new JMenu("Édition");
        JMenuItem menuUndo = new JMenuItem("Annuler");
        menuUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        menuUndo.addActionListener(e -> CommandManager.getInstance().undoLast());
        menuEdition.add(menuUndo);

        JMenuItem menuRedo = new JMenuItem("Rétablir");
        menuRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));
        menuRedo.addActionListener(e -> CommandManager.getInstance().undoLast());
        menuEdition.add(menuRedo);

        // Ajout des menus
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);
        setJMenuBar(menuBar);

        // Panneau central
        JPanel viewsPanel = new JPanel(new GridLayout(1, 3));
        viewsPanel.add(thumbnailView);
        viewsPanel.add(perspectiveView1);
        viewsPanel.add(perspectiveView2);
        add(viewsPanel, BorderLayout.CENTER);

        setTitle("Labo5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }
}
