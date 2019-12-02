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
            e.to.setMarked(true);
            e.to.setSatisfies(true);
        }
    }

    public List<Vertex> getDirectNeighbours(){
        List<Vertex> neigh = new ArrayList<>();
        for(Edge e: this.edges){
            neigh.add(e.to);
        }
        return neigh;
    }

    public void addEdges(Edge edge){
        if(!edges.contains(edge)){
            edges.add(edge);
        }
    }


    public void removeEdges(Edge edge){
        if(edges.contains(edge)){
            edges.remove(edge);
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

    public int getNumOfEdges(){
        return edges.size();
    }

    public ArrayList<Edge> getEdges(){
        return edges;
    }

    public boolean isSatisfies(){
        return satisfies;
    }

    public void setSatisfies(boolean val){
        satisfies = val;
    }

    public boolean isRoot(){
        return edges.isEmpty();
    }


    public static String[] getNames(Represantation[] arr){
        String[] ans = new String[arr.length];
        for(int i=0;i<arr.length;++i){
            ans[i] = arr[i].getName();
        }
        return ans;
    }

    public boolean equals(Object vertex){
        Vertex v = (Vertex) vertex;
        return data.hashCode() == v.data.hashCode();
    }

    public Vertex copy(){
        Vertex vertex = new Vertex(data,this.nodes);
        for(int i = 0; i < edges.size(); i++){
            vertex.edges.add(new Edge (vertex, edges.get(i).getTo()));
        }
        return vertex;
    }

    public Vertex getAdjacentVertex(Edge edge){
        return edge.getAdjacentVertex(this);
    }


    public int getHeight(){
        String[] arr = data.split(",");
        int height = 0;
        for(int i = 0; i < arr.length; i++){
            height += Integer.parseInt(arr[i].substring(arr[i].indexOf(" ") + 2));
        }
        return height;
    }

    public ArrayList<Vertex> getDirectGeneralizations(ArrayList<Vertex> generalizations){
        for(int i = 0; i < edges.size(); i++){
            Vertex v = edges.get(i).getTo();
            if(generalizations.contains(v) == false)
            {
                generalizations.add(edges.get(i).getTo());
            }
        }
        return generalizations;
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