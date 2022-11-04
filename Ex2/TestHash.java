import java.awt.geom.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import api.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestHash {
    public static void main(String[] args) throws IOException, InterruptedException {
        graph("C:/G1.json");
    }

    public static DirectedWeigtet graph(String file1) throws IOException, InterruptedException {
        DirectedWeightedGraphAlgorithms alg = new Algo();
        DirectedWeigtet g = new DirectedWeigtet();
        String file = file1;
        String content = new String(Files.readAllBytes(Paths.get(file)));
        JSONObject obj = new JSONObject(content);
        JSONArray objArr1 = obj.getJSONArray("Nodes".trim());
        for (int i = 0; i < objArr1.length(); i++) {
            JSONObject x1 = objArr1.getJSONObject(i);
            int id = (int) x1.get("id");
            String pos = (String) x1.get("pos");
            String xyz = new String();
            double loc[] = new double[3];
            int count = 0;
            for (int j = 0; j < pos.length(); j++) {
                if (pos.charAt(j) != ',') {
                    xyz += pos.charAt(j);
                } else {
                    loc[count] = Double.parseDouble(xyz);
                    xyz = new String();
                    count++;
                }
            }

            MyLoc c = new MyLoc(loc[0], loc[1], loc[2]);
            ND N = new ND(c, id);
            g.addNode(N);

        }

        JSONArray objArr = obj.getJSONArray("Edges".trim());
        for (int i = 0; i < objArr.length(); i++) {


            JSONObject edge1 = objArr.getJSONObject(i);

            int src = (int) edge1.get("src");
            int dest = (int) edge1.get("dest");
            Object y2 = (Object) edge1.get("w");
            String s2 = y2.toString();
            Double s3 = Double.parseDouble(s2);
            //Float y21 = (Float) y2;
            EdgeDatas s = new EdgeDatas(src, s3, dest);
            g.connect(src, dest, s3);
        }
        System.out.println(g.edgeSize);
        DirectedWeightedGraphAlgorithms al = new Algo();
        al.init(g);
        System.out.println(al.isConnected());
        System.out.println(g.edgeSize);


        pp(g);

        return g;
    }

    public static HashMap<Point2D, GeoLocation> pp(DirectedWeigtet g) {
       HashMap<Point2D,GeoLocation> p = new HashMap<>();
        Iterator<NodeData> N = g.nodeIter();
        while (N.hasNext()) {
            NodeData n = N.next();
            double x = n.getLocation().x();
            double y = n.getLocation().y();
            GeoLocation lo=new MyLoc(x,y,0);

            java.awt.geom.Point2D pp = new java.awt.geom.Point2D() {
                @Override
                public double getX() {
                    return x;
                }

                @Override
                public double getY() {
                    return y;
                }

                @Override
                public void setLocation(double x, double y) {

                }
            };
            p.put(pp,lo);
        }
        return p;
    }

    public static HashMap<Point2D,HashMap<Point2D,Double>> ed(DirectedWeigtet t) {
        HashMap<Point2D,HashMap<Point2D,Double>> point2= new HashMap<>();

        Iterator<EdgeData> itNode = t.edgeIter();

        while (itNode.hasNext()) {
            EdgeDatas ed = (EdgeDatas) itNode.next();
            NodeData src = t.getNode(ed.src);
            double x1 = (src.getLocation().x());
            double y1 = (src.getLocation().y());
            Point2D src1 = new Point2D() {
                @Override
                public double getX() {
                    return x1;
                }

                @Override
                public double getY() {
                    return y1;
                }

                @Override
                public void setLocation(double x, double y) {

                }
            };
            NodeData dest = t.getNode(ed.dest);

            double x2 = (dest.getLocation().x());
            double y2 = (dest.getLocation().y());
            Point2D dest1=new Point2D() {
                @Override
                public double getX() {
                    return x2;
                }

                @Override
                public double getY() {
                    return y2;
                }

                @Override
                public void setLocation(double x, double y) {

                }
            };
            HashMap<Point2D,Double> tt=new HashMap();
            tt.put(dest1, 1.1);

            if (point2.get(src1) != null) {

              point2.get(src1).put(dest1, 0.0);
            } else {
                point2.put(src1, tt);

            }
        }
        return point2;
    }
}
