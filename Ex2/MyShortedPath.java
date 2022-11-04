import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MyShortedPath {
    public static List<NodeData> path(DirectedWeightedGraph g, int s, int e) {
        ArrayList<NodeData> Q = new ArrayList<>();
        ArrayList<NodeData> shorted = new ArrayList<>();
        HashMap<NodeData, Double> dist = new HashMap<>();
        HashMap<NodeData, NodeData> prev = new HashMap<>();

        Iterator<NodeData> it = g.nodeIter();

        while (it.hasNext()) {
            NodeData t = it.next();
            dist.put(t, (double) Integer.MAX_VALUE);
            Q.add(t);
        }
        NodeData N = g.getNode(s);
        dist.remove(N);
        dist.put(N, 0.0);
        HashMap<NodeData, Double> dist1 = new HashMap<>();
        for (int i = 0; i < dist.size(); i++) {
            NodeData NN = g.getNode(i);
            double dd = dist.get(NN);
            dist1.put(NN, dd);
        }
        while (Q.contains(g.getNode(e - 1))) {

            // for (int i = 0; i <dist.size() ; i++) {
            //   NodeData NNN=g.getNode(i);
            // System.out.println(dist1.get(NNN));

            //}
            NodeData close = minDistrval(dist1, g, dist.size());

            Q.remove(close);
            Iterator<EdgeData> it1 = g.edgeIter(close.getKey());
            while (it1.hasNext() && dist.get(g.getNode(e)) == Integer.MAX_VALUE) {
                EdgeData e1 = it1.next();
                int neig = e1.getDest();
                double alt = dist.get(close) + e1.getWeight();
                if (alt < dist.get(g.getNode(neig))) {
                    NodeData N2 = g.getNode(neig);
                    prev.put(N2, close);
                    dist.remove(N2);
                    dist.put(N2, alt);
                    dist1.remove(N2);
                    dist1.put(N2, alt);
                }
            }
            dist1.remove(close);
        }
        NodeData temp = g.getNode(e);
        while (temp.getKey() != s) {
            shorted.add(temp);
            temp = prev.get(temp);
        }
       // System.out.print(temp.getKey() + ",");
        shorted.add(g.getNode(s));
          shorted=replace(shorted);
        return shorted;
    }

    private static ArrayList<NodeData> replace(ArrayList<NodeData> shorted) {
        ArrayList<NodeData> rep = new ArrayList<>();
        for (int i = shorted.size() - 1; i >= 0; i--) {
            rep.add(shorted.get(i));
        }
        return rep;
    }

    private static HashMap<NodeData, Double> updateclose(HashMap<NodeData, Double> dist, DirectedWeightedGraph g, NodeData n) {
        Iterator<EdgeData> it = g.edgeIter(n.getKey());
        while (it.hasNext()) {
            EdgeData e = it.next();
        }

        return null;
    }

    private static NodeData minDistrval(HashMap<NodeData, Double> dist, DirectedWeightedGraph g, int size) {
        NodeData ret = null;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            NodeData N = g.getNode(i);
            if (dist.get(N) != null) {
                if (dist.get(N) < min) {

                    min = dist.get(N);
                    ret = N;
                }
            }
        }

        return ret;
    }

    public static double minDist(DirectedWeightedGraph g, List<NodeData> myL) {
        double sum = 0;
        if (myL.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < myL.size() - 1; i++) {
            //  System.out.println(myL.size());
            int n1 = myL.get(i).getKey();
            int n2 = myL.get(i + 1).getKey();
            //  System.out.println(g.getEdge(n1,n2).getWeight());
            sum += g.getEdge(n1, n2).getWeight();
        }
        return sum;
    }
}
