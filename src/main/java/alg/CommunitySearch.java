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
            if((v.trussness>=k)&& !(v.processed)){
                v.processed=true;
                l++;
                ArrayList<Edge> A= new ArrayList<>();
                LinkedList<SuperNode> Q= new LinkedList<>();
                Q.addLast(v);
                while (!Q.isEmpty()){
                    v=Q.removeFirst();
                    A.addAll(v.getEdge());
                    for(SuperNode u:v.getNeighood()){
                        if((u.trussness>=k)&&(!u.processed)){
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
    public static HashSet<Vertex> printCommunityPoint(ArrayList<ArrayList<Edge>> communities){
        HashSet<Vertex> pts=new HashSet<>();
        for(ArrayList<Edge> community:communities){
            for(Edge edge:community){
                pts.add(edge.from);
                pts.add(edge.to);
            }
        }
        //System.out.println(pts);
        return pts;
    }
}
