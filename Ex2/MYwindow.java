import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class MYwindow extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    DirectedWeightedGraphAlgorithms algo;
    DirectedWeightedGraph myGraph = new DirectedWeigtet();
    private MenuItem item1, item2, item3, itemIsConnected, itemPath, itemTSP, ItemGetNode, ItemGetEdge, ItemConnect, ItemRemoveEdge;
    private MenuItem ItemRemoveNode, itemAddNode;
    private MenuBar menuBar;
    private Menu menu, algoMenu, MenuGraph;
    JLabel label1;
    private DisplayGraphics p;
    JFrame myf1 = new JFrame();
    JFrame myf2 = new JFrame();
    JFrame myf3 = new JFrame();
    JFrame myfpath = new JFrame();
    JFrame myfIsconnectet = new JFrame();
    JFrame myftsp = new JFrame();
    JFrame myfGetNode = new JFrame();
    JFrame myfGetEdge = new JFrame();
    JFrame myfConnect = new JFrame();
    JFrame myfRemoveNode = new JFrame();
    JFrame myfRemoveEdge = new JFrame();
    JFrame myfAddNode = new JFrame();


    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panelPath = new JPanel();
    JPanel panelIconnected = new JPanel();
    JPanel panelTSP = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panelGetnode = new JPanel();
    JPanel panelgetEdge = new JPanel();
    JPanel panelConnect = new JPanel();
    JPanel panelRemoveNode = new JPanel();
    JPanel panelRemoveEdge = new JPanel();
    JPanel panelAddNode = new JPanel();


    JPanel Mypanel = new JPanel();
    JPanel Npanelrun = new JPanel();
    private JLabel label, labelCenter, labelWight, labeltsp, labgetNode, labelGetED, labelConS, labelconD, labelremN, labelremES, labelremED;
    private JTextField text, text1, textSrcPath, TDP, TSPSrc, TSPDest, getNode, GetES, GetED, ConS, conD, remN, addN, remES, remED, addLoc;
    //private  JButton  button;
    JButton ButtonLoad, ButtonTSP;
    JButton ButtonSave;
    JButton ButtonRun;
    JButton ButtonGetNode, ButtonAddNode;
    JButton ButtonGetEdge, ButtonConnect, ButtonRemoveEdge, ButtonRemoveNode;
    boolean paint = false;

    String t = new String();
    JFrame f1 = new JFrame();


    public MYwindow(DirectedWeightedGraphAlgorithms alg) {
        menu = new Menu("menu");
        algoMenu = new Menu("ALGOMenu");
        menuBar = new MenuBar();
        MenuGraph = new Menu("GraphMenu");
        item1 = new MenuItem("load");
        item1.addActionListener(this);
        item2 = new MenuItem("save");
        item2.addActionListener(this);
        item3 = new MenuItem("center");
        item3.addActionListener(this);
        itemIsConnected = new MenuItem("IsConnected?");
        itemIsConnected.addActionListener(this);
        itemPath = new MenuItem("Shorted path");
        itemPath.addActionListener(this);
        itemTSP = new MenuItem("findTSP");
        itemTSP.addActionListener(this);
        ItemGetNode = new MenuItem("GetNode");
        ItemGetNode.addActionListener(this);
        ItemGetEdge = new MenuItem("GetEdge");
        ItemGetEdge.addActionListener(this);
        ItemConnect = new MenuItem("Connect");
        ItemConnect.addActionListener(this);
        ItemRemoveNode = new MenuItem("RemoveNode");
        ItemRemoveNode.addActionListener(this);
        ItemRemoveEdge = new MenuItem("RemoveEdge");
        ItemRemoveEdge.addActionListener(this);
        itemAddNode = new MenuItem("addNode");
        itemAddNode.addActionListener(this);
        menu.add(item1);
        menu.add(item2);
        algoMenu.add(item3);
        algoMenu.add(itemIsConnected);
        algoMenu.add(itemPath);
        algoMenu.add(itemTSP);
        MenuGraph.add(itemAddNode);
        MenuGraph.add(ItemGetNode);
        MenuGraph.add(ItemGetEdge);
        MenuGraph.add(ItemConnect);
        MenuGraph.add(ItemRemoveNode);
        MenuGraph.add(ItemRemoveEdge);
        menu.add(algoMenu);
        menu.add(MenuGraph);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        menuBar.add(menu);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setMenuBar(menuBar);
        f1.setSize(1000, 1000);
        this.pack();


        myf1.setSize(200, 200);
        panel.setLayout(null);
        myf1.add(panel);

        myf2.setSize(200, 200);
        panel1.setLayout(null);
        myf2.add(panel1);

        myf3.setSize(200, 200);
        panel3.setLayout(null);
        myf3.add(panel3);

        myfIsconnectet.setSize(200, 200);
        panelIconnected.setLayout(null);
        myfIsconnectet.add(panelIconnected);

        myfpath.setSize(400, 400);
        panelPath.setLayout(null);
        myfpath.add(panelPath);

        myftsp.setSize(200, 200);
        panelTSP.setLayout(null);
        myftsp.add(panelTSP);

        myfGetNode.setSize(200, 200);
        panelGetnode.setLayout(null);
        myfGetNode.add(panelGetnode);

        myfGetEdge.setSize(400, 300);
        panelgetEdge.setLayout(null);
        myfGetEdge.add(panelgetEdge);

        myfConnect.setSize(200, 200);
        panelConnect.setLayout(null);
        myfConnect.add(panelConnect);

        myfRemoveNode.setSize(200, 200);
        panelRemoveNode.setLayout(null);
        myfRemoveNode.add(panelRemoveNode);

        myfAddNode.setSize(200, 200);
        panelAddNode.setLayout(null);
        myfAddNode.add(panelAddNode);

        myfRemoveEdge.setSize(200, 200);
        panelRemoveEdge.setLayout(null);
        myfRemoveEdge.add(panelRemoveEdge);

        ButtonLoad = new JButton("Loading");
        ButtonLoad.setBounds(10, 80, 80, 25);
        ButtonLoad.addActionListener(this);
        panel.add(ButtonLoad);
        text = new JTextField(50);
        text.setBounds(100, 20, 165, 25);
        panel.add(text);

        ButtonGetNode = new JButton("GetNode1");
        ButtonGetNode.setBounds(100, 80, 180, 25);
        ButtonGetNode.addActionListener(this);
        panelGetnode.add(ButtonGetNode);
        getNode = new JTextField(50);
        getNode.setBounds(100, 20, 165, 25);
        panelGetnode.add(getNode);

        ButtonSave = new JButton("Saving");
        ButtonSave.setBounds(100, 80, 180, 25);
        ButtonSave.addActionListener(this);
        panel1.add(ButtonSave);
        text1 = new JTextField(50);
        text1.setBounds(100, 20, 165, 25);
        panel1.add(text1);

        ButtonRun = new JButton("CheckPath");
        ButtonRun.setBounds(100, 100, 180, 25);
        ButtonRun.addActionListener(this);
        panelPath.add(ButtonRun);
        textSrcPath = new JTextField(50);
        textSrcPath.setBounds(100, 20, 165, 25);
        TDP = new JTextField(50);
        TDP.setBounds(100, 60, 165, 25);
        panelPath.add(textSrcPath);
        panelPath.add(TDP);

        ButtonTSP = new JButton("CheckTSP");
        ButtonTSP.setBounds(100, 100, 180, 25);
        ButtonTSP.addActionListener(this);
        panelTSP.add(ButtonTSP);
        TSPSrc = new JTextField(50);
        TSPSrc.setBounds(100, 20, 165, 25);
        TSPDest = new JTextField(50);
        TSPDest.setBounds(100, 50, 165, 25);
        panelTSP.add(TSPSrc);
        panelTSP.add(TSPDest);

        ButtonGetEdge = new JButton("FindThisEdge");
        ButtonGetEdge.setBounds(100, 100, 180, 25);
        ButtonGetEdge.addActionListener(this);
        panelgetEdge.add(ButtonGetEdge);
        GetES = new JTextField(50);
        GetES.setBounds(100, 20, 165, 25);
        GetED = new JTextField(50);
        GetED.setBounds(100, 50, 165, 25);
        panelgetEdge.add(GetES);
        panelgetEdge.add(GetED);

        ButtonConnect = new JButton("ConnectNow");
        ButtonConnect.setBounds(100, 100, 180, 25);
        ButtonConnect.addActionListener(this);
        panelConnect.add(ButtonConnect);
        ConS = new JTextField(50);
        ConS.setBounds(100, 20, 165, 25);
        conD = new JTextField(50);
        conD.setBounds(100, 50, 165, 25);
        panelConnect.add(ConS);
        panelConnect.add(conD);

        ButtonRemoveEdge = new JButton("RemoveNow");
        ButtonRemoveEdge.setBounds(100, 100, 180, 25);
        ButtonRemoveEdge.addActionListener(this);
        panelRemoveEdge.add(ButtonRemoveEdge);
        remES = new JTextField(50);
        remES.setBounds(100, 20, 165, 25);
        remED = new JTextField(50);
        remED.setBounds(100, 50, 165, 25);
        panelRemoveEdge.add(remES);
        panelRemoveEdge.add(remED);

        ButtonAddNode = new JButton("AddThisNode");
        ButtonAddNode.setBounds(100, 100, 180, 25);
        ButtonAddNode.addActionListener(this);
        panelAddNode.add(ButtonAddNode);
        addN = new JTextField(50);
        addN.setBounds(100, 20, 165, 25);
        addLoc = new JTextField(50);
        addLoc.setBounds(100, 50, 165, 25);
        panelAddNode.add(addN);
        panelAddNode.add(addLoc);


        ButtonRemoveNode = new JButton("RemoveThisNode");
        ButtonRemoveNode.setBounds(100, 100, 180, 25);
        ButtonRemoveNode.addActionListener(this);
        panelRemoveNode.add(ButtonRemoveNode);
        remN = new JTextField(50);
        remN.setBounds(100, 50, 165, 25);
        panelRemoveNode.add(remN);


        f1.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        algo = new Algo();

        String str = e.getActionCommand();
        if (str.equals("load")) {
            label = new JLabel("load");
            label.setBounds(10, 20, 80, 25);
            panel.add(label);
            myf1.setVisible(true);
        }

        if (str.equals("Loading")) {
            if (paint) {

            }

            t = text.getText();
            System.out.println(t);
            try {
                this.algo.load(t);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            myGraph = algo.getGraph();
            myf1.setVisible(true);
            p = new DisplayGraphics(algo);
            f1.add(p);
            paint = true;
            this.add(Npanelrun);
            f1.add(p);
            f1 = new JFrame();
            f1.add(p);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setMenuBar(menuBar);
            f1.setSize(1000, 1000);
            f1.repaint();
            f1.setVisible(true);
        }

        if (str.equals("save")) {
            label1 = new JLabel("save:");
            label1.setBounds(10, 20, 80, 25);
            panel1.add(label1);
            myf2.setVisible(true);
        }
        if (str.equals("saving")) {
            String t = text1.getText();
            boolean a = this.algo.save(t);


            myf2.setVisible(true);
        }


        if (str.equals("center")) {
            algo.init(myGraph);
            int center1 = algo.center().getKey();
            labelCenter = new JLabel("center= " + center1);
            labelCenter.setBounds(10, 20, 80, 25);
            panel3.add(labelCenter);
            myf3.setVisible(true);
        }
        if (str.equals("IsConnected?")) {
            algo.init(myGraph);
            boolean connected = algo.isConnected();
            labelCenter = new JLabel(String.valueOf(connected));
            labelCenter.setBounds(10, 20, 80, 25);
            panelIconnected.add(labelCenter);
            myfIsconnectet.setVisible(true);
        }
        if (str.equals("Shorted path")) {
            labelWight = new JLabel("setNodes:");
            labelWight.setBounds(10, 20, 80, 25);
            panelPath.add(labelWight);
            myfpath.setVisible(true);
        }
        if (str.equals("CheckPath")) {
            String s = textSrcPath.getText();
            String d = TDP.getText();
            int src = Integer.parseInt(s);
            int dest = Integer.parseInt(d);
            algo.init(myGraph);
            double dist = algo.shortestPathDist(src, dest);
            if (labelWight.getText() != "") {
                labelWight.setText(String.valueOf(dist));
            } else {
                labelWight = new JLabel(String.valueOf(dist));
            }
            labelWight.setBounds(100, 140, 180, 25);
            panelPath.add(labelWight);
            myfpath.setVisible(true);
        }
        if (str.equals("findTSP")) {
            labeltsp = new JLabel("setNodes:");
            labeltsp.setBounds(10, 20, 80, 25);
            panelTSP.add(labeltsp);
            myftsp.setVisible(true);

            myftsp.setVisible(true);
        }
        if (str.equals("CheckTSP")) {
            String s = TSPSrc.getText();
            String d = TSPDest.getText();
            int src = Integer.parseInt(s);
            int dest = Integer.parseInt(d);
            algo.init(myGraph);
            String lis = new String();
            ArrayList<NodeData> ts = (ArrayList<NodeData>) algo.tsp(algo.shortestPath(src, dest));
            for (int i = 0; i < ts.size(); i++) {
                int cur = ts.get(i).getKey();
                lis += cur + ",";
            }
            if (labeltsp.getText() != "") {
                labeltsp.setText(lis);
            } else {
                labeltsp = new JLabel(lis);
            }
            labeltsp.setBounds(100, 130, 180, 100);
            panelTSP.add(labeltsp);
            myftsp.setVisible(true);
        }
        if (str.equals("GetNode")) {
            labgetNode = new JLabel("node:");
            labgetNode.setBounds(10, 20, 80, 25);
            panelGetnode.add(labgetNode);
            myfGetNode.setVisible(true);
        }
        if (str.equals("GetNode1")) {
            String t = getNode.getText();
            NodeData N = myGraph.getNode(Integer.parseInt(t));


            myfGetNode.setVisible(true);
        }

        if (str.equals("GetEdge")) {
            labelGetED = new JLabel("setNodes:");
            labelGetED.setBounds(10, 20, 80, 25);
            panelgetEdge.add(labelGetED);
            myfGetEdge.setVisible(true);

            myfGetEdge.setVisible(true);
        }
        if (str.equals("FindThisEdge")) {
            String s = GetES.getText();
            String d = GetED.getText();
            int src = Integer.parseInt(s);
            int dest = Integer.parseInt(d);
            double w = myGraph.getEdge(src, dest).getWeight();
            if (labelGetED.getText() != "") {
                labelGetED.setText(String.valueOf(w));
            } else {
                labelGetED = new JLabel(String.valueOf(w));
            }
            labelGetED.setBounds(100, 130, 180, 100);
            panelgetEdge.add(labelGetED);
            myfGetEdge.setVisible(true);
        }
        if (str.equals("Connect")) {
            labelConS = new JLabel("setNodes:");
            labelConS.setBounds(10, 20, 80, 25);
            panelConnect.add(labelConS);
            myfConnect.setVisible(true);
        }
        if (str.equals("ConnectNow")) {
            String s = ConS.getText();
            String d = conD.getText();
            int src = Integer.parseInt(s);
            int dest = Integer.parseInt(d);
            NodeData src1 = myGraph.getNode(src);
            NodeData dest1 = myGraph.getNode(dest);


            myGraph.connect(src, dest, 1.1);
            this.algo.init(myGraph);
            Point2D t = new Point2D() {
                @Override
                public double getX() {
                    return src1.getLocation().x();
                }


                @Override
                public double getY() {
                    return src1.getLocation().y();
                }

                @Override
                public void setLocation(double x, double y) {

                }
            };
            Point2D de = new Point2D() {
                @Override
                public double getX() {
                    return dest1.getLocation().x();
                }

                @Override
                public double getY() {
                    return dest1.getLocation().y();
                }

                @Override
                public void setLocation(double x, double y) {

                }
            };
            DisplayGraphics c = new DisplayGraphics(algo);
            p.setEdges(t, de);
            f1.add(p);
            f1.repaint();
            f1.setVisible(true);

            myfConnect.setVisible(true);
        }
        if (str.equals("RemoveEdge")) {
            labelremES = new JLabel("setNodes:");
            labelremES.setBounds(10, 20, 80, 25);
            panelRemoveEdge.add(labelremES);
            myfRemoveEdge.setVisible(true);
        }
        if (str.equals("RemoveNow")) {
            String s = remES.getText();
            String d = remED.getText();
            int src = Integer.parseInt(s);
            int dest = Integer.parseInt(d);
            NodeData src1 = myGraph.getNode(src);
            NodeData dest1 = myGraph.getNode(dest);

            if (myGraph.getEdge(src, dest) != null) {
                myGraph.removeEdge(src, dest);
                this.algo.init(myGraph);
                Point2D t = new Point2D() {
                    @Override
                    public double getX() {
                        return src1.getLocation().x();
                    }


                    @Override
                    public double getY() {
                        return src1.getLocation().y();
                    }

                    @Override
                    public void setLocation(double x, double y) {

                    }
                };
                Point2D de = new Point2D() {
                    @Override
                    public double getX() {
                        return dest1.getLocation().x();
                    }

                    @Override
                    public double getY() {
                        return dest1.getLocation().y();
                    }

                    @Override
                    public void setLocation(double x, double y) {

                    }
                };
                // DisplayGraphics.addEdge(t, de, p.getEdges());
                p.removeEdge(t, de);
                f1 = new JFrame();
                f1.add(p);
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setMenuBar(menuBar);
                f1.setSize(1000, 1000);
                f1.repaint();
                f1.setVisible(true);

                myfRemoveEdge.setVisible(true);

            }
        }
        if (str.equals("RemoveNode")) {
            labelremN = new JLabel("setNodes:");
            remN.setBounds(10, 20, 80, 25);
            panelRemoveNode.add(labelremN);
            myfRemoveNode.setVisible(true);
        }
        if (str.equals("RemoveThisNode")) {
            String s = remN.getText();
            int src = Integer.parseInt(s);
            NodeData src1 = myGraph.getNode(src);
            if (myGraph.getNode(src) != null) {
                Point2D po = new Point2D() {
                    @Override
                    public double getX() {
                        return src1.getLocation().x();
                    }

                    @Override
                    public double getY() {
                        return src1.getLocation().y();
                    }

                    @Override
                    public void setLocation(double x, double y) {

                    }
                };
                this.myGraph.removeNode(src);
                this.algo.init(myGraph);
                this.p.removeNode(po);
                p.updateedge(myGraph);
                f1.add(p);
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f1.setMenuBar(menuBar);
                f1.setSize(1000, 1000);
                f1.repaint();
                f1.setVisible(true);
            }
        }
        if (str.equals("addNode")) {
            myfAddNode.setVisible(true);
        }
        if (str.equals("AddThisNode")) {
            String s = addN.getText();
            String l = addLoc.getText();
            String xyz = new String();
            double loc[] = new double[3];
            int count = 0;

            for (int j = 0; j < l.length(); j++) {
                if (l.charAt(j) != ',') {
                    xyz += l.charAt(j);
                } else {
                    loc[count] = Double.parseDouble(xyz);
                    xyz = new String();
                    count++;
                }
            }

            MyLoc c = new MyLoc(loc[0], loc[1], loc[2]);
            ND N = new ND(c, Integer.parseInt(s));
            myGraph.addNode(N);

            int src = Integer.parseInt(s);
            NodeData src1 = myGraph.getNode(src);
            Point2D po = new Point2D() {
                @Override
                public double getX() {
                    return src1.getLocation().x();
                }

                @Override
                public double getY() {
                    return src1.getLocation().y();
                }

                @Override
                public void setLocation(double x, double y) {

                }
            };
            this.p.Addnode(po, c);
            this.algo.init(myGraph);
            f1.add(p);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f1.setMenuBar(menuBar);
            f1.setSize(1000, 1000);
            f1.repaint();
            f1.setVisible(true);


        }
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
