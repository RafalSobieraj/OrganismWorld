package JavaProjekt.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class WorldGUI extends JFrame {

    private JTextArea textArea;

    private PrintStream standardOut;

    public WorldGUI(){

        super("Swiat organizmow");

        textArea = new JTextArea(50, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));

        standardOut = System.out;

        System.setOut(printStream);
        System.setErr(printStream);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10,10);
        constraints.anchor = GridBagConstraints.WEST;

        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        add(new JScrollPane(textArea), constraints);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 680);
        setLocationRelativeTo(null);
    }
}
