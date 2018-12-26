package alg;

import entity.*;

import java.util.*;

public class IndexConstruction {
    private static Set<Edge> processed;
    private static Queue<Edge> edgeQueue;
    private static Map<Edge, List<SuperNode>> edgeListMap;
    private static Set<Edge> removed;

    public static SuperGraph run(Graph g) {
        TrussDecomposition.run(g);

        processed = new HashSet<>();
        edgeListMap = new HashMap<>();
        removed = new HashSet<>();
        List<Edge>[] trussEdges = new List[g.maxTrussness];

        Set<SuperNode> superNodes = new HashSet<>();
        List<SuperEdge> superEdges = new ArrayList<>();

        List<Edge> edges = g.listEdges();
        for (Edge edge : edges) {
            if (trussEdges[edge.trussness - 1] == null)
                trussEdges[edge.trussness - 1] = new ArrayList();
            trussEdges[edge.trussness - 1].add(edge);
            edgeListMap.put(edge, new ArrayList<>());
        }

        edgeQueue = new ArrayDeque<>();
        for (int k = 3; k <= g.maxTrussness; k++) {
            while(!trussEdges[k].isEmpty()) {
                Edge e = trussEdges[k].remove(trussEdges.length-1);
                processed.add(e);
                SuperNode sv = new SuperNode();
                superNodes.add(sv);
                edgeQueue.add(e);
                while(!edgeQueue.isEmpty()) {
                    Edge currEdge = edgeQueue.remove();
                    sv.addEdge(currEdge);
                    List<SuperNode> edgeSNs = edgeListMap.get(currEdge);
                    for (SuperNode su : edgeSNs) {
                        SuperEdge superEdge = new SuperEdge(sv, su);
                        superEdges.add(superEdge);
                    }
                    Vertex u = currEdge.from;
                    Vertex v = currEdge.to;
                    for (Edge uw : u.outEdges.values()) {
                        if (removed.contains(uw))
                            continue;
                        Vertex w = uw.to;
                        Edge vw;
                        if (w.id < v.id) {
                            vw = w.outEdges.get(v.id);
                        } else {
                            vw = v.outEdges.get(w.id);
                        }
                        if (vw != null) {
                            if(uw.trussness >= k && vw.trussness >= k) {
                                processEdge(uw, k, sv);
                                processEdge(vw, k, sv);
                            }
                        }
                    }
                    removed.add(currEdge);
                }
            }
        }

        return new SuperGraph(superNodes, superEdges);
    }

    private static void processEdge(Edge edge, int k, SuperNode currSN) {
        if (removed.contains(edge))
            return;
        if(edge.trussness == k) {
            if (!processed.contains(edge)) {
                processed.add(edge);
                edgeQueue.add(edge);
            }
        } else {
            List<SuperNode> edgeSNList = edgeListMap.get(edge);
            if(edgeSNList.size() > 0 &&
                    edgeSNList.get(edgeSNList.size() - 1).id != currSN.id) {
                edgeSNList.add(currSN);
            }
        }
    }
}
