package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatisticsGUI extends JPanel {

        private JLabel winsLabel;
        private JLabel lossesLabel;
        private JProgressBar winLossRatioBar;

    private JButton cancelButton;

    public static final String DELETE_CANCEL_BTN = "DELETE_CANCEL_BTN";

    public StatisticsGUI() {
        setLayout(new GridLayout());
        int wins = 70;
        int losses = 100; //Losses es partidas de bbdd


        // Etiquetas para mostrar las estadísticas
        winsLabel = new JLabel("Victorias: " + wins);
        lossesLabel = new JLabel("Derrotas: " + losses);
        JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
        labelsPanel.add(winsLabel);
        labelsPanel.add(lossesLabel);
        add(labelsPanel, BorderLayout.NORTH);

        // Gráfico de barras para mostrar la relación de victorias/derrotas
        int totalGames = wins + losses;
        int winLossRatio = (int) Math.round(wins * 100.0 / totalGames);
        winLossRatioBar = new JProgressBar(0, 100);
        winLossRatioBar.setValue(winLossRatio);
        winLossRatioBar.setStringPainted(true);
        winLossRatioBar.setPreferredSize(new Dimension(100, 20)); // Reducir tamaño de barra
        add(winLossRatioBar, BorderLayout.CENTER);

        // Botón de cancelar
        cancelButton = new JButton("Cancelar");
        cancelButton.setActionCommand(DELETE_CANCEL_BTN);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public void addStatListeners(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    }


