import javax.swing.*;

/**
 * Class for launching the application
 */
public class DecisionTreeRunner {
    DecisionTree tree;

    public DecisionTreeRunner() {
        tree = new DecisionTree();
        // Initialisez l'arbre ici, en ajoutant les nœuds appropriés plus tard
    }

    public void run() {
        DecisionTreeNode currentNode = tree.getRoot();

        while (!currentNode.isLeaf()) {
            String userInput = (String) JOptionPane.showInputDialog(
                    null,
                    currentNode.getMessage(),
                    "Decision Tree",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    currentNode.getChildren().keySet().toArray(),
                    currentNode.getChildren().keySet().toArray()[0]
            );

            currentNode = currentNode.getChild(userInput);

            if (currentNode == null) {
                JOptionPane.showMessageDialog(null, "Choix invalide. Veuillez réessayer."); // si choix non pertinent
                currentNode = tree.getRoot();  // restart from the beginning
            }
        }

        JOptionPane.showMessageDialog(null, "Le nœud final atteint est: " + currentNode.getMessage()); // le noeud final est affiché
    }

    public static void main(String[] args) {
        new DecisionTreeRunner().run();
    }

}
