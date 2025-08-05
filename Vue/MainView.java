/*
 * -------------------------------------------------------------------------
 * Auteur           : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Fenêtre principale de l’application.
 *                    Gère la création de l’interface utilisateur (barre de menus,
 *                    vues perspectives et miniature), ainsi que les actions
 *                    de chargement/sauvegarde d’images et de perspectives.
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Instancie ImageModel et deux PerspectiveModel pour les deux vues.
 *   - Configure les menus "Fichier" et "Édition" avec leurs actions
 *     (ouvrir image, charger/sauvegarder perspectives, annuler commande).
 *   - Dispose les composants via BorderLayout et GridLayout.
 *   - Dépendances : Swing (JFrame, JMenuBar, JFileChooser, etc.),
 *     Model.ImageModel, Model.PerspectiveModel, Controller.CommandManager.
 * -------------------------------------------------------------------------
 */
package Vue;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import Model.ImageModel;
import Model.PerspectiveModel;
import Controller.CommandManager;

/**
 * Vue principale de l’application, responsable du montage de
 * l’interface et de la connexion des événements aux modèles et
 * au gestionnaire de commandes.
 */
public class MainView extends JFrame {
    // Vues de perspective et miniature
    private PerspectiveView perspectiveView1;
    private PerspectiveView perspectiveView2;
    private ThumbnailView thumbnailView;

    // Modèles de perspective associés aux vues
    private PerspectiveModel model1;
    private PerspectiveModel model2;

    /**
     * Construit et configure tous les composants graphiques,
     * les menus et les actions de l’application.
     * Doit être appelé après la création de l’instance MainView.
     */
    public void initComponents() {
        // Récupère le modèle d’image unique et crée deux perspectives
        ImageModel imgModel = ImageModel.getInstance();
        model1 = new PerspectiveModel();
        model2 = new PerspectiveModel();
        imgModel.addPerspective(model1);
        imgModel.addPerspective(model2);

        // Crée les vues et leur applique une bordure pour la démarcation
        perspectiveView1 = new PerspectiveView(model1);
        perspectiveView2 = new PerspectiveView(model2);
        thumbnailView = new ThumbnailView(imgModel);
        thumbnailView.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        perspectiveView1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        perspectiveView2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        // Définit le layout principal
        setLayout(new BorderLayout());

        // ========== Menu Fichier ==========
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");

        // Ouvrir une image
        JMenuItem loadItem = new JMenuItem("Ouvrir une image...");
        loadItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Fichier image", "png", "jpg", "jpeg", "wbmp", "gif"
            ));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String path = chooser.getSelectedFile().getAbsolutePath();
                imgModel.loadImage(path);
                model1.setImagePath(path);
                model2.setImagePath(path);
            }
        });
        menuFichier.add(loadItem);

        // Charger une perspective
        JMenuItem loadPersp = new JMenuItem("Charger une perspective");
        loadPersp.addActionListener(e -> {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Fichiers de perspective .ser", "ser"
                ));
                if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                        double[][] data = (double[][]) in.readObject();
                        // Applique les données aux deux perspectives
                        model1.setTranslation(data[0][0], data[0][1]);
                        model1.setScale(data[0][2]);
                        model2.setTranslation(data[1][0], data[1][1]);
                        model2.setScale(data[1][2]);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Erreur lors du chargement : " + ex.getMessage()
                );
            }
        });
        menuFichier.add(loadPersp);

        // Sauvegarder les deux perspectives
        JMenuItem savePersp = new JMenuItem("Sauvegarder la perspective");
        savePersp.addActionListener(e -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Enregistrer les perspectives");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                        "Fichier perspective .ser", "ser"
                ));
                if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!path.endsWith(".ser")) path += ".ser";

                    // Prépare les données à sérialiser
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

                    // Sérialisation dans un fichier
                    try (FileOutputStream fos = new FileOutputStream(path);
                         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        oos.writeObject(bothPerspectives);
                    }
                    JOptionPane.showMessageDialog(this,
                            "Données des deux perspectives sauvegardées dans :\n" + path
                    );
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });
        menuFichier.add(savePersp);

        // ========== Menu Édition ==========
        JMenu menuEdition = new JMenu("Édition");
        JMenuItem menuUndo = new JMenuItem("Annuler");
        menuUndo.addActionListener(e ->
                CommandManager.getInstance().undoLast()
        );
        menuEdition.add(menuUndo);

        // Assemble la barre de menus
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);
        setJMenuBar(menuBar);

        // Panneau central avec grille 1x3 : miniature + 2 perspectives
        JPanel viewsPanel = new JPanel(new GridLayout(1, 3));
        viewsPanel.add(thumbnailView);
        viewsPanel.add(perspectiveView1);
        viewsPanel.add(perspectiveView2);
        add(viewsPanel, BorderLayout.CENTER);

        // Configuration finale de la fenêtre
        setTitle("Labo5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }
}
