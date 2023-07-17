import java.util.HashMap;

/**
 * Noeuds de l'arbre de décision
 */
public class DecisionTreeNode {
    private String message;
    private HashMap<String, DecisionTreeNode> children; // hashmap utilisée à modifier potentiellement avec linked list

    /**
     * Constructeur
     * @param message
     */
    public DecisionTreeNode(String message) {
        this.message = message;
        this.children = new HashMap<>();
    }

    /**
     * Pour obtenir le résultat du noeud choisis
     * @return
     */
    public String getMessage() {
        return this.message;
    }

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
        return this.children.isEmpty();
    }

    /**
     * Pour obtenir le noeud associé de l'arbre de décision
     * @return
     */
    public HashMap<String, DecisionTreeNode> getChildren() {
        return this.children;
    }

}
