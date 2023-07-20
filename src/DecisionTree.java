import java.util.Scanner;

/**
 * Classe de l'arbre de décision
 */
public class DecisionTree {
    DecisionTreeNode root;
    public DecisionTree() {
        // Hauteur 1
        DecisionTreeNode nodeApplicationEquipment = new DecisionTreeNode("Application ou équipement");
        DecisionTreeNode nodeLocation = new DecisionTreeNode("Localisation géographique");

        // Hauteur 2
        DecisionTreeNode nodeServer = new DecisionTreeNode("Serveur");
        DecisionTreeNode nodeTerminal = new DecisionTreeNode("Terminal client (PC, VDI, MAC, Mobile, IOT...)");
        DecisionTreeNode nodePrinter = new DecisionTreeNode("I : Imprimante");
        DecisionTreeNode nodeScanner = new DecisionTreeNode("M : Scanneur médical");

        // Hauteur 3
        DecisionTreeNode nodeExposition = new DecisionTreeNode("Exposition"); // Exposition
        DecisionTreeNode nodeReseau = new DecisionTreeNode("Réseau"); // Réseau
        DecisionTreeNode nodeZIImprim = new DecisionTreeNode("Z_I_IMPRIM"); // Z_I_IMPRIM
        DecisionTreeNode nodeZMScanMedic = new DecisionTreeNode("Z_M_SCAN_MEDIC"); // Z_M_SCAN_MEDIC

        // Hauteur 4
        DecisionTreeNode nodeE = new DecisionTreeNode("E : Interne, GHT, Publique Large, Publique Restreint"); // E : Interne, GHT, Publique Large, Public Restreint
        DecisionTreeNode nodeR = new DecisionTreeNode("R : WIFI ou FILAIRE"); // R : WIFI ou FILAIRE

        // Hauteur 5
        DecisionTreeNode nodeEnvironnement1 = new DecisionTreeNode("Environnement"); // Environnement
        DecisionTreeNode nodeEnvironnement2 = new DecisionTreeNode("Environnement"); // Environnement

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

        nodeExposition.addChild("E", nodeE); // E
        nodeReseau.addChild("R", nodeR); // R

        nodeE.addChild("Environnement1", nodeEnvironnement1); // Environnement1
        nodeR.addChild("Environnement2", nodeEnvironnement2); // Environnement2

        DecisionTreeNode nodeIntegration = new DecisionTreeNode("Integration");
        DecisionTreeNode nodeDeveloppement = new DecisionTreeNode("Development");
        DecisionTreeNode nodeProduction = new DecisionTreeNode("Production");
        DecisionTreeNode nodeRecette = new DecisionTreeNode("Recette");
        DecisionTreeNode nodeFormation = new DecisionTreeNode("Formation");
        DecisionTreeNode nodeTest = new DecisionTreeNode("Test");

        // Ajout précédent à ne pas modifier
        nodeEnvironnement1.addChild("Integration", nodeIntegration);
        nodeDeveloppement.addChild("Developpement", nodeDeveloppement);
        nodeProduction.addChild("Production", nodeProduction);
        nodeRecette.addChild("Recette", nodeRecette);
        nodeFormation.addChild("Formation", nodeFormation);
        nodeTest.addChild("Test", nodeTest);

        // Ajout précédent à ne pas modifier
        // Continuer à ajouter les noeuds à partir de la hauteur 6 de l'arbre
        DecisionTreeNode nodeSIE = new DecisionTreeNode("SIE");
        DecisionTreeNode nodeSIIV = new DecisionTreeNode("SIIV");
        DecisionTreeNode nodeHDS = new DecisionTreeNode("HDS");
        DecisionTreeNode nodeDiffusionRestreinte = new DecisionTreeNode("Diffusion Restreinte");
        DecisionTreeNode nodeNon = new DecisionTreeNode("Non");

        nodeSIE.addChild("SIE", nodeSIE);
        nodeSIIV.addChild("SIIV", nodeSIIV);
        nodeHDS.addChild("HDS", nodeHDS);
        nodeDiffusionRestreinte.addChild("Diffusion Restreinte", nodeDiffusionRestreinte); // Noeud "Diffusion Restreinte"
        nodeNon.addChild("Non", nodeNon); // Noeud "non"

       // DecisionTreeNode nodeCriticitePASSI = new DecisionTreeNode("Criticite (PASSI)");
        DecisionTreeNode nodeFaible = new DecisionTreeNode("Faible");
        DecisionTreeNode nodeImportante = new DecisionTreeNode("Importante");
        DecisionTreeNode nodeCritique = new DecisionTreeNode("Critique");
        DecisionTreeNode nodeVitale = new DecisionTreeNode("Vitale");

        DecisionTreeNode nodeYSEFaible = new DecisionTreeNode("Z_YSE_FAIBLE"); // Noeud final
        DecisionTreeNode nodeYSEImportante = new DecisionTreeNode("Z_YSE_IMPOR"); // Noeud final
        DecisionTreeNode nodeYSECritique = new DecisionTreeNode("Z_YSE_CRITIQUE"); // Noeud final
        DecisionTreeNode nodeYSEVitale = new DecisionTreeNode("YSE_LSE_VITALE"); // Noeud final

        nodeYSEFaible.addChild("Z_YSE_FAIBLE", nodeYSEFaible); // Noeud final
        nodeYSECritique.addChild("Z_YSE_Critique", nodeYSECritique); // Noeud final
        nodeYSEImportante.addChild("Z_YSE_Importante", nodeYSEImportante); // Noeud final
        nodeYSEVitale.addChild("Z_YSE_Vitale", nodeYSEVitale); // Noeud final

        // Continuer à ajouter les noeuds à partir de la hauteur 7 de l'arbre et modifier les choix après "Environnement"

    }

    /**
     * Méthode pour implémenter la décision d'un noeud par l'utilisateur
     * TODO : à modifier et personnaliser le choix de l'utilisateur
     */
    public void decide() {
        Scanner scanner = new Scanner(System.in);
        DecisionTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getInput());
            String choice = scanner.nextLine();
            currentNode = currentNode.getChild(choice);
            if (currentNode == null) {
                System.out.println("Choix non valide. Veuillez recommencer."); // si choix non valide, sélectionner un nouveau noeud
                currentNode = root;
            }
        }
        System.out.println("Noeud final atteint : " + currentNode.getInput()); // Noeud final obtenu et atteint
    }

    public DecisionTreeNode getRoot() {
        return this.root;  // obtenir la racine de l'arbre
    }

}
