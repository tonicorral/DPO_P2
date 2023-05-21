package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class StatisticsGUI extends JPanel {

    private JLabel winsLabel, lossesLabel, title, percentageLabel, partidasTotales;

    private JButton cancelButton, deleteButton, closeButton;
    private Color buttonColor;
    private JProgressBar winLossRatioBar;
    private JPanel generalPanel, borderPanel, twoB, generalPanel2;


    private int[] gameResults = {30, 40, 25, 35, 50, 60, 45, 55, 20, 38, 42, 48, 33, 27, 52, 37, 65, 22, 29, 44, 63, 31, 57, 47, 39, 53, 28, 41, 56, 59, 34, 23, 36, 66, 43, 26, 51, 32, 58, 21, 24, 49, 46, 67, 54, 68, 69};
    private int barWidth =5; // Ancho de cada barra
    private int barSpacing; // Espacio entre barras
    private int barChartHeight = 100; // Altura del gráfico de barras


    public static final String CANCEL_BTN = "CANCEL_BTN";
    public static final String DELETE_BTN = "DELETE_BTN";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";
    private int wins = 70,totalGames = 1000, losses = 100; //Losses es partidas de bbdd;


    public StatisticsGUI() {


        buttonColor = new Color(124,136,248);

        title = new JLabel("PLAYER STATS");
        title.setFont(new Font("Iceland",Font.BOLD,96));
        title.setForeground(Color.white);

        JPanel leftPanel = new JPanel(new GridLayout(4, 1));

        winsLabel = new JLabel("Wins: " + wins);
        lossesLabel = new JLabel("Losses: " + losses);
        winsLabel.setForeground(Color.GREEN);
        lossesLabel.setForeground(Color.RED);
        percentageLabel = new JLabel("Percentage of Wins: ");
        leftPanel.add(winsLabel);
        leftPanel.add(lossesLabel);
        leftPanel.add(percentageLabel);

        winLossRatioBar = new JProgressBar(0, 100);
        winLossRatioBar.setForeground(Color.GREEN);
        winLossRatioBar.setValue(calculateWinLossRatio(wins, losses));
        winLossRatioBar.setStringPainted(true);
        leftPanel.add(winLossRatioBar);
        leftPanel.setBackground(new Color(217,249,253));

        partidasTotales = new JLabel("Total games: " + totalGames);

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
        twoB = new JPanel(new GridLayout(1,3,10,10));
        twoB.add(cancelButton);
        twoB.add(deleteButton);
        twoB.add(closeButton);
        borderPanel.setOpaque(false);
        borderPanel.add("North",twoB);


        generalPanel = new JPanel(new GridLayout(2,2,10,10));
        generalPanel.add(title);
        generalPanel.add(borderPanel);
        generalPanel.add(leftPanel);
        generalPanel.add(partidasTotales, CENTER_ALIGNMENT);
        generalPanel.setOpaque(false);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(50,300,50,300));






        this.setBackground(new Color(217,249,253));

        this.add(generalPanel);


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Llamar a la bbdd para sacar wins y losses
        int totalGames = wins + losses;
        double winPercentage = (double) wins / totalGames;
        double lossPercentage = (double) losses / totalGames;

        int width = getWidth();
        int height = getHeight();

        int diameter = Math.min(width, height) / 6;
        int centerX = width / 4;
        int centerY = height -250;

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

        int numBars = 52; // Número de barras a mostrar
        int minValue = 17; // Valor mínimo en el eje x
        int maxValue = 69; // Valor máximo en el eje x

        barWidth = 10; // Ancho de cada barra
        barSpacing = 5; // Espacio entre barras

        // Verificar que las barras se ajusten correctamente en el ancho de la ventana
        if ((barWidth + barSpacing) * numBars > width) {
            barWidth = (width - barSpacing * (numBars - 1)) / numBars;
        }


        //Dibujo gráfico

        // posición y tamaño del gráfico de barras en la parte derecha
        int chartWidth = (barWidth + barSpacing) * gameResults.length - barSpacing;
        int chartX = width /2;
        int chartY = height -250;

        // Dibujar las barras según los resultados de las partidas
        for (int i = 0; i < gameResults.length; i++) {
            int gameResult = gameResults[i];

            // Calcula el tamaño y posición de la barra
            int barHeight = (int) (((double) gameResult / 20) * barChartHeight);
            int barX = chartX + (barWidth + barSpacing) * i;
            int barY = chartY + barChartHeight - barHeight;

            // Dibujar la barra
            g.setColor(Color.blue);
            g.fillRect(barX, barY, barWidth, barHeight);
            g.setColor(Color.black);
            g.drawRect(barX, barY, barWidth, barHeight);

            // Dibujar el número debajo de la barra
            g.setColor(Color.black);
            Font font = new Font("Arial", Font.PLAIN, 9); // Establece el tamaño de fuente deseado
            g.setFont(font);
            g.drawString(Integer.toString(i + 17), barX + barWidth / 2, chartY + barChartHeight + 15);

            // Obtener el tamaño del texto
            FontMetrics fontMetrics = g.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(Integer.toString(gameResult));
            int textHeight = fontMetrics.getHeight();

            // Calcular la posición del texto en la parte superior de la barra
            int textX = barX + (barWidth - textWidth) / 2; // Centrar el texto horizontalmente
            int textY = barY - textHeight; // Posicionar el texto arriba de la barra

            // Dibujar el número encima de la barra
            g.setColor(Color.black);
            g.drawString(Integer.toString(gameResult), textX, textY);
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
