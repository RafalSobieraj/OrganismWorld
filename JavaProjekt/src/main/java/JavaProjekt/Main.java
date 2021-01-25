package JavaProjekt;

import JavaProjekt.GUI.CustomOutputStream;
import JavaProjekt.GUI.WorldGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WorldGUI().setVisible(true);
            }
        });

    }

}
