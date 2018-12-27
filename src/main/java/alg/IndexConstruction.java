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
        System.out.println("Index construction started...");
        long startTime = System.currentTimeMillis();

        processed = new HashSet<>();
        edgeListMap = new HashMap<>();
        removed = new HashSet<>();
        List<Edge>[] trussEdges = new List[g.maxTrussness + 1];

        Map<Integer, SuperNode> superNodes = new HashMap<>();
        List<SuperEdge> superEdges = new ArrayList<>();

        List<Edge> edges = g.listEdges();
        for (Edge edge : edges) {
            if (trussEdges[edge.trussness] == null)
                trussEdges[edge.trussness] = new ArrayList();
            trussEdges[edge.trussness].add(edge);
            edgeListMap.put(edge, new ArrayList<>());
        }

        edgeQueue = new ArrayDeque<>();
        for (int k = 3; k <= g.maxTrussness; k++) {
            System.out.println(String.format("Processing truessness = %d, # edges = %d", k,
                    trussEdges[k] != null ? trussEdges[k].size() : 0));
            long trussStartTime = System.currentTimeMillis();

            while(trussEdges[k] != null && !trussEdges[k].isEmpty()) {
                Edge e = trussEdges[k].remove(trussEdges[k].size()-1);
                if (removed.contains(e))
                    continue;

                processed.add(e);
                SuperNode sv = new SuperNode();
                sv.trussness = k;
                superNodes.put(sv.id, sv);
                edgeQueue.add(e);
                while(!edgeQueue.isEmpty()) {
                    Edge currEdge = edgeQueue.remove();
                    sv.addEdge(currEdge);
                    List<SuperNode> edgeSNs = edgeListMap.get(currEdge);
                    for (SuperNode su : edgeSNs) {
                        SuperEdge superEdge = new SuperEdge(sv, su);
                        if (!superEdges.contains(superEdge)) {
                            superEdges.add(superEdge);
                            su.superEdges.add(superEdge);
                            sv.superEdges.add(superEdge);
                        }
                    }
                    Vertex u = currEdge.from;
                    Vertex v = currEdge.to;

                    for (Edge uw : u.getEdges()) {
                        if (removed.contains(uw))
                            continue;
                        Vertex w = uw.from == u ? uw.to : uw.from;
                        Edge vw;
                        if (w.id < v.id) {
                            vw = w.getEdge(v.id);
                        } else {
                            vw = v.getEdge(w.id);
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
            System.out.println(String.format("Trusses = %d consumed %dms", k, (System.currentTimeMillis() - trussStartTime)));
        }

        System.out.println(String.format("Index construction ends after %dms", (System.currentTimeMillis() - startTime)));
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
            if(edgeSNList.size() == 0 ||
                    edgeSNList.get(edgeSNList.size() - 1).id != currSN.id) {
                edgeSNList.add(currSN);
            }
        }
    }
}
