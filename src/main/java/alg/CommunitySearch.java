package alg;

import entity.*;
import entity.SuperNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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
    public ArrayList<ArrayList<Edge>> run(int k,Vertex q,SuperGraph superGraph){
        ArrayList<ArrayList<Edge>> communities=new ArrayList<>();
        for(SuperNode v:superGraph.superNodes){
            v.processed=false;
        }
        int l=0;
        ArrayList<SuperNode> Hq=hashStruc(q,superGraph);
        for(SuperNode v:Hq){
            if((v.trussness>=k)&&(v.processed)==false){
                v.processed=true;
                l++;
                ArrayList<Edge> A=null;
                LinkedList<SuperNode> Q=null;
                Q.addLast(v);
                while (Q!=null){
                    v=Q.removeFirst();
                    A.addAll(v.getEdge());
                    for(SuperNode u:v.getNeighood()){
                        if((u.trussness>=k)&&(u.processed==false)){
                            u.processed=true;
                            Q.addLast(u);
                        }
                    }
                }
                communities.add(A);
            }
        }
        return communities;
    }
    public static void main(String[] args){
        CommunitySearch communitySearch=new CommunitySearch();
        communitySearch.run(3,null,null);
    }
}
