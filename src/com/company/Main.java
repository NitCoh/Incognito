package com.company;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static guru.nidi.graphviz.attribute.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {

    public static void main(String[] args) {
        //Giving the indexes of the QIs in the csv file.
        List<Integer> setOfQI = new ArrayList<>();
        setOfQI.add(3);
        setOfQI.add(7);
        setOfQI.add(14);
        List<Represantation> gens = buildRepres(); // Build the generalization functions
        int qiSize = 3;
        int k=10;
        int numOfAttsExpected = 90; //Expected for each record in the db to have X features in order to avoid "bad" records".
        Incognito inc = new Incognito("data.csv", setOfQI,numOfAttsExpected,k,gens,qiSize,buildDummyHash(gens));
        HashMap<String,List<String>> verToNei = new HashMap<>();
        for(Vertex v : inc.mainAlgorithm()) {
            System.out.println(v.toString());
            verToNei.put(v.toString(),v.neighToString());
        }
            Utils.createGraphPNG(verToNei,qiSize+1);
    }



    private static HashMap<String,String> buildDummyHash(List<Represantation> gens){
        HashMap<String,String> dummyHash = new HashMap<>();
        List<HashMap<String,String>> generator = new ArrayList<>();
        for(Represantation r: gens){
            generator.add(r.getGen());
        }
        for(HashMap<String,String> gen : generator){
            for(String val : gen.values())
                dummyHash.put(val,"null");
        }
        return dummyHash;
    }

    private static List<Represantation> buildRepres(){
        List<Represantation> ans = new ArrayList<>();

        HashMap<String,String> f0 = new HashMap<>();
        f0.put("Right","Right");
        f0.put("Left","Left");
        ans.add(new Represantation("F0",f0,QI.FOOT));
        HashMap<String,String> f1 = new HashMap<>();
        f1.put("Right","Foot");
        f1.put("Left","Foot");
        ans.add(new Represantation("F1",f1,QI.FOOT));

        HashMap<String,String> a0 = new HashMap<>();
        for(int i=0;i<=50;++i){
                a0.put(Integer.toString(i),Integer.toString(i));
        }
        ans.add(new Represantation("A0",a0,QI.AGE));

        HashMap<String,String> a1 = new HashMap<>();
        for(int i=0;i<=50;++i){
            if(i<31)
                a1.put(Integer.toString(i),"0-30");
            else
                a1.put(Integer.toString(i),"31-50");
        }
        ans.add(new Represantation("A1",a1,QI.AGE));

        HashMap<String,String> a2 = new HashMap<>();
        a2.put("0-30","*");
        a2.put("31-50","*");
        ans.add(new Represantation("A2",a2,QI.AGE));

        HashMap<String,String> o0 = new HashMap<>();
        for(int i=0;i<=100;++i){
            o0.put(Integer.toString(i),Integer.toString(i));
        }
        ans.add(new Represantation("O0",o0,QI.OVERALL));

        HashMap<String,String> o1 = new HashMap<>();
        for(int i=0;i<=100;++i){
            o1.put(Integer.toString(i),"*");
        }
        ans.add(new Represantation("O1",o1,QI.OVERALL));

        return ans;
    }
}
