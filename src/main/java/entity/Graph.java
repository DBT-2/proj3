package entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public Map<Integer, Vertex> vertexs;
    public List<Edge> edges;

    public int maxTrussness;
    public Graph(){vertexs=new HashMap<>();edges=new ArrayList<>();}
    public Graph(int vNum) {
        vertexs = new HashMap<>(vNum);
    }

    public void updateSupport(int from, int to, int update) {
        Edge e = findEdge(from, to);
        if (e != null)
            e.support += update;
    }

    private void collectEdges() {
        edges = new ArrayList<Edge>();
        for (Vertex v : vertexs.values()) {
            if(v.getEdges() != null)
                edges.addAll(v.getEdges());
        }
    }

    public List<Edge> listEdges() {
        if (edges == null) {
            collectEdges();
        }
        List<Edge> ret = new ArrayList<>();
        ret.addAll(edges);
        return ret;
    }

    public Edge findEdge(int from, int to) {
        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }
        return vertexs.get(from).getEdge(to);
    }

    public Vertex getVertex(int id) {
        return vertexs.get(id);
    }
}
