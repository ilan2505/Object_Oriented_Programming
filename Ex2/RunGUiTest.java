import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.io.IOException;
import java.util.Iterator;

public class RunGUiTest {
    public static void test(DirectedWeightedGraphAlgorithms a, DirectedWeightedGraph t) throws IOException, InterruptedException {
        StdDraw.setCanvasSize(900, 900);
        double xmin = Double.MAX_VALUE;
        double xmax = Double.MIN_VALUE;
        double ymin = Double.MAX_VALUE;
        double ymax = Double.MIN_VALUE;
        double[] sizes = sizes(t.nodeIter());
        xmin = sizes[0];
        xmax = sizes[1];
        ymin = sizes[2];
        ymax = sizes[3];
        StdDraw.setXscale(xmin, xmax);
        StdDraw.setYscale(ymin, ymax);
        Iterator<NodeData> nd = t.nodeIter();
        while (nd.hasNext()) {
            StdDraw.setPenRadius(0.05);
            StdDraw.setPenColor(StdDraw.GREEN);
            NodeData N = (NodeData) nd.next();
            double x = N.getLocation().x();
            double y = N.getLocation().y();
            StdDraw.point(x, y);
        }
        Iterator<EdgeData> itNode = t.edgeIter();
        System.out.println(t.edgeSize()+"edges");
        int t333=0;
        while (itNode.hasNext()) {
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLUE);
            EdgeDatas ed = (EdgeDatas) itNode.next();
            //StdDraw.point(N.getLocation().x()-35,N.getLocation().y()-32);
            //StdDraw.setPenColor(StdDraw.MAGENTA);

            //if (t.getNode(ed.src) != null&&t.getNode(ed.dest)!=n) {
            NodeData src = (NodeData) t.getNode(ed.src);
            double x1 = (src.getLocation().x());
            double y1 = (src.getLocation().y());

            NodeData dest = (NodeData) t.getNode(ed.dest);

            double x2 = (dest.getLocation().x());
            double y2 = (dest.getLocation().y());
            System.out.println(++t333);
            System.out.println(ed.src+","+ed.dest);


            StdDraw.line(x1, y1, x2, y2);
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.setPenRadius(0.02);
            double x3 = 0.1 * x1 + 0.9 * x2;
            double y3 = 0.1 * y1 + 0.9 * y2;
            StdDraw.point(x3, y3);
            // }


        }
        if (a.isConnected()) {
            NodeData t1 = a.center();
            System.out.println(t1);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.07);
            double xc = t1.getLocation().x();
            double yc = t1.getLocation().y();
            StdDraw.point(xc, yc);
        }
        NodeData t2 = t.getNode(5);
    }

    private static double[] sizes(Iterator<NodeData> nodeIter) {
        double xmin = Double.MAX_VALUE;
        double xmax = Double.MIN_VALUE;
        double ymin = Double.MAX_VALUE;
        double ymax = Double.MIN_VALUE;
        while (nodeIter.hasNext()) {
            NodeData N = (NodeData) nodeIter.next();
            double x = N.getLocation().x();
            double y = N.getLocation().y();
            if (x < xmin) {
                xmin = x;
            }
            if (x > xmax) {
                xmax = x;
            }
            if (y < ymin) {
                ymin = y;
            }
            if (y > ymax) {
                ymax = y;
            }
        }
        return new double[]{xmin, xmax, ymin, ymax};
    }

    public static void main(String[] args) {

    }
}
