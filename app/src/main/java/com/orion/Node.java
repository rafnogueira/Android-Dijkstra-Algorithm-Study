package com.orion;

import android.support.annotation.NonNull;

import java.util.LinkedList;

/**
 * Created by rafael on 17/11/2017.
 */

public class Node  implements Comparable
{

    boolean active = true;
    boolean visited = false;
    private int index;
    private int distance = Integer.MAX_VALUE;
    private String name;
    private Node previous;
    private LinkedList<Edge> listAdj;

    public Node(){

    }

    public Node(String name , int index)
    {
        this.active = true;
        this.name = name ;
        this.index = index;
        listAdj = new LinkedList<>(); // Starts with empty adj list
    }


    public void addEdge(Node node , int weight)
    {
        this.listAdj.add(new Edge(this,node,weight));
    }

    // Fucntion for add 3 weights
    public void addEdge(Node node,int weight_A, int weight_B,int weight_C)
    {
        //add link to the edges
        this.listAdj.add(new Edge(this,node, weight_A, weight_B, weight_C));
    }

    public String isActive()
    {
        return active ?  "Active" : "Not Active";
    }

    public boolean checkActive(){
        return this.active;
    }

    public void disable()
    {
        this.active = false;
    }

    public void visit()
    {
        this.visited = true;
    }

    public boolean isVisited()
    {
        return this.visited;
    }


//    public int compareTo(@NonNull Object o)
    @Override
    public int compareTo(Object o)
    {
        Node node_tmp = (Node) o;
        if(!this.active)
        {
            this.distance =  Integer.MAX_VALUE;
        }
        if(this.distance < node_tmp.distance){
            return -1;
        }else if (this.distance  == node_tmp.distance){
            return 0;
        }

        return 1;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public LinkedList<Edge> getListAdj() {
        return listAdj;
    }

    public void setListAdj(LinkedList<Edge> listAdj) {
        this.listAdj = listAdj;
    }


}
