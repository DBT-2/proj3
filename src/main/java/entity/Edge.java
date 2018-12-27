package entity;

import main.IndexMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

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

    public void serializeTo(Writer writer) throws IOException {
        writer.write(from.id + "," + to.id + "," + trussness + "\n");
    }

    public static Edge deserializeFrom(BufferedReader reader, Graph g) throws IOException {
        String[] fields = reader.readLine().split(",");
        Edge newEdge = new Edge(g.getVertex(Integer.parseInt(fields[0])), g.getVertex(Integer.parseInt(fields[1])));
        newEdge.support = Integer.parseInt(fields[2]);
        return newEdge;
    }
}
