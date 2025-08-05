
/*
 * -------------------------------------------------------------------------
 * Auteurs          : Flora Auvergnas, Nassim Bouchemella, Elyesse Kourdourli
 * Description      : Point d’entrée de l’application. Crée et affiche
 *                    la fenêtre principale (MainView).
 * Cours/Groupe     : Laboratoire 5 LOG121 / Groupe 11
 * Date             : 05-08-2025
 * Détails          :
 *   - Instancie MainView.
 *   - Appelle initComponents() pour configurer l’interface.
 *   - Rend la fenêtre visible.
 * -------------------------------------------------------------------------
 */

import Vue.MainView;

/**
 * Classe de démarrage de l’application.
 * Contient la méthode main qui initialise et affiche la vue principale.
 */
public class App {

    /**
     * Point d’entrée de l’application.
     *
     * @param args Arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        // Création de la fenêtre principale
        MainView window = new MainView();
        // Initialisation des composants graphiques
        window.initComponents();
        // Affichage de la fenêtre
        window.setVisible(true);
    }
}
