import javax.swing.*;
import java.awt.*;

public class DecisionTreeRunner {
    DecisionTree tree; // Arbre de décision APHM

    public DecisionTreeRunner() {
        tree = new DecisionTree();
    }

    public void run() {
        // Créer une nouvelle fenêtre
        JFrame frame = new JFrame("Arbre De Décision APHM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajouter un menu déroulant à rétirer éventuellement
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Fichiers");
        JMenuItem option1 = new JMenuItem("Ouvrir PDF");
        JMenuItem option2 = new JMenuItem("Générer Word");
        menu.add(option1);
        menu.add(option2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // TODO : Ajouter le logo OCD
        JLabel logo = new JLabel(new ImageIcon("ocd.png"));  // logo.png se trouve dans le bon répertoire
        frame.add(logo, BorderLayout.NORTH);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Taille de la fenêtre : Plein écran


        frame.setVisible(true); // Afficher la fenêtre

        DecisionTreeNode currentNode = tree.getRoot();
        // Pour afficher la boîte de dialogue selon le noeud courant
        while (!currentNode.isLeaf()) {
            String userInput = (String) JOptionPane.showInputDialog(
                    frame,
                    currentNode.getMessage(),
                    "Arbre De Décision APHM",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    currentNode.getChildren().keySet().toArray(),
                    currentNode.getChildren().keySet().toArray()[0]
            ); // Pour obtenir la réponse

            currentNode = currentNode.getChild(userInput);

            if (currentNode == null) {
                JOptionPane.showMessageDialog(frame, "Choix invalide. Veuillez réessayer."); // Si choix invalide, choix à nouveau posssible
                currentNode = tree.getRoot();  // restart from the beginning
            }
        }

        JOptionPane.showMessageDialog(frame, "Le nœud final atteint est: " + currentNode.getMessage()); // Le noeud final est affiché
    }

    public static void main(String[] args) {
        new DecisionTreeRunner().run();
    }
}
