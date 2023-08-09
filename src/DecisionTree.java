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
         // R : WIFI ou FILAIRE

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

        DecisionTreeNode nodeE = new DecisionTreeNode("E : Interne ou autre"); // E : Interne, GHT, Publique Large, Public Restreint
        nodeExposition.addChild("E : Interne ou autre", nodeE); // E : "noeud Exposition"

        DecisionTreeNode nodeR = new DecisionTreeNode("R : WIFI ou FILAIRE");
        nodeReseau.addChild("R : WIFI ou FILAIRE", nodeR); // ERREUR

        // Nodes
        nodeE.addChild("Environnement1", nodeEnvironnement1); // Environnement1
        nodeR.addChild("Environnement2", nodeEnvironnement2); // Environnement2

        DecisionTreeNode nodeIntegration = new DecisionTreeNode("Integration"); // Integration
        DecisionTreeNode nodeDeveloppement = new DecisionTreeNode("Developpement"); // Developpement
        DecisionTreeNode nodeProduction = new DecisionTreeNode("Production"); // Production
        DecisionTreeNode nodeRecette = new DecisionTreeNode("Recette"); // Recette
        DecisionTreeNode nodeFormation = new DecisionTreeNode("Formation"); // Formation
        DecisionTreeNode nodeTest = new DecisionTreeNode("Test"); // Test

        /**
         * TODO : modif en cours
         */
        nodeEnvironnement1.addChild("Integration", nodeIntegration); // Noeud Integration
        nodeEnvironnement1.addChild("Developpement", nodeDeveloppement); // Noeud Développement
        nodeEnvironnement1.addChild("Production", nodeProduction); // Noeud Production
        nodeEnvironnement1.addChild("Recette", nodeRecette); // Noeud Recette
        nodeEnvironnement1.addChild("Formation", nodeFormation); // Noeud Formation
        nodeEnvironnement1.addChild("Test", nodeTest); // "Noeud test"

        DecisionTreeNode nodeZLSEINT = new DecisionTreeNode("Z_LSE_INT"); // Noeud
        nodeIntegration.addChild("Z_LSE_INT", nodeZLSEINT);
        DecisionTreeNode nodeZLSEDEV = new DecisionTreeNode("Z_LSE_DEV");
        nodeDeveloppement.addChild("Z_LSE_DEV", nodeZLSEDEV);
        DecisionTreeNode nodeExigence = new DecisionTreeNode("Exigence réglementaire");
        nodeProduction.addChild("Exigence réglementaire", nodeExigence);
        DecisionTreeNode nodeZLSERECETTE = new DecisionTreeNode("Z_LSE_RECETTE");
        nodeRecette.addChild("Z_LSE_RECETTE", nodeZLSERECETTE);
        DecisionTreeNode nodeZLSEFORM = new DecisionTreeNode("Z_LSE_FORM");
        nodeFormation.addChild("Z_LSE_FORM", nodeZLSEFORM);
        DecisionTreeNode nodeZLSETEST = new DecisionTreeNode("Z_LSE_TEST");
        nodeZLSETEST.addChild("Z_LSE_TEST", nodeZLSETEST);
        nodeProduction.addChild("Exigence réglementaire", nodeExigence);

        // Continuer à ajouter les noeuds à partir de la hauteur 6 de l'arbre
        DecisionTreeNode nodeSIE = new DecisionTreeNode("SIE"); // Noeud SIE
        DecisionTreeNode nodeSIIV = new DecisionTreeNode("SIIV"); // Noeud SIIV
        DecisionTreeNode nodeHDS = new DecisionTreeNode("HDS"); // Noeud HDS
        DecisionTreeNode nodeDiffusionRestreinte = new DecisionTreeNode("Diffusion Restreinte"); // Noeud Diffusion Restreinte
        DecisionTreeNode nodeNon = new DecisionTreeNode("Non"); // Noeud "Non"

        nodeExigence.addChild("SIE", nodeSIE); // SIE node
        nodeExigence.addChild("SIIV", nodeSIIV); // SIIV node
        nodeExigence.addChild("HDS", nodeHDS); // HDS node
        nodeExigence.addChild("Diffusion Restreinte", nodeDiffusionRestreinte); // Diffusion Restreinte node
        nodeExigence.addChild("Non", nodeNon); // Non node

        DecisionTreeNode nodeDSI = new DecisionTreeNode("Maitrisée par la DSI APHM");
        nodeNon.addChild("Maitrisée par la DSI APHM", nodeDSI);

        DecisionTreeNode nodeNo = new DecisionTreeNode("Non");
        DecisionTreeNode nodeYes = new DecisionTreeNode("Oui");
        nodeDSI.addChild("Non", nodeNo);
        nodeDSI.addChild("Yes", nodeYes);

        DecisionTreeNode nodeCriticite = new DecisionTreeNode("Criticité (FASSI)");
        nodeYes.addChild("Criticité (FASSI)", nodeCriticite);

        DecisionTreeNode nodeFaible = new DecisionTreeNode("Faible");
        DecisionTreeNode nodeImportante = new DecisionTreeNode("Importante");
        DecisionTreeNode nodeCritique = new DecisionTreeNode("Critique");
        DecisionTreeNode nodeVitale = new DecisionTreeNode("Vitale");

        nodeCriticite.addChild("Faible", nodeFaible);
        nodeCriticite.addChild("Importante", nodeImportante);
        nodeCriticite.addChild("Critique", nodeCritique);
        nodeCriticite.addChild("Vitale", nodeVitale);

        // A modifier : pas un noeud final
        DecisionTreeNode nodeYSEFaible = new DecisionTreeNode("Z_YSE_FAIBLE");
        DecisionTreeNode nodeYSEImportante = new DecisionTreeNode("Z_YSE_IMPOR");
        DecisionTreeNode nodeYSECritique = new DecisionTreeNode("Z_YSE_CRITIQUE");
        DecisionTreeNode nodeYSEVitale = new DecisionTreeNode("YSE_LSE_VITALE");

        nodeYSEFaible.addChild("Z_YSE_FAIBLE", nodeYSEFaible); // A modifier : pas un noeud final
        nodeYSECritique.addChild("Z_YSE_Critique", nodeYSECritique);
        nodeYSEImportante.addChild("Z_YSE_Importante", nodeYSEImportante);
        nodeYSEVitale.addChild("Z_YSE_Vitale", nodeYSEVitale);

        /**
         * TODO : Fin des modif en cours
         */

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
