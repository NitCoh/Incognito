package com.company;

import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.node;

public class Utils {

    private static String[] deriveNeighNodes(HashMap<String, List<String>> verToNei, String vertex){
        List<String> neighs = new ArrayList<>();
        for(String neigh : verToNei.get(vertex)){
            if(verToNei.keySet().contains(neigh))
                neighs.add(neigh);
        }
        String[] neighsArr = new String[neighs.size()];
        for(int i=0;i<neighs.size();++i){
            neighsArr[i] = neighs.get(i);
        }
        return neighsArr;
    }

    public static void createGraphPNG(HashMap<String,List<String>> verToNei,int iteration) {
        try {
            MutableGraph g = mutGraph("example1").setDirected(true).graphAttrs().add(RankDir.BOTTOM_TO_TOP);
            for (String vertex : verToNei.keySet()) {
                g.add(node(vertex).link(deriveNeighNodes(verToNei, vertex)));
            }
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("example/size" + iteration + ".png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

}
