package com.company;

public class Edge {

    private Vertex to;
    private Vertex from;

    public Edge(Vertex from, Vertex to){
        this.from = from;
        this.to = to;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public boolean equals(Object edge){
        if(edge.getClass() != this.getClass()){
            return false;
        }
        Edge e = (Edge) edge;
        return to.equals(e.to) && from.equals(e.from);
    }



    public String toString(){
        return from.toString() + " -> " + to.toString();
    }
}