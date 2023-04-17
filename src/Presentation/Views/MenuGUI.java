package Presentation.Views;

import Presentation.MainView;

import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JPanel {

    private JLabel title;
    private JButton newGame,oldGame,stats,logout,delete;
    private JPanel titlePanel,newGamePanel,oldGamePanel,statsPanel,logoutPanel,deletePanel;
    private Color buttonColor;
    private JPanel generalPanel,borderPanel,twoB;

    public MenuGUI(){

        buttonColor = new Color(124,136,248);

        title = new JLabel("BATTLESHIPS");
        title.setFont(new Font("Iceland",Font.BOLD,96));
        title.setForeground(Color.white);

        newGame = new JButton("Nova partida");
        newGame.setFont(new Font("Inter",Font.BOLD,24));
        newGame.setBackground(buttonColor);
        newGame.setForeground(Color.white);

        oldGame = new JButton("Carregar partida");
        oldGame.setFont(new Font("Inter",Font.BOLD,24));
        oldGame.setBackground(buttonColor);
        oldGame.setForeground(Color.white);

        stats = new JButton("Estadístiques");
        stats.setFont(new Font("Inter",Font.BOLD,24));
        stats.setBackground(buttonColor);
        stats.setForeground(Color.white);

        delete = new JButton("Eliminar compte");
        delete.setFont(new Font("Inter",Font.BOLD,14));
        delete.setBackground(buttonColor);
        delete.setForeground(Color.white);

        logout = new JButton("Tancar sessió");
        logout.setFont(new Font("Inter",Font.BOLD,14));
        logout.setBackground(buttonColor);
        logout.setForeground(Color.white);


        generalPanel = new JPanel(new GridLayout(4,1,10,10));
        generalPanel.add(title);
        generalPanel.add(newGame);
        generalPanel.add(oldGame);
        generalPanel.add(stats);
        generalPanel.setOpaque(false);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(50,300,50,300));


        borderPanel = new JPanel(new BorderLayout());
        twoB = new JPanel(new GridLayout(1,2,10,10));
        twoB.add(delete);
        twoB.add(logout);
        borderPanel.setOpaque(false);
        borderPanel.add("North",twoB);



        this.setBackground(new Color(217,249,253));
        this.add(borderPanel);
        this.add(generalPanel);

        setVisible(true);
    }

}

