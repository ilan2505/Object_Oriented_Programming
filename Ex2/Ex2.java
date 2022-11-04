import api.DirectedWeightedGraph;

    import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import java.io.IOException;

public class Ex2 {

    /**
     * This class is the main class for Ex2 - your implementation will be tested using this class.
     */

        /**
         * This static function will be used to test your implementation
         * @param json_file - a json file (e.g., G1.json - G3.gson)
         * @return
         */
        public static DirectedWeightedGraph getGrapg(String json_file) throws IOException {
            DirectedWeightedGraph ans =  getGrapgAlgo(json_file).getGraph();
            // ****** Add your code here ******
            //
            // ********************************
            return ans;
        }
        /**
         * This static function will be used to test your implementation
         * @param json_file - a json file (e.g., G1.json - G3.gson)
         * @return
         */
        public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws IOException {
            DirectedWeightedGraphAlgorithms ans =null ;
            ans=new Algo();
            ans.load(json_file);
            // ****** Add your code here ******
            //
            // ********************************
            return ans;
        }
        /**
         * This static function will run your GUI using the json fime.
         * @param json_file - a json file (e.g., G1.json - G3.gson)
         *
         */
        public static void runGUI(String json_file) throws IOException {
            DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
            MYwindow m=new MYwindow(alg);

        }

    public static void main(String[] args) throws IOException {
        runGUI("G2.json");
    }
    }

