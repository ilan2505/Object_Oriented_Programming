import api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


class DirectedWeigtetTest {
    ArrayList<NodeData> mynode;
    HashMap<NodeData, HashMap<NodeData, EdgeData>> edge;
    DirectedWeightedGraphAlgorithms algp=new Algo();
    DirectedWeightedGraph g = new DirectedWeigtet();
    GeoLocation l1 = new MyLoc(1, 2, 4);

    @Test
    void testNode() {
        ND bb = new ND(l1, 1);
        g.addNode(bb);
        ND y = new ND(l1, 2);
        g.addNode(y);
        ND x1 = new ND(l1, 3);
        g.addNode(x1);
        ND y1 = new ND(l1, 4);
        g.addNode(y1);
        ND x2 = new ND(l1, 5);
        g.addNode(x2);
        ND y2 = new ND(l1, 6);
        g.addNode(y2);
        ND x3 = new ND(l1, 7);
        g.addNode(x3);
        ND y3 = new ND(l1, 8);
        g.addNode(y3);
        ND x4 = new ND(l1, 9);
        g.addNode(x4);
        ND y4 = new ND(l1, 10);
        g.addNode(y4);
        ND x5 = new ND(l1, 11);
        g.addNode(x5);
        ND y5 = new ND(l1, 12);
        g.addNode(y5);

        Assertions.assertEquals(g.nodeSize(), 12);
        Assertions.assertEquals(g.getNode(1).getKey(), 1);

    }

    @Test
    void TestEdge() {
        ND bb = new ND(l1, 1);
        g.addNode(bb);
        ND y = new ND(l1, 2);
        g.addNode(y);
        ND x1 = new ND(l1, 3);
        g.addNode(x1);
        ND y1 = new ND(l1, 4);
        g.addNode(y1);
        ND x2 = new ND(l1, 5);
        g.addNode(x2);
        ND y2 = new ND(l1, 6);
        g.addNode(y2);
        ND x3 = new ND(l1, 7);
        g.addNode(x3);
        ND y3 = new ND(l1, 8);
        g.addNode(y3);
        ND x4 = new ND(l1, 9);
        g.addNode(x4);
        ND y4 = new ND(l1, 10);
        g.addNode(y4);
        ND x5 = new ND(l1, 11);
        g.addNode(x5);
        ND y5 = new ND(l1, 12);
        g.connect(1, 2, 3);
        g.connect(1, 4, 2);
        g.connect(1, 3, 4);
        g.connect(4, 5, 3);
        g.connect(5, 1, 2);
        g.connect(5, 6, 2);
        g.connect(2, 4, 3);
        g.connect(11, 7, 2);
        g.connect(3, 8, 2);
        g.connect(11, 8, 3);
        g.connect(5, 9, 2);
        g.connect(2, 11, 2);
        g.connect(7, 10, 3);
        g.connect(3, 11, 2);
        g.connect(3, 11, 3);
        g.connect(8, 3, 2);
        Assertions.assertEquals(g.edgeSize(),16);
        g.removeNode(11);
        Assertions.assertEquals(g.edgeSize(),12);


    }
}



