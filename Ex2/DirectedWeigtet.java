import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

/**
 * The edges and Nodes do with HashMap
 */
public class DirectedWeigtet implements DirectedWeightedGraph {
    HashMap<Integer, NodeData> node;
    int w;
    int MC;
    HashMap<NodeData, HashMap<NodeData, EdgeData>> edge;
    HashMap<Integer, HashMap<Integer, EdgeData>> edgein;
    int edgeSize;

    public DirectedWeigtet() {
        this.edgeSize = 0;
        this.edge = new HashMap<>();
        this.edgein = new HashMap<>();
        this.node = new HashMap<>();

        this.MC = 0;
    }

    public DirectedWeigtet(HashMap mynode, HashMap<NodeData, HashMap<NodeData, EdgeData>> edge) {
        this.edge = edge;
        this.edgein = new HashMap<>();
        this.node = mynode;
    }

    @Override
    public NodeData getNode(int key) {
        return node.get(key);


    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        NodeData N1 = getNode(src);
        NodeData N2 = getNode(dest);
        //HashMap<NodeData,NodeData> t=new HashMap<>();
        //t.put(N1,N2);
        if (edge.get(N1) != null) {
            if (edge.get(N1).get(N2) != null) {
                return this.edge.get(N1).get(N2);
            }
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        node.put(n.getKey(), n);
        this.MC++;

    }

    @Override
    public void connect(int src, int dest, double w) {
        NodeData dest1 = getNode(dest);
        NodeData src1 = getNode(src);
        EdgeData e = new EdgeDatas(src, w, dest);
        HashMap<NodeData, EdgeData> t = new HashMap<>();
        HashMap<Integer, EdgeData> tin = new HashMap<>();

        t.put(dest1, e);
        tin.put(src, e);

        if (this.edge.get(src1) != null) {

            this.edge.get(src1).put(dest1, e);
        } else {
            this.edge.put(src1, t);

        }

        if (this.edgein.get(dest) != null) {
            this.edgein.get(dest).put(src, e);


        } else {

            this.edgein.put(dest, tin);
        }
        this.MC++;
        this.edgeSize++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Iterator<NodeData> ie = null;
        NodeData d[] = this.node.values().toArray(new NodeData[0]);
        ie = Arrays.stream(d).iterator();
        return ie;

    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        try {


            ArrayList<EdgeData> ADATA = new ArrayList<>();
           Object c[]= node.keySet().toArray();
         //  System.out.println(Arrays.toString(c));
            for (int i = 0; i < c.length; i++) {
                NodeData n = node.get((int)c[i]);
                if (edgeIter((int)c[i] ) != null) {

                    Iterator<EdgeData> t = edgeIter((Integer) c[i]);
                    while (t.hasNext()) {
                        EdgeData thisedge = (EdgeData) t.next();
                        ADATA.add(thisedge);
                   }
                }
            }
            Iterator<EdgeData> t = ADATA.iterator();
            return t;
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Iterator<EdgeData> ie = null;

        try {

                NodeData N = getNode(node_id);
                if (this.edge.get(N) != null) {
                    EdgeData[] t = this.edge.get(N).values().toArray(new EdgeData[0]);
                    ie = Arrays.stream(t).iterator();

                }


        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ie;

    }

    @Override
    public NodeData removeNode(int key) {
        if (getNode(key) != null) {

            NodeData n = getNode(key);
            NodeData t1[]=edge.get(n).keySet().toArray(new NodeData[0]);

            int t = this.edge.get(n).size();
            System.out.println("t=" + t);
            this.edgeSize -= t;
            removeEdge(key);
            node.remove(key);
            edge.remove(n);
            this.MC++;
            return n;
        }
        return null;
    }

    private void removeEdge(int key) {
        Object[] src1 = edgein.get(key).keySet().toArray();
        for (int i = 0; i < src1.length; i++) {
            System.out.println(src1[i]+"!");
            removeEdge((Integer) src1[i],key);
            }


        }
        /**
         NodeData N = getNode(key);
         this.edge.remove(N);
         for (int i = 0; i < this.node.size(); i++) {
         if (i != key) {
         // System.out.println(key);
         removeEdge(i, key);
         }
         }
         */


    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e = null;
        NodeData dest1 = getNode(dest);
        NodeData src1 = getNode(src);
        if (this.edge.get(src1) != null) {
            if (this.edge.get(src1).get(dest1) != null) {
                e = this.edge.get(src1).get(dest1);
                this.edgeSize--;
                //   System.out.println(src + "," + dest);
                this.edge.get(src1).remove(dest1);
                this.MC++;

            }
        }

        return e;
    }

    @Override
    public int nodeSize() {
        return this.node.size();
    }

    @Override
    public int edgeSize() {
        return this.edgeSize;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
