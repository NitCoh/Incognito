package com.company;

import java.util.HashMap;

public class FreqSet {
    private HashMap<Record,Integer> freqSet = new HashMap<>();

    public int getMinVal(){
        Integer min = Integer.MAX_VALUE;
       for(Integer val : freqSet.values()){
           if(val < min)
               min=val;
       }
       return min;
    }

    public void put(Record rec){
        Record toComp = contains(rec);
        if(toComp == null) {
            freqSet.put(rec,1);
        }
        else{
            freqSet.put(toComp,freqSet.get(toComp)+1);
        }
    }

    public Record contains(Record rec){
        for(Record cur : freqSet.keySet()){
            if(cur.equals(rec))
                return cur;
        }
        return null;
    }
}
