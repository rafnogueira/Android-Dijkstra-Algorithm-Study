package com.example.rafael.projeto_rafael;

import com.example.rafael.projeto_rafael.principal.grafo.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends AppCompatActivity {

    String descRouteType = "";


    // Objetos da Interface
    Spinner spinnerStart;
    Spinner spinnerDestination;
    Button btnShow;
    ArrayAdapter<String> aOpcoes;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options);

        // capturando o spinner do xml pela id
        spinnerStart = (Spinner) findViewById(R.id.spnOrigem);
        spinnerStart.setAdapter(aOpcoes);

        spinnerDestination = (Spinner) findViewById(R.id.spnDestino);
        spinnerDestination.setAdapter(aOpcoes);

        // Entrada Spinner Origem e Destino
        spinnerStart = (Spinner) findViewById(R.id.spnOrigem);
        spinnerDestination = (Spinner) findViewById(R.id.spnDestino);

        // Entrada do Botão
        btnShow = (Button) findViewById(R.id.btnCalcular);


    }

    // Android xml and layout stuff and logic
    public void Calc(View view) {

        try {

            TextView response = (TextView) findViewById(R.id.txt_resp);
            int tprota = ((RadioGroup) findViewById(R.id.tipoRota)).getCheckedRadioButtonId();
            response.setText("Calculando... ");

            String msg = "";

            if (tprota == R.id.hops)
            {

                GraphA GraphTypeA = new GraphA();

                GraphTypeA.findBestPath(
                        GraphTypeA.searchSelectedPoint(spinnerStart.getSelectedItem().toString()),
                        GraphTypeA.searchSelectedPoint(spinnerDestination.getSelectedItem().toString()),null);

                descRouteType = "HOPS";

                msg += GraphTypeA.getBestPath().vertexList + "\n" + descRouteType + GraphTypeA.getBestPath().pathSize;

            } else if (tprota == R.id.dist) {

                GraphB GraphTypeB = new GraphB();
                GraphTypeB.findBestPath(
                        GraphTypeB.searchSelectedPoint(spinnerStart.getSelectedItem().toString()),
                        GraphTypeB.searchSelectedPoint(spinnerDestination.getSelectedItem().toString()),null);

                descRouteType = "Short Distance, Total :";
                msg += GraphTypeB.getBestPath().vertexList + "\n" + descRouteType + GraphTypeB.getBestPath().pathSize;

            } else if (tprota == R.id.custo) {
                GraphC GraphTypeC = new GraphC();
                GraphTypeC.findBestPath(
                        GraphTypeC.searchSelectedPoint(spinnerStart.getSelectedItem().toString()),
                        GraphTypeC.searchSelectedPoint(spinnerDestination.getSelectedItem().toString()),null);

                descRouteType = "Minor cost,Total:";

                msg += GraphTypeC.getBestPath().vertexList + "\n" + descRouteType + GraphTypeC.getBestPath().pathSize;
            }

            response.setText(msg);

        }catch (Exception ex){
            Mensagem(ex.getMessage());
        }
    }


    //Valores para Escolha Spinner
    public static final String[] options = {
            "BAU","BEL","BHO","BLU","BSB","CMP","CPG","CUI","CUR","FLO",
            "LON","MAN","NTL","POA","RBP","REC","RJO","SJC","SLV","SPO"
    };

    public void Mensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT).show();
    }
}
