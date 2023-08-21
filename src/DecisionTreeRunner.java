import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DecisionTreeRunner Class
 */
public class DecisionTreeRunner {
    DecisionTree tree;
    public DecisionTreeRunner() {
        try {
            tree = new DecisionTree();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
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

        // while loop for displaying "Arbre de décision APHM"
        while (!currentNode.isLeaf()) {
            String userInput = (String) JOptionPane.showInputDialog(
                    frame,
                    currentNode.getInput(),
                    "Arbre de décision APHM",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    currentNode.getChildren().keySet().toArray(),
                    currentNode.getChildren().keySet().toArray()[0]
            );
            /**
             * Debut of modifications
             */
            // debut of test
            // Mise à jour du tableau avec les données associées au VLAN choisi
            // updateTableData(userInput);
            // Rafraîchir l'interface graphique pour afficher les nouvelles données dans le tableau
            // refreshGUI();

            // Méthode pour mettre à jour les données du tableau en fonction du choix de VLAN
            // private void updateTableData(String vlanChoice) {
                // Obtenez les données associées au VLAN choisi à partir de votre source de données
                // HashMap<String, Vlan> vlanData = tree.readVlan();
                // Vlan selectedVlan = vlanData.get(vlanChoice);

                // Si le VLAN choisi est trouvé dans les données
                // if (selectedVlan != null) {
                    // Mettez à jour le modèle du tableau avec les champs et valeurs du VLAN choisi
                    // DefaultTableModel tableModel = (DefaultTableModel) questionTable.getModel();
                    // tableModel.setRowCount(0); // Efface les anciennes données

                    // Ajoutez chaque champ et sa valeur associée dans le tableau
                    // tableModel.addRow(new Object[]{"IP Machine", selectedVlan.ipMachine});
                    // tableModel.addRow(new Object[]{"IP Réseau", selectedVlan.ipReseau});
                    // Ajout d'éventuels autres champs et valeurs associées
                // }
            //}

            // Méthode pour rafraîchir l'interface graphique et afficher les nouvelles données dans le tableau
           // private void refreshGUI() {
                //questionTable.repaint(); // Redessine le tableau avec les nouvelles données
            // }
            /**
             * TODO : end of the modifications
             */

            currentNode = currentNode.getChild(userInput);
            // table
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
        try {
            DecisionTree.Vlan currentVlan = DecisionTree.readVlan().get(currentNode.getInput());
            questionList.add(currentVlan.getIpMachine() + " ip machine");
            questionList.add(currentVlan.getIpReseau() + " ip reseau");
            questionList.add(currentVlan.getIpPasserelle() + " passerelle");
            questionList.add(currentVlan.getIpMasque() + " masque");
            questionList.add(currentVlan.getDns1() + " DNS1");
            questionList.add(currentVlan.getDns2() + " DNS2");
            questionList.add(currentVlan.getNtp() + " NTP");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Object[][] rowData = new Object[questionList.size()][1];
        for (int i = 0; i < questionList.size(); i++) {
            rowData[i][0] = questionList.get(i);
        }
        Object[] columnNames = {"VOS CHOIX SELECTIONNÉS"};
        System.out.println("PATH NODES");
        tree.getPathNodes().stream().forEach(System.out::println);
        JTable questionTable = new JTable(new DefaultTableModel(rowData, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        questionTable.setBackground(Color.LIGHT_GRAY);
        questionTable.setForeground(new Color(255, 140, 0));
        questionTable.setRowHeight(40);
        questionTable.setFont(new Font("Arial", Font.PLAIN, 18));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        centerRenderer.setForeground(new Color(0, 0, 0)); // color black for the tables writing
        questionTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(questionTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        DecisionTreePanel treePanel = new DecisionTreePanel(tree);
        treePanel.setBackground(Color.LIGHT_GRAY); // color LIGHT_GRAY of the background of the decision tree

        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
        contentPanel.add(treePanel);
        contentPanel.add(scrollPane);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        frame.add(mainPanel);

        JLabel titleLabel = new JLabel("Arbre de décision APHM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 140, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        /**
         * TODO : add logo
         */
        // Load the original logo image
        ImageIcon originalLogoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        // Define the desired width and height for the scaled logo
        int desiredWidth = 150;
        int desiredHeight = 80;
        // Scale the original logo image to the desire size
        Image scaledImage = originalLogoIcon.getImage().getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        // ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        // JLabel logoLabel = new JLabel(logoIcon);
        titleLabel.setText("ARBRE DE DÉCISION APHM"); // title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 140, 0));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.setBackground(Color.BLACK);
        topBarPanel.add(logoLabel, BorderLayout.WEST);
        topBarPanel.add(titleLabel, BorderLayout.CENTER);

        frame.add(topBarPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DecisionTreeRunner().run());
    }

}

class DecisionTreePanel extends JPanel {
    private static final int NODE_SIZE = 40;
    private static final int LEVEL_HEIGHT = 100;
    private static final int TEXT_RECTANGLE_WIDTH = 160;
    private static final int TEXT_RECTANGLE_HEIGHT = 60;
    private DecisionTree tree;
    public DecisionTreePanel(DecisionTree tree) {
        this.tree = tree;
    }
    /**
     * Draw the nodes et decisions of the decision tree
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(tree.getRoot(), getWidth() / 2, 50, 200, 0, g);
    }

    /**
     * Draw the decision tree
     * @param node
     * @param x
     * @param y
     * @param xOffset
     * @param level
     * @param g
     */
    private void drawTree(DecisionTreeNode node, int x, int y, int xOffset, int level, Graphics g) {
        Color darkGreen = new Color(0, 100, 0);
        Color darkRed = new Color(139, 0, 0);
        // to color the path chosen by the user
        if (node.getNameNode().equals("Application ou équipement")) {
            g.setColor(darkRed);
        } else if (tree.getPathNodes().stream().anyMatch(decision -> decision.equals(node.getNameNode()))) {
            g.setColor(darkRed);
        } else {
            g.setColor(darkGreen);
        }
        int textX = x - TEXT_RECTANGLE_WIDTH / 2;
        int textY = y - TEXT_RECTANGLE_HEIGHT / 2;

        g.fillRect(textX, textY, TEXT_RECTANGLE_WIDTH, TEXT_RECTANGLE_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString(node.getInput(), textX + 10, textY + TEXT_RECTANGLE_HEIGHT / 2 + 5);

        if (!node.isLeaf()) {
            int childCount = node.getChildren().size();
            int startX = x - xOffset * (childCount - 1) / 2;
            int startY = y + NODE_SIZE / 2 + LEVEL_HEIGHT;

            for (DecisionTreeNode child : node.getChildren().values()) {
                g.setColor(Color.BLACK);
                g.drawLine(x, textY + TEXT_RECTANGLE_HEIGHT, startX, startY);
                drawTree(child, startX, startY, xOffset / 2, level + 1, g);
                startX += xOffset;
            }
        }
    }

}
