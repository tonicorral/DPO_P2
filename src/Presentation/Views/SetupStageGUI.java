package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class SetupStageGUI extends JPanel{

    private JLabel titleLabel,players,numberTable;
    private JPanel titlePanel,playersPanel,generalPanel,tablePanel;
    private JComboBox<Integer> comboBox;
    private JButton startGame;

    public SetupStageGUI(){
        //Title: Iniciar Sessió
        titleLabel = new JLabel("PREPARACIÓ");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 48));

        //User
        players = new JLabel("JUGADORS");
        players.setFont(new Font("Inter", Font.BOLD, 13));

        //Add to the layouts
        generalPanel = new JPanel(new GridLayout(5, 1));

        //Title
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);


        //User
        playersPanel = new JPanel(new GridLayout(1,2));
        playersPanel.add(players);


        comboBox = new JComboBox<Integer>();
        comboBox.setBounds(10,10,10,20);
        playersPanel.add(comboBox);
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(4);
        //comboBox.addItemListener(this);
        generalPanel.add(playersPanel);

        startGame = new JButton("COMENÇAR");
        generalPanel.add(startGame);

        tablePanel = new JPanel(new GridLayout(15,16));

        for (char c = 'a'; c <= 'o'; c++) {
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            tablePanel.add(label);

            // Create cells for this row
            for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel.add(cell);
            }
        }

        JPanel emptyPanel = new JPanel();
        tablePanel.add(emptyPanel);

        // Create x-axis labels (1-15)
        for (int i = 1; i <= 15; i++) {
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 12));
            tablePanel.add(label);
        }

        // Create cells for each row/column

        generalPanel.setBorder(BorderFactory.createEmptyBorder(100,200,20,200));

        generalPanel.add(tablePanel);




        //Center the GridLayout
        add(titlePanel,BorderLayout.CENTER);
        add(generalPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}


