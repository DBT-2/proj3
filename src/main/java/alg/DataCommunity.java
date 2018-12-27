package alg;

import entity.Edge;
import entity.Graph;
import entity.SuperGraph;
import entity.Vertex;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class DataCommunity {
    public static Graph readData(String filename) throws IOException {
        Graph dataGraph=new Graph();
        try{
            File file=new File(filename);
            if (file.isFile()&&file.exists()){
                InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file),"utf-8");
                BufferedReader bufferedInputStream=new BufferedReader(inputStreamReader);
                String lineTxt=null;
                while ((lineTxt=bufferedInputStream.readLine())!=null){
                    String[] line=lineTxt.split("\\s+");
                    //System.out.println(line[0]);
                    try{
                        int fromId=Integer.parseInt(line[0]);
                        int toId=Integer.parseInt(line[1]);
                        //System.out.println(();

                        Vertex from = dataGraph.vertexs.get(fromId);
                        if (from == null) {
                            from = new Vertex(fromId);
                            dataGraph.vertexs.put(fromId, from);
                        }
                        Vertex to=dataGraph.vertexs.get(toId);
                        if (to == null) {
                            to = new Vertex(toId);
                            dataGraph.vertexs.put(toId, to);
                        }

                        Edge a=new Edge(from,to);
                        from.addEdge(a);
                        to.addEdge(a);
                        dataGraph.edges.add(a);
                        //System.out.println("fromId"+"toId");
                        //break;
                    }
                    catch (Exception e){
                        continue;
                    }
                }
                bufferedInputStream.close();
            }
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return dataGraph;
    }

}
