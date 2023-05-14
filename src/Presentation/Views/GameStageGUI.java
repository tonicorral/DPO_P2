package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameStageGUI extends JPanel{
    private Point mouseSelect = null;
    private JLabel joc, turno, clk, barcos, portaviones, destructores, submarino, lancha1, lancha2, floteP, hundidoP,floteD, hundidoD, floteS, hundidoS, floteL1, hundidoL1, floteL2, hundidoL2,estado, xo;
    private JPanel generalPanel,centerPanel, gamePanel, buttonPanel, emptyPanel1,emptyPanel2, emptyPanel3, emptyPanel4, emptyPanelUser, flowButton,info,title, empty,turnoPanel, tablePanelUser, tablePanel1, tablePanel2, tablePanel3, tablePanel4,clock, grid1, grid2, boxUser, gridTabla, p, d, s, l1, l2;
    private JButton eliminar, tancar, abandonar;
    private JButton[][] cellsUser;
    private JComboBox<Integer> comboBoxUser, comboBox1, comboBox2, comboBox3, comboBox4;
    public static final String ELIMINAR = "ELIMINAR";
    public static final String TANCAR = "TANCAR";
    public static final String ABANDORNAR = "ABANDONAR";

    private Color buttonColor;

    public GameStageGUI(){

        buttonColor = new Color(124,136,248);
        generalPanel = new JPanel();
        generalPanel.setLayout(new BorderLayout());

        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());

        joc = new JLabel("GAME");
        joc.setFont(new Font("Inter", Font.BOLD, 40));

        //gamePanel.add(joc, BorderLayout.CENTER);

        clock = new JPanel();
        clock.setLayout(new BoxLayout(clock, BoxLayout.Y_AXIS));
        clk = new JLabel("HORA");
        clk.setFont(new Font("Inter",Font.BOLD,14));
        clock.add(joc);
        clock.add(clk);



        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        flowButton = new JPanel();
        flowButton.setLayout(new FlowLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        eliminar = new JButton("Eliminar Compte");
        //eliminar.setActionCommand();
        eliminar.setFont(new Font("Inter",Font.BOLD,14));
        eliminar.setBackground(buttonColor);
        eliminar.setForeground(Color.white);

        tancar = new JButton("Tancar Sessió");
        //tancar.setActionCommand();
        tancar.setFont(new Font("Inter",Font.BOLD,14));
        tancar.setBackground(buttonColor);
        tancar.setForeground(Color.white);

        flowButton.add(eliminar);
        flowButton.add(tancar);

        buttonPanel.add(flowButton, BorderLayout.NORTH);

        abandonar = new JButton("Abandonar Partida");
        //abandonar.setActionCommand();
        abandonar.setFont(new Font("Inter",Font.BOLD,14));
        abandonar.setBackground(buttonColor);
        abandonar.setForeground(Color.white);

        buttonPanel.add(abandonar, BorderLayout.CENTER);

        gamePanel.add(buttonPanel, BorderLayout.EAST);

        turnoPanel = new JPanel();
        turnoPanel.setLayout(new BorderLayout());

        turno = new JLabel("TU TURNO");
        turno.setFont(new Font("Inter",Font.BOLD,14));
        turnoPanel.add(turno, BorderLayout.SOUTH);

        gamePanel.add(turnoPanel, BorderLayout.WEST);
        clock.setBorder(BorderFactory.createEmptyBorder(20,500,10,20));
        gamePanel.add(clock, BorderLayout.CENTER);
        //parte de arriba done
        generalPanel.add(gamePanel, BorderLayout.NORTH);
        generalPanel.add(Box.createVerticalStrut(50));

        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(2, 1));

        emptyPanel1 = new JPanel();
        emptyPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel1.setBackground(new Color(89,185,198));

        emptyPanel2 = new JPanel();
        emptyPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel2.setBackground(new Color(89,185,198));
        //tablePanel.add(emptyPanel);
        tablePanel1 = new JPanel(new GridLayout(16,16));
        tablePanel1.add(emptyPanel1);

        tablePanel2 = new JPanel(new GridLayout(16,16));
        //tablePanel1.add(emptyPanel);
        tablePanel2.add(emptyPanel2);
        for (int i = 1; i <= 15; i++) {
            JPanel cell = new JPanel();
            //cell.setPreferredSize(new Dimension(10,10));
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 10));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel1.add(cell);
        }

        /*cells1 = new JButton[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells1[i][j] = new JButton();
                cells1[i][j].setName(String.format("cell%d%d", i, j));
                // Add button to panel or container
            }
        }*/

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            //cell1.setPreferredSize(new Dimension(10,10));
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 10));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanel1.add(cell1);
            for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel1.add(cell);
            }

