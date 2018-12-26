package alg;

import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * TriangleEnumeration calculates the supports of egdes in a map.
 */
public class TriangleEnumeration {

    public static void run(Graph g) {
        Set<Integer> marked = new HashSet<Integer>();
        Set<Integer> removed = new HashSet<Integer>();
        for(Vertex u : g.vertexs.values()) {
            for(Edge v : u.outEdges.values()) {
                if(!removed.contains(v.to.id))
                    marked.add(v.to.id);
            }
            for(Edge v : u.outEdges.values()) {
                if (removed.contains(v.to.id))
                    continue;
                for(Edge w : v.to.outEdges.values()) {
                    if (marked.contains(w.to.id) && !removed.contains(w.to.id)) {
                        g.updateSupport(u.id, v.to.id, 1);
                        g.updateSupport(v.to.id, w.to.id,1 );
                        g.updateSupport(u.id, w.to.id, 1);
                    }
                }
                marked.remove(v.to.id);
            }
            removed.add(u.id);
        }
    }
}
