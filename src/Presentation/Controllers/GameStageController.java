package Presentation.Controllers;

import Presentation.Views.GameStageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStageController {
    private GameStageGUI gameStageGUI;

    public GameStageController() {
        gameStageGUI = new GameStageGUI();
        gameStageGUI.addEliminarListener(new EliminarListener());
        gameStageGUI.addTancarListener(new TancarListener());
        gameStageGUI.addAbandonarListener(new AbandonarListener());
    }

   /* public void displayGUI() {
        JFrame frame = new JFrame("Game Stage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameStageGUI.getMainPanel());
        frame.pack();
        frame.setVisible(true);
    }*/

    private static class EliminarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Lógica para el botón "Eliminar"
        }
    }

    private static class TancarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Lógica para el botón "Tancar"
        }
    }

    private static class AbandonarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Lógica para el botón "Abandonar"
        }
    }


}
