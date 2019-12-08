package com.company;


import java.util.ArrayList;

public class Graph {

    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final ArrayList<Edge> edges = new ArrayList<>();

    public Graph(){}

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void addEdge(Vertex x, Vertex y){
        Edge edge = new Edge(x, y);
        if(!edges.contains(edge)) {
            edges.add(edge);
            x.addEdges(edge);
        }
    }


    public void addVertex(Vertex v){
        if(!vertices.contains(v))
        {
            vertices.add(v);
        }
    }


    public ArrayList<Vertex> startBFS(){
        ArrayList<Vertex> vertexes = new ArrayList<>();
        for(int j = 0; j < vertices.size(); j++){
            boolean addRoot = true;
            for(int i = 0; i < edges.size(); i++){
                if(vertices.get(j).equals(edges.get(i).getTo())){
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