package com.company;

import java.util.HashMap;

public class Represantation implements Comparable<Represantation> {
    private String name;
    private HashMap<String,String> gen;
    private QI type;
    public Represantation(String name,HashMap<String,String> gen,QI type){
        this.name = name;
        this.gen = gen;
        this.type = type;
    }

    @Override
    public String toString(){
        return this.name;
    }


    public String getName() {
        return name;
    }

    public HashMap<String, String> getGen() {
        return gen;
    }

    public QI getType() {
        return type;
    }

    @Override
    public int compareTo(Represantation o) {
        return Integer.compare(this.type.ordinal(), o.getType().ordinal());
    }
}
