import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Mytsp {
    public static List<NodeData> checktsp(DirectedWeightedGraph g,List<NodeData> ListN){
        HashMap<Integer,Double> dist=new HashMap<>();
        for (int i = 0; i < ListN.size() ; i++) {
            dist.put(ListN.get(i).getKey(),Double.MAX_VALUE);
        }
        NodeData closest=ListN.get(0);

        ArrayList<NodeData> tsp=new ArrayList<>();
        tsp.add(ListN.get(0));
        while (ListN.size()>1) {
            if (closest!=null){
           // System.out.println(ListN.size());
         //   System.out.println(tsp.size());
            NodeData d=ListN.get(0);
            ListN.remove(d);
            tsp.add(findclosest(g,ListN,d));
           }
        }
        //if (!ListN.isEmpty()){
          //  return null;
        //}
        //tsp.add(ListN.get(0));
      //  System.out.println(tsp.size());
        return tsp;
    }

    private static NodeData findclosest(DirectedWeightedGraph g, List<NodeData> listN, NodeData d)
    {
        Double min=Double.MAX_VALUE;
        NodeData N=null;
        for (int i = 0; i <listN.size() ; i++) {
           List<NodeData>t= MyShortedPath.path(g,d.getKey(),listN.get(i).getKey());
           double t1=MyShortedPath.minDist(g,t);
           if (t1<min){
               min=t1;
               N=listN.get(i);
           }
        }
        return N;
    }
}
