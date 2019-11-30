package com.company;

public class Edge {

    Vertex to;
    Vertex from;

    public Edge(Vertex from, Vertex to){
        this.from = from;
        this.to = to;
    }

    public Vertex getAdjacentVertex(Vertex current){
        if(to.equals(current))
            return from;
        if(from.equals(current))
            return to;
        return null;
    }

    public boolean equals(Object edge){
        if(edge.getClass() != this.getClass()){
            return false;
        }
        Edge e = (Edge) edge;
        return to.equals(e.to) && from.equals(e.from);
    }

    public Edge copy(){
        return new Edge(from.copy(), to.copy());
    }

    public Vertex getTo(){
        return to;
    }

    public Vertex getFrom(){
        return from;
    }

    public String toString(){
        return from.toString() + " -> " + to.toString();
    }
}