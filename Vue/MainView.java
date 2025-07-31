package Vue;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import Model.ImageModel;
import Model.PerspectiveModel;




public class MainView extends JFrame{
    private PerspectiveView perspectiveView1;
    private PerspectiveView perspectiveView2;
    private ThumbnailView thumbnailView; 
    
    public void initComponents(){
        thumbnailView = new ThumbnailView(new ImageModel());
        perspectiveView1 = new PerspectiveView(new PerspectiveModel());
        perspectiveView2 = new PerspectiveView(new PerspectiveModel());
        thumbnailView.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        perspectiveView1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        perspectiveView2.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        setLayout(new BorderLayout());

                // Création de la barre de menus
        JMenuBar menuBar = new JMenuBar();

        // Création des menus principaux
        JMenu menuFichier = new JMenu("Fichier");
        JMenu menuEdition = new JMenu("Édition");

        // Création d'un sous-menu pour "Fichier"
        JMenu sousMenuImport = new JMenu("Importer");
        JMenuItem itemImportFromFile = new JMenuItem("Depuis un fichier");
        JMenuItem itemImportFromClipboard = new JMenuItem("Depuis le presse-papiers");
        sousMenuImport.add(itemImportFromFile);
        sousMenuImport.add(itemImportFromClipboard);

        // Ajouter le sous-menu à "Fichier"
        menuFichier.add(sousMenuImport);

        // Ajouter un autre item simple dans Fichier
        menuFichier.add(new JMenuItem("Exporter"));

        // Création d'un sous-menu pour "Édition"
        JMenu sousMenuPreferences = new JMenu("Préférences");
        JMenuItem itemTheme = new JMenuItem("Thème");
        JMenuItem itemLangue = new JMenuItem("Langue");
        sousMenuPreferences.add(itemTheme);
        sousMenuPreferences.add(itemLangue);

        // Ajouter le sous-menu à "Édition"
        menuEdition.add(sousMenuPreferences);

        // Ajouter un autre item simple dans Édition
        menuEdition.add(new JMenuItem("Annuler"));
        menuEdition.add(new JMenuItem("Rétablir"));

        // Ajout des menus principaux à la barre de menu
        menuBar.add(menuFichier);
        menuBar.add(menuEdition);

        // Affecter la barre de menus à la fenêtre
        setJMenuBar(menuBar);


        JPanel viewsPanel = new JPanel(new GridLayout(1, 3));
        viewsPanel.add(perspectiveView1);
        viewsPanel.add(perspectiveView2);
        viewsPanel.add(thumbnailView);

        add(viewsPanel, BorderLayout.CENTER);

        setTitle("Labo5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        }
}
