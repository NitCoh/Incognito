package com.company;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String data;
    private ArrayList<Edge> edges = new ArrayList<>();
    private boolean satisfies = false;
    private boolean marked = false;
    private Represantation[] nodes;

    public Vertex(String data, Represantation[] arr){
        this.data = data;
        this.nodes = arr;
    }

    public Represantation[] getNodes() {
        return nodes;
    }

    public List<String> neighToString(){
        List<Vertex> neigh = this.getDirectNeighbours();
        List<String> neighToStr = new ArrayList<>();
        for(Vertex v: neigh){
            neighToStr.add(v.toString());
        }
        return neighToStr;
    }

    public void markAndSatisfyNeighbours(){
        for(Edge e: this.edges){
            e.getTo().setMarked(true);
            e.getTo().setSatisfies(true);
        }
    }

    public List<Vertex> getDirectNeighbours(){
        List<Vertex> neigh = new ArrayList<>();
        for(Edge e: this.edges){
            neigh.add(e.getTo());
        }
        return neigh;
    }

    public void addEdges(Edge edge){
        if(!edges.contains(edge)){
            edges.add(edge);
        }
    }


    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }


    public String getData(){
        return data;
    }


    public boolean isSatisfies(){
        return satisfies;
    }

    public void setSatisfies(boolean val){
        satisfies = val;
    }



    public boolean equals(Object vertex){
        Vertex v = (Vertex) vertex;
        return data.hashCode() == v.data.hashCode();
    }


    public int getHeight(){
        String[] arr = data.split(",");
        int height = 0;
        for(int i = 0; i < arr.length; i++){
            height += Integer.parseInt(arr[i].substring(arr[i].indexOf(" ") + 2));
        }
        return height;
    }


    public String getGeneralization() {
        String toReturn = "";
        String[] arr = data.split(",");
        for(int i = 0; i < arr.length; i++){
            toReturn += arr[i].substring(0, 1);
        }
        return toReturn;
    }

    public String toString(){
        return this.getData();
    }
}