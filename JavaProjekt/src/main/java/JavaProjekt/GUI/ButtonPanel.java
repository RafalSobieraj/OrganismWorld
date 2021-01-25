package JavaProjekt.GUI;


import JavaProjekt.Organisms.WorldBalance;
import JavaProjekt.World;
import JavaProjekt.WorldCreation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {

    public static final int HEIGHT = 150;
    public static final int WIDTH = 350;
    private JButton makeTurnButton;
    private JButton exitButton;
    private JButton startButton;
    World world;

    public ButtonPanel() {

        world = new World(10, 10);
        WorldBalance worldBalance = new WorldBalance(world);
        GridBagConstraints constraints = new GridBagConstraints();

        makeTurnButton = new JButton("Nastepna runda");
        exitButton = new JButton("Wyjscie");
        startButton = new JButton("Start");
        add(makeTurnButton);
        constraints.gridx = 1;
        add(exitButton);
        add(startButton);
        makeTurnButton.setVisible(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                printLog();
                makeTurnButton.setVisible(true);}
        });

        makeTurnButton.addActionListener(new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (i >= 20) {
                        makeTurnButton.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "Max round reached. The program is closed");
                    } else {
                        world.makeTurn();
                        worldBalance.woldBalance();
                        System.out.println(world);
                    }
                    i++;
                }
                catch(NullPointerException exception){
                        exception.printStackTrace();
                    }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(1);
            }
        });
    }

    private void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                    WorldCreation worldCreation = new WorldCreation();
                    worldCreation.worldCreation();
            }
        });
        thread.start();
    }
}
