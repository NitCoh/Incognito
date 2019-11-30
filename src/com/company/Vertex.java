package com.company;

import java.util.ArrayList;

public class Vertex {
    private final String data;
    private ArrayList<Edge> edges = new ArrayList<>();
    private boolean satisfies = false;

    public Vertex(String data){
        this.data = data;
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


    public boolean equals(Object vertex){
        Vertex v = (Vertex) vertex;
        return data.hashCode() == v.data.hashCode();
    }

    public Vertex copy(){
        Vertex vertex = new Vertex(data);
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

    public String getGeneralization()
    {
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