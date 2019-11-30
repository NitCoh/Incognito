package com.company;


import java.util.ArrayList;

public class Graph {

    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final ArrayList<Edge> edges = new ArrayList<>();

    public Graph(){}

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Graph copy(){
        Graph graph = new Graph();
        for(int i = 0; i < vertices.size(); i++ ){
            graph.vertices.add(vertices.get(i));
        }
        for(int i = 0; i < edges.size(); i++ ){
            graph.edges.add(edges.get(i));
        }
        return graph;
    }

    public int numOfEdges(){
        return edges.size();
    }

    public int numOfVertices(){
        return vertices.size();
    }

    public void addEdge(Vertex x, Vertex y){
        Edge edge = new Edge(x, y);
        if(!edges.contains(edge)) {
            edges.add(edge);
            x.addEdges(edge);
        }
    }

    public void addEdge(Edge edge){
        if(!edges.contains(edge))
        {
            edges.add(edge);
            edge.from.addEdges(edge);
        }
    }

    public void addVertex(Vertex v){
        if(!vertices.contains(v))
        {
            vertices.add(v);
        }
    }

    public void removeEdge(Edge e){
        if(edges.contains(e)){
            edges.remove(e);
        }
        e.from.getEdges().remove(e);
    }

    public void removeVertex(Vertex v){
        vertices.remove(v);
        for(int i = 0; i < v.getNumOfEdges(); i++){
            removeEdge(v.getEdges().get(i));
        }
    }

    public Vertex getVertex(int index){
        return vertices.get(index);
    }

    public void printOut(){
        for(int i = 0; i < vertices.size(); i++){
            for(int j = 0; j < vertices.get(i).getNumOfEdges(); j++){
                System.out.println(vertices.get(i).getData() + " -> "
                        + vertices.get(i).getEdges().get(j).getAdjacentVertex(vertices.get(i)).getData());
            }
            System.out.println();
        }
    }


    public Vertex hasVertex(Vertex v){
        for(int i = 0; i < vertices.size(); i++){
            if(vertices.get(i).equals(v)){
                return vertices.get(i);
            }
        }
        return null;
    }

    public ArrayList<Vertex> startBFS(){
        ArrayList<Vertex> vertexes = new ArrayList<>();
        for(int j = 0; j < vertices.size(); j++){
            boolean addRoot = true;
            for(int i = 0; i < edges.size(); i++){
                if(vertices.get(j).equals(edges.get(i).to)){
                    addRoot = false;
                }
            }
            if(addRoot){
                vertexes.add(vertices.get(j));
            }

        }
        return vertexes;
    }

}