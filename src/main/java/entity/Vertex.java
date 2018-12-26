package entity;

import java.util.*;

public class Vertex {
    public int id;


    public Vertex(int id){
        this.id=id;
        outEdges = new HashMap<>();
    }

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

    public void addEdge(Edge e) {
        if (e.from == this) {
            outEdges.put(e.to.id, e);
        } else {
            outEdges.put(e.from.id, e);
        }
    }
}
