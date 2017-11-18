package com.orion;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rafael on 17/11/2017.
 */

public class Graph {

  private List<Node> nodes;
  private int size;

  public Graph(){};

  public String printNodes(List<Node> nodes)
  {
      String result  = null;
      for(Node n : nodes)
      {
          result +=  n.getName()+"/" + n.getDistance() + "->";
      }

      return result;
  }

    public void addNode(Node node) {

        if (nodes.contains(node)) {

            Toast.makeText(null," This node is already added", Toast.LENGTH_LONG);

            //JOptionPane.showMessageDialog(null, "Nó adicionado já existe");
        } else {
            this.nodes.add(node);
            this.size = nodes.size();
        }

    }

  public void updateNodeList(List<Node> newNodeList)
  {
      this.nodes = newNodeList;
  }

    public List<Node>  findShortestPathB(Node n1 , Node n2)
    {
        List<Node> shortestPath  = new ArrayList<Node>();
        List<Node> notVisited = new ArrayList<Node>();

        Node linkPath  = new Node();
        Node actualEdge  = new Node();
        Node neighbor  = new Node();

        shortestPath.add(n1);

        for(int i = 0; i < this.nodes.size(); i++)
        {
            if(this.nodes.get(i).getName().equals(n1.getName()))
            {
                this.nodes.get(i).setDistance(0);
            }else{
                this.nodes.get(i).setDistance(Integer.MAX_VALUE);
            }
            notVisited.add(this.nodes.get(i));
        }

        Collections.sort(notVisited);
        while(!notVisited.isEmpty())
        {
            actualEdge = notVisited.get(0);
            //Adquirindo vértice
            for(int i = 0; i < actualEdge.getListAdj().size(); i++)
            {//Verifica vizinhos
                neighbor = actualEdge.getListAdj().get(i).destination;
                if(!neighbor.isVisited())
                {

                    if(neighbor.getDistance() > ( actualEdge.getDistance()+actualEdge.getListAdj().get(i).weight_B_Metric ))
                    {
                        neighbor.setDistance(actualEdge.getDistance()+actualEdge.getListAdj().get(i).weight_B_Metric);
                        neighbor.setPrevious(actualEdge);
                        if(neighbor == n2)
                        {
                            shortestPath.clear();
                            linkPath = neighbor;
                            shortestPath.add(neighbor);
                            while(linkPath.getPrevious() != null)
                            {
                                shortestPath.add(linkPath.getPrevious());
                                linkPath = linkPath.getPrevious();
                            }
                            Collections.sort(shortestPath);
                        }
                    }
                }
            }

            actualEdge.visited = true;
            notVisited.remove(actualEdge);
            Collections.sort(notVisited);
        }
        return shortestPath;

    }

}
