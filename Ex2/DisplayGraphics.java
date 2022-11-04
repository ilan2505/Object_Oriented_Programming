// source https://www.javatpoint.com/Graphics-in-swing

//import MenuExample;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.GeoLocation;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class DisplayGraphics extends Canvas implements MouseListener {
    DirectedWeightedGraphAlgorithms myalgo;
    DirectedWeightedGraph MyGraph;
    HashMap<Point2D, GeoLocation> points;
    HashMap<Point2D, HashMap<Point2D, Double>> edges;
    Graphics g;


    public DisplayGraphics(DirectedWeightedGraphAlgorithms algo1) {
        super();
        this.myalgo = algo1;
        MyGraph = myalgo.getGraph();
        this.setBackground(Color.DARK_GRAY);
        this.addMouseListener(this);
        this.myalgo = algo1;
        points = TestHash.pp((DirectedWeigtet) MyGraph);
        this.edges = TestHash.ed((DirectedWeigtet) MyGraph);
    }

    public static void addEdge(Point2D a, Point2D b, ArrayList<Point2D> edges) {
        edges.add(a);
        edges.add(b);

    }

    public HashMap<Point2D, HashMap<Point2D, Double>> getEdges() {
        return edges;
    }

    public void setEdges(Point2D t, Point2D q) {
        this.edges.get(t).put(q, 1.1);

    }

    public void reset() {
        this.points = new HashMap<>();
        this.edges = new HashMap<>();
        this.repaint();
    }

    public void paint(Graphics g) {
        this.g = g;
        super.paint(g);
        setBackground(Color.YELLOW);
        double sizes[] = checksizes(points);
        Object poi[] = points.keySet().toArray();
        for (int i = 0; i < poi.length; i++) {
            DrawPoint((Point2D) poi[i], sizes);
        }
        Object srcc[] = edges.keySet().toArray();
        for (int i = 0; i < srcc.length; i++) {
            Object destt[] = edges.get(srcc[i]).keySet().toArray();
            for (int j = 0; j < destt.length; j++) {
                Drawline((Point2D) srcc[i], (Point2D) destt[j], sizes);

            }
        }

    }

    public void DrawPoint(Point2D po, double[] sizes) {
        g.setColor(Color.BLACK);
        Double xdist = sizes[1] - sizes[0];
        double xx = ((950 / xdist) * (po.getX() - sizes[0]));
        Double ydist = (sizes[3] - sizes[2]);
        double yy = (950 / ydist) * (po.getY() - sizes[2]);
        g.fillOval((int) xx - 10, (int) yy - 10, 20, 20);
    }

    private void Drawline(Point2D src, Point2D dest, double[] sizes) {
        g.setColor(Color.green);
        Double x1 = src.getX();
        Double y1 = src.getY();
        Double x2 = dest.getX();
        Double y2 = dest.getY();
        Double xdist = sizes[1] - sizes[0];
        double x11 = ((950 / xdist) * (x1 - sizes[0]));
        Double ydist = (sizes[3] - sizes[2]);
        double y11 = (950 / ydist) * (y1 - sizes[2]);
        double x21 = ((950 / xdist) * (x2 - sizes[0]));
        double y21 = (950 / ydist) * (y2 - sizes[2]);
        Point2D dir = new Point2D() {
            @Override
            public double getX() {
                return 0.9 * x21 + 0.1 * x11;
            }

            @Override
            public double getY() {
                return 0.9 * y21 + 0.1 * y11;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
        DrawPointdir(dir, sizes);
        g.drawLine((int) x11, (int) y11, (int) x21, (int) y21);
    }

    private void DrawPointdir(Point2D dir, double[] sizes) {
        Double xdist = sizes[1] - sizes[0];
        double xx = dir.getX();
        Double ydist = (sizes[3] - sizes[2]);
        double yy = dir.getY();

        g.fillOval((int) xx, (int) yy, 10, 10);
    }

    private double[] checksizes(HashMap<Point2D, GeoLocation> p) {
        double xmin = Double.MAX_VALUE;
        double xmax = Double.MIN_VALUE;
        double ymin = Double.MAX_VALUE;
        double ymax = Double.MIN_VALUE;
        Object[] t = p.keySet().toArray();
        for (int i = 0; i < t.length; i++) {
            Point2D pp = (Point2D) t[i];

            if (pp.getX() < xmin) {
                xmin = pp.getX();
            }
            if (pp.getX() > xmax) {
                xmax = pp.getX();
            }
            if (pp.getY() < ymin) {
                ymin = pp.getY();
            }
            if (pp.getY() > ymax) {
                ymax = pp.getY();
            }
        }
        return new double[]{xmin, xmax, ymin, ymax};
    }

    public void removeNode(Point2D t) {
        this.points.remove(t);
    }

    public static void main(String[] args) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void removeEdge(Point2D t, Point2D de) {
        this.edges.get(t).remove(de);

    }

    public void Addnode(Point2D po, GeoLocation gg) {
        this.points.put(po, gg);
    }

    public void updateedge(DirectedWeightedGraph myGraph) {
        this.edges = TestHash.ed((DirectedWeigtet) myGraph);
    }
}