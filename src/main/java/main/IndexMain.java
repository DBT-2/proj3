package main;

import alg.DataSets;
import alg.IndexConstruction;
import entity.Graph;
import entity.SuperGraph;

import java.io.*;

public class IndexMain {

    public static String[] dataset=new String[]{"/Users/tianyu/JavaProject/database_3/dataset/Amazon/amazon.ungraph.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/DBLP/dblp.ungraph.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Youtube/youtube.ungraph.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Livejournal/lj.ungraph.txt",
    "/Users/tianyu/JavaProject/database_3/dataset/Orkut/orkut.ungraph.txt"};

    public static void main(String[] args) throws IOException {
        int datasetNum = 4;

        String inputFileName = dataset[datasetNum];
        String outputFileName = inputFileName.replace(".txt", ".index");

        Graph graph= DataSets.readData(inputFileName);
        long startTime = System.currentTimeMillis();
        SuperGraph superGraph= IndexConstruction.run(graph);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            superGraph.serializeTo(bufferedWriter);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
