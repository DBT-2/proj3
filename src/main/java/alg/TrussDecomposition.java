package alg;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class TrussDecomposition {
    public static void run(Graph g) {
        TriangleEnumeration.run(g);
        List<Edge> edges = g.listEdges();
        TreeSet<Edge> edgeSet = new TreeSet<>(Comparator.comparingInt(o -> o.support));
        edgeSet.addAll(edges);

        int k = 2;
        while (!edgeSet.isEmpty()) {
            Edge minEdge = edgeSet.first();
            if (minEdge.support > k-2) {
                k++;
                continue;
            }
            Vertex u = minEdge.from;
            Vertex v = minEdge.to;
            if(u.outEdges.size() > v.outEdges.size()) {
                Vertex temp = u;
                u = v;
                v = temp;
            }
            for (Edge uw : u.outEdges.values()) {
                Vertex w = uw.to;
                Edge vw = g.findEdge(v.id, w.id);
                if (vw != null) {
                    edgeSet.remove(uw);
                    edgeSet.remove(vw);
                    uw.support--;
                    vw.support--;
                    edgeSet.add(uw);
                    edgeSet.add(vw);
                }
            }
            minEdge.trussness = k;
            g.maxTrussness = k;
            edgeSet.remove(minEdge);
        }
    }
}
