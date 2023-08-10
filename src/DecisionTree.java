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

        DecisionTreeNode nodeIntegration1 = new DecisionTreeNode("Integration1"); // Integration
        DecisionTreeNode nodeDeveloppement1 = new DecisionTreeNode("Developpement1"); // Developpement
        DecisionTreeNode nodeProduction1 = new DecisionTreeNode("Production1"); // Production
        DecisionTreeNode nodeRecette1 = new DecisionTreeNode("Recette1"); // Recette
        DecisionTreeNode nodeFormation1 = new DecisionTreeNode("Formation1"); // Formation
        DecisionTreeNode nodeTest1 = new DecisionTreeNode("Test1"); // Test


        DecisionTreeNode nodeIntegration2 = new DecisionTreeNode("Integration2"); // Integration
        DecisionTreeNode nodeDeveloppement2 = new DecisionTreeNode("Developpement2"); // Developpement
        DecisionTreeNode nodeProduction2 = new DecisionTreeNode("Production2"); // Production
        DecisionTreeNode nodeRecette2 = new DecisionTreeNode("Recette2"); // Recette
        DecisionTreeNode nodeFormation2 = new DecisionTreeNode("Formation2"); // Formation
        DecisionTreeNode nodeTest2 = new DecisionTreeNode("Test2"); // Test
        /**
         * TODO : implements decision tree : environnement2
         */
        nodeEnvironnement2.addChild("Integration2", nodeIntegration2);
        nodeEnvironnement2.addChild("Developpement2", nodeDeveloppement2);
        nodeEnvironnement2.addChild("Production2", nodeProduction2);
        nodeEnvironnement2.addChild("Recette2", nodeRecette2);
        nodeEnvironnement2.addChild("Formation2", nodeFormation2);
        nodeEnvironnement2.addChild("Test2", nodeTest2);



        /**
         * TODO : modif en cours
         */
        nodeEnvironnement1.addChild("Integration1", nodeIntegration1); // Noeud Integration
        nodeEnvironnement1.addChild("Developpement1", nodeDeveloppement1); // Noeud Développement
        nodeEnvironnement1.addChild("Production1", nodeProduction1); // Noeud Production
        nodeEnvironnement1.addChild("Recette1", nodeRecette1); // Noeud Recette
        nodeEnvironnement1.addChild("Formation1", nodeFormation1); // Noeud Formation
        nodeEnvironnement1.addChild("Test1", nodeTest1); // "Noeud test"

        DecisionTreeNode nodeZLSEINT = new DecisionTreeNode("Z_LSE_INT"); // Noeud
        nodeIntegration1.addChild("Z_LSE_INT", nodeZLSEINT);
        DecisionTreeNode nodeZLSEDEV = new DecisionTreeNode("Z_LSE_DEV");
        nodeDeveloppement1.addChild("Z_LSE_DEV", nodeZLSEDEV);
        DecisionTreeNode nodeExigence = new DecisionTreeNode("Exigence réglementaire");
        nodeProduction1.addChild("Exigence réglementaire", nodeExigence);
        DecisionTreeNode nodeZLSERECETTE = new DecisionTreeNode("Z_LSE_RECETTE");
        nodeRecette1.addChild("Z_LSE_RECETTE", nodeZLSERECETTE);
        DecisionTreeNode nodeZLSEFORM = new DecisionTreeNode("Z_LSE_FORM");
        nodeFormation1.addChild("Z_LSE_FORM", nodeZLSEFORM);
        DecisionTreeNode nodeZLSETEST = new DecisionTreeNode("Z_LSE_TEST");
        nodeZLSETEST.addChild("Z_LSE_TEST", nodeZLSETEST);
        nodeProduction1.addChild("Exigence réglementaire", nodeExigence);

        // Continuer à ajouter les noeuds à partir de la hauteur 6 de l'arbre
        DecisionTreeNode nodeSIE = new DecisionTreeNode("SIE"); // Noeud SIE
        DecisionTreeNode nodeSIIV = new DecisionTreeNode("SIIV"); // Noeud SIIV
        DecisionTreeNode nodeHDS = new DecisionTreeNode("HDS"); // Noeud HDS
        DecisionTreeNode nodeDiffusionRestreinte = new DecisionTreeNode("Diffusion Restreinte"); // Noeud Diffusion Restreinte


        DecisionTreeNode nodeNon1 = new DecisionTreeNode("Non1"); // Premier noeud "Non"
        nodeExigence.addChild("Non1", nodeNon1); // Non node
        DecisionTreeNode nodeDSI1 = new DecisionTreeNode("Maitrisée par la DSI1 APHM");
        nodeNon1.addChild("Maitrisée par la DSI1 APHM", nodeDSI1); // en cours de modification actuelle

        DecisionTreeNode nodeDSI2 = new DecisionTreeNode("Maitrisée par la DSI2 APHM"); // pas encore modifié
        nodeNon1.addChild("Maitrisée par la DSI2 APHM", nodeDSI2);

        DecisionTreeNode nodeNon2 = new DecisionTreeNode("Non2");
        DecisionTreeNode nodeOui1 = new DecisionTreeNode("Oui1"); // pas encore modifié
        nodeDSI1.addChild("Non2", nodeNon2);
        nodeDSI1.addChild("Oui1", nodeOui1); // pas encore modifié


        /**
         * TODO : Non nodes here
         */
        /**
         * Nodes after Poste admin
         */
        // for node Poste admin and more
        DecisionTreeNode nodeAdmin = new DecisionTreeNode("Poste admin");

        DecisionTreeNode nodeCategory = new DecisionTreeNode("Catégorie d'usage");

        DecisionTreeNode nodeZLTRDSI = new DecisionTreeNode("Z_LTR_DSI");

        DecisionTreeNode nodeMedical = new DecisionTreeNode("Médical");
        DecisionTreeNode nodeAd = new DecisionTreeNode("Administratif");
        DecisionTreeNode nodeDSICategory = new DecisionTreeNode("DSI");

        DecisionTreeNode nodeZLTRMEDI = new DecisionTreeNode("Z_LTR_MEDI");
        DecisionTreeNode nodeZLTRADMI = new DecisionTreeNode("Z_LTR_ADMI");
        DecisionTreeNode nodeZLTRDSICategory = new DecisionTreeNode("Z_LTR_DSI");

        nodeCategory.addChild("Médical", nodeMedical);
        nodeCategory.addChild("Administratif", nodeAd);
        nodeCategory.addChild("Médical", nodeMedical);

        nodeMedical.addChild("Z_LTR_MEDI", nodeZLTRMEDI);
        nodeAd.addChild("Z_LTR_ADMI", nodeZLTRADMI);
        nodeDSICategory.addChild("Z_LTR_DSI", nodeZLTRDSICategory);
        // for node Maitrisée par la DSI APHM 2 -nodeNon3
        DecisionTreeNode nodeOui2 = new DecisionTreeNode("Oui2"); // pas encore modifié
        DecisionTreeNode nodeNon3 = new DecisionTreeNode("Non3");
        nodeDSI2.addChild("Oui2", nodeOui2); // pas encore modifié
        nodeAdmin.addChild("Non3", nodeNon3);

        // for node Maitrisée par la DSI APHM 2 - nodeNon4
        DecisionTreeNode nodeOui3 = new DecisionTreeNode("Oui3"); // pas encore modifié
        DecisionTreeNode nodeNon4 = new DecisionTreeNode("Non4");
        nodeAdmin.addChild("Oui3", nodeOui3); // pas encore modifié
        nodeDSI2.addChild("Non4", nodeNon4);

        DecisionTreeNode nodeType = new DecisionTreeNode("Type d'actif Non Maitrisé");

        DecisionTreeNode nodeShadowIT = new DecisionTreeNode("Shadow IT");
        DecisionTreeNode nodeBYOD = new DecisionTreeNode("BYOD (MAC)");
        DecisionTreeNode nodeProvider = new DecisionTreeNode("Prestataire");

        DecisionTreeNode nodeSha = new DecisionTreeNode("Z_LTR_NM_SHA");
        DecisionTreeNode nodeByod = new DecisionTreeNode("Z_LTR_NM_BYOD");
        DecisionTreeNode nodePres = new DecisionTreeNode("Z_LTR_NM_PRES");

        nodeNon4.addChild("Type d'actif Non Maitrisé", nodeType);
        nodeType.addChild("Shadow IT", nodeShadowIT);
        nodeType.addChild("BYOD (MAC)", nodeBYOD);
        nodeType.addChild("Prestataire", nodeProvider);

        nodeShadowIT.addChild("Z_LTR_NM_SHA", nodeSha);
        nodeShadowIT.addChild("Z_LTR_NM_BYOD", nodeByod);
        nodeShadowIT.addChild("Z_LTR_NM_PRES", nodePres);





        /**
         * TODO : Non nodes end
         */

        nodeExigence.addChild("SIE", nodeSIE); // SIE node
        nodeExigence.addChild("SIIV", nodeSIIV); // SIIV node
        nodeExigence.addChild("HDS", nodeHDS); // HDS node
        nodeExigence.addChild("Diffusion Restreinte", nodeDiffusionRestreinte); // Diffusion Restreinte node



        DecisionTreeNode nodeCriticite = new DecisionTreeNode("Criticité (FASSI)");
        nodeOui1.addChild("Criticité (FASSI)", nodeCriticite);

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

        // for node Envrionnement2
        nodeEnvironnement2.addChild("Production2", nodeProduction2);
        nodeProduction2.addChild("Maitrisée par la DSI2 APHM", nodeDSI2);



        /**
         * TODO : add children after Non, after Maitrisée la DSI APHM
         */




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
