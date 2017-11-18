package com.orion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafael.projeto_rafael.R;

import java.util.ArrayList;
import java.util.List;


public class Main extends AppCompatActivity implements View.OnClickListener {

    String descRouteType = "";


    // Objetos da Interface
    private Spinner spinnerStartSelection;
    private Spinner spinnerDestinationSelection;
    private Spinner spinnerDisabledNodes;

    private Button btnMakePath;
    private ArrayAdapter<String> optionsAdapter;

    // The new code
    private Graph graph = null;
    private List<Node> nodes = null;     //Nós que serão mandados para o graph
    private static int MAX_NODES_ALLOWED = 20;
    private int disabledNodes[];

    //Nós
    private Node MAN;
    private Node BEL;
    private Node NTL;
    private Node REC;
    private Node SLV;
    private Node BSB;
    private Node CUI;
    private Node CPG;
    private Node BAU;
    private Node RBP;
    private Node CMP;
    private Node BHO;
    private Node LON;
    private Node SPO;
    private Node RJO;
    private Node SJC;
    private Node CUR;
    private Node BLU;
    private Node FLO;
    private Node POA;
    //Valores para Escolha Spinner

    private static final String[] options = {
            "MAN",
            "BEL",
            "NTL",
            "REC",
            "SLV",
            "BSB",
            "CUI",
            "CPG",
            "BAU",
            "RBP",
            "CMP",
            "BHO",
            "LON",
            "SPO",
            "RJO",
            "SJC",
            "CUR",
            "BLU",
            "FLO",
            "POA"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        optionsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options);

        // capturando o spinner do xml pela id
        spinnerStartSelection = (Spinner) findViewById(R.id.spinnerSource);
        spinnerStartSelection.setAdapter(optionsAdapter);

        spinnerDestinationSelection = (Spinner) findViewById(R.id.spinnerDestination);
        spinnerDestinationSelection.setAdapter(optionsAdapter);

        spinnerDisabledNodes = (Spinner) findViewById(R.id.spinnerDisabler);
        spinnerDisabledNodes.setAdapter(optionsAdapter);


        // Entrada do Botão
        btnMakePath = (Button) findViewById(R.id.btnMakePath);
        btnMakePath.setOnClickListener(this);


        graph = new Graph();
        nodes = new ArrayList<Node>();
        disabledNodes = new int[MAX_NODES_ALLOWED];
        for (int i = 0; i < disabledNodes.length; i++) {
            // if the nod is zero so its enabled,
            // 1 for disabled, this litle array control
            // needs to be syncronized with spinner list and nodes in the graph
            disabledNodes[i] = 0;
        }

