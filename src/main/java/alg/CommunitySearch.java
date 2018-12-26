package alg;

import entity.*;
import entity.SuperNode;

import java.util.ArrayList;
import java.util.HashMap;

public class CommunitySearch {
    ArrayList<SuperNode> hashStruc(Vertex u, SuperGraph superGraph){
        ArrayList<SuperNode> superNodeSet=new ArrayList<>();
        for(SuperNode v:superGraph.superNodes){
            for(Edge e:v.getEdge()){
                if(e.containVertex(u)){
                    superNodeSet.add(v);
                    break;
                }
            }
        }
        return superNodeSet;
    }
    public void run(int k,Vertex q,SuperGraph superGraph){
        for(SuperNode v:superGraph.superNodes){
            v.processed=false;
        }
        int l=0;
        ArrayList<SuperNode> Hq=hashStruc(q,superGraph);
        for(SuperNode v:Hq){
            if((v.trussness>=k)&&(v.processed)==false){
                v.processed=true;
                l++;

            }
        }
    }
}
