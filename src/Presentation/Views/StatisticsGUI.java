package Presentation.Views;

import Business.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class StatisticsGUI extends JPanel {

    private JLabel title;

    private JButton cancelButton, deleteButton, closeButton;
    private Color buttonColor;
    //private JProgressBar winLossRatioBar;
    private JPanel generalPanel, borderPanel, threeB;


    public static final String CANCEL_BTN = "CANCEL_BTN";
    public static final String DELETE_BTN = "DELETE_BTN";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";


    private SaveGame saveGame;

    public StatisticsGUI(SaveGame saveGame) {

        this.saveGame = saveGame;

        //System.out.println(wins);
        //System.out.println(totalGames);

        buttonColor = new Color(124,136,248);

        title = new JLabel("PLAYER STATS");
        title.setFont(new Font("Iceland",Font.BOLD,96));
        title.setForeground(Color.blue);


        JPanel leftPanel = new JPanel(new GridLayout(4, 1));


        // Botón de cancelar
        cancelButton = new JButton("Enrere");
        cancelButton.setActionCommand(StatisticsGUI.CANCEL_BTN);
        cancelButton.setFont(new Font("Inter",Font.BOLD,14));
        cancelButton.setBackground(buttonColor);
        cancelButton.setForeground(Color.white);

        deleteButton = new JButton("Eliminar Compte");
        deleteButton.setActionCommand(StatisticsGUI.DELETE_BTN);
        deleteButton.setFont(new Font("Inter",Font.BOLD,14));
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(Color.white);

        closeButton = new JButton("Tancar sessió");
        closeButton.setActionCommand(StatisticsGUI.LOGOUT_BTN);
        closeButton.setFont(new Font("Inter",Font.BOLD,14));
        closeButton.setBackground(buttonColor);
        closeButton.setForeground(Color.white);

        borderPanel = new JPanel(new FlowLayout());
        threeB = new JPanel(new GridLayout(1,3,10,10));
        threeB.add(cancelButton);
        threeB.add(deleteButton);
        threeB.add(closeButton);
        borderPanel.setOpaque(false);
        borderPanel.add("North",threeB);


        generalPanel = new JPanel(new GridLayout(1,2,10,10));
        generalPanel.add(title);
        generalPanel.add(borderPanel);

        generalPanel.setOpaque(false);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(50,300,50,300));






        this.setBackground(new Color(217,249,253));

        this.add(generalPanel);


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        String user = saveGame.getUser();
        int wins = saveGame.calcularVictorias(user);
        int totalGames = saveGame.calcularPartidas(user);
        int losses = totalGames - wins;
        double winPercentage = (double) wins / totalGames;
        double lossPercentage = (double) losses / totalGames;

        int width = getWidth();
        int height = getHeight();

        int diameter = Math.min(width, height) / 6;
        int centerX = width / 4;
        int centerY = height -250;

        // Obtener el tamaño del texto de victorias y derrotas
        FontMetrics fontMetrics = g.getFontMetrics();
        int winsTextWidth = fontMetrics.stringWidth("Wins: " + wins);
        int lossesTextWidth = fontMetrics.stringWidth("Losses: " + losses);
        int textHeight = fontMetrics.getHeight();

        // Calcular la posición de los textos encima del gráfico circular
        int winsTextX = centerX - winsTextWidth / 2;
        int winsTextY = centerY - diameter - textHeight - 10; // 10 pixels de separación
        int lossesTextX = centerX - lossesTextWidth / 2;
        int lossesTextY = centerY - diameter - textHeight * 2 - 20; // 20 pixels de separación

        // Dibujar el texto de victorias en verde
        g.setColor(Color.green);
        g.drawString("Wins: " + wins, winsTextX, winsTextY);

// Dibujar el texto de derrotas en rojo
        g.setColor(Color.red);
        g.drawString("Losses: " + losses, lossesTextX, lossesTextY);


        int winAngle = (int) (360 * winPercentage);
        int lossAngle = (int) (360 * lossPercentage);

        // Dibujar el segmento de victorias
        g.setColor(Color.green);
        g.fillArc(centerX - diameter, centerY - diameter, diameter * 2, diameter * 2, 0, winAngle);

        // Dibujar el segmento de derrotas
        g.setColor(Color.red);
        g.fillArc(centerX - diameter, centerY - diameter, diameter * 2, diameter * 2, winAngle, lossAngle);

        // Dibujar el porcentaje de victorias
        g.setColor(Color.white);
        g.setFont(new Font("Inter", Font.BOLD, 20));
        String winPercentageString = String.format("%.2f%%", winPercentage * 100);
        g.drawString(winPercentageString, centerX - 50, centerY);

        // Dibujar el porcentaje de derrotas
        g.setColor(Color.white);
        g.setFont(new Font("Inter", Font.BOLD, 20));
        String lossPercentageString = String.format("%.2f%%", lossPercentage * 100);
        g.drawString(lossPercentageString, centerX - 50, centerY + 30);
        g.setColor(Color.blue);

        //Fin del diagrama de quesos

        //Diagrama de BARRAS

        ArrayList<Integer> gameResults = saveGame.extraerAtaques(user);


        int minValue = 17; // Valor mínimo en el eje x

        int maxValue = saveGame.extraerAtaqueMasAlto(user);

        int numBars = maxValue - minValue + 1; // Número de barras a mostrar

// Ancho de cada barra y espacio entre barras
        int barWidth = 9;
        int barSpacing = 5;

// Crear un mapa para realizar el recuento de la frecuencia de los números
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < gameResults.size(); i++) {
            int gameResult = gameResults.get(i);
            frequencyMap.put(gameResult, frequencyMap.getOrDefault(gameResult, 0) + 1);
        }

