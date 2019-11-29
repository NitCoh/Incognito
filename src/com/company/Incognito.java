package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Incognito {
    private List<Record> records;
    private int k;

    public Incognito(String filename, List<Integer> idx,int numOfAttsExpected,int k){
        this.k=k;
        this.records = readRecordsFromCSV(filename,numOfAttsExpected);
        filterByIndex(this.records,idx);
        int i=0;
        for(Record rec: this.records){
            System.out.println(i+ " Age: "+rec.getRecs().get(0)+" Overall: "+rec.getRecs().get(1)+" Foot: "+rec.getRecs().get(2));
            ++i;
        }
    }

    private static String[] listToArr(List<String> toExch){
        String[] toEx = new String[toExch.size()];
        int i=0;
        for(String s: toExch) {
            toEx[i++] = s;
        }
        return toEx;
    }

    private static String[] applyGeneralization(String[] app, List<HashMap<String,String>> genFuncs){
        int i=0;
        for(HashMap<String,String> map : genFuncs){
            app[i] = map.get(app[i++]);
        }
        return app;
    }

    public int buildFreqSet(List<HashMap<String,String>> genFuncs){
        FreqSet freqSet = new FreqSet();
        for(Record rec: this.records){
            Record genRec = new Record(applyGeneralization(listToArr(rec.getRecs()),genFuncs));
            freqSet.put(genRec);
        }
        return freqSet.getMinVal();
    }

    private static List<Record> readRecordsFromCSV(String filename, int numOfAttsExpected){
        List<Record> records = new ArrayList();
        Path localPath = Paths.get(filename);


        try (BufferedReader br = Files.newBufferedReader(localPath,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                if(attributes.length == numOfAttsExpected) {
                    Record rec = createRecord(attributes);
                    records.add(rec);
                }
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return records;
    }

    private static Record createRecord(String[] att){
        return new Record(att);
    }

    private static void filterByIndex(List<Record> recs,List<Integer> idx){
        for(Record rec : recs) {
            rec.filterByIndex(idx);
        }
    }


}
