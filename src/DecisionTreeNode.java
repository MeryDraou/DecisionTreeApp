import java.util.HashMap;

/**
 * Nodes Class
 */
public class DecisionTreeNode {
    private String input;
    private HashMap<String, DecisionTreeNode> children; // hashmap à modifier avec linked list
    private String nameNode;

    /**
     * Constructor
     * @param message
     */
    public DecisionTreeNode(String message) {
        this.input = message;
        this.children = new HashMap<>();
        this.nameNode = message;
    }

    public String getNameNode() { return this.nameNode; }

    /**
     * Method to get the result of the selected node
     * @return input
     */
    public String getInput() {
        return this.input;
    }
    // Récupérer noeud choisis par l'utilisateur
    /**
     * Method to get the child
     * @param decision
     * @return
     */
    public DecisionTreeNode getChild(String decision) {
        return this.children.get(decision);
    }

    /**
     * Method to add a child
     * @param decision
     * @param child
     */
    public void addChild(String decision, DecisionTreeNode child) {
        this.children.put(decision, child);
    }

    /**
     * Method to detect if it's the correct node
     * @return
     */
    public boolean isLeaf() { return this.children.isEmpty(); }

    /**
     * Method to get the correct child
     * @return
     */
    public HashMap<String, DecisionTreeNode> getChildren() {
        return this.children;
    }

}
