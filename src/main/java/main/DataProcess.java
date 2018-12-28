package main;

import java.io.*;
import java.util.*;

public class DataProcess {
    public static String[] datasets={"/Users/tianyu/JavaProject/database_3/dataset/Youtube/youtube.top5000.cmty.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Livejournal/lj.top5000.cmty.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Orkut/orkut.top5000.cmty.txt"};
    public static String[] processedData={"/Users/tianyu/JavaProject/database_3/dataset/Youtube/youtube.pro.top5000.cmty.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Livejournal/lj.pro.top5000.cmty.txt",
            "/Users/tianyu/JavaProject/database_3/dataset/Orkut/orkut.pro.top5000.cmty.txt"};
    public static void dataProcess(String inputFile,String outputFile) throws IOException {
        File file=new File(inputFile);
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        ArrayList<HashSet<Integer>> rows=new ArrayList<>();
        String lineTxt=null;
        while((lineTxt=bufferedReader.readLine())!=null){
            HashSet<Integer> row=new HashSet<>();
            String[] items=lineTxt.split("\\s+");
            for(String item:items){
                row.add(Integer.parseInt(item));
            }
            rows.add(row);
        }
        //System.out.println(rows);
        HashMap<Integer, HashSet<Integer>> community=new HashMap<>();
        for(HashSet<Integer> line:rows){
            for(Integer entry:line){
                if(!community.containsKey(entry)) {
                    community.put(entry, line);
                } else {
                    community.get(entry).addAll(line);
                }
            }
        }
        File file1=new File(outputFile);
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file1));
        Iterator iterator=community.entrySet().iterator();

        while (iterator.hasNext()){
            for(int i=0;i<100;i++){
                Map.Entry entry=(Map.Entry)iterator.next();
                int key=(Integer)entry.getKey();
                HashSet value=(HashSet)entry.getValue();

                bufferedWriter.write(key+""+":"+value+"\n");
                //int i=1;
            }
            break;
        }
        bufferedReader.close();
        bufferedWriter.flush();

    }
    public static void main(String[] args)throws IOException{
        DataProcess.dataProcess(datasets[2],processedData[2]);
    }
}
