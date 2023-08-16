import java.util.HashMap;
/**
 * DecisionTreeNode Class
 */
public class DecisionTreeNode {
    private String input;
    private HashMap<String, DecisionTreeNode> children;
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
     * Method to get the result of the input
     * @return input
     */
    public String getInput() { return this.input; }

    /**
     * Method to get the child node
     * @param decision
     * @return
     */
    public DecisionTreeNode getChild(String decision) {
        return this.children.get(decision);
    }

    /**
     * Method to add a child node
     * @param decision
     * @param child
     */
    public void addChild(String decision, DecisionTreeNode child) {
        this.children.put(decision, child);
    }

    /**
     * Method to detect if it is the correct leaf
     * @return
     */
    public boolean isLeaf() { return this.children.isEmpty(); }

    /**
     * Method to get the children
     * @return
     */
    public HashMap<String, DecisionTreeNode> getChildren() {
        return this.children;
    }

}
