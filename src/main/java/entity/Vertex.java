package entity;

import java.util.Map;

public class Vertex {
    public int id;

    public Map<Integer, Edge> outEdges;
    public Vertex(int id){
        this.id=id;
    }
}
