package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Incognito {
    private List<Record> records;
    private int k;
    private int sizeQI;
    private Represantation[] indexNodes;
    private HashMap<String,String> dummyHash;

    public Incognito(String filename, List<Integer> idx,int numOfAttsExpected,int k, List<Represantation> indexNodes,int qiSize,HashMap<String,String> dummyHash){
        this.sizeQI = qiSize;
        this.dummyHash = dummyHash;
        this.indexNodes = new Represantation[indexNodes.size()];
        int j=0;
        for(Represantation r: indexNodes){
            this.indexNodes[j++] = r;
        }
        this.k=k;
        this.records = readRecordsFromCSV(filename,numOfAttsExpected);
//        QI = new String[]{"S0", "S1", "Z0", "Z1", "Z2", "B0", "B1"};
        filterByIndex(this.records,idx);
        int i=0;
        for(Record rec: this.records){
            System.out.println(i+ " Age: "+rec.getRecs().get(0)+" Overall: "+rec.getRecs().get(1)+" Foot: "+rec.getRecs().get(2));
            ++i;
        }
    }


    //For each Vertex, if we apply this generalization, we get k-anonymization.
    public List<Vertex> mainAlgorithm (){
        Combinations activeGraph = null;
        List<Vertex> ans = new ArrayList<>();
        Set<String> forbidden = new HashSet<>();
        for(int i=1;i<=sizeQI;++i){
            System.out.println(i);
            activeGraph = new Combinations(this.indexNodes,i,forbidden);
//            forbidden = new HashSet<>();
            List<Vertex> roots = activeGraph.getGraph().startBFS();
            roots.sort(Comparator.comparingInt(Vertex::getHeight));
            Queue<Vertex> q = new LinkedList(roots);
            while(!q.isEmpty()){
                Vertex cur = q.remove();
                if(!cur.isMarked()) {
                    cur.setMarked(true);
                    int curk = buildFreqSet(cur);
                    if (curk < this.k) { // This generalization doesn't satisfy the k-anon.
                        forbidden.add(cur.toString());
                        List<Vertex> neighs = cur.getDirectNeighbours();
                        neighs.sort(Comparator.comparingInt(Vertex::getHeight));
                        q.addAll(neighs);

                    } else {
                        cur.setSatisfies(true);
                        cur.markAndSatisfyNeighbours();
                    }
                }
            }

        }
        List<Vertex> lastRound = activeGraph.getGraph().getVertices();
        for(Vertex v : lastRound){
            if(v.isSatisfies()){
                ans.add(v);
            }
        }
        return ans;
    }

    public Set<String> modBFS(){


        return null;
    }


    private static String[] listToArr(List<String> toExch){
        String[] toEx = new String[toExch.size()];
        int i=0;
        for(String s: toExch) {
            toEx[i++] = s;
        }
        return toEx;
    }

    private static String[] applyGeneralization(String[] app, HashMap<String,String>[] genFuncs){
        int i=0;
        for(HashMap<String,String> map : genFuncs){
            app[i] = map.get(app[i++]);
        }
        return app;
    }

    public int buildFreqSet(Vertex v){
        HashMap<String,String>[] arrFuncs = new HashMap[this.sizeQI];
        for(int i=0;i<this.sizeQI;i++){
            arrFuncs[i] = null;
        }
        for(Represantation r: v.getNodes()){
            arrFuncs[r.getType().ordinal()] = r.getGen();
        }
        padGeneralizationWithNullMaps(arrFuncs);
        FreqSet freqSet = new FreqSet();
        int i=0;
        for(Record rec: this.records){
            Record genRec = new Record(applyGeneralization(listToArr(rec.getRecs()),arrFuncs));
//            System.out.println((i++)+ " Age: "+genRec.getRecs().get(0)+" Overall: "+genRec.getRecs().get(1)+" Foot: "+genRec.getRecs().get(2));
            freqSet.put(genRec);
        }
        return freqSet.getMinVal();
    }

    private void padGeneralizationWithNullMaps(HashMap<String,String>[] genFuncs){
        for(int i=0; i<sizeQI;++i){
            if(genFuncs[i] == null)
                genFuncs[i] = dummyHash;
        }
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
