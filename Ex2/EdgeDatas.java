import api.EdgeData;
import api.NodeData;

public class EdgeDatas implements EdgeData {
    int src;
    int dest;
    double weight;
    String info;
    int tag;
    public EdgeDatas(int s,double w,int d){
        this.src=s;
        this.dest=d;
        this.weight=w;
        this.info="";
        this.tag=0;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest
                ;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
