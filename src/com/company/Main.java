package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> setOfQI = new ArrayList<>();
        setOfQI.add(3);
        setOfQI.add(7);
        setOfQI.add(14);
        List<Represantation> gens = buildRepres();
        Incognito inc = new Incognito("minidata.csv", setOfQI,90,2,gens,3,buildDummyHash(gens));
        for(Vertex v : inc.mainAlgorithm())
            System.out.println(v.toString());

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
