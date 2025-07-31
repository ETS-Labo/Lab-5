package Vue;

import javax.swing.*;

import Controller.CommandManager;
import Controller.SavePerspectiveCommand;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

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

        JMenuItem savePersp = new JMenuItem("Sauvegarder la perspective");
        savePersp.addActionListener(e -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Enregistrer les perspectives");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Fichiers de perspective (*.ser)", "ser"));
        
                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File fileToSave = fileChooser.getSelectedFile();
                    String path = fileToSave.getAbsolutePath();
                    if (!path.endsWith(".ser")) path += ".ser";
        
                    // Données des deux modèles : [ [x1, y1, zoom1], [x2, y2, zoom2] ]
                    double[] perspective1 = {
                        model1.getTranslation().x,
                        model1.getTranslation().y,
                        model1.getScale()
                    };
                    double[] perspective2 = {
                        model2.getTranslation().x,
                        model2.getTranslation().y,
                        model2.getScale()
                    };
                    double[][] bothPerspectives = { perspective1, perspective2 };
        
                    FileOutputStream fileOut = new FileOutputStream(path);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(bothPerspectives);
                    out.close();
                    fileOut.close();
        
                    JOptionPane.showMessageDialog(this, "Données des deux perspectives sauvegardées dans :\n" + path);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });
        

        JMenuItem loadPersp = new JMenuItem("Charger une perspective");
        loadPersp.addActionListener(e -> {
            try {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
                    double[][] data = (double[][]) in.readObject();
                    in.close();
                    // Perspective 1
                    model1.setTranslation(data[0][0], data[0][1]);
                    model1.setScale(data[0][2]);
                    // Perspective 2
                    model2.setTranslation(data[1][0], data[1][1]);
                    model2.setScale(data[1][2]);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors du chargement : " + ex.getMessage());
            }            
        });

        menuFichier.add(loadItem);
        menuFichier.add(loadPersp);
        menuFichier.add(savePersp);
        


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
