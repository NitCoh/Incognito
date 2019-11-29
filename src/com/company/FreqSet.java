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
        if(!contains(rec)) {
            freqSet.put(rec,1);
        }
        else{
            freqSet.put(rec,freqSet.get(rec)+1);
        }
    }

    public boolean contains(Record rec){
        for(Record cur : freqSet.keySet()){
            if(cur.equals(rec))
                return true;
        }
        return false;
    }
}
