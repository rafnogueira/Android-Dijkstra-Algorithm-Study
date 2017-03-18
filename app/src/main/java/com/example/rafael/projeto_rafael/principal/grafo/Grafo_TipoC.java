package com.example.rafael.projeto_rafael.principal.grafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class Grafo_TipoC {

    List<Aresta> listaArestas =  new ArrayList<Aresta>();
    public Grafo_TipoC()
    {
        listaArestas.add(POA);
        listaArestas.add(BLU);
        listaArestas.add(FLO);
        listaArestas.add(CUR);
        listaArestas.add(LON);
        listaArestas.add(SPO);
        listaArestas.add(BAU);
        listaArestas.add(CAM);
        listaArestas.add(SJC);
        listaArestas.add(RJO);
        listaArestas.add(CPG);
        listaArestas.add(RBP);
        listaArestas.add(BHO);
        listaArestas.add(CUI);
        listaArestas.add(BSB);
        listaArestas.add(SLV);
        listaArestas.add(MAN);
        listaArestas.add(BEL);
        listaArestas.add(NTL);
        listaArestas.add(REC);
    }

    public Aresta POA = new Aresta("POA");
    public Aresta BLU = new Aresta("BLU");
    public Aresta FLO = new Aresta("FLO");
    public Aresta CUR = new Aresta("CUR");
    public Aresta LON = new Aresta("LON");
    public Aresta SPO = new Aresta("SPO");
    public Aresta BAU = new Aresta("BAU");
    public Aresta CAM = new Aresta("CAM");
    public Aresta SJC = new Aresta("SJC");
    public Aresta RJO = new Aresta("RJO");
    public Aresta CPG = new Aresta("CPG");
    public Aresta RBP = new Aresta("RBP");
    public Aresta BHO = new Aresta("BHO");
    public Aresta CUI = new Aresta("CUI");
    public Aresta BSB = new Aresta("BSB");
    public Aresta SLV = new Aresta("SLV");
    public Aresta MAN = new Aresta("MAN");
    public Aresta BEL = new Aresta("BEL");
    public Aresta NTL = new Aresta("NTL");
    public Aresta REC = new Aresta("REC");

    public Vertice vertice01 = new Vertice(2   , POA, FLO);
    public Vertice vertice02 = new Vertice( 2  , POA, BLU);
    public Vertice vertice03 = new Vertice(  3 , FLO, BLU);
    public Vertice vertice04 = new Vertice(  5 , FLO, CUR);
    public Vertice vertice05 = new Vertice(  10 ,  FLO, RJO);
    public Vertice vertice06 = new Vertice(   5, BLU, CUR);
    public Vertice vertice07 = new Vertice(  2 , CUR, LON);
    public Vertice vertice08 = new Vertice(  10 , CUR, SPO);
    public Vertice vertice09 = new Vertice(  2 , LON, SPO);
    public Vertice vertice10 = new Vertice(  2 , LON, BAU);
    public Vertice vertice11 = new Vertice(  15 , SPO, RJO);
    public Vertice vertice12 = new Vertice(  7 , SPO, CAM);
    public Vertice vertice13 = new Vertice(  16 , SPO, SJC);
    public Vertice vertice14 = new Vertice(   10, SJC, CAM);
    public Vertice vertice15 = new Vertice(   10, RJO, SJC);
    public Vertice vertice16 = new Vertice( 6  , RJO, BHO);
    public Vertice vertice17 = new Vertice( 6  ,  RJO, SLV);
    public Vertice vertice18 = new Vertice( 8  , BHO, SJC);
    public Vertice vertice19 = new Vertice( 6  , BHO, BSB);
    public Vertice vertice20 = new Vertice( 6  , CAM, BAU);
    public Vertice vertice21 = new Vertice(  4 , CAM, RBP);
    public Vertice vertice22 = new Vertice(  4 , RBP, BSB);
    public Vertice vertice23 = new Vertice(  3 ,  BAU, CPG);
    public Vertice vertice24 = new Vertice(  2 , CPG, CUI);
    public Vertice vertice25 = new Vertice(  3 ,  CUI, MAN);
    public Vertice vertice26 = new Vertice(  2 ,  MAN, BEL);
    public Vertice vertice27 = new Vertice(  3 ,  BEL, NTL);
    public Vertice vertice28 = new Vertice(  6 ,  BSB, MAN);
    public Vertice vertice29 = new Vertice( 7 ,  BSB, NTL);
    public Vertice vertice30 = new Vertice( 3  , NTL, REC);
    public Vertice vertice31 = new Vertice( 5  , REC, SLV);
    public Vertice vertice32 = new Vertice( 4  ,  SLV, NTL);

    public Caminho caminhoOptmizado = null;

    public Caminho getCaminhoOptmizado() {
        return caminhoOptmizado;
    }



    public class Aresta {

        String Nome;
        public List<Vertice> vertices = new ArrayList<Vertice>();

        public Aresta(String Nome) {
            this.Nome = Nome;
        }

        public String toString() {
            return Nome;
        }
    }

    public class Vertice {

        int peso;
        public Aresta inicio;
        public Aresta destino;

        public Vertice(int peso, Aresta inicio, Aresta destino) {
            this.peso = peso;
            this.inicio = inicio;
            this.destino = destino;

            inicio.vertices.add(this);
            destino.vertices.add(this);

        }
        @Override
        public String toString() {
            return inicio + " para " + destino;
        }

    }
    public class Caminho {

        public List<Vertice> vertices = new ArrayList<Vertice>();

        public int tamanhoCaminho;

        @Override
        public String toString() {
            return "caminho ";
        }

    }

    public Aresta PesquisaPontoSelecionado(String ponto) {

        Aresta tmp = null;

        for (int i = 0; i < listaArestas.size(); i++)
        {
            if (listaArestas.get(i).Nome.equalsIgnoreCase(ponto))
            {
                tmp= listaArestas.get(i);
            }
        }

        return tmp;
    }


    public void EncontreMenorCaminho(Aresta origem, Aresta destino, Caminho caminho) {
        for (Vertice v : origem.vertices) {
            Caminho novoCaminho = new Caminho();
            if (caminho != null) {
                if(caminho.vertices.contains(v))
                {
                    continue;
                }

                novoCaminho.vertices.addAll(caminho.vertices);
                novoCaminho.tamanhoCaminho += caminho.tamanhoCaminho;
            }
            novoCaminho.vertices.add(v);
            novoCaminho.tamanhoCaminho += v.peso;
            if (v.inicio.Nome.equalsIgnoreCase(destino.Nome) || v.destino.Nome.equalsIgnoreCase(destino.Nome)) {
                //
                if (caminhoOptmizado == null || caminhoOptmizado.tamanhoCaminho > novoCaminho.tamanhoCaminho) {
                    caminhoOptmizado = novoCaminho;
                }
                return;
            }

            Aresta novaAresta = v.inicio.Nome.equalsIgnoreCase(origem.Nome) ? v.destino : v.inicio;
            EncontreMenorCaminho(novaAresta, destino, novoCaminho);

        }
    }
}
