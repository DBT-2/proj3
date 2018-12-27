package alg;

import entity.Edge;
import entity.Graph;
import entity.SuperGraph;
import entity.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Experiment {
    public static String dataset="/Users/tianyu/JavaProject/database_3/dataset/Amazon/amazon.top5000.cmty.txt";
    public static ArrayList<Integer> returnPreRec(String filename) throws IOException {
        Graph graph=DataCommunity.readData(DataCommunity.dataset[0]);
        SuperGraph superGraph=IndexConstruction.run(graph);
        CommunitySearch communitySearch=new CommunitySearch();
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
            int trainNum=0;
            int testNum=0;
            for(ArrayList<Integer> row:rows){
                trainNum+=row.size();
                ArrayList<ArrayList<Edge>> commuties = communitySearch.run(, graph.vertexs.get(row.get(0)), superGraph);
                //System.out.println(row);
                HashSet<Vertex> pts=CommunitySearch.printCommunityPoint(commuties);
                ArrayList<Integer> indexs=new ArrayList<>();
                for(Vertex pt:pts){
                    indexs.add(pt.id);
                    if(!row.contains(pt.id)){
                        preNum++;
                    }
                }
                for(int item:row){
                    if(!indexs.contains(item)){
                        recNum++;
                    }
                }
                testNum+=indexs.size();
            }
            System.out.println(preNum);
            System.out.println(recNum);
            System.out.println(trainNum);
            System.out.println(testNum);

        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }
    public static void main(String[] args) throws IOException{
        Experiment.returnPreRec(Experiment.dataset);
        //ArrayList<ArrayList<Edge>> commuties=communitySearch.run(3, graph.vertexs.get(213603),superGraph);
        //CommunitySearch.printCommunityPoint(commuties);
    }
}
