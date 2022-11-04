import api.DirectedWeightedGraphAlgorithms;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AlgoTest {
        DirectedWeightedGraphAlgorithms algo=Ex2.getGrapgAlgo("c:/1000nodes.json");

    AlgoTest() throws IOException {
    }


    @Test
    void isConnected() throws IOException {
        //algo.load("c:/10000nodes.json");
        Assertions.assertTrue(algo.isConnected());
    }

    @Test
    void shortestPathDist() throws IOException {
       // algo.load("c:/10000nodes.json");
        algo.shortestPathDist(1,111);
    }

    @Test
    void shortestPath() throws IOException {
      //  algo.load("c:/10000nodes.json");
        algo.shortestPath(1,111);

    }

    @Test
    void center() throws IOException {
        algo.center();
;
    }

    @Test
    void tsp() {
        algo.tsp(algo.shortestPath(1,4));
    }
}