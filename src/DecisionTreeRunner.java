import javax.swing.*;
import java.awt.*;

public class DecisionTreeRunner {
    DecisionTree tree; // Arbre de décision APHM

    public DecisionTreeRunner() {
        tree = new DecisionTree();
    }

    public void run() {
        // Créer la fenêtre
        JFrame frame = new JFrame("Arbre de décision APHM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajouter un menu déroulant à retirer éventuellement
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Fichiers");
        JMenuItem option1 = new JMenuItem("Ouvrir PDF"); // Ouvrir PDF, implémenter l'évènement associé
        JMenuItem option2 = new JMenuItem("Générer Word"); // Générer Word, implémenter l'évènement associé
        menu.add(option1);
        menu.add(option2);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // TODO : Ajouter le logo OCD au sein de l'interface
        JLabel logo = new JLabel(new ImageIcon("ocd.png"));  // logo.png se trouve dans le bon répertoire
        frame.add(logo, BorderLayout.NORTH);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Taille de la fenêtre en plein écran

        frame.setVisible(true); // Afficher la fenêtre

        DecisionTreeNode currentNode = tree.getRoot();
        // Pour aafficher la boîte de dialogue selon le noeud courant
        while (!currentNode.isLeaf()) {
            String userInput = (String) JOptionPane.showInputDialog(
                    frame,
                    currentNode.getInput(),
                    "Arbre De Décision APHM",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    currentNode.getChildren().keySet().toArray(),
                    currentNode.getChildren().keySet().toArray()[0]
            ); // Pour obtenir la réponse

            currentNode = currentNode.getChild(userInput);

            if (currentNode == null) {
                JOptionPane.showMessageDialog(frame, "Choix invalide. Veuillez essayer à nouveau."); // Si choix invalide, choix à nouveau possible
                currentNode = tree.getRoot(); // Recommencer depuis le début
            }
        }
        JOptionPane.showMessageDialog(frame, "Le noeud final atteint est : " + currentNode.getInput()); // Le noeud est affiché
    }

    public static void main(String[] args) {
        new DecisionTreeRunner().run();
    }

}
