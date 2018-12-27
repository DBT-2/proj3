package alg;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.*;

public class TrussDecomposition {
    public static void run(Graph g) {
        TriangleEnumeration.run(g);
        System.out.println("Truss decomposition started...");
        long startTime = System.currentTimeMillis();

        List<Edge> edges = g.listEdges();
        TreeSet<Edge> edgeSet = new TreeSet<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                int c1 = Integer.compare(o1.support, o2.support);
                if (c1 != 0)
                    return c1;
                c1 = Integer.compare(o1.from.id, o2.from.id);
                if (c1 != 0)
                    return c1;
                return Integer.compare(o1.to.id, o2.to.id);
            }
        });
        edgeSet.addAll(edges);
        Set<Edge> removed = new HashSet<>();

        int k = 2;
        while (!edgeSet.isEmpty()) {
            Edge minEdge = edgeSet.first();
            if (minEdge.support > k-2) {
                k++;
                continue;
            }
            Vertex u = minEdge.from;
            Vertex v = minEdge.to;
            if(u.degree() > v.degree()) {
                Vertex temp = u;
                u = v;
                v = temp;
            }
            for (Vertex w : u.getNeighbors()) {
                Edge vw = g.findEdge(v.id, w.id);
                Edge uw = g.findEdge(u.id, w.id);
                if (removed.contains(uw))
                    continue;
                if (vw != null && !removed.contains(vw)) {
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
            removed.add(minEdge);
        }
        System.out.println(String.format("Truss decomposition completes after %dms", (System.currentTimeMillis() - startTime)));
    }
}
