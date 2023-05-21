package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatisticsGUI extends JPanel {

    private JLabel winsLabel;
    private JLabel lossesLabel;
    private JLabel winLossRatioLabel;
    private JButton cancelButton;

    public static final String CANCEL_BTN = "CANCEL_BTN";

    public StatisticsGUI() {

        int wins = 70;
        int losses = 100; //Losses es partidas de bbdd

        setLayout(new BorderLayout());

        // Panel izquierdo para el gráfico de victorias y derrotas
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(Box.createVerticalGlue()); // Espacio en blanco en la parte superior
        winsLabel = new JLabel("Victorias: " + wins);
        lossesLabel = new JLabel("Derrotas: " + losses);
        leftPanel.add(winsLabel);
        leftPanel.add(lossesLabel);
        leftPanel.add(Box.createVerticalGlue()); // Espacio en blanco en la parte inferior

        // Panel derecho para el porcentaje de victorias frente a las derrotas
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.add(Box.createVerticalGlue()); // Espacio en blanco en la parte superior
        winLossRatioLabel = new JLabel("Porcentaje de victorias: " + calculateWinLossRatio(wins, losses) + "%");
        rightPanel.add(winLossRatioLabel);
        rightPanel.add(Box.createVerticalGlue()); // Espacio en blanco en la parte inferior

        // JSplitPane para dividir la pantalla
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.5); // Ajustar el tamaño de las dos partes
        add(splitPane, BorderLayout.CENTER);

        // Botón de cancelar
        cancelButton = new JButton("Cancelar");
        cancelButton.setActionCommand(CANCEL_BTN);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addStatListeners(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    private int calculateWinLossRatio(int wins, int losses) {
        int totalGames = wins + losses;
        return (int) Math.round(wins * 100.0 / totalGames);
    }
}