// Dibujo gráfico
        int chartX = width / 3 +120;
        int chartY = height / 2;
        int barChartHeight = 200; // Altura del gráfico de barras


// Dibujar el texto del número total de juegos
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 12)); // Establecer la fuente y tamaño del texto
        String totalGamesText = "Total Games: " + totalGames;
        String info1 = "\nEje horizontal : Ataques por partida";
        String info2 = "\nBarras verticales : Partidas con ese número de ataques ";
        int textWidth = g.getFontMetrics().stringWidth(totalGamesText); // Obtener el ancho del texto
        int textX = chartX - textWidth / 2; // Calcular la posición x del texto
        int textY = chartY - 20; // Calcular la posición y del texto
        g.drawString(totalGamesText, textX, textY);
        g.drawString(info1, textX, textY-15);
        g.drawString(info2, textX, textY-30);


// Dibujar las barras según la frecuencia de los números
        for (int i = minValue; i <= maxValue; i++) {
            int gameResult = frequencyMap.getOrDefault(i, 0);

            // Calcula el tamaño y posición de la barra
            int barHeight = (int) (((double) gameResult / gameResults.size()) * barChartHeight);
            int barX = chartX + (barWidth + barSpacing) * (i - minValue);
            int barY = chartY + barChartHeight - barHeight;

            // Dibujar la barra
            g.setColor(Color.blue);
            g.fillRect(barX, barY, barWidth, barHeight);
            g.setColor(Color.black);
            g.drawRect(barX, barY, barWidth, barHeight);

            // Dibujar el número debajo de la barra
            g.setColor(Color.black);
            Font font = new Font("Arial", Font.PLAIN, 9);
            g.setFont(font);
            g.drawString(Integer.toString(i), barX + barWidth / 2, chartY + barChartHeight + 15);

            // Obtener la frecuencia del número en el mapa
            int frequency = frequencyMap.getOrDefault(i, 0);

            // Dibujar la frecuencia encima de la barra
            g.setColor(Color.black);
            g.drawString(Integer.toString(frequency), barX + barWidth / 2, barY - 5);


        }


    }



    public void addStatListeners(ActionListener listener) {
        cancelButton.addActionListener(listener);
        closeButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
    }

    private int calculateWinLossRatio(int wins, int losses) {
        int totalGames = wins + losses;
        return (int) Math.round(wins * 100.0 / totalGames);
    }
}
