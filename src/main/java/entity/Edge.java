package entity;

public class Edge {
    public Vertex from;
    public Vertex to;

    public int support;
    public int trussness;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }
    public boolean containVertex(Vertex u){
        if(this.from!=u&&this.to!=u) return false;
        else return true;
    }
}