/*            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cells1[i - 1][c - 'A'].setActionCommand(actionCommand);
                cells1[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells1[i - 1][c - 'A'].setBackground(Color.white);
                grid1.add(cells1[i - 1][c - 'A']);
            }*/
        }
        //   tablePanel1.add(Box.createHorizontalStrut(20));
        tablePanel1.setPreferredSize(new Dimension(50, 50));
        grid1.add(tablePanel1);
        //  grid1.add(Box.createVerticalStrut(20));
        for (int i = 1; i <= 15; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 10));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel2.add(cell);

        }

       /* cells2 = new JButton[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells2[i][j] = new JButton();
                cells2[i][j].setName(String.format("cell%d%d", i, j));
                // Add button to panel or container
            }
        }*/

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 10));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanel2.add(cell1);
            for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel2.add(cell);
            }

           /* for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cells2[i - 1][c - 'A'].setActionCommand(actionCommand);
                cells2[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells2[i - 1][c - 'A'].setBackground(Color.white);
                grid1.add(cells2[i - 1][c - 'A']);
            }*/
        }
        //tablePanel2.add(Box.createHorizontalStrut(20));
        // tablePanel2.setPreferredSize(new Dimension(100, 100));
        grid1.add(tablePanel2);
        grid1.setPreferredSize(new Dimension(300,0));
        generalPanel.add(grid1, BorderLayout.WEST);



        emptyPanel3 = new JPanel();
        emptyPanel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel3.setBackground(new Color(89,185,198));

        emptyPanel4= new JPanel();
        emptyPanel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel4.setBackground(new Color(89,185,198));

        grid2 = new JPanel();
        grid2.setLayout(new GridLayout(2,1));

        tablePanel3 = new JPanel(new GridLayout(16,16));
        tablePanel3.add(emptyPanel3);

        tablePanel4 = new JPanel(new GridLayout(16,16));
        tablePanel4.add(emptyPanel4);

        for (int i = 1; i <= 15; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel3.add(cell);
        }

        /*cells3 = new JButton[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells3[i][j] = new JButton();
                cells3[i][j].setName(String.format("cell%d%d", i, j));
                // Add button to panel or container
            }
        }*/

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanel3.add(cell1);
            for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel3.add(cell);
            }

            /*for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cells3[i - 1][c - 'A'].setActionCommand(actionCommand);
                cells3[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells3[i - 1][c - 'A'].setBackground(Color.white);
                grid2.add(cells3[i - 1][c - 'A']);
            }*/
        }
        grid2.add(tablePanel3);

        for (int i = 1; i <= 15; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel4.add(cell);
        }

        /*cells4 = new JButton[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells4[i][j] = new JButton();
                cells4[i][j].setName(String.format("cell%d%d", i, j));
                // Add button to panel or container
            }
        }*/

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanel4.add(cell1);

            for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel4.add(cell);
            }
            /*for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cells4[i - 1][c - 'A'].setActionCommand(actionCommand);
                cells4[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells4[i - 1][c - 'A'].setBackground(Color.white);
                grid2.add(cells4[i - 1][c - 'A']);
            }*/
        }
        grid2.add(tablePanel4);
        grid2.setPreferredSize(new Dimension(300,0));
        generalPanel.add(grid2, BorderLayout.EAST);



        boxUser = new JPanel();
        boxUser.setLayout(new BoxLayout(boxUser, BoxLayout.X_AXIS));

        emptyPanelUser = new JPanel();
        emptyPanelUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanelUser.setBackground(new Color(89,185,198));

        tablePanelUser = new JPanel(new GridLayout(16,16));
        tablePanelUser.add(emptyPanelUser);

        for (int i = 1; i <= 15; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanelUser.add(cell);
        }

        cellsUser = new JButton[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cellsUser[i][j] = new JButton();
                cellsUser[i][j].setName(String.format("cell%d%d", i, j));
                // Add button to panel or container
                // tablePanelUser.add(cellsUser[i][j]);
            }
        }

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanelUser.add(cell1);

           /* for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanelUser.add(cell);
            }*/

            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cellsUser[i - 1][c - 'A'].setActionCommand(actionCommand);
                cellsUser[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellsUser[i - 1][c - 'A'].setBackground(Color.white);
                tablePanelUser.add(cellsUser[i - 1][c - 'A']);
            }
        }

        tablePanelUser.setPreferredSize(new Dimension(200,200));
        // boxUser.add(tablePanelUser);
        tablePanelUser.setBorder(BorderFactory.createEmptyBorder(20,40,5,40));
        tablePanelUser.setOpaque(false);
        //boxUser.add(tablePanelUser);
        //boxUser.setPreferredSize(new Dimension(300,300));



        gridTabla = new JPanel();
        gridTabla.setLayout(new BoxLayout(gridTabla,BoxLayout.Y_AXIS));

        title = new JPanel();
        title.setLayout(new BoxLayout(title, BoxLayout.X_AXIS));

        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));

        d = new JPanel();
        d.setLayout(new BoxLayout(d, BoxLayout.X_AXIS));

        s = new JPanel();
        s.setLayout(new BoxLayout(s, BoxLayout.X_AXIS));

        l1 = new JPanel();
        l1.setLayout(new BoxLayout(l1, BoxLayout.X_AXIS));

        l2 = new JPanel();
        l2.setLayout(new BoxLayout(l2, BoxLayout.X_AXIS));

        hundidoP =  new JLabel("      Enfonsat                  ");
        hundidoP.setFont(new Font("Inter", Font.BOLD, 10));
        floteP =  new JLabel("       Navegant   ");
        floteP.setFont(new Font("Inter", Font.BOLD, 10));

        hundidoD =  new JLabel("      Enfonsat                  ");
        hundidoD.setFont(new Font("Inter", Font.BOLD, 10));
        floteD =  new JLabel("       Navegant   ");
        floteD.setFont(new Font("Inter", Font.BOLD, 10));


        hundidoS =  new JLabel("      Enfonsat                  ");
        hundidoS.setFont(new Font("Inter", Font.BOLD, 10));
        floteS =  new JLabel("       Navegant   ");
        floteS.setFont(new Font("Inter", Font.BOLD, 10));

        hundidoL1 =  new JLabel("      Enfonsat                  ");
        hundidoL1.setFont(new Font("Inter", Font.BOLD, 10));
        floteL1 =  new JLabel("      Navegant   ");
        floteL1.setFont(new Font("Inter", Font.BOLD, 10));


        hundidoL2 =  new JLabel("      Enfonsat                  ");
        hundidoL2.setFont(new Font("Inter", Font.BOLD, 10));
        floteL2=  new JLabel("       Navegant   ");
        floteL2.setFont(new Font("Inter", Font.BOLD, 10));





        barcos = new JLabel("           Vaixells                          ");
        barcos.setFont(new Font("Inter", Font.BOLD, 10));
        title.add(barcos);
        estado = new JLabel("Estat Actual                                   ");
        estado.setFont(new Font("Inter", Font.BOLD, 10));
        title.add(estado);
        gridTabla.add(title);


        portaviones =  new JLabel("      Portaviones              ");
        portaviones.setFont(new Font("Inter", Font.BOLD, 10));
        p.add(portaviones);
        p.add(floteP);
        p.add(hundidoP);
        gridTabla.add(p);


        destructores =  new JLabel("      Destructors               ");
        destructores.setFont(new Font("Inter", Font.BOLD, 10));
        d.add(destructores);
        d.add(floteD);
        d.add(hundidoD);
        gridTabla.add(d);


        submarino =  new JLabel("      Submarí                    ");
        submarino.setFont(new Font("Inter", Font.BOLD, 10));
        s.add(submarino);
        s.add(floteS);
        s.add(hundidoS);
        gridTabla.add(s);


        lancha1 =  new JLabel("     Llanxa1                      ");
        lancha1.setFont(new Font("Inter", Font.BOLD, 10));
        l1.add(lancha1);
        l1.add(floteL1);
        l1.add(hundidoL1);
        gridTabla.add(l1);

        lancha2 =  new JLabel("     Llanxa2                     ");
        lancha2.setFont(new Font("Inter", Font.BOLD, 10));
        l2.add(lancha2);
        l2.add(floteL2);
        l2.add(hundidoL2);
        gridTabla.add(l2);


        gridTabla.setBackground(Color.lightGray);

        gridTabla.setPreferredSize(new Dimension(150, 100));
        gridTabla.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //  info.add(gridTabla);
        //  info.setBackground(Color.WHITE);
        //info.setBorder(BorderFactory.createEmptyBorder(10,40, 10, 40));

        empty = new JPanel();
        empty.setLayout(new BoxLayout(empty, BoxLayout.X_AXIS));
        empty.setBorder(BorderFactory.createEmptyBorder(60,40, 10, 40));
        generalPanel.add(empty, BorderLayout.SOUTH);


        //centerPanel.add(boxUser);
        centerPanel.add(tablePanelUser);
        centerPanel.add(gridTabla);
        generalPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(generalPanel);
        setVisible(true);
    }

}
