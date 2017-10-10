package com.example.rafael.projeto_rafael.principal.grafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Nogueira
 */

public class GraphA {
    List<Link> linksLists =  new ArrayList<Link>();
    public GraphA()
    {
        linksLists.add(POA);
        linksLists.add(BLU);
        linksLists.add(FLO);
        linksLists.add(CUR);
        linksLists.add(LON);
        linksLists.add(SPO);
        linksLists.add(BAU);
        linksLists.add(CAM);
        linksLists.add(SJC);
        linksLists.add(RJO);
        linksLists.add(CPG);
        linksLists.add(RBP);
        linksLists.add(BHO);
        linksLists.add(CUI);
        linksLists.add(BSB);
        linksLists.add(SLV);
        linksLists.add(MAN);
        linksLists.add(BEL);
        linksLists.add(NTL);
        linksLists.add(REC);
    }
    public Link POA = new Link("POA");
    public Link BLU = new Link("BLU");
    public Link FLO = new Link("FLO");
    public Link CUR = new Link("CUR");
    public Link LON = new Link("LON");
    public Link SPO = new Link("SPO");
    public Link BAU = new Link("BAU");
    public Link CAM = new Link("CAM");
    public Link SJC = new Link("SJC");
    public Link RJO = new Link("RJO");
    public Link CPG = new Link("CPG");
    public Link RBP = new Link("RBP");
    public Link BHO = new Link("BHO");
    public Link CUI = new Link("CUI");
    public Link BSB = new Link("BSB");
    public Link SLV = new Link("SLV");
    public Link MAN = new Link("MAN");
    public Link BEL = new Link("BEL");
    public Link NTL = new Link("NTL");
    public Link REC = new Link("REC");
    public Vertex vertex01 = new Vertex(1, POA, FLO);
    public Vertex vertex02 = new Vertex(1, POA, BLU);
    public Vertex vertex03 = new Vertex(1, FLO, BLU);
    public Vertex vertex04 = new Vertex(1, FLO, CUR);
    public Vertex vertex05 = new Vertex(1, FLO, RJO);
    public Vertex vertex06 = new Vertex(1, BLU, CUR);
    public Vertex vertex07 = new Vertex(1, CUR, LON);
    public Vertex vertex08 = new Vertex(1, CUR, SPO);
    public Vertex vertex09 = new Vertex(1, LON, SPO);
    public Vertex vertex10 = new Vertex(1, LON, BAU);
    public Vertex vertex11 = new Vertex(1, SPO, RJO);
    public Vertex vertex12 = new Vertex(1, SPO, CAM);
    public Vertex vertex13 = new Vertex(1, SPO, SJC);
    public Vertex vertex14 = new Vertex(1, SJC, CAM);
    public Vertex vertex15 = new Vertex(1, RJO, SJC);
    public Vertex vertex16 = new Vertex(1, RJO, BHO);
    public Vertex vertex17 = new Vertex(1, RJO, SLV);
    public Vertex vertex18 = new Vertex(1, BHO, SJC);
    public Vertex vertex19 = new Vertex(1, BHO, BSB);
    public Vertex vertex20 = new Vertex(1, CAM, BAU);
    public Vertex vertex21 = new Vertex(1, CAM, RBP);
    public Vertex vertex22 = new Vertex(1, RBP, BSB);
    public Vertex vertex23 = new Vertex(1, BAU, CPG);
    public Vertex vertex24 = new Vertex(1,CPG, CUI);
    public Vertex vertex25 = new Vertex(1, CUI, MAN);
    public Vertex vertex26 = new Vertex(1, MAN, BEL);
    public Vertex vertex27 = new Vertex(1, BEL, NTL);
    public Vertex vertex28 = new Vertex(1, BSB, MAN);
    public Vertex vertex29 = new Vertex(1, BSB, NTL);
    public Vertex vertex30 = new Vertex(1, NTL, REC);
    public Vertex vertex31 = new Vertex(1, REC, SLV);
    public Vertex vertex32 = new Vertex(1, SLV, NTL);

    public Path bestPath = null;

    public Path getBestPath() {
        return bestPath;
    }

    public class Link {

        String Name;
        public List<Vertex> vertex = new ArrayList<Vertex>();

        public Link(String Name) {
            this.Name = Name;
        }

        public String toString() {
            return Name;
        }
    }

    public class Vertex {

        int weight;
        public Link start;
        public Link end;

        public Vertex(int weight, Link start, Link destination) {
            this.weight = weight;
            this.start = start;
            this.end = destination;

            start.vertex.add(this);
            destination.vertex.add(this);

        }
        @Override
        public String toString() {
            return start + " to " + end;
        }

    }
    public class Path {
        public List<Vertex> vertexList = new ArrayList<Vertex>();
        public int pathSize;
        @Override
        public String toString() {
            return "path  ";
        }
    }

    // Search a vertex in the list, by a name
    public Link searchSelectedPoint(String ponto) {
        Link tmp = null;
        for (int i = 0; i < linksLists.size(); i++)
        {
            if (linksLists.get(i).Name.equalsIgnoreCase(ponto))
            {
                tmp= linksLists.get(i);
            }
        }
        return tmp;
    }
    // This recursive function will remake  the path list for new destination   // obs: Needs to be updated.
    public void findBestPath(Link start, Link destination, Path path) {
        for (Vertex v : start.vertex) {
            Path newPath = new Path();
            if (path != null) {
                if(path.vertexList.contains(v))
                {
                    continue;
                }
                newPath.vertexList.addAll(path.vertexList);
                newPath.pathSize += path.pathSize;
            }
            newPath.vertexList.add(v);
            newPath.pathSize += v.weight;
            if (v.start.Name.equalsIgnoreCase(destination.Name) || v.end.Name.equalsIgnoreCase(destination.Name)) {
                //
                if (bestPath == null || bestPath.pathSize > newPath.pathSize) {
                    bestPath = newPath;
                }
                return;
            }
            Link novaLink = v.start.Name.equalsIgnoreCase(start.Name) ? v.end : v.start;
            findBestPath(novaLink, destination, newPath);

        }
    }
}
