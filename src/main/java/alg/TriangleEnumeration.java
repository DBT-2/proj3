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
            for(Vertex v : u.getNeighbors()) {
                if(!removed.contains(v.id))
                    marked.add(v.id);
            }
            for(Vertex v : u.getNeighbors()) {
                if (removed.contains(v.id))
                    continue;
                for(Vertex w : v.getNeighbors()) {
                    if (marked.contains(w.id) && !removed.contains(w.id)) {
                        g.updateSupport(u.id, v.id, 1);
                        g.updateSupport(v.id, w.id,1 );
                        g.updateSupport(u.id, w.id, 1);
                    }
                }
                marked.remove(v.id);
            }
            removed.add(u.id);
        }
    }
}
