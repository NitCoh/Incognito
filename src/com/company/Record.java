package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Record {
    private List<String> recs;
    public Record(String[] att){
        this.recs = new ArrayList<>();
        this.recs.addAll(Arrays.asList(att));
    }

    public void filterByIndex(List<Integer> idx){
        List<String> filteredRecs = new ArrayList<>();
        for(Integer i: idx){
            filteredRecs.add(this.recs.get(i));
        }
        this.recs = filteredRecs;
    }

    public List<String> getRecs(){
        return this.recs;
    }


    //TODO:: Implement!
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
