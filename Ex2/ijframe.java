// Java program to create frames
// using association

import javax.swing.*;
class iijframe {
    JFrame frame;

    {
        // creating instance of JFrame with name "first way"
        frame=new JFrame("first way");

        // creates instance of JButton
        JButton button = new JButton("let's see");

        button.setBounds(0, 0, 100, 20);
        frame.add(button);

        button = new JButton("let's see1");

        button.setBounds(100,0 , 100, 20);
        frame.add(button);

        // setting close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // adds button in JFrame

        // sets 500 width and 600 height
        frame.setSize(1000, 1000);

        // uses no layout managers
        frame.setLayout(null);

        // makes the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        new iijframe();
    }
}
