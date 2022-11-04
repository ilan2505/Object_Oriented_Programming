import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * this class implant if the graph is connected
 *
 */
public class DFS {

    public static DirectedWeightedGraph Trans(DirectedWeightedGraph g) {
        DirectedWeightedGraph trans = g;
        Iterator<EdgeData> n = g.edgeIter();
        while (n.hasNext()) {
            EdgeData e = n.next();
            trans.removeEdge(e.getSrc(), e.getDest());
            trans.connect(e.getDest(), e.getSrc(), e.getWeight());
        }
        return trans;
    }

    public static boolean dfs(DirectedWeightedGraph g, NodeData r) {
        ArrayList<NodeData> nodeList = new ArrayList<>();
        Iterator<NodeData> it = g.nodeIter();
        while (it.hasNext()) {
            nodeList.add(it.next());
        }

        Iterator<EdgeData> t = g.edgeIter(r.getKey());
        return checkcCon(g, t, r, nodeList);
        //  return false;
    }

    private static boolean checkcCon(DirectedWeightedGraph g, Iterator<EdgeData> t, NodeData r, ArrayList<NodeData> list) {
        if (list.isEmpty()) {
            return true;
        }
        for (int i = 0; i < list.size(); i++) {

        }
        boolean b = false;
        list.remove(r);
        if (t != null && !list.isEmpty()) {
            while (t.hasNext()) {
                Iterator<EdgeData> it = null;
                EdgeData e = t.next();
                int dest = e.getDest();
                NodeData N = g.getNode(dest);
                if (list.contains(N)) {
                    list.remove(N);
                    it = g.edgeIter(N.getKey());
                    b= checkcCon(g, it, N, list);
                }else {
                    return true;
                }
            }
        }
        return b;
    }
}
