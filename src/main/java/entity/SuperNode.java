package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
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

    public void addSuperEdge(SuperEdge se) {
        superEdges.add(se);
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

    public void serializeTo(Writer writer) throws IOException {
        writer.write(String.valueOf(id) + "," + trussness + "\n");
        writer.write(String.valueOf(edgeList.size()) + "\n");
        for(Edge edge : edgeList) {
            edge.serializeTo(writer);
        }
    }

    public static SuperNode deserializeFrom(BufferedReader reader, Graph g) throws IOException {
        String[] fields = reader.readLine().split(",");
        SuperNode sn = new SuperNode();
        sn.id = Integer.parseInt(fields[0]);
        sn.trussness = Integer.parseInt(fields[1]);
        int edgeNum = Integer.parseInt(reader.readLine());
        for (int i = 0; i < edgeNum; i++) {
            Edge edge = Edge.deserializeFrom(reader, g);
            sn.addEdge(edge);
        }
        return sn;
    }
}
