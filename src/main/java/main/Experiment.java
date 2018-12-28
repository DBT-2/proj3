package main;

import alg.CommunitySearch;
import alg.DataSets;
import alg.IndexConstruction;
import entity.Edge;
import entity.Graph;
import entity.SuperGraph;
import entity.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Experiment {
    public static String[] dataset={"/Users/tianyu/JavaProject/database_3/dataset/Amazon/amazon.pro.top5000.cmty.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/DBLP/dblp.pro.top5000.cmty.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Youtube/youtube.pro.top5000.cmty.txt",
    };
    public static String[] index={"/Users/tianyu/JavaProject/database_3/dataset/Amazon/amazon.ungraph.index",
    "/Users/tianyu/JavaProject/database_3/dataset/DBLP/dblp.ungraph.index",
    "/Users/tianyu/JavaProject/database_3/dataset/Youtube/youtube.ungraph.index"};
    public static void queryVertex(int k)throws IOException{
        Graph graph= DataSets.readData(IndexMain.dataset[2]);
        //IndexConstruction.run(graph);
        BufferedReader reader = new BufferedReader(new FileReader(index[2]));
        SuperGraph superGraph= SuperGraph.deserializeFrom(reader, graph);
        CommunitySearch communitySearch=new CommunitySearch();
        ArrayList<ArrayList<Edge>> commuties=communitySearch.run(k, graph.vertexs.get(72), superGraph);
        CommunitySearch.printCommunityPoint(commuties);
    }

    public static ArrayList<Integer> returnPreRec(String filename,int k) throws IOException {
        Graph graph= DataSets.readData(IndexMain.dataset[2]);
        BufferedReader reader = new BufferedReader(new FileReader(index[2]));
        SuperGraph superGraph= SuperGraph.deserializeFrom(reader, graph);
        CommunitySearch communitySearch=new CommunitySearch();
        long totalTime=0;
        try{
            HashSet<ArrayList<Integer>> rows=new HashSet<>();
            File file=new File(filename);
            if(file.isFile()&&file.exists()){
                InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file),"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String lineTxt=null;
                while ((lineTxt=bufferedReader.readLine())!=null){
                    String[] line=lineTxt.split("\\s+");
                    ArrayList<Integer> entries=new ArrayList<>();
                    for(String item:line){
                        int entry=Integer.parseInt(item);
                        entries.add(entry);
                    }
                    rows.add(entries);
                }
            }
            int preNum=0;
            int recNum=0;
            int trainTotalNum=0;
            int testTotalNum=0;
            for(ArrayList<Integer> row:rows){
                trainTotalNum+=row.size()-1;
                long startTime=System.currentTimeMillis();
                ArrayList<ArrayList<Edge>> commuties = communitySearch.run(k, graph.vertexs.get(row.get(0)), superGraph);
                HashSet<Vertex> pts=CommunitySearch.printCommunityPoint(commuties);
                long currentloop=System.currentTimeMillis()-startTime;
                totalTime+=currentloop;
                ArrayList<Integer> indexs=new ArrayList<>();
                for(Vertex pt:pts){
                    indexs.add(pt.id);
                    if(!row.contains(pt.id)) {
                        preNum++;
                    }
                }
                for(int item:row){
                    if(!indexs.contains(item)){
                        recNum++;
                    }
                }
                testTotalNum+=indexs.size();
            }
            System.out.println("precision:"+(testTotalNum-preNum)/(float)testTotalNum);
            System.out.println("recall:"+(trainTotalNum-recNum)/(float)trainTotalNum);
            //System.out.println(preNum);
            //System.out.println(recNum);
            System.out.println("TP:"+(testTotalNum-preNum));
            System.out.println(trainTotalNum);
            System.out.println(testTotalNum);

        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        System.out.println("calculate "+k+" communities consume "+totalTime+"ms");
        return null;
    }
    public static void main(String[] args) throws IOException{
        int k=6;
        //queryVertex(k);
        Experiment.returnPreRec(Experiment.dataset[2],k);

    }
}
