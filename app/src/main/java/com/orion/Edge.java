package com.orion;

/**
 * Created by Rafael Nogueira on 17/11/2017.
 */

public class Edge
{
    int weight_A_Metric;
    int weight_B_Metric;
    int weight_C_Metric;

    Node source;
    Node destination;

    public Edge(Node src ,Node dst, int a,int b,int c)
    {
        this.source = src;
        this.destination = dst;
        this.weight_A_Metric = a;
        this.weight_B_Metric = b;
        this.weight_C_Metric = c;
    }

    public Edge(Node src ,Node dst, int peso)
    {
        this.source = src;
        this.destination = dst;
    }


}
