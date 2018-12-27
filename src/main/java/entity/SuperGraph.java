package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class SuperGraph {

    public Map<Integer, SuperNode> superNodes;
    public Collection<SuperEdge> superEdges;

    public SuperGraph(Map<Integer, SuperNode> superNodes, Set<SuperEdge> superEdges) {
        this.superNodes = superNodes;
        this.superEdges = superEdges;
    }

    public SuperGraph() {
        this.superNodes = new HashMap<>();
        this.superEdges = new HashSet<>();
    }

    public void serializeTo(Writer writer) throws IOException {
        writer.write(superNodes.size() + "\n");
        for(SuperNode sn : superNodes.values()) {
            sn.serializeTo(writer);
        }
        writer.write(superEdges.size() + "\n");
        for(SuperEdge se : superEdges) {
            se.serializeTo(writer);
        }
    }

    public SuperNode getNode(int id) {
        return superNodes.get(id);
    }

    public static SuperGraph deserializeFrom(BufferedReader reader, Graph g) throws IOException {
        SuperGraph ret = new SuperGraph();
        int nodeNum = Integer.parseInt(reader.readLine());
        for(int i = 0; i < nodeNum; i++) {
            SuperNode sn = SuperNode.deserializeFrom(reader, g);
            ret.superNodes.put(sn.id, sn);
        }
        int edgeNum = Integer.parseInt(reader.readLine());
        for(int i = 0; i < edgeNum; i++) {
            SuperEdge se = SuperEdge.deserializeFrom(reader, ret);
            ret.superEdges.add(se);
            ret.getNode(se.from.id).addSuperEdge(se);
            ret.getNode(se.to.id).addSuperEdge(se);
        }
        return ret;
    }
}
