import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import gui.DisplayGraphics;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class JFrametest extends Canvas {

    public void paint(Graphics g) {
        System.out.println(1);
        g.drawString("Hello",40,40);
        setBackground(Color.BLUE);
        g.fillRect(130, 30,100, 80);
        g.drawOval(30,130,50, 60);
        setForeground(Color.BLUE);
        g.fillOval(130,130,10, 10);
        g.drawArc(30, 200, 40,50,90,60);
        g.fillArc(30, 130, 40,50,180,40);

    }
    public static void main(String[] args) {
        DisplayGraphics m=new DisplayGraphics();
        JFrame f=new JFrame();
        f.add(m);
        f.setSize(400,400);
        //f.setLayout(null);
        f.setVisible(true);

    }

}
