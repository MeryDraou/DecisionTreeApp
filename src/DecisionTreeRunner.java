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
        Object[] columnNames = {"Questions"};

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
        treePanel.setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel(new GridLayout(1, 2)); // Créer une disposition en deux colonnes
        contentPanel.add(treePanel);
        contentPanel.add(scrollPane);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.add(mainPanel);

        // Personnaliser le texte de la barre de titre
        JLabel titleLabel = new JLabel("Arbre de décision APHM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 140, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DecisionTreeRunner().run());
    }
}

class DecisionTreePanel extends JPanel {
    private static final int NODE_SIZE = 40; // Taille des nœuds
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

        // Dessiner les nœuds et les liens de l'arbre de décision ici
        drawTree(tree.getRoot(), getWidth() / 2, 50, 200, 0, g);
    }

    private void drawTree(DecisionTreeNode node, int x, int y, int xOffset, int level, Graphics g) {
        // Dessinez le nœud à la position (x, y)
        if (tree.getPathNodes().stream().anyMatch(decision -> decision.equals(node.getNameNode())))
            g.setColor(Color.RED);
        else
            g.setColor(Color.GREEN);

        int textX = x - TEXT_RECTANGLE_WIDTH / 2;
        int textY = y - TEXT_RECTANGLE_HEIGHT / 2;

        g.fillRect(textX, textY, TEXT_RECTANGLE_WIDTH, TEXT_RECTANGLE_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString(node.getInput(), textX + 10, textY + TEXT_RECTANGLE_HEIGHT / 2 + 5);

        if (!node.isLeaf()) {
            int childCount = node.getChildren().size();
            int startX = x - xOffset * (childCount - 1) / 2;
            int startY = y + NODE_SIZE / 2 + LEVEL_HEIGHT; // Ajustez la position Y pour le lien et l'espacement

            for (DecisionTreeNode child : node.getChildren().values()) {
                // Dessinez le lien entre le noeud actuel et l'enfant
                g.setColor(Color.BLACK);
                g.drawLine(x, textY + TEXT_RECTANGLE_HEIGHT, startX, startY); // Ligne partant du bas du rectangle

                drawTree(child, startX, startY, xOffset / 2, level + 1, g);
                startX += xOffset;
            }
        }
    }
}
