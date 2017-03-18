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


public class Principal extends AppCompatActivity {

    String desc_tipo_rota = "";


    // Objetos da Interface
    Spinner spinnerOrigem;
    Spinner spinnerDestino;
    Button mostrar;
    ArrayAdapter<String> aOpcoes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        aOpcoes = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, opcoes);

        // capturando o spinner do xml pela id
        spinnerOrigem = (Spinner) findViewById(R.id.spnOrigem);
        spinnerOrigem.setAdapter(aOpcoes);

        spinnerDestino = (Spinner) findViewById(R.id.spnDestino);
        spinnerDestino.setAdapter(aOpcoes);

        // Entrada Spinner Origem e Destino
        spinnerOrigem = (Spinner) findViewById(R.id.spnOrigem);
        spinnerDestino = (Spinner) findViewById(R.id.spnDestino);

        // Entrada do Botão
        mostrar = (Button) findViewById(R.id.btnCalcular);


    }


    public void Calc(View view) {

        try {

            TextView resposta = (TextView) findViewById(R.id.txt_resp);
            int tprota = ((RadioGroup) findViewById(R.id.tipoRota)).getCheckedRadioButtonId();
            resposta.setText("Calculando... ");

            String msg = "";

            if (tprota == R.id.hops)
            {

                Grafo_TipoA grafoTipoA = new Grafo_TipoA();

                grafoTipoA.EncontreMenorCaminho(
                        grafoTipoA.PesquisaPontoSelecionado(spinnerOrigem.getSelectedItem().toString()),
                        grafoTipoA.PesquisaPontoSelecionado(spinnerDestino.getSelectedItem().toString()),null);

                desc_tipo_rota= "HOPS";

                msg += grafoTipoA.getCaminhoOptmizado().vertices + "\n" +desc_tipo_rota + grafoTipoA.getCaminhoOptmizado().tamanhoCaminho;

            } else if (tprota == R.id.dist) {

                Grafo_TipoB grafoTipoB = new Grafo_TipoB();
                grafoTipoB.EncontreMenorCaminho(
                        grafoTipoB.PesquisaPontoSelecionado(spinnerOrigem.getSelectedItem().toString()),
                        grafoTipoB.PesquisaPontoSelecionado(spinnerDestino.getSelectedItem().toString()),null);

                desc_tipo_rota= "Menor Distância, Total Percorrido:";
                msg += grafoTipoB.getCaminhoOptmizado().vertices + "\n" +desc_tipo_rota + grafoTipoB.getCaminhoOptmizado().tamanhoCaminho;

            } else if (tprota == R.id.custo) {


                Grafo_TipoC grafoTipoC = new Grafo_TipoC();

                grafoTipoC.EncontreMenorCaminho(
                        grafoTipoC.PesquisaPontoSelecionado(spinnerOrigem.getSelectedItem().toString()),
                        grafoTipoC.PesquisaPontoSelecionado(spinnerDestino.getSelectedItem().toString()),null);

                desc_tipo_rota= "Menor Custo , Total Gasto:";

                msg += grafoTipoC.getCaminhoOptmizado().vertices + "\n" +desc_tipo_rota + grafoTipoC.getCaminhoOptmizado().tamanhoCaminho;
            }


            resposta.setText(msg);

        }catch (Exception ex){
            Mensagem(ex.getMessage());
        }
    }


    //Valores para Escolha Spinner
    public static final String[] opcoes = {
            "BAU","BEL","BHO","BLU","BSB","CMP","CPG","CUI","CUR","FLO",
            "LON","MAN","NTL","POA","RBP","REC","RJO","SJC","SLV","SPO"
    };

    public void Mensagem(String msg)
    {
        Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT).show();
    }
}
