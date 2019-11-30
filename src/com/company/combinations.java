package com.company;

import java.util.ArrayList;

public class combinations {
    public static Graph graph = new Graph();
    // https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
    static void combinationUtil(String arr[], String data[], int start,
                                int end, int index, int r)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            makeNodes(data);
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r);
        }
    }

    private static void makeNodes(String[] data) {
        String nodeData="";
        for (int i=0; i<data.length; i++)
        {
            nodeData+=data[i]+",";
        }
        nodeData=nodeData.substring(0,nodeData.length()-1);
        Vertex v = new Vertex(nodeData);
        String s = v.getGeneralization();
        int[] count= new int[256];
        for (int i=0; i<s.length(); i++)
        {
            count[s.charAt(i)-'A']++;
        }
        boolean toAdd=true;
        for (int i=0; i<count.length; i++)
        {
            if (count[i]>1) {
                toAdd = false;
                break;
            }
        }
        if (toAdd)
        {
            graph.addVertex(v);
        }
    }


    static void printCombination(String arr[], int n, int r) {

        String data[] = new String[r];

        combinationUtil(arr, data, 0, n - 1, 0, r);
    }


    public static void main(String[] args) {
        String arr[] = {"S0","S1","Z0","Z1","Z2","B0","B1"};
        int r = 2;
        int n = arr.length;
        printCombination(arr, n, r);
        makeEdges();
    }

    private static void makeEdges() {
        ArrayList<Vertex> vertexes = graph.getVertices();
        for (int i=0; i<vertexes.size(); i++)
        {
            for (int j=i+1; j<vertexes.size(); j++)
            {
                if (vertexes.get(i).getHeight() + 1 == vertexes.get(j).getHeight() && vertexes.get(i).getGeneralization().equals(vertexes.get(j).getGeneralization()))
                {
                    graph.addEdge(vertexes.get(i),vertexes.get(j));
                }
            }
        }
        graph.printOut();
        System.out.println(graph.startBFS());
    }
}
