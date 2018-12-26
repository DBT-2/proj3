package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SuperNode {
    public static int snID = 0;

    public int id;
    private List<Edge> edgeList;
    public List<SuperEdge> superEdges;
    public boolean processed;
    public int trussness;

    public SuperNode() {
        this.id = ++snID;
        edgeList = new ArrayList<>();
        superEdges = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        edgeList.add(e);
    }

    public List<Edge> getEdge(){
        return edgeList;
    }
    public ArrayList<SuperNode> getNeighood(){
        ArrayList<SuperNode> neighood=new ArrayList<>();
        for(SuperEdge e:this.superEdges){
            neighood.add(e.anotherPoint(this));
        }
        return neighood;
    }
}