        //reloadGraph();

    }

    public void reloadGraph() {
        graph = new Graph();
        nodes = new ArrayList<>();
        //Nó -> Nome do Nö ,  index dentro do vetor;

        //  nA.addEdge(nB, 1);
        //add nodes with links in graph
        MAN = new Node("MAN", 0);
        BEL = new Node("BEL", 1);
        NTL = new Node("NTL", 2);
        REC = new Node("REC", 3);
        SLV = new Node("SLV", 4);
        BSB = new Node("BSB", 5);
        CUI = new Node("CUI", 6);
        CPG = new Node("CPG", 7);
        BAU = new Node("BAU", 8);
        RBP = new Node("RBP", 9);
        CMP = new Node("CMP", 10);
        BHO = new Node("BHO", 11);
        LON = new Node("LON", 12);
        SPO = new Node("SPO", 13);
        RJO = new Node("RJO", 14);
        SJC = new Node("SJC", 15);
        CUR = new Node("CUR", 16);
        BLU = new Node("BLU", 17);
        FLO = new Node("FLO", 18);
        POA = new Node("POA", 19);

        //create links // node, destination , metric :A B C
        MAN.addEdge(BEL, 1, 18, 2);
        MAN.addEdge(BSB, 1, 22, 6);
        MAN.addEdge(CUI, 1, 20, 3);

        BEL.addEdge(NTL, 1, 21, 3);
        BEL.addEdge(MAN, 1, 18, 2);

        NTL.addEdge(BEL, 1, 21, 3);
        NTL.addEdge(REC, 1, 4, 3);
        NTL.addEdge(SLV, 1, 15, 4);
        NTL.addEdge(BSB, 1, 22, 7);

        REC.addEdge(NTL, 1, 4, 3);
        REC.addEdge(SLV, 1, 8, 5);

        SLV.addEdge(NTL, 1, 15, 4);
        SLV.addEdge(REC, 1, 8, 5);
        SLV.addEdge(RJO, 1, 20, 6);

        BSB.addEdge(MAN, 1, 22, 6);
        BSB.addEdge(NTL, 1, 22, 7);
        BSB.addEdge(BHO, 1, 9, 6);
        BSB.addEdge(RBP, 1, 8, 4);

        CUI.addEdge(MAN, 1, 20, 3);
        CUI.addEdge(CPG, 1, 8, 2);

        CPG.addEdge(CUI, 1, 8, 2);
        CPG.addEdge(BAU, 1, 10, 3);

        BAU.addEdge(CPG, 1, 10, 3);
        BAU.addEdge(LON, 1, 3, 2);
        BAU.addEdge(CMP, 1, 3, 6);

        RBP.addEdge(BSB, 1, 8, 4);
        RBP.addEdge(CMP, 1, 2, 4);

        CMP.addEdge(RBP, 1, 2, 4);
        CMP.addEdge(SJC, 1, 2, 10);
        CMP.addEdge(BAU, 1, 3, 6);
        CMP.addEdge(SPO, 1, 1, 7);

        BHO.addEdge(BSB, 1, 9, 6);
        BHO.addEdge(RJO, 1, 7, 6);
        BHO.addEdge(SJC, 1, 7, 8);

        LON.addEdge(BAU, 1, 3, 2);
        LON.addEdge(SPO, 1, 7, 2);
        LON.addEdge(CUR, 1, 6, 2);

        SPO.addEdge(LON, 1, 7, 2);
        SPO.addEdge(CUR, 1, 5, 10);
        SPO.addEdge(CMP, 1, 1, 7);
        SPO.addEdge(SJC, 1, 2, 16);
        SPO.addEdge(RJO, 1, 5, 15);

        RJO.addEdge(FLO, 1, 12, 10);
        RJO.addEdge(SPO, 1, 5, 15);
        RJO.addEdge(SJC, 1, 3, 10);
        RJO.addEdge(BHO, 1, 7, 6);
        RJO.addEdge(SLV, 1, 20, 6);

        SJC.addEdge(SPO, 1, 2, 16);
        SJC.addEdge(CMP, 1, 2, 10);
        SJC.addEdge(BHO, 1, 7, 8);
        SJC.addEdge(RJO, 1, 3, 10);

        CUR.addEdge(LON, 1, 6, 2);
        CUR.addEdge(SPO, 1, 5, 10);
        CUR.addEdge(FLO, 1, 2, 5);
        CUR.addEdge(BLU, 1, 2, 5);

        BLU.addEdge(CUR, 1, 2, 5);
        BLU.addEdge(FLO, 1, 1, 3);
        BLU.addEdge(POA, 1, 7, 2);

        FLO.addEdge(CUR, 1, 2, 5);
        FLO.addEdge(BLU, 1, 1, 3);
        FLO.addEdge(RJO, 1, 12, 10);
        FLO.addEdge(POA, 1, 6, 2);

        POA.addEdge(FLO, 1, 6, 2);
        POA.addEdge(BLU, 1, 7, 2);

        nodes.add(MAN);
        nodes.add(BEL);
        nodes.add(NTL);
        nodes.add(REC);
        nodes.add(SLV);
        nodes.add(BSB);
        nodes.add(CUI);
        nodes.add(CPG);
        nodes.add(BAU);
        nodes.add(RBP);
        nodes.add(CMP);
        nodes.add(BHO);
        nodes.add(LON);
        nodes.add(SPO);
        nodes.add(RJO);
        nodes.add(SJC);
        nodes.add(CUR);
        nodes.add(BLU);
        nodes.add(FLO);
        nodes.add(POA);

        for (int i = 0; i < nodes.size(); i++) {
            if (disabledNodes[i] == 1) {
                nodes.get(i).disable();
            }
        }
        graph.updateNodeList(nodes);
    }

    // Android xml and layout
    public void calculateShortestPath() {
        reloadGraph();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupRouteType);
//        radioGroup.setOnCheckedChangeListener(this);

        switch (radioGroup.getCheckedRadioButtonId()) {
//            case R.id.heuristics_A:
//                graph.encontrarCaminhoMetricaB();
//                break;
//            case R.id.heuristics_B:
//                graph.encontrarCaminhoMetricaB();
//                break;
//            case R.id.heuristics_C:
//                graph.encontrarCaminhoMetricaB();
//                break;

            default:
                break;
        }

    }
//    public void calcularDistancia() {
//
//        reloadGraph();
//        //Will return a list of nodes
//        List<Node> path = graph.findShortestPathB(transformSelectionSrcNo(), transformSelectionDstNo());
//
//
//    }
//
//    public void calcularCusto() {
//        // 2 para fazer rota pelo custo
//
//        recarregarGrafo();
//        List<No> caminho = grafo.encontrarCaminhoMetricaC(transformSelectionSrcNo(), transformSelectionDstNo());
//        criarAnimacao(caminho, 2);
//
//    }
//
//    // ; )
//    public No transformSelectionDstNo() {
//        return nos.get(this.cbDestino.getSelectedIndex());
//    }
//
//    // :)
//    public No transformSelectionSrcNo() {
//        return nos.get(this.cbInicio.getSelectedIndex());
//    }
//
//    public void desabilitarNo()
//    {
//        NosDesabilitados[cbHabilitado.getSelectedIndex()] = 1;
//    }
//    public void habilitarNo()
//    {
//        NosDesabilitados[cbHabilitado.getSelectedIndex()] = 0;
//    }


    //Buttons click event listeners
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnMakePath) {
            calculateShortestPath();
        }
    }

    public void MessageToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

}
