import java.util.ArrayList;
import java.util.Scanner;

/**
 * DecisionTree class
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

        DecisionTreeNode nodeApplicationEquipment = new DecisionTreeNode("Application ou équipement");
        DecisionTreeNode nodeLocation = new DecisionTreeNode("Localisation géographique");

        DecisionTreeNode nodeServer = new DecisionTreeNode("Serveur");
        DecisionTreeNode nodeTerminal = new DecisionTreeNode("Terminal client");
        DecisionTreeNode nodePrinter = new DecisionTreeNode("I : Imprimante");
        DecisionTreeNode nodeScanner = new DecisionTreeNode("M : Scanneur médical");

        DecisionTreeNode nodeExposition = new DecisionTreeNode("Exposition");
        DecisionTreeNode nodeReseau = new DecisionTreeNode("Réseau");
        DecisionTreeNode nodeZIImprim = new DecisionTreeNode("Z_I_IMPRIM");
        DecisionTreeNode nodeZMScanMedic = new DecisionTreeNode("Z_M_SCAN_MEDIC");

        DecisionTreeNode nodeEnvironnement1 = new DecisionTreeNode("Environnement1");
        DecisionTreeNode nodeEnvironnement2 = new DecisionTreeNode("Environnement2");

        root = nodeApplicationEquipment;
        root.addChild("Serveur", nodeServer);
        root.addChild("Terminal", nodeTerminal);
        root.addChild("Imprimante", nodePrinter);
        root.addChild("Scanneur", nodeScanner);

        nodeServer.addChild("Exposition", nodeExposition);
        nodeTerminal.addChild("Réseau", nodeReseau);
        nodePrinter.addChild("Z_I_IMPRIM", nodeZIImprim);
        nodeScanner.addChild("Z_M_SCAN_MEDIC", nodeZMScanMedic);

        DecisionTreeNode nodeE = new DecisionTreeNode("E : Interne ou autre");
        nodeExposition.addChild("E : Interne ou autre", nodeE);

        DecisionTreeNode nodeR = new DecisionTreeNode("R : WIFI ou FILAIRE");
        nodeReseau.addChild("R : WIFI ou FILAIRE", nodeR);

        nodeE.addChild("Environnement1", nodeEnvironnement1);
        nodeR.addChild("Environnement2", nodeEnvironnement2);

        DecisionTreeNode nodeIntegration1 = new DecisionTreeNode("Integration1");
        DecisionTreeNode nodeDeveloppement1 = new DecisionTreeNode("Developpement1");
        DecisionTreeNode nodeProduction1 = new DecisionTreeNode("Production1");
        DecisionTreeNode nodeRecette1 = new DecisionTreeNode("Recette1");
        DecisionTreeNode nodeFormation1 = new DecisionTreeNode("Formation1");
        DecisionTreeNode nodeTest1 = new DecisionTreeNode("Test1");

        DecisionTreeNode nodeIntegration2 = new DecisionTreeNode("Integration2");
        DecisionTreeNode nodeDeveloppement2 = new DecisionTreeNode("Developpement2");
        DecisionTreeNode nodeProduction2 = new DecisionTreeNode("Production2");
        DecisionTreeNode nodeRecette2 = new DecisionTreeNode("Recette2");
        DecisionTreeNode nodeFormation2 = new DecisionTreeNode("Formation2");
        DecisionTreeNode nodeTest2 = new DecisionTreeNode("Test2");

        // environnement2 here
        nodeEnvironnement2.addChild("Integration2", nodeIntegration2);
        nodeEnvironnement2.addChild("Developpement2", nodeDeveloppement2);
        nodeEnvironnement2.addChild("Production2", nodeProduction2);
        nodeEnvironnement2.addChild("Recette2", nodeRecette2);
        nodeEnvironnement2.addChild("Formation2", nodeFormation2);
        nodeEnvironnement2.addChild("Test2", nodeTest2);

        // node after Environnement2
        DecisionTreeNode nodeZLTRREC = new DecisionTreeNode("Z_LTR_RECE");
        DecisionTreeNode nodeZLTDEV = new DecisionTreeNode("Z_LTR_DEV");
        // DecisionTreeNode nodeZLTRREC = new DecisionTreeNode("Z_LTR_RECE");
        DecisionTreeNode nodeZLTRRECETTE = new DecisionTreeNode("Z_LTR_RECETTE");
        DecisionTreeNode nodeZLTRFORM = new DecisionTreeNode("Z_LTR_FORM");
        DecisionTreeNode nodeZLTRTEST = new DecisionTreeNode("Z_LTR_TEST");
        nodeIntegration2.addChild("Z_LTR_RECE", nodeZLTRREC);
        nodeDeveloppement2.addChild("Z_LTR_DEV", nodeZLTDEV);
        // nodeProduction2.addChild("Maitrisée par la DSI APHM", nodeDSI2);
        nodeRecette2.addChild("Z_LTR_RECETTE", nodeZLTRRECETTE);
        nodeFormation2.addChild("Z_LTR_FORM", nodeZLTRFORM);
        nodeTest2.addChild("Z_LTR_TEST", nodeZLTRTEST);

        nodeEnvironnement1.addChild("Integration1", nodeIntegration1);
        nodeEnvironnement1.addChild("Developpement1", nodeDeveloppement1);
        nodeEnvironnement1.addChild("Production1", nodeProduction1);
        nodeEnvironnement1.addChild("Recette1", nodeRecette1);
        nodeEnvironnement1.addChild("Formation1", nodeFormation1);
        nodeEnvironnement1.addChild("Test1", nodeTest1);

        DecisionTreeNode nodeZLSEINT = new DecisionTreeNode("Z_LSE_INT");
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

        DecisionTreeNode nodeSIE = new DecisionTreeNode("SIE");
        DecisionTreeNode nodeSIIV = new DecisionTreeNode("SIIV");
        DecisionTreeNode nodeHDS = new DecisionTreeNode("HDS");
        DecisionTreeNode nodeDiffusionRestreinte = new DecisionTreeNode("Diffusion Restreinte");
        DecisionTreeNode nodeNon1 = new DecisionTreeNode("Non1");
        DecisionTreeNode nodeDSI1 = new DecisionTreeNode("Maitrisée par la DSI1 APHM");

        nodeExigence.addChild("Non1", nodeNon1);
        nodeNon1.addChild("Maitrisée par la DSI1 APHM", nodeDSI1);
        nodeExigence.addChild("SIE", nodeSIE);
        nodeExigence.addChild("SIIV", nodeSIIV);
        nodeExigence.addChild("HDS", nodeHDS);
        nodeExigence.addChild("Diffusion Restreinte", nodeDiffusionRestreinte);

        DecisionTreeNode nodeZLSESIE = new DecisionTreeNode("Z_LSE_SIE");
        DecisionTreeNode nodeZLSESIIV = new DecisionTreeNode("Z_LSE_SIIV");
        DecisionTreeNode nodeZLSEHDS = new DecisionTreeNode("Z_LSE_HDS");
        DecisionTreeNode nodeDiffusion = new DecisionTreeNode("Z_LSE_DR");

        nodeSIE.addChild("Z_LSE_SIE", nodeZLSESIE);
        nodeSIIV.addChild("Z_LSE_SIIV", nodeZLSESIIV);
        nodeHDS.addChild("Z_LSE_HDS", nodeZLSEHDS);
        nodeDiffusionRestreinte.addChild("Z_LSE_DR", nodeDiffusion);

        DecisionTreeNode nodeDSI2 = new DecisionTreeNode("Maitrisée par la DSI2 APHM");
        DecisionTreeNode nodeNon2 = new DecisionTreeNode("Non2");
        DecisionTreeNode nodeOui1 = new DecisionTreeNode("Oui1");

        nodeDSI1.addChild("Non2", nodeNon2);
        nodeDSI1.addChild("Oui1", nodeOui1);

        DecisionTreeNode nodeOui2 = new DecisionTreeNode("Oui2");
        DecisionTreeNode nodeNon3 = new DecisionTreeNode("Non3");
        DecisionTreeNode nodeCategory = new DecisionTreeNode("Catégorie d'usage");
        DecisionTreeNode nodeZLTRDSI = new DecisionTreeNode("Z_LTR_DSI");
        DecisionTreeNode nodeMedical = new DecisionTreeNode("Médical");
        DecisionTreeNode nodeAd = new DecisionTreeNode("Administratif");
        DecisionTreeNode nodeDSICategory = new DecisionTreeNode("DSI");
        DecisionTreeNode nodeZLTRMEDI = new DecisionTreeNode("Z_LTR_MEDI");
        DecisionTreeNode nodeZLTRADMI = new DecisionTreeNode("Z_LTR_ADMI");
        DecisionTreeNode nodeZLTRDSICategory = new DecisionTreeNode("Z_LTR_DSI");

        nodeNon3.addChild("Catégorie d'usage", nodeCategory);
        nodeCategory.addChild("Médical", nodeMedical);
        nodeCategory.addChild("Administratif", nodeAd);
        nodeCategory.addChild("DSI", nodeDSICategory);
        nodeMedical.addChild("Z_LTR_MEDI", nodeZLTRMEDI);
        nodeAd.addChild("Z_LTR_ADMI", nodeZLTRADMI);
        nodeDSICategory.addChild("Z_LTR_DSI", nodeZLTRDSICategory);

        // for node "Poste admin" and more
        DecisionTreeNode nodeAdmin = new DecisionTreeNode("Poste admin");
        nodeAdmin.addChild("Non3", nodeNon3);
        nodeDSI2.addChild("Oui2", nodeOui2); // pas encore modifié
        nodeOui2.addChild("Poste admin", nodeAdmin);

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

        // Nodes after "Oui1"
        DecisionTreeNode nodeCriticite = new DecisionTreeNode("Criticité (FASSI)");
        DecisionTreeNode nodeFaible = new DecisionTreeNode("Faible");
        DecisionTreeNode nodeImportante = new DecisionTreeNode("Importante");
        DecisionTreeNode nodeCritique = new DecisionTreeNode("Critique");
        DecisionTreeNode nodeVitale = new DecisionTreeNode("Vitale");
        DecisionTreeNode nodeYSEFaible = new DecisionTreeNode("Z_YSE_FAIBLE");
        DecisionTreeNode nodeYSEImportante = new DecisionTreeNode("Z_YSE_IMPOR");
        DecisionTreeNode nodeYSECritique = new DecisionTreeNode("Z_YSE_CRITIQUE");
        DecisionTreeNode nodeYSEVitale = new DecisionTreeNode("YSE_LSE_VITALE");

        nodeOui1.addChild("Criticité (FASSI)", nodeCriticite);
        nodeCriticite.addChild("Faible", nodeFaible);
        nodeCriticite.addChild("Importante", nodeImportante);
        nodeCriticite.addChild("Critique", nodeCritique);
        nodeCriticite.addChild("Vitale", nodeVitale);
        nodeFaible.addChild("Z_YSE_FAIBLE", nodeYSEFaible); // A modifier : pas un noeud final
        nodeCritique.addChild("Z_YSE_Critique", nodeYSECritique);
        nodeImportante.addChild("Z_YSE_Importante", nodeYSEImportante);
        nodeVitale.addChild("Z_YSE_Vitale", nodeYSEVitale);
        // for node Envrionnement2
        nodeEnvironnement2.addChild("Production2", nodeProduction2);
        nodeProduction2.addChild("Maitrisée par la DSI2 APHM", nodeDSI2);

    }

    /**
     * Method for selecting a choice
     */
    public void decide() {

        Scanner scanner = new Scanner(System.in);
        DecisionTreeNode currentNode = root;
        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getInput());
            String choice = scanner.nextLine();
            currentNode = currentNode.getChild(choice);
            if (currentNode == null) {
                System.out.println("Choix non valide. Veuillez recommencer.");
                currentNode = root;
            }
            else {
                System.out.println("Choix valide. Veuillez continuer.");
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
