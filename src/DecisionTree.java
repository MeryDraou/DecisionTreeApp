import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
/**
 * DecisionTree Class
 * NB : The decision tree is considered to be read from left to right
 */
public class DecisionTree {
    DecisionTreeNode root;
    ArrayList<String> pathNodes = new ArrayList<>();
    public void addPathNodes(String decision) {
        pathNodes.add(decision);
    }
    public ArrayList<String> getPathNodes() {
        return pathNodes;
    }
    /**
     * Constructor
     */
    public DecisionTree() throws IOException {
        // differents nodes of the decision tree
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
        root.addChild("Serveur", nodeServer); // node Serveur
        root.addChild("Terminal", nodeTerminal); // node Terminal
        root.addChild("Imprimante", nodePrinter); // node Imprimante
        root.addChild("Scanneur", nodeScanner); // node Scanneur
        nodeServer.addChild("Exposition", nodeExposition); // node Exposition
        nodeTerminal.addChild("Réseau", nodeReseau); // node Réseau
        nodePrinter.addChild("Z_I_IMPRIM", nodeZIImprim); // node Z_I_IMPRIM
        nodeScanner.addChild("Z_M_SCAN_MEDIC", nodeZMScanMedic); // node Z_M_SCAN_MEDIC
        DecisionTreeNode nodeE = new DecisionTreeNode("E : Interne ou autre"); // node E : Interne ou autre
        nodeExposition.addChild("E : Interne ou autre", nodeE); // node E : Interne ou autre
        DecisionTreeNode nodeR = new DecisionTreeNode("R : WIFI ou FILAIRE"); // node R : WIFI ou FILAIRE
        nodeReseau.addChild("R : WIFI ou FILAIRE", nodeR); // node R : WIFI ou FILAIRE
        nodeE.addChild("Environnement1", nodeEnvironnement1); // node Envrionnement
        nodeR.addChild("Environnement2", nodeEnvironnement2); // node Envrionnement
        DecisionTreeNode nodeIntegration1 = new DecisionTreeNode("Integration1"); // node Intégration
        DecisionTreeNode nodeDeveloppement1 = new DecisionTreeNode("Developpement1"); // node Développement
        DecisionTreeNode nodeProduction1 = new DecisionTreeNode("Production1"); // node Production
        DecisionTreeNode nodeRecette1 = new DecisionTreeNode("Recette1"); // node Recette
        DecisionTreeNode nodeFormation1 = new DecisionTreeNode("Formation1"); // node Formation
        DecisionTreeNode nodeTest1 = new DecisionTreeNode("Test1"); // node Test at the left
        DecisionTreeNode nodeIntegration2 = new DecisionTreeNode("Integration2");
        DecisionTreeNode nodeDeveloppement2 = new DecisionTreeNode("Developpement2");
        DecisionTreeNode nodeProduction2 = new DecisionTreeNode("Production2");
        DecisionTreeNode nodeRecette2 = new DecisionTreeNode("Recette2");
        DecisionTreeNode nodeFormation2 = new DecisionTreeNode("Formation2");
        DecisionTreeNode nodeTest2 = new DecisionTreeNode("Test2");
        // for Environnement2
        nodeEnvironnement2.addChild("Integration2", nodeIntegration2);
        nodeEnvironnement2.addChild("Developpement2", nodeDeveloppement2);
        nodeEnvironnement2.addChild("Production2", nodeProduction2);
        nodeEnvironnement2.addChild("Recette2", nodeRecette2);
        nodeEnvironnement2.addChild("Formation2", nodeFormation2);
        nodeEnvironnement2.addChild("Test2", nodeTest2);
        // node after Environnement2
        DecisionTreeNode nodeZLTRREC = new DecisionTreeNode("Z_LTR_RECE"); // node Z_LTR_RECE
        DecisionTreeNode nodeZLTDEV = new DecisionTreeNode("Z_LTR_DEV"); // node Z_LTR_DEV
        DecisionTreeNode nodeZLTRRECETTE = new DecisionTreeNode("Z_LTR_RECETTE"); // node Z_LTR_RECETTE
        DecisionTreeNode nodeZLTRFORM = new DecisionTreeNode("Z_LTR_FORM"); // node Z_LTR_FORM
        DecisionTreeNode nodeZLTRTEST = new DecisionTreeNode("Z_LTR_TEST"); // node Z_LTR_TEST
        nodeIntegration2.addChild("Z_LTR_RECE", nodeZLTRREC); // node Z_LTR_RECE
        nodeDeveloppement2.addChild("Z_LTR_DEV", nodeZLTDEV); // node Z_LTR_DEV
        nodeRecette2.addChild("Z_LTR_RECETTE", nodeZLTRRECETTE); // node Z_LTR_RECETTE
        nodeFormation2.addChild("Z_LTR_FORM", nodeZLTRFORM); // node Z_LTR_FORM
        nodeTest2.addChild("Z_LTR_TEST", nodeZLTRTEST); // node Z_LTR_TEST
        nodeEnvironnement1.addChild("Integration1", nodeIntegration1); // node Intégration
        nodeEnvironnement1.addChild("Developpement1", nodeDeveloppement1); // node Développement
        nodeEnvironnement1.addChild("Production1", nodeProduction1); // node Production
        nodeEnvironnement1.addChild("Recette1", nodeRecette1); // node Recette
        nodeEnvironnement1.addChild("Formation1", nodeFormation1); // node Formation
        nodeEnvironnement1.addChild("Test1", nodeTest1); // node Test at the left
        DecisionTreeNode nodeZLSEINT = new DecisionTreeNode("Z_LSE_INT"); // node Z_LSE_INT
        nodeIntegration1.addChild("Z_LSE_INT", nodeZLSEINT); // node Z_LSE_INT
        DecisionTreeNode nodeZLSEDEV = new DecisionTreeNode("Z_LSE_DEV"); // node Z_LSE_DEV
        nodeDeveloppement1.addChild("Z_LSE_DEV", nodeZLSEDEV); // node Z_LSE_DEV
        DecisionTreeNode nodeExigence = new DecisionTreeNode("Exigence réglementaire");
        nodeProduction1.addChild("Exigence réglementaire", nodeExigence);
        DecisionTreeNode nodeZLSERECETTE = new DecisionTreeNode("Z_LSE_RECETTE");
        nodeRecette1.addChild("Z_LSE_RECETTE", nodeZLSERECETTE);
        DecisionTreeNode nodeZLSEFORM = new DecisionTreeNode("Z_LSE_FORM");
        nodeFormation1.addChild("Z_LSE_FORM", nodeZLSEFORM);
        DecisionTreeNode nodeZLSETEST = new DecisionTreeNode("Z_LSE_TEST");
        nodeTest1.addChild("Z_LSE_TEST", nodeZLSETEST);
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
        DecisionTreeNode nodeZLSESIE = new DecisionTreeNode("Z_LSE_SIE"); // Z_LSE_SIE
        DecisionTreeNode nodeZLSESIIV = new DecisionTreeNode("Z_LSE_SIIV"); // Z_LSE_SIIV
        DecisionTreeNode nodeZLSEHDS = new DecisionTreeNode("Z_LSE_HDS"); // Z_LSE_HDS
        DecisionTreeNode nodeDiffusion = new DecisionTreeNode("Z_LSE_DR"); // Z_LSE_DR
        nodeSIE.addChild("Z_LSE_SIE", nodeZLSESIE); // Z_LSE_SIE
        nodeSIIV.addChild("Z_LSE_SIIV", nodeZLSESIIV); // Z_LSE_SIIV
        nodeHDS.addChild("Z_LSE_HDS", nodeZLSEHDS); // Z_LSE_HDS
        nodeDiffusionRestreinte.addChild("Z_LSE_DR", nodeDiffusion);
        DecisionTreeNode nodeDSI2 = new DecisionTreeNode("Maitrisée par la DSI2 APHM"); // node Maitrisée par la DSI APHM, the second one
        DecisionTreeNode nodeNon2 = new DecisionTreeNode("Non2"); // Non
        DecisionTreeNode nodeOui1 = new DecisionTreeNode("Oui1"); // Oui
        nodeDSI1.addChild("Non2", nodeNon2); // Non
        nodeDSI1.addChild("Oui1", nodeOui1); // Oui
        DecisionTreeNode vlanNon2 = new DecisionTreeNode("VLAN : Non");
        nodeNon2.addChild("VLAN : Non", vlanNon2);
        DecisionTreeNode nodeOui2 = new DecisionTreeNode("Oui2"); // node Oui
        DecisionTreeNode nodeNon3 = new DecisionTreeNode("Non3"); // node Non
        DecisionTreeNode nodeCategory = new DecisionTreeNode("Catégorie d'usage"); // node Catégorie d'usage
        DecisionTreeNode nodeZLTRDSI = new DecisionTreeNode("Z_LTR_DSI"); // node Z_LTR_DSI
        DecisionTreeNode nodeMedical = new DecisionTreeNode("Médical"); // node Médical
        DecisionTreeNode nodeAd = new DecisionTreeNode("Administratif"); // node Administratif
        DecisionTreeNode nodeDSICategory = new DecisionTreeNode("DSI"); // node DSI
        DecisionTreeNode nodeZLTRMEDI = new DecisionTreeNode("Z_LTR_MEDI"); // node Z_LTR_MEDI
        DecisionTreeNode nodeZLTRADMI = new DecisionTreeNode("Z_LTR_ADMI"); // node Z_LTR_ADMI
        DecisionTreeNode nodeZLTRDSICategory = new DecisionTreeNode("Z_LTR_DSI"); // node Z_LTR_DSI
        nodeNon3.addChild("Catégorie d'usage", nodeCategory); // node Catégorie d'usage
        nodeCategory.addChild("Médical", nodeMedical);
        nodeCategory.addChild("Administratif", nodeAd); // node Administratif
        nodeCategory.addChild("DSI", nodeDSICategory); // node DSI
        nodeMedical.addChild("Z_LTR_MEDI", nodeZLTRMEDI); // node Z_LTR_MEDI
        nodeAd.addChild("Z_LTR_ADMI", nodeZLTRADMI); // node Z_LTR_ADMI
        nodeDSICategory.addChild("Z_LTR_DSI", nodeZLTRDSICategory); // node Z_LTR_DSI
        // for node "Poste admin" and more
        DecisionTreeNode nodeAdmin = new DecisionTreeNode("Poste admin"); // node Poste admin
        nodeAdmin.addChild("Non3", nodeNon3); // node Non
        nodeDSI2.addChild("Oui2", nodeOui2); // node Oui
        nodeOui2.addChild("Poste admin", nodeAdmin); // node Poste admin
        // for node Maitrisée par la DSI APHM 2 - nodeNon4
        DecisionTreeNode nodeOui3 = new DecisionTreeNode("Oui3"); // node Oui
        DecisionTreeNode nodeNon4 = new DecisionTreeNode("Non4"); // node Non
        nodeAdmin.addChild("Oui3", nodeOui3); // node Oui
        nodeDSI2.addChild("Non4", nodeNon4); // node Non
        DecisionTreeNode nodeType = new DecisionTreeNode("Type d'actif Non Maitrisé"); // node Type d'actif Non Maitrisé
        DecisionTreeNode nodeShadowIT = new DecisionTreeNode("Shadow IT"); // node Shadow IT
        DecisionTreeNode nodeBYOD = new DecisionTreeNode("BYOD (MAC)"); // node BYOD (MAC)
        DecisionTreeNode nodeProvider = new DecisionTreeNode("Prestataire"); // node Prestataire
        DecisionTreeNode nodeSha = new DecisionTreeNode("Z_LTR_NM_SHA"); // node Z_LTR_NM_SHA
        DecisionTreeNode nodeByod = new DecisionTreeNode("Z_LTR_NM_BYOD"); // node Z_LTR_NM_BYOD
        DecisionTreeNode nodePres = new DecisionTreeNode("Z_LTR_NM_PRES"); // node Z_LTR_NM_PRES
        nodeNon4.addChild("Type d'actif Non Maitrisé", nodeType); // node Type d'actif Non Maitrisé
        nodeType.addChild("Shadow IT", nodeShadowIT); // node Shadow IT
        nodeType.addChild("BYOD (MAC)", nodeBYOD); // node BYOD (MAC)
        nodeType.addChild("Prestataire", nodeProvider); // node Prestataire
        nodeShadowIT.addChild("Z_LTR_NM_SHA", nodeSha); // node Z_LTR_NM_SHA
        nodeShadowIT.addChild("Z_LTR_NM_BYOD", nodeByod); // node Z_LTR_NM_BYOD
        nodeShadowIT.addChild("Z_LTR_NM_PRES", nodePres); // node Z_LTR_NM_PRES
        // nodes after node "Oui1"
        DecisionTreeNode nodeCriticite = new DecisionTreeNode("Criticité (FASSI)"); // node Criticité (FASSI)
        DecisionTreeNode nodeFaible = new DecisionTreeNode("Faible"); // node Faible
        DecisionTreeNode nodeImportante = new DecisionTreeNode("Importante"); // node Importante
        DecisionTreeNode nodeCritique = new DecisionTreeNode("Critique"); // node Critique
        DecisionTreeNode nodeVitale = new DecisionTreeNode("Vitale"); // node Vitale
        DecisionTreeNode nodeYSEFaible = new DecisionTreeNode("Z_YSE_FAIBLE"); // node Z_YSE_FAIBLE
        DecisionTreeNode nodeYSEImportante = new DecisionTreeNode("Z_YSE_IMPOR"); // node Z_YSE_IMPOR
        DecisionTreeNode nodeYSECritique = new DecisionTreeNode("Z_YSE_CRITIQUE"); // node Z_YSE_CRITIQUE
        DecisionTreeNode nodeYSEVitale = new DecisionTreeNode("YSE_LSE_VITALE"); // node YSE_LSE_VITALE

        // for VLANs "Intégration"
        DecisionTreeNode vlanInt = new DecisionTreeNode("VLAN"); // choose your Intégration vlan
        Set<String> vlanIntMap = readVlan().keySet();
        for(String vlanId : vlanIntMap) {
            vlanInt.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_DEV : INT n
         */
        DecisionTreeNode vlanDevelopment = new DecisionTreeNode("VLAN DEV");
        nodeZLSEDEV.addChild("VLAN DEV", vlanDevelopment); // choose your Développement vlan
        Set<String> vlanDevMap = readVlan().keySet();
        for(String vlanId : vlanDevMap) {
            vlanDevelopment.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_TEST : TEST n
         */
        DecisionTreeNode vlanTest = new DecisionTreeNode("VLAN TEST");
        nodeZLSETEST.addChild("VLAN TEST", vlanTest); // choose your Test vlan
        Set<String> vlanTestMap = readVlan().keySet();
        for(String vlanId : vlanTestMap) {
            vlanTest.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_FORM : FORM n
         */
        DecisionTreeNode vlanForm = new DecisionTreeNode("VLAN FORM");
        nodeZLSEFORM.addChild("VLAN FORM", vlanForm); // choose your Formation vlan
        Set<String> vlanFormMap = readVlan().keySet();
        for(String vlanId : vlanFormMap) {
            vlanForm.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_RECETTE : RECETTE n
         */
        DecisionTreeNode vlanRecette = new DecisionTreeNode("VLAN RECETTE");
        nodeZLSERECETTE.addChild("VLAN RECETTE", vlanRecette); // choose your Recette vlan
        Set<String> vlanRecetteMap = readVlan().keySet();
        for(String vlanId : vlanRecetteMap) {
            vlanRecette.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_SIE : SIE n
         */
        DecisionTreeNode vlanSIE = new DecisionTreeNode("VLAN SIE");
        nodeZLSESIE.addChild("VLAN SIE", vlanSIE); // choose your SIE vlan
        Set<String> vlanSIEMap = readVlan().keySet();
        for(String vlanId : vlanSIEMap) {
            vlanSIE.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_SIIV : SIIV
         */
        DecisionTreeNode vlanSIIV = new DecisionTreeNode("VLAN SIIV");
        nodeZLSESIIV.addChild("VLAN SIIV", vlanSIIV); // choose your SIIV vlan
        Set<String> vlanSIVVMap = readVlan().keySet();
        for(String vlanId : vlanSIEMap) {
            vlanSIE.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_HDS : HDS n
         */
        DecisionTreeNode vlanHDS = new DecisionTreeNode("VLAN HDS");
        nodeZLSEHDS.addChild("VLAN HDS", vlanHDS); // choose your HDS vlan
        Set<String> vlanHDSMap = readVlan().keySet();
        for(String vlanId : vlanHDSMap) {
            vlanHDS.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        /**
         * To select a vlan id for Z_LSE_DR : SI DR, if more vlan zones
         */
        DecisionTreeNode nodeSiDr = new DecisionTreeNode("SI DR"); // choose your SI DR vlan
        nodeDiffusion.addChild("SI DR", nodeSiDr);
        Set<String> vlanSiDrMap = readVlan().keySet();
        for(String vlanId : vlanSiDrMap) {
            nodeSiDr.addChild(vlanId, new DecisionTreeNode(vlanId));
        }
        DecisionTreeNode vlanYSEFaible = new DecisionTreeNode("VLAN FAIBLE"); // faible
        nodeFaible.addChild("VLAN FAIBLE", vlanYSEFaible);
        DecisionTreeNode vlanYSEImpor = new DecisionTreeNode("VLAN IMPOR"); // importante
        nodeImportante.addChild("VLAN IMPOR", vlanYSEImpor);
        DecisionTreeNode vlanYSECritique = new DecisionTreeNode("VLAN CRITIQUE"); // critique
        nodeCritique.addChild("VLAN CRITIQUE", vlanYSECritique);
        DecisionTreeNode vlanYSEVitale = new DecisionTreeNode("VLAN VITALE"); // vitale
        nodeVitale.addChild("VLAN VITALE", vlanYSEVitale);
        DecisionTreeNode vlanShadow = new DecisionTreeNode("VLAN SHA"); // shadow it
        nodeShadowIT.addChild("VLAN SHA", vlanShadow);
        DecisionTreeNode vlanByod = new DecisionTreeNode("VLAN BYOD"); // byod
        nodeBYOD.addChild("VLAN BYOD", vlanByod);
        DecisionTreeNode vlanPres = new DecisionTreeNode("VLAN PRES"); // pres
        nodePres.addChild("VLAN PRES", vlanPres);
        nodeZLSEINT.addChild("VLAN", vlanInt); // intégration
        nodeOui1.addChild("Criticité (FASSI)", nodeCriticite);
        nodeCriticite.addChild("Faible", nodeFaible); // faible
        nodeCriticite.addChild("Importante", nodeImportante); // importante
        nodeCriticite.addChild("Critique", nodeCritique); // critique
        nodeCriticite.addChild("Vitale", nodeVitale); // vitale
        nodeFaible.addChild("Z_YSE_FAIBLE", nodeYSEFaible); // faible
        nodeCritique.addChild("Z_YSE_Critique", nodeYSECritique); // critique
        nodeImportante.addChild("Z_YSE_Importante", nodeYSEImportante); // Z_YSE_Importante
        nodeVitale.addChild("Z_YSE_Vitale", nodeYSEVitale); // Z_YSE_Vitale
        // for node Environnement2
        nodeEnvironnement2.addChild("Production2", nodeProduction2); // Production
        nodeProduction2.addChild("Maitrisée par la DSI2 APHM", nodeDSI2); // Maitrisée par la DSI APHM
    }
    /**
     * Method for selecting/reading the vlan number from the test file vlan_data.csv
     * Change the file name in the code
     * @return
     * @throws IOException
     */
    public static HashMap<String, Vlan> readVlan() throws IOException {
        HashMap<String, Vlan> vlanType = new HashMap<>();
        String cheminCourant = System.getProperty("user.dir");
        System.out.println("Chemin courant : " + cheminCourant);
        Reader in = new FileReader("data\\vlan_data.csv"); // csv file (modify if necessary)
        CSVParser csvParser = new CSVParser(in, CSVFormat.DEFAULT.withHeader());
        Iterable<CSVRecord> records = csvParser.getRecords();
        for (CSVRecord record : records) {
            vlanType.put(record.get("vlan id"),
                    new Vlan(record.get("\uFEFFip machine"),
                            record.get("vlan id"),
                            record.get("ip reseau"),
                            record.get("passerelle"),
                            record.get("masque"),
                            record.get("DNS1"),
                            record.get("DNS2"),
                            record.get("NTP")));
        }
        return vlanType;
    }
    static class Vlan {
        String ipMachine;
        String vlanId;
        String ipReseau;
        String ipPasserelle;
        String ipMasque;
        String dns1;
        String dns2;
        String ntp;

        public String getIpMachine() {
            return ipMachine;
        }

        public String getVlanId() {
            return vlanId;
        }

        public String getIpReseau() {
            return ipReseau;
        }

        public String getIpPasserelle() {
            return ipPasserelle;
        }

        public String getIpMasque() {
            return ipMasque;
        }

        public String getDns1() {
            return dns1;
        }

        public String getDns2() {
            return dns2;
        }

        public String getNtp() {
            return ntp;
        }

        public Vlan(String ipMachine,
                    String vlanId,
                    String ipReseau,
                    String ipPasserelle,
                    String ipMasque,
                    String dns1,
                    String dns2,
                    String ntp) {
            this.ipMachine = ipMachine;
            this.vlanId = vlanId;
            this.ipReseau = ipReseau;
            this.ipPasserelle = ipPasserelle;
            this.ipMasque = ipMasque;
            this.dns1 = dns1;
            this.dns2 = dns2;
            this.ntp = ntp;
        }
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
                System.out.println("Choix non valide. Veuillez recommencer."); // retry
                currentNode = root;
            }
            else {
                System.out.println("Choix valide. Veuillez continuer."); // continue
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
