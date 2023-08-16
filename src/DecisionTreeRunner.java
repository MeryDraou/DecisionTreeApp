import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DecisionTreeRunner {
    DecisionTree tree;
    public DecisionTreeRunner() {
        tree = new DecisionTree();
    }

    public void run() {

        // Personnaliser l'apparence de la barre de titre
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(Color.BLACK);

        JPanel mainPanel = new JPanel(new BorderLayout());
        DecisionTreeNode currentNode = tree.getRoot();
        List<String> questionList = new ArrayList<>();

        while (!currentNode.isLeaf()) {
            String userInput = (String) JOptionPane.showInputDialog(
                    frame,
                    currentNode.getInput(),
                    "Arbre De Décision APHM",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    currentNode.getChildren().keySet().toArray(),
                    currentNode.getChildren().keySet().toArray()[0]
            );

            currentNode = currentNode.getChild(userInput);

            if (currentNode == null) {
                JOptionPane.showMessageDialog(frame, "Choix invalide. Veuillez essayer à nouveau.");
                currentNode = tree.getRoot();
            }
            else {
                System.out.println("Choix valide. Veuillez continuer.");
                tree.addPathNodes(userInput);
            }

            questionList.add(currentNode.getInput());
        }

        Object[][] rowData = new Object[questionList.size()][1];
        for (int i = 0; i < questionList.size(); i++) {
            rowData[i][0] = questionList.get(i);
        }
        Object[] columnNames = {"Vos choix sélectionnés"};

        // ajout
        System.out.println("PATH NODES");
        tree.getPathNodes().stream().forEach(System.out::println);
        JTable questionTable = new JTable(new DefaultTableModel(rowData, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        questionTable.setBackground(Color.BLACK);
        questionTable.setForeground(new Color(255, 140, 0));
        questionTable.setRowHeight(40);
        questionTable.setFont(new Font("Arial", Font.PLAIN, 18));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setForeground(new Color(255, 140, 0));
        questionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(questionTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DecisionTreePanel treePanel = new DecisionTreePanel(tree);
        treePanel.setBackground(Color.LIGHT_GRAY); // ajout test

        JPanel contentPanel = new JPanel(new GridLayout(1, 2)); // Créer une disposition en deux colonnes
        contentPanel.add(treePanel); // ajout du treePanel
        contentPanel.add(scrollPane); // ajout du scrollPane

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.add(mainPanel);

        // Personnaliser le texte de la barre de titre
        JLabel titleLabel = new JLabel("Arbre de décision APHM"); // arbre de décision aphm
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 140, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);
        // ajout du logo
        // Chargement de l'image du logo
        ImageIcon logoIcon = new ImageIcon("logo.png"); // Assurez-vous que "logo.png" est dans le même répertoire que le fichier Java

// Création du label pour afficher le logo
        JLabel logoLabel = new JLabel(logoIcon);

// Modification de la propriété du label existant pour le titre
        titleLabel.setText("Arbre de décision APHM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 140, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

// Création du panel pour la barre supérieure
        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.setBackground(Color.BLACK);
        topBarPanel.add(logoLabel, BorderLayout.WEST); // Ajout du logo à gauche
        topBarPanel.add(titleLabel, BorderLayout.CENTER);

        frame.add(topBarPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DecisionTreeRunner().run());
    }

}

class DecisionTreePanel extends JPanel {
    private static final int NODE_SIZE = 40; // Taille des nodes
    private static final int LEVEL_HEIGHT = 100; // Espacement vertical entre les niveaux
    private static final int TEXT_RECTANGLE_WIDTH = 160; // Largeur du rectangle de fond
    private static final int TEXT_RECTANGLE_HEIGHT = 60; // Hauteur du rectangle de fond
    private DecisionTree tree;

    public DecisionTreePanel(DecisionTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner les nœuds et les liens de l'arbre de décision
        drawTree(tree.getRoot(), getWidth() / 2, 50, 200, 0, g);
    }
    // Dessiner l'arbre de décision
    private void drawTree(DecisionTreeNode node, int x, int y, int xOffset, int level, Graphics g) {

        Color darkGreen = new Color(0, 100, 0); // Vert foncé
        Color darkRed = new Color(139, 0, 0); // Pour un rouge vif : new Color(255, 0 , 0)

        // Dessinez le nœud à la position (x, y)
        if (node.getNameNode().equals("Application ou équipement")) {
            g.setColor(darkRed); // Utiliser le rouge foncé pour les nœuds "Application" ou "Équipement"
        } else if (tree.getPathNodes().stream().anyMatch(decision -> decision.equals(node.getNameNode()))) {
            g.setColor(darkRed); // Utiliser le rouge standard pour les autres noeuds marqués
        } else {
            g.setColor(darkGreen); // Utiliser le vert foncé pour les noeuds suivants
        }

        int textX = x - TEXT_RECTANGLE_WIDTH / 2;
        int textY = y - TEXT_RECTANGLE_HEIGHT / 2;

        g.fillRect(textX, textY, TEXT_RECTANGLE_WIDTH, TEXT_RECTANGLE_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString(node.getInput(), textX + 10, textY + TEXT_RECTANGLE_HEIGHT / 2 + 5);

        if (!node.isLeaf()) {
            int childCount = node.getChildren().size();
            int startX = x - xOffset * (childCount - 1) / 2;
            int startY = y + NODE_SIZE / 2 + LEVEL_HEIGHT; // la position Y pour le lien et l'espacement à ajuster

            for (DecisionTreeNode child : node.getChildren().values()) {
                // Dessinez le lien entre le noeud actuel et l'enfant
                g.setColor(Color.BLACK);
                g.drawLine(x, textY + TEXT_RECTANGLE_HEIGHT, startX, startY); // ligne partant du bas du rectangle

                drawTree(child, startX, startY, xOffset / 2, level + 1, g);
                startX += xOffset;
            }
        }
    }
}
