import javax.swing.*;
import java.awt.*;

/**
 * APHM Decision tree
 */
public class DecisionTreeRunner {
    DecisionTree tree; // Arbre de décision APHM

    public DecisionTreeRunner() {
        tree = new DecisionTree();
    }

    public void run() {
        // Créer une entreprise
        JFrame frame = new JFrame("Arbre de décision APHM"); // Arbre de décision de l'APHM
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JOptionPane.showMessageDialog(frame, "Le noeud final atteint est : " + currentNode.getInput()); // Le noeud affiché

        // Afficher la fenêtre en plein écran
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Utilisation d'un JPanel personnalisé pour dessiner l'arbre
        TreePanel treePanel = new TreePanel(tree.getRoot());
        frame.add(treePanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DecisionTreeRunner().run();
    }
}

// Classe pour dessiner l'arbre
class TreePanel extends JPanel {
    private DecisionTreeNode root;

    public TreePanel(DecisionTreeNode root) {
        this.root = root;
        setPreferredSize(new Dimension(800, 600)); // Définir la taille préférée du JPanel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, root, getWidth() / 2, 50, getWidth() / 4); // Dessiner l'arbre à partir du noeud racine
    }

    private void drawTree(Graphics g, DecisionTreeNode node, int x, int y, int spacing) {
        g.setColor(Color.BLACK);
        g.drawString(node.getInput(), x - 10, y + 20); // Dessiner le texte du nœud

        if (!node.isLeaf()) {
            DecisionTreeNode leftChild = node.getChild(node.getChildren().keySet().toArray()[0].toString());
            DecisionTreeNode rightChild = node.getChild(node.getChildren().keySet().toArray()[1].toString());

            int xLeft = x - spacing;
            int xRight = x + spacing;

            int yLeft = y + 50; // left à modifier
            int yRight = y + 50; // right à modifier

            g.drawLine(x, y + 5, xLeft, yLeft - 15);
            g.drawLine(x, y + 5, xRight, yRight - 15);

            drawTree(g, leftChild, xLeft, yLeft, spacing / 2); // Dessiner le sous-arbre gauche
            drawTree(g, rightChild, xRight, yRight, spacing / 2); // Dessiner le sous-arbre droit
        }
    }
}
