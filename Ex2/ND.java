import api.GeoLocation;
import api.NodeData;
import org.w3c.dom.Node;

public class ND implements NodeData {
    double id;
    double weight;
    GeoLocation loc;
    String info;
    int tag;
    public ND(GeoLocation loc,double id) {
        this.id=id;
        this.loc=loc;
        this.weight=0;
        this.info="";
        this.tag=0;

    }
    @Override
    public int getKey() {
        return (int) this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.loc;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.loc=p;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
            this.weight=w;

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
