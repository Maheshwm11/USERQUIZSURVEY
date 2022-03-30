import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class MyPanel extends JPanel {
    private JButton jcomp1;
    private JLabel jcomp2;

    public MyPanel() {
        //construct components
        jcomp1 = new JButton ("Open File");
        jcomp1.addActionListener(actionListener);
        jcomp2 = new JLabel ("Final Grade Calculator");

        //adjust size and set layout
        setPreferredSize (new Dimension (243, 171));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (55, 80, 130, 30);
        jcomp2.setBounds (55, 25, 130, 25);
    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jcomp1) {
                String input;
                ArrayList<ArrayList<Quiz>> quiz2D;
                do { // course name prompt
                    input = JOptionPane.showInputDialog(null, ".csv file name only:",
                            "Enter file name", JOptionPane.QUESTION_MESSAGE);
                    if ((input.equals("")) || (input.isBlank())) {
                        JOptionPane.showMessageDialog(null, "file name cannot be blank!",
                                "Enter file name", JOptionPane.ERROR_MESSAGE);
                    }
                } while (input.equals("") || input.isBlank());
                try {
                    quiz2D = Data.readInputFile(input);
                    QuizApplication.calculate(quiz2D);
                    JOptionPane.showMessageDialog(null, "Results exported for student Check file!",
                                "Exported", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "File not found!",
                                "Enter correct file name", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };
    public static void main (String[] args) {
        JFrame frame = new JFrame ("Calculate");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }
}
