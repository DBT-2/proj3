package entity;

import java.util.List;
import java.util.Set;

public class SuperGraph {

    public Set<SuperNode> superNodes;
    public List<SuperEdge> superEdges;

    public SuperGraph(Set<SuperNode> superNodes, List<SuperEdge> superEdges) {
        this.superNodes = superNodes;
        this.superEdges = superEdges;
    }
}
