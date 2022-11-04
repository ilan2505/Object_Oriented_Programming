// source: https://www.javatpoint.com/java-jmenuitem-and-jmenu

import java.awt.*;

public class MenuExample {

    Menu menu, submenu, submenu1;
    MenuItem i1, i2, i3, i4, i5, i6;

    public  Menu MenuExample1() {
        Frame f = new Frame("Menu and MenuItem Example");
        MenuBar mb = new MenuBar();
        menu = new Menu("Menu");
        submenu = new Menu("Algo");
        submenu1 = new Menu("Graph");
        i1 = new MenuItem("Graph");
        i2 = new MenuItem("Algo");
        i3 = new MenuItem("Load");
        //i3.addActionListener((ActionListener) this);
        i4 = new MenuItem("Is connected");
        i5 = new MenuItem("Delete Node");
        i6 = new MenuItem("Delete Edge");

        submenu1.add(i5);
        submenu1.add(i6);
        submenu.add(i2);
        submenu.add(i3);
        submenu.add(i4);
        menu.add(submenu);
        menu.add(submenu1);
        mb.add(menu);
        f.setMenuBar(mb);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
        return menu;
    }

    public static void main(String args[]) {
        new MenuExample();
    }


}