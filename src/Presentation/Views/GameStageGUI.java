package Presentation.Views;

import Business.Boat;
import Business.Game;
import Business.Tablero;
import Presentation.Controllers.GameStageController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class GameStageGUI extends JPanel{

    private Point mouseSelect = null;
    private JLabel joc, turno, clk, barcos, portaviones, destructores, submarino, lancha1, lancha2, floteP, hundidoP,floteD, hundidoD, floteS, hundidoS, floteL1, hundidoL1, floteL2, hundidoL2,estado, xo;
    private JPanel generalPanel,centerPanel, gamePanel, buttonPanel, emptyPanel1,emptyPanel2, emptyPanel3, emptyPanel4, emptyPanelUser, flowButton,info,title, empty,turnoPanel, tablePanelUser, tablePanel1, tablePanel2, tablePanel3, tablePanel4,clock, grid1, grid2, boxUser, gridTabla, p, d, s, l1, l2, infoTable;
    private JButton eliminar, tancar, abandonar;
    private JTable[] tables;
    private JTable userTable;
    private JButton[][] cellsUser;
    private DefaultTableModel model;
    private JComboBox<Integer> comboBoxUser, comboBox1, comboBox2, comboBox3, comboBox4;

    public static final String ABANDONAR = "ABANDONAR";

    private Color buttonColor;

    public GameStageGUI(){

        this.setLayout(new BorderLayout());
        buttonColor = new Color(124,136,248);
        //generalPanel = new JPanel();
        //generalPanel.setBackground(new Color(217,249,253));
        //generalPanel.setLayout(new BorderLayout());

        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(217,249,253));

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

 /*
        eliminar = new JButton("Eliminar Compte");
        eliminar.setActionCommand("ELIMINAR");
        eliminar.setFont(new Font("Inter",Font.BOLD,14));
        eliminar.setBackground(buttonColor);
        eliminar.setForeground(Color.white);

        tancar = new JButton("Tancar Sessió");
        tancar.setActionCommand("TANCAR");
        tancar.setFont(new Font("Inter",Font.BOLD,14));
        tancar.setBackground(buttonColor);
        tancar.setForeground(Color.white);


        flowButton.add(eliminar);
        flowButton.add(tancar);

        buttonPanel.add(flowButton, BorderLayout.NORTH);
 */
        abandonar = new JButton("Abandonar Partida");
        abandonar.setActionCommand("ABANDONAR");
        abandonar.setFont(new Font("Inter",Font.BOLD,14));
        abandonar.setBackground(buttonColor);
        abandonar.setForeground(Color.white);



        buttonPanel.add(abandonar, BorderLayout.CENTER);

        gamePanel.add(buttonPanel, BorderLayout.EAST);

        turnoPanel = new JPanel();
        turnoPanel.setLayout(new BorderLayout());

        turno = new JLabel("TU TURNO");
        turno.setFont(new Font("Inter",Font.BOLD,14));
        turnoPanel.add(turno, BorderLayout.CENTER);

        gamePanel.add(turnoPanel, BorderLayout.WEST);
        clock.setBorder(BorderFactory.createEmptyBorder(20,500,10,20));
        gamePanel.add(clock, BorderLayout.CENTER);
        //parte de arriba done
        add(gamePanel, BorderLayout.NORTH);
        add(Box.createVerticalStrut(50));

        grid1 = new JPanel();
        grid1.setLayout(new GridLayout(2, 1));

        emptyPanel1 = new JPanel();
        emptyPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel1.setBackground(Color.YELLOW);

        emptyPanel2 = new JPanel();
        emptyPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel2.setBackground(Color.RED);
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


        }
        //tablePanel2.add(Box.createHorizontalStrut(20));
        // tablePanel2.setPreferredSize(new Dimension(100, 100));
        grid1.add(tablePanel2);
        grid1.setPreferredSize(new Dimension(300,0));
        add(grid1, BorderLayout.WEST);



        emptyPanel3 = new JPanel();
        emptyPanel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel3.setBackground(Color.PINK);

        emptyPanel4= new JPanel();
        emptyPanel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel4.setBackground(Color.ORANGE);

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

        }
        grid2.add(tablePanel4);
        grid2.setPreferredSize(new Dimension(300,0));
        add(grid2, BorderLayout.EAST);



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



            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cellsUser[i - 1][c - 'A'].setActionCommand(actionCommand);
                cellsUser[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellsUser[i - 1][c - 'A'].setBackground(Color.white);

                tablePanelUser.add(cellsUser[i - 1][c - 'A']);
            }
        }

        tablePanelUser.setPreferredSize(new Dimension(350,420));

        //tablePanelUser.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        tablePanelUser.setOpaque(false);


        infoTable = new JPanel();
        infoTable.setLayout(new FlowLayout());
        tables = new JTable[5];
        userTable = new JTable();

        for(int i = 0; i < 5; i++){
            if(i == 0){
                tables[i] = createTable();
                tables[i].setBackground(Color.YELLOW);
                infoTable.add(tables[i]);


            }
            if(i == 1){
                tables[i] = createTable();
                tables[i].setBackground(Color.RED);
                infoTable.add(tables[i]);
            }
            if(i == 3){
                tables[i] = createTable();
                tables[i].setBackground(Color.ORANGE);
                infoTable.add(tables[i]);

            }
            if(i == 4){
                tables[i] = createTable();
                tables[i].setBackground(Color.PINK);
                infoTable.add(tables[i]);
            }
            if(i == 2){
                userTable = createUserTable();
                userTable.setBackground(new Color(89,185,198));
                infoTable.add(userTable);

            }
        }

        infoTable.setBackground(Color.gray);
        empty = new JPanel();
        empty.setLayout(new BoxLayout(empty, BoxLayout.X_AXIS));
        empty.setBorder(BorderFactory.createEmptyBorder(60,10, 10, 40));
        add(empty, BorderLayout.SOUTH);



        centerPanel.add(tablePanelUser);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(infoTable);
        add(centerPanel, BorderLayout.CENTER);

        this.setBackground(new Color(217,249,253));

        setVisible(true);
    }

    public void setGameListener(ActionListener listener, MouseListener mouseListener){

        abandonar.addActionListener(listener);

    }

    private JTable createTable() {
        String[] columnNames = {"Barco", "Estado"};
        String[][] rowData = {
                {"Portaviones", "TOCADO"},
                {"Destructor", "Hundido"},
                {"Submarino", "Tocado"},
                {"Lancha", "Intacto"},
                {"Lancha2", "Intacto"}
        };
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);

        table.setPreferredSize(new Dimension(120, 90));
        return table;
    }
    private JTable createUserTable() {
        String[] columnNames = {"BarcoUser", "Estado"};
        String[][] rowData = {
                {"Portaviones", "TOCADO"},
                {"Destructor", "Hundido"},
                {"Submarino", "Tocado"},
                {"Lancha", "Intacto"},
                {"Lancha2", "Intacto"}
        };
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model);
        table.setPreferredSize(new Dimension(200, 100));
        return table;
    }

    public void setBoats(Game game){
        for(int m = 0; m < 5; m++){
            if(game.getPlayer().getBoats().get(0).getOrientation()){
                cellsUser[game.getPlayer().getBoats().get(0).getPositionX()-1][game.getPlayer().getBoats().get(0).getPositionY()+m-1].setBackground(new Color(124,136,248));
            }
            else{
                cellsUser[game.getPlayer().getBoats().get(0).getPositionX()+m-1][game.getPlayer().getBoats().get(0).getPositionY()-1].setBackground(new Color(124,136,248));
            }

        }
        for(int m = 0; m < 4; m++){
            if(game.getPlayer().getBoats().get(1).getOrientation()){
                cellsUser[game.getPlayer().getBoats().get(1).getPositionX()-1][game.getPlayer().getBoats().get(1).getPositionY()+m-1].setBackground(Color.YELLOW);
            }
            else{
                cellsUser[game.getPlayer().getBoats().get(1).getPositionX()+m-1][game.getPlayer().getBoats().get(1).getPositionY()-1].setBackground(Color.YELLOW);
            }

        }
        for(int m = 0; m < 3; m++){
            if(game.getPlayer().getBoats().get(2).getOrientation()){
                cellsUser[game.getPlayer().getBoats().get(2).getPositionX()-1][game.getPlayer().getBoats().get(2).getPositionY()+m-1].setBackground(Color.PINK);
            }
            else{
                cellsUser[game.getPlayer().getBoats().get(2).getPositionX()+m-1][game.getPlayer().getBoats().get(2).getPositionY()-1].setBackground(Color.PINK);
            }

        }
        for(int m = 0; m < 3; m++){
            if(game.getPlayer().getBoats().get(3).getOrientation()){
                cellsUser[game.getPlayer().getBoats().get(3).getPositionX()-1][game.getPlayer().getBoats().get(3).getPositionY()+m-1].setBackground(Color.PINK);
            }
            else{
                cellsUser[game.getPlayer().getBoats().get(3).getPositionX()+m-1][game.getPlayer().getBoats().get(3).getPositionY()-1].setBackground(Color.PINK);
            }

        }
        for(int m = 0; m < 2; m++){
            if(game.getPlayer().getBoats().get(4).getOrientation()){
                cellsUser[game.getPlayer().getBoats().get(4).getPositionX()-1][game.getPlayer().getBoats().get(4).getPositionY()+m-1].setBackground(Color.GREEN);
            }
            else{
                cellsUser[game.getPlayer().getBoats().get(4).getPositionX()+m-1][game.getPlayer().getBoats().get(4).getPositionY()-1].setBackground(Color.GREEN);
            }

        }
    }

}
