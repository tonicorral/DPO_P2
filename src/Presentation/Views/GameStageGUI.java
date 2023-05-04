package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class GameStageGUI extends JPanel {
    private static int xEnemy, yEnemy;
    private static int xUser, yUser;

    private JPanel generalPanel, enemyPanel, userPanel, infoPanel, boatsPanel,grid2,porta,destructor,submari,llanxa,rectPorta,rectDestructor,rectSubmari,rectSubmari2,rectLlanxa;;
    private Point mouseCoords = null;
    private JLabel titleLabel, player, round, titleBoats,titlePorta,titleDestructor,titleSubmari,titleLlanxa;;

    private JButton finalizar;
    private JTextField playerField, roundField;
    private JComboBox<Integer> comboBoxEnemy, comboBoxUser;

    private Color buttonColor;
    public GameStageGUI(){
        rectPorta = new JPanel(new GridLayout(1,5));
        //titulo
        titleLabel = new JLabel("BattleShip Game");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 48));

        //jugador
        player = new JLabel("Player");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 12));
        playerField = new JTextField();

        //ronda
        round = new JLabel("Round");
        round.setFont(new Font("Inter", Font.BOLD, 12));
        roundField = new JTextField();

        //Layouts
        generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.X_AXIS));

        enemyPanel = new JPanel();
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.X_AXIS));

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));

        finalizar = new JButton("Finalizar Partida");
        //set ActionComant menuGUI
        finalizar.setFont(new Font("Inter",Font.BOLD,14));
        finalizar.setBackground(buttonColor);
        finalizar.setForeground(Color.white);
        infoPanel.add(finalizar);


        boatsPanel = new JPanel();
        boatsPanel.setLayout(new BoxLayout(boatsPanel,BoxLayout.Y_AXIS));

        titleBoats = new JLabel("Els teus vaixells");
        titleBoats.setFont(new Font("Inter",Font.BOLD,14));
        JPanel titleLayout = new JPanel(new FlowLayout());
        titleLayout.add(titleBoats);
        titleLayout.setBackground(Color.white);
        boatsPanel.add(titleLayout);

        titlePorta = new JLabel("Portaavions");
        JPanel titlePortaLayout = new JPanel(new FlowLayout());
        titlePortaLayout.add(titlePorta);
        titlePortaLayout.setBackground(Color.white);


        for (int i=0; i<5;i++){
            JPanel cell = new JPanel();
            JLabel p = new JLabel("P");
            cell.setBackground(new Color(124,136,248));
            cell.setBorder(BorderFactory.createLineBorder(Color.gray));
            p.setHorizontalAlignment(JLabel.CENTER);
            p.setVerticalAlignment(JLabel.CENTER);
            cell.add(p);
            rectPorta.add(cell);
        }
        rectPorta.setBorder(BorderFactory.createEmptyBorder(20,120,20,120));
        boatsPanel.add(titlePortaLayout);
        boatsPanel.add(rectPorta);

        titleDestructor = new JLabel("Destructor");
        JPanel titleDestructorLayout = new JPanel(new FlowLayout());
        titleDestructorLayout.add(titleDestructor);
        titleDestructorLayout.setBackground(Color.white);

        rectDestructor = new JPanel(new GridLayout(1,4));
        for (int i=0; i<4;i++){
            JPanel cell = new JPanel();
            JLabel p = new JLabel("D");
            cell.setBorder(BorderFactory.createLineBorder(Color.gray));
            cell.setBackground(Color.yellow);
            cell.add(p);
            rectDestructor.add(cell);
        }
        rectDestructor.setBorder(BorderFactory.createEmptyBorder(20,130,20,130));
        boatsPanel.add(titleDestructorLayout);
        boatsPanel.add(rectDestructor);

        titleSubmari = new JLabel("Submarí");
        JPanel titleSubmariLayout = new JPanel(new FlowLayout());
        titleSubmariLayout.add(titleSubmari);
        titleSubmariLayout.setBackground(Color.white);

        rectSubmari = new JPanel(new GridLayout(1,3));
        for (int i=0; i<3;i++){
            JPanel cell = new JPanel();
            JLabel p = new JLabel("S");
            cell.setBorder(BorderFactory.createLineBorder(Color.gray));
            cell.setBackground(Color.pink);
            cell.add(p);
            rectSubmari.add(cell);
        }
        rectSubmari.setBorder(BorderFactory.createEmptyBorder(20,140,20,140));

        rectSubmari2 = new JPanel(new GridLayout(1,3));
        for (int i=0; i<3;i++){
            JPanel cell = new JPanel();
            JLabel p = new JLabel("S");
            cell.setBorder(BorderFactory.createLineBorder(Color.gray));
            cell.setBackground(Color.pink);
            cell.add(p);
            rectSubmari2.add(cell);
        }
        rectSubmari2.setBorder(BorderFactory.createEmptyBorder(20,140,20,140));

        boatsPanel.add(titleSubmariLayout);
        boatsPanel.add(rectSubmari);
        boatsPanel.add(rectSubmari2);

        titleLlanxa = new JLabel("Llanxa");
        JPanel titleLlanxaLayout = new JPanel(new FlowLayout());
        titleLlanxaLayout.add(titleLlanxa);
        titleLlanxaLayout.setBackground(Color.white);

        rectLlanxa = new JPanel(new GridLayout(1,2));

        for (int i=0; i<2;i++){
            JPanel cell = new JPanel();
            JLabel p = new JLabel("L");
            cell.setBorder(BorderFactory.createLineBorder(Color.gray));
            cell.setBackground(Color.green);
            cell.add(p);
            rectLlanxa.add(cell);
        }
        rectLlanxa.setBorder(BorderFactory.createEmptyBorder(20,150,20,150));
        boatsPanel.add(titleLlanxaLayout);
        boatsPanel.add(rectLlanxa);

        infoPanel.add(rectPorta);

        setVisible(true);
    }

}
