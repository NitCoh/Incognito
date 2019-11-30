package com.company;

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
        Incognito inc = new Incognito("minidata.csv", setOfQI,90,3,buildGeneralizations());
    }

    private static List<HashMap<String,String>> buildGeneralizations(){
        HashMap<String,String> age = new HashMap<>();
        HashMap<String,String> overall = new HashMap<>();
        HashMap<String,String> foot = new HashMap<>();
        List<HashMap<String,String>> ans = new ArrayList<>();
        ans.add(age);
        ans.add(overall);
        ans.add(foot);
        foot.put("Right","Foot");
        foot.put("Left","Foot");
        for(int i=0;i<=50;++i){
            if(i<25)
                age.put(Integer.toString(i),"0-24");
            else
                age.put(Integer.toString(i),"25-50");
        }
        for(int i=0;i<=100;++i){
            if(i<=50)
                overall.put(Integer.toString(i),"0-50");
            else
                overall.put(Integer.toString(i),"51-100");
        }

        return ans;
    }
}
