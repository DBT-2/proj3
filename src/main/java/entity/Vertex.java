package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Vertex {
    public int id;

    private Map<Integer, Edge> outEdges;

    private List<Vertex> neighbors;

    public List<Vertex> getNeighbors() {
        if (neighbors == null) {
            neighbors = new ArrayList<>();
            for(Edge e : outEdges.values()) {
                if(e.from.id == id)
                    neighbors.add(e.to);
                else
                    neighbors.add(e.from);
            }
        }
        return neighbors;
    }

    public int degree() {
        return outEdges.size();
    }

    public Collection<Edge> getEdges() {
        return outEdges.values();
    }

    public Edge getEdge(int v) {
        return outEdges.get(v);
    }
}
