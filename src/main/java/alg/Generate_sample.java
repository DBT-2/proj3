package alg;


import entity.Edge;
import entity.Graph;
import entity.SuperGraph;
import entity.Vertex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Generate_sample {
    public static Graph generate_sample(){
        Graph sample=new Graph();
        ArrayList<Vertex> vertices=new ArrayList<>();
        for(int i=1;i<12;i++) {
            Vertex k=new Vertex(i);
            vertices.add(k);
            //HashMap<Integer,Vertex> a=new HashMap<>();
            sample.vertexs.put(i,k);
        }
        int[][] edges = new int[][] {
                {1,2},{1,3},{1,4},{1,5},{2,3},{2,4},{3,4},{3,7},{3,9},{4,5},{4,6},{4,7},{5,6},{5,7},{6,7},{6,8},{6,11},
                {7,8},{7,9},{7,10},{7,11},{8,9},{8,10},{8,11},{9,10},{9,11},{10,11}
        };
        for(int[] edge : edges) {
            int left = edge[0];
            int right = edge[1];
            //Vertex from=new Vertex(left);
            //Vertex to=new Vertex(right);
            Vertex from=sample.vertexs.get(left);
            Vertex to=sample.vertexs.get(right);
            Edge a=new Edge(from,to);
            from.addEdge(a);
            to.addEdge(a);
            sample.edges.add(a);
        }
        return sample;
    }
    public static void main(String[] args){
        Graph sample_graph=Generate_sample.generate_sample();
        TriangleEnumeration.run(sample_graph);
        TrussDecomposition.run(sample_graph);
        SuperGraph superGraph=IndexConstruction.run(sample_graph);
        CommunitySearch communitySearch=new CommunitySearch();
        communitySearch.run(3,new Vertex(7),superGraph);
    }
}
