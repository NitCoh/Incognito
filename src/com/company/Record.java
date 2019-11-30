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

    @Override
    public boolean equals(Object obj) {
        boolean ans = true;
       if(obj instanceof Record && ((Record) obj).recs.size() == this.recs.size()){
           for(int i=0;i<this.recs.size();++i){
               if(this.recs.get(i) == null || ((Record) obj).recs.get(i) == null)
                   continue;
               if (!this.recs.get(i).equals(((Record) obj).recs.get(i))) {
                   ans = false;
                   break;
               }
           }
       }
       else
           ans = false;
       return ans;
    }

}
