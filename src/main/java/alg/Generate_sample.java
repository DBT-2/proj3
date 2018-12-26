package alg;


import entity.Graph;
import entity.Vertex;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Generate_sample {
    public Graph generate_sample(){
        Graph sample=new Graph();
        ArrayList<Vertex> vertices=new ArrayList<>();
        for(int i=1;i<12;i++) {
            Vertex k=new Vertex(i);
            vertices.add(k);
            //HashMap<Integer,Vertex> a=new HashMap<>();
            sample.vertexs.put(i,k);
        }
        int[][] edges = new int[][] {
                {1,2},{2,3},
        };
        for(int[] edge : edges) {
            int left = edge[0];
            int right = edge[1];
        }
        ArrayList<>
        sample.edges.add();
    }
}
