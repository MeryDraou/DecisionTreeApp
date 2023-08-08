import java.util.HashMap;

/**
 * Decision Tree
 */
public class DecisionTreeNode {
    private String input;
    private HashMap<String, DecisionTreeNode> children; // hashmap à modifier avec linked list
    private String nameNode;

    /**
     * Constructeur
     * @param message
     */
    public DecisionTreeNode(String message) {
        this.input = message;
        this.children = new HashMap<>();
        this.nameNode = message;
    }

    public String getNameNode() {
        return this.nameNode;
    }

    /**
     * Pour obtenir le résultat du noeud choisis
     * @return message retourné
     */
    public String getInput() {
        return this.input;
    }
    // Récupérer noeud choisis par l'utilisateur
    /**
     * Pour obtenir l'enfant choisis
     * @param decision
     * @return
     */
    public DecisionTreeNode getChild(String decision) {
        return this.children.get(decision);
    }

    /**
     * Pour ajouter un enfant au noeud
     * @param decision
     * @param child
     */
    public void addChild(String decision, DecisionTreeNode child) {
        this.children.put(decision, child);
    }

    /**
     * Pour détecter si c'est le noeud correspondant
     * @return
     */
    public boolean isLeaf() {
        return this.children.isEmpty(); // si le noeud est vi
    }

    /**
     * Pour obtenir le noeud associé de l'arbre de décision
     * @return
     */
    public HashMap<String, DecisionTreeNode> getChildren() {
        return this.children;
    }

}
