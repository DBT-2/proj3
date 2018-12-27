package main;

import alg.DataSets;
import alg.IndexConstruction;
import entity.Graph;
import entity.SuperGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IndexMain {

    public static String[] dataset=new String[]{"/Users/koutakashi/codes/proj-3/data/amazon.ungraph.txt"};

    public static void main(String[] args) throws IOException {
        int datasetNum = 0;

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
