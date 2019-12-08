package com.company;

import java.util.*;

public class Combinations {

    private Graph graph = new Graph();
    private Set<String> innerForbidden;

    public Combinations(Represantation[] arr,int r,Set<String> forbidden) {
        int n = arr.length;
        innerForbidden = createInnerForbidden(arr);
        createCombination(arr, n, r,forbidden);
        makeEdges();
    }



    public Graph getGraph(){
        return this.graph;
    }
    private Set<String> createInnerForbidden(Represantation[] arr){
        return new HashSet<>();
    }


    private static Represantation[] deepCopy(Represantation[] data){
        Represantation[] deepCopy = new Represantation[data.length];
        for(int i=0;i<data.length;++i){
            deepCopy[i] = new Represantation(data[i].getName(),data[i].getGen(),data[i].getType());
        }
        return deepCopy;
    }

    //If exist 1 node in data that in forbidden, it contains, we shall pass on the creation of this vertex
    private boolean contains(Set<String> forbidden, Represantation[] data){
        //If exist 1 argument in the forbidden set that the vertex contains it all, then return true
        Set<String> dataToString = new HashSet<>();
        for(Represantation r: data){
            if(r !=null)
                dataToString.add(r.toString());
        }
        for(String rep: forbidden){
            String[] ans = rep.split(",");
            boolean checker = false;
            for(String toCheck: ans){
                if(!dataToString.contains(toCheck)) {
                    checker = true;
                    break;
                }
            }
            if(!checker)
                return true;
        }
        return false;
    }

    private boolean checkSameTypes(Represantation[] arr){
        for(Represantation r: arr){
            if(r !=null) {
                for (Represantation x : arr) {
                    if (x != null && r!=x && r.getType()==x.getType())
                        return true;
                }
            }
        }
        return false;
    }


    // https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
    public void combinationUtil(Represantation arr[], Represantation data[], int start,
                                int end, int index, int r, Set<String> forbidden)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            if(checkSameTypes(data))
                return;
            if(this.contains(forbidden,data))
                return;
            makeNode(deepCopy(data));
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r,forbidden);
        }
    }

    private void makeNode(Represantation[] data) {
        Arrays.sort(data); // Sort by type.
        String nodeData="";
        for (int i=0; i<data.length; i++) {
            nodeData += data[i].getName()+",";
        }
        nodeData=nodeData.substring(0,nodeData.length()-1);
        Vertex v = new Vertex(nodeData,data);
        graph.addVertex(v);
    }


    public void createCombination(Represantation arr[], int n, int r,Set<String> forbidden) {

        Represantation data[] = new Represantation[r];

        combinationUtil(arr, data, 0, n - 1, 0, r,forbidden);
    }



    private void makeEdges() {
        ArrayList<Vertex> vertexes = graph.getVertices();
        for (int i=0; i<vertexes.size(); i++)
        {
            for (int j=i+1; j<vertexes.size(); j++)
            {
                if (vertexes.get(i).getHeight() + 1 == vertexes.get(j).getHeight() &&
                        vertexes.get(i).getGeneralization().equals(vertexes.get(j).getGeneralization()))
                {
                    graph.addEdge(vertexes.get(i),vertexes.get(j));
                }
            }
        }
    }

    public Set<String> BFS() {
        return null;
    }
}
