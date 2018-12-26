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
        return this.from == u || this.to == u;
    }


    @Override
    public String toString() {
        return "Edge{" +
                + from.id +
                "," + to.id +
                '}';
    }
}
