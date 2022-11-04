import api.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;

import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Algo implements DirectedWeightedGraphAlgorithms {
    DirectedWeightedGraph e;

    public Algo() {
        this.e = new DirectedWeigtet();
    }

    /**
     * this func init to my algo graph
     * @param g
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.e = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.e;
    }
    /**
     * this func do deep copy
     * @param
     */
    @Override
    public DirectedWeightedGraph copy() {
        HashMap<Integer, NodeData> Node = new HashMap<>();
        Iterator<NodeData> no = e.nodeIter();
        while (no.hasNext()) {
            NodeData N = no.next();
            Node.put(N.getKey(), N);
        }
        HashMap<NodeData, HashMap<NodeData, EdgeData>> Edge = new HashMap<>();
        Iterator<EdgeData> ed = e.edgeIter();
        while (ed.hasNext()) {
            EdgeData e1 = ed.next();
            NodeData ns = e.getNode(e1.getSrc());
            NodeData nd = e.getNode(e1.getDest());
            HashMap<NodeData, EdgeData> t = new HashMap<>();
            t.put(ns, e1);
            Edge.put(ns, t);

        }
        DirectedWeightedGraph c = new DirectedWeigtet(Node, Edge);

        return c;
    }
    /**
     * this func check if the graph is connected with 2 help func
     * the first one check if we can get to all nodes from one node and the second transport the graph
     * @param
     */
    @Override
    public boolean isConnected() {
        boolean b = false;
        DirectedWeightedGraph g;
        DirectedWeightedGraph c = copy();
        int i = 0;
        while (c.getNode(i) == null) {
            i++;
        }
        if (DFS.dfs(c, c.getNode(i))) {

            g = DFS.Trans(c);
            b = DFS.dfs(g, g.getNode(i));
        }
        return b;
    }

    /**
     * this func find the shorted path distance by check the shorted path dist
     * @param
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        return MyShortedPath.minDist(this.e, shortestPath(src, dest));
    }

    /**
     * this func find the shorted path by help func
     * @param
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return MyShortedPath.path(this.e, src, dest);
    }
    /**
     * this func find the center by mat
     * @param
     */
    @Override
    public NodeData center() {
        int size = e.nodeSize();
        NodeData center = e.getNode(0);
        double mat[][] = new double[size][size];
        for (int i = 0; i < e.nodeSize(); i++) {
            for (int j = 0; j < e.nodeSize(); j++) {
                if (e.getEdge(i, j) != null && e.getNode(i) != null && e.getNode(j) != null) {
                    mat[i][j] = e.getEdge(i, j).getWeight();
                } else {
                    mat[i][j] = Double.MAX_VALUE;
                    if (i == j) {
                        mat[i][j] = 0;
                    }
                }
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (mat[i][j] > mat[i][k] + mat[k][j] ) {
                        mat[i][j] = mat[i][k] + mat[k][j];
                    }
                }
            }
        }
        int center1 = 0;
        Double min = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            double max = Double.MIN_VALUE;
            for (int j = 0; j < size; j++) {

                if (mat[i][j] > max&&mat[i][j]<Double.MAX_VALUE) {
                    max = mat[i][j];
                }

            }
            if (max < min && max > 0&&e.getNode(i)!=null) {


                min = max;
                center1 = i;

            }
        }

        //  System.out.println(center1);
        return e.getNode(center1);

    }
    /**
     * this func find the tsp by help func
     *
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return Mytsp.checktsp(this.e, cities);

    }

    @Override
    public boolean save(String file) {
        JSONObject myobj = new JSONObject();
        JSONArray edge_arr = new JSONArray();
        myobj.put("Edges", edge_arr);
        JSONArray node_arr = new JSONArray();
        myobj.put("Nodes", node_arr);
        Iterator<EdgeData> itr_edge = this.e.edgeIter();
        while (itr_edge.hasNext()) {
            EdgeData temp = itr_edge.next();
            JSONObject edge_obj = new JSONObject();
            edge_obj.put("src", temp.getSrc());
            edge_obj.put("w", temp.getWeight());
            edge_obj.put("dest", temp.getDest());
            edge_arr.put(edge_obj);
        }
        Iterator<NodeData> itr_nodes = this.e.nodeIter();
        while (itr_nodes.hasNext()) {
            NodeData temp = itr_nodes.next();
            JSONObject node_obj = new JSONObject();
            node_obj.put("pos", temp.getLocation().x() + "," + temp.getLocation().y() + "," + temp.getLocation().z());
            node_obj.put("id", temp.getKey());
            node_arr.put(node_obj);
        }
        try (FileWriter f = new FileWriter(file)) {
             f.write(myobj.toString());
            f.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean load(String file) throws IOException {
        System.out.println(file + "!");

        try {
              DirectedWeigtet g = TestHash.graph(file);
             System.out.println(g.edgeSize+"!!");


               init(g);

            return true;
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
