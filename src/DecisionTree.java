import java.util.ArrayList;
import java.util.Scanner;

/**
 * Decision tree class
 */
public class DecisionTree {
    DecisionTreeNode root;
    ArrayList<String> pathNodes = new ArrayList<>(); // list of decisions

    public void addPathNodes(String decision) {
        pathNodes.add(decision);
    }
    public ArrayList<String> getPathNodes() {
        return pathNodes;
    }

    /**
     * Constructor
     */
    public DecisionTree() {
        // Hauteur 1
        DecisionTreeNode nodeApplicationEquipment = new DecisionTreeNode("Application ou équipement");
        DecisionTreeNode nodeLocation = new DecisionTreeNode("Localisation géographique");

        // Hauteur 2
        DecisionTreeNode nodeServer = new DecisionTreeNode("Serveur");
        DecisionTreeNode nodeTerminal = new DecisionTreeNode("Terminal client");
        DecisionTreeNode nodePrinter = new DecisionTreeNode("I : Imprimante");
        DecisionTreeNode nodeScanner = new DecisionTreeNode("M : Scanneur médical");

        // Hauteur 3
        DecisionTreeNode nodeExposition = new DecisionTreeNode("Exposition"); // Exposition
        DecisionTreeNode nodeReseau = new DecisionTreeNode("Réseau"); // Réseau
        DecisionTreeNode nodeZIImprim = new DecisionTreeNode("Z_I_IMPRIM"); // Z_I_IMPRIM
        DecisionTreeNode nodeZMScanMedic = new DecisionTreeNode("Z_M_SCAN_MEDIC"); // Z_M_SCAN_MEDIC

        // Hauteur 4
        DecisionTreeNode nodeE = new DecisionTreeNode("E : Interne ou autre"); // E : Interne, GHT, Publique Large, Public Restreint
        DecisionTreeNode nodeR = new DecisionTreeNode("R : WIFI ou FILAIRE"); // R : WIFI ou FILAIRE

        // Hauteur 5
        DecisionTreeNode nodeEnvironnement1 = new DecisionTreeNode("Environnement1"); // Environnement, left leaf
        DecisionTreeNode nodeEnvironnement2 = new DecisionTreeNode("Environnement2"); // Environnement, right leaf

        // Hauteur 6
        root = nodeApplicationEquipment;
        root.addChild("Serveur", nodeServer); // Serveur
        root.addChild("Terminal", nodeTerminal); // Terminal
        root.addChild("Imprimante", nodePrinter); // Imprimante
        root.addChild("Scanneur", nodeScanner); // Scanneur

        nodeServer.addChild("Exposition", nodeExposition); // Exposition
        nodeTerminal.addChild("Réseau", nodeReseau); // Réseau
        nodePrinter.addChild("Z_I_IMPRIM", nodeZIImprim); // Z_I_IMPRIM
        nodeScanner.addChild("Z_M_SCAN_MEDIC", nodeZMScanMedic); // Z_M_SCAN_MEDIC

        nodeExposition.addChild("E : Interne ou autre", nodeE); // E : "noeud Exposition"
        nodeReseau.addChild("Réseau", nodeR); // Noeud Réseau

        // Nodes
        nodeE.addChild("Environnement1", nodeEnvironnement1); // Environnement1
        nodeR.addChild("Environnement2", nodeEnvironnement2); // Environnement2

        DecisionTreeNode nodeIntegration = new DecisionTreeNode("Integration"); // Integration
        DecisionTreeNode nodeDeveloppement = new DecisionTreeNode("Developpement"); // Developpement
        DecisionTreeNode nodeProduction = new DecisionTreeNode("Production"); // Production
        DecisionTreeNode nodeRecette = new DecisionTreeNode("Recette"); // Recette
        DecisionTreeNode nodeFormation = new DecisionTreeNode("Formation"); // Formation
        DecisionTreeNode nodeTest = new DecisionTreeNode("Test"); // Test

        nodeEnvironnement2.addChild("Integration", nodeIntegration); // Noeud Integration
        nodeDeveloppement.addChild("Developpement", nodeDeveloppement); // Noeud Développement
        nodeProduction.addChild("Production", nodeProduction); // Noeud Production
        nodeRecette.addChild("Recette", nodeRecette); // Noeud Recette
        nodeFormation.addChild("Formation", nodeFormation); // Noeud Formation
        nodeTest.addChild("Test", nodeTest); // "Noeud test"

        // Continuer à ajouter les noeuds à partir de la hauteur 6 de l'arbre
        DecisionTreeNode nodeSIE = new DecisionTreeNode("SIE"); // Noeud SIE
        DecisionTreeNode nodeSIIV = new DecisionTreeNode("SIIV"); // Noeud SIIV
        DecisionTreeNode nodeHDS = new DecisionTreeNode("HDS"); // Noeud HDS
        DecisionTreeNode nodeDiffusionRestreinte = new DecisionTreeNode("Diffusion Restreinte"); // Noeud Diffusion Restreinte
        DecisionTreeNode nodeNon = new DecisionTreeNode("Non"); // Noeud "Non"

        nodeSIE.addChild("SIE", nodeSIE); // SIE node
        nodeSIIV.addChild("SIIV", nodeSIIV); // SIIV node
        nodeHDS.addChild("HDS", nodeHDS); // HDS node
        nodeDiffusionRestreinte.addChild("Diffusion Restreinte", nodeDiffusionRestreinte); // Diffusion Restreinte node
        nodeNon.addChild("Non", nodeNon); // Non node

       // DecisionTreeNode nodeCriticitePASSI = new DecisionTreeNode("Criticite (PASSI)");
        DecisionTreeNode nodeFaible = new DecisionTreeNode("Faible");
        DecisionTreeNode nodeImportante = new DecisionTreeNode("Importante");
        DecisionTreeNode nodeCritique = new DecisionTreeNode("Critique");
        DecisionTreeNode nodeVitale = new DecisionTreeNode("Vitale");

        DecisionTreeNode nodeYSEFaible = new DecisionTreeNode("Z_YSE_FAIBLE"); // A modifier : pas un noeud final
        DecisionTreeNode nodeYSEImportante = new DecisionTreeNode("Z_YSE_IMPOR");
        DecisionTreeNode nodeYSECritique = new DecisionTreeNode("Z_YSE_CRITIQUE");
        DecisionTreeNode nodeYSEVitale = new DecisionTreeNode("YSE_LSE_VITALE");

        nodeYSEFaible.addChild("Z_YSE_FAIBLE", nodeYSEFaible); // A modifier : pas un noeud final
        nodeYSECritique.addChild("Z_YSE_Critique", nodeYSECritique);
        nodeYSEImportante.addChild("Z_YSE_Importante", nodeYSEImportante);
        nodeYSEVitale.addChild("Z_YSE_Vitale", nodeYSEVitale);

        DecisionTreeNode nodeNo = new DecisionTreeNode("No");
        nodeNo.addChild("VLAN1", nodeNo);
    }

    /**
     * Method for deciding the choice of the user
     * TODO : à modifier et personnaliser le choix de l'utilisateur
     */
    public void decide() {
        Scanner scanner = new Scanner(System.in);
        DecisionTreeNode currentNode = root; // root

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getInput());
            String choice = scanner.nextLine();
            currentNode = currentNode.getChild(choice);
            if (currentNode == null) {
                System.out.println("Choix non valide. Veuillez recommencer."); // if correct choice, select a node
                currentNode = root;
            }
            else {
                System.out.println("Choix valide. Veuillez continuer."); // if correct choice, select a node
                addPathNodes(choice);
            }
        }
        System.out.println("Noeud final atteint : " + currentNode.getInput()); // final node
    }

    /**
     * Method to get the root
     * @return
     */
    public DecisionTreeNode getRoot() {
        return this.root;
    }

}
