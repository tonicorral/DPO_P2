package Presentation.Views;

import Business.Game;
import Business.TimeThread;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameStageGUI extends JPanel{

    private Date currentTime;
    private long startTime = 0;
    private Point mouseSelect = null;
    private JLabel joc, turno, clk, barcos, portaviones, destructores, submarino, lancha1, lancha2, floteP, hundidoP,floteD, hundidoD, floteS, hundidoS, floteL1, hundidoL1, floteL2, hundidoL2,estado, xo;
    private JPanel generalPanel,centerPanel, gamePanel, buttonPanel, emptyPanel1,emptyPanel2, emptyPanel3, emptyPanel4, emptyPanelUser, flowButton,info,title, empty,turnoPanel, tablePanelUser, tablePanel1, tablePanel2, tablePanel3, tablePanel4,clock, grid1, grid2, boxUser, gridTabla, p, d, s, l1, l2, infoTable, buttonsPanelAbandonar;
    private JButton eliminar, tancar, abandonar, guardar;
    private JTable[] tables;
    private JTable userTable;
    private JButton[][] cellsUser;
    private DefaultTableModel model;
    private JComboBox<Integer> comboBoxUser, comboBox1, comboBox2, comboBox3, comboBox4;
    private String text1="",text2="",text3="",text4="";
    public static final String ABANDONAR = "ABANDONAR";

    public static final String GUARDAR = "GUARDAR";
    private Color buttonColor;

    private JPanel[][] cell1,cell2,cell3,cell4;

    private TimeThread timeThread;

    public GameStageGUI(){

        timeThread = new TimeThread();


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
        timeThread.start();
        clock = new JPanel();
        clock.setLayout(new BoxLayout(clock, BoxLayout.X_AXIS));
        clk = new JLabel();
        clk.setFont(new Font("Inter",Font.BOLD,40));
        clock.add(joc);
        clock.add(Box.createHorizontalStrut(50));
        clock.add(clk);


        updateLabel();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        buttonsPanelAbandonar = new JPanel();
        buttonsPanelAbandonar.setLayout(new FlowLayout());

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

        guardar = new JButton("Guardar Partida");
        guardar.setActionCommand("GUARDAR");
        guardar.setFont(new Font("Inter", Font.BOLD, 14));
        guardar.setBackground(buttonColor);
        guardar.setForeground(Color.white);

        buttonPanel.add(abandonar, BorderLayout.CENTER);
        buttonPanel.add(guardar, BorderLayout.WEST);
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
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel1.add(cell);
        }

        cell1 = new JPanel[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cell1[i][j] = new JPanel();
                cell1[i][j].setName(String.format("cell%d%d", i, j));

                // Add button to panel or container
                // tablePanelUser.add(cellsUser[i][j]);
            }
        }


        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell5 = new JPanel();
            cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell5.add(label);
            tablePanel1.add(cell5);



            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                cell1[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell1[i - 1][c - 'A'].setBackground(Color.white);

                tablePanel1.add(cell1[i - 1][c - 'A']);
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
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel2.add(cell);
        }

        cell2 = new JPanel[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cell2[i][j] = new JPanel();
                cell2[i][j].setName(String.format("cell%d%d", i, j));

                // Add button to panel or container
                // tablePanelUser.add(cellsUser[i][j]);
            }
        }


        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell5 = new JPanel();
            cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell5.add(label);
            tablePanel2.add(cell5);



            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                cell2[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell2[i - 1][c - 'A'].setBackground(Color.white);

                tablePanel2.add(cell2[i - 1][c - 'A']);
            }
        }
        //   tablePanel1.add(Box.createHorizontalStrut(20));
        tablePanel2.setPreferredSize(new Dimension(50, 50));
        grid1.add(tablePanel2);
        grid1.setPreferredSize(new Dimension(500,0));
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

        cell3 = new JPanel[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cell3[i][j] = new JPanel();
                cell3[i][j].setName(String.format("cell%d%d", i, j));

                // Add button to panel or container
                // tablePanelUser.add(cellsUser[i][j]);
            }
        }


        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell5 = new JPanel();
            cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell5.add(label);
            tablePanel3.add(cell5);



            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                cell3[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell3[i - 1][c - 'A'].setBackground(Color.white);

                tablePanel3.add(cell3[i - 1][c - 'A']);
            }
        }
        //   tablePanel1.add(Box.createHorizontalStrut(20));
        tablePanel3.setPreferredSize(new Dimension(50, 50));
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

        cell4 = new JPanel[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cell4[i][j] = new JPanel();
                cell4[i][j].setName(String.format("cell%d%d", i, j));

                // Add button to panel or container
                // tablePanelUser.add(cellsUser[i][j]);
            }
        }


        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell5 = new JPanel();
            cell5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell5.add(label);
            tablePanel4.add(cell5);



            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                cell4[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell4[i - 1][c - 'A'].setBackground(Color.white);

                tablePanel4.add(cell4[i - 1][c - 'A']);
            }
        }
        //   tablePanel1.add(Box.createHorizontalStrut(20));
        tablePanel4.setPreferredSize(new Dimension(50, 50));
        grid2.add(tablePanel4);
        grid2.setPreferredSize(new Dimension(500,0));
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

    public void setGameListener(ActionListener listener){
        this.guardar.addActionListener(listener);
        abandonar.addActionListener(listener);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cellsUser[i][j].addActionListener(listener);
                // Add button to panel or container
            }
        }
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

    public void paintIA(Game game, int i, int j, int numPlayers){
        paintUser(game,i,j,numPlayers);
        for (int m = 0;m<numPlayers;m++){
            int touch = game.getJugadorIA().get(m).getTablero().getTablero()[i][j];
            if (m == 0){
                paintIA1(touch,i,j);
            } else if(m == 1){
                paintIA2(touch,i,j);
            }else if(m == 2){

                paintIA3(touch,i,j);
            }
            else if(m == 3){

                paintIA4(touch,i,j);
            }
        }


    }

    private void paintIA1(int touch, int i, int j){
        if(touch == 1){
            cell1[i][j].setBackground(Color.red);
        }else if(touch == -1){
            cell1[i][j].setBackground(Color.white);
        }else if(touch==2){
            cell1[i][j].setBackground(Color.yellow);
        }else if(touch==3){
            cell1[i][j].setBackground(Color.cyan);
        }else if(touch==4){
            cell1[i][j].setBackground(Color.green);
        }else if(touch==5){
            cell1[i][j].setBackground(Color.pink);
        }else if(touch==6){
            cell1[i][j].setBackground(Color.gray);
        }else if(touch==-2){
            cell1[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("T");
            cell1[i][j].add(text);
        }else if(touch==-3){
            cell1[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("T");
            cell1[i][j].add(text);
        }
        else if(touch==-4){
            cell1[i][j].setBackground(Color.green);
            JLabel text = new JLabel("T");
            cell1[i][j].add(text);
        }
        else if(touch==-5){
            cell1[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("T");
            cell1[i][j].add(text);
        }
        else if(touch==-6){
            cell1[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("T");
            cell1[i][j].add(text);
        }else if(touch==-7){
            cell1[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("H");
            cell1[i][j].add(text);
        }else if(touch==-8){
            cell1[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("H");
            cell1[i][j].add(text);
        }else if(touch==-9){
            cell1[i][j].setBackground(Color.green);
            JLabel text = new JLabel("H");
            cell1[i][j].add(text);
        }else if(touch==-10){
            cell1[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("H");
            cell1[i][j].add(text);
        }else if(touch==-11){
            cell1[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("H");
            cell1[i][j].add(text);
        }
        else{
            cell1[i][j].setBackground(Color.blue);
        }
    }
    private void paintIA2(int touch, int i, int j){
        if(touch == 1){
            cell2[i][j].setBackground(Color.red);
        }else if(touch == -1){
            cell2[i][j].setBackground(Color.white);
        }else if(touch==2){
            cell2[i][j].setBackground(Color.yellow);
        }else if(touch==3){
            cell2[i][j].setBackground(Color.cyan);
        }else if(touch==4){
            cell2[i][j].setBackground(Color.green);
        }else if(touch==5){
            cell2[i][j].setBackground(Color.pink);
        }else if(touch==6){
            cell2[i][j].setBackground(Color.gray);
        }else if(touch==-2){
            cell2[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("T");
            cell2[i][j].add(text);
        }else if(touch==-3){
            cell2[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("T");
            cell2[i][j].add(text);
        }
        else if(touch==-4){
            cell2[i][j].setBackground(Color.green);
            JLabel text = new JLabel("T");
            cell2[i][j].add(text);
        }
        else if(touch==-5){
            cell2[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("T");
            cell2[i][j].add(text);
        }
        else if(touch==-6){
            cell2[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("T");
            cell2[i][j].add(text);
        }else if(touch==-7){
            cell2[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("H");
            cell2[i][j].add(text);
        }else if(touch==-8){
            cell2[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("H");
            cell2[i][j].add(text);
        }else if(touch==-9){
            cell2[i][j].setBackground(Color.green);
            JLabel text = new JLabel("H");
            cell2[i][j].add(text);
        }else if(touch==-10){
            cell2[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("H");
            cell2[i][j].add(text);
        }else if(touch==-11){
            cell2[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("H");
            cell2[i][j].add(text);
        }
        else{
            cell2[i][j].setBackground(Color.blue);
        }
    }
    private void paintIA3(int touch, int i, int j){
        if(touch == 1){
            cell3[i][j].setBackground(Color.red);
        }else if(touch == -1){
            cell3[i][j].setBackground(Color.white);
        }else if(touch==2){
            cell3[i][j].setBackground(Color.yellow);
        }else if(touch==3){
            cell3[i][j].setBackground(Color.cyan);
        }else if(touch==4){
            cell3[i][j].setBackground(Color.green);
        }else if(touch==5){
            cell3[i][j].setBackground(Color.pink);
        }else if(touch==6){
            cell3[i][j].setBackground(Color.gray);
        }else if(touch==-2){
            cell3[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("T");
            cell3[i][j].add(text);
        }else if(touch==-3){
            cell3[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("T");
            cell3[i][j].add(text);
        }
        else if(touch==-4){
            cell3[i][j].setBackground(Color.green);
            JLabel text = new JLabel("T");
            cell3[i][j].add(text);
        }
        else if(touch==-5){
            cell3[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("T");
            cell3[i][j].add(text);
        }
        else if(touch==-6){
            cell3[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("T");
            cell3[i][j].add(text);
        }else if(touch==-7){
            cell3[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("H");
            cell3[i][j].add(text);
        }else if(touch==-8){
            cell3[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("H");
            cell3[i][j].add(text);
        }else if(touch==-9){
            cell3[i][j].setBackground(Color.green);
            JLabel text = new JLabel("H");
            cell3[i][j].add(text);
        }else if(touch==-10){
            cell3[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("H");
            cell3[i][j].add(text);
        }else if(touch==-11){
            cell3[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("H");
            cell3[i][j].add(text);
        }
        else{
            cell3[i][j].setBackground(Color.blue);
        }
    }
    private void paintIA4(int touch, int i, int j){
        if(touch == 1){
            cell4[i][j].setBackground(Color.red);
        }else if(touch == -1){
            cell4[i][j].setBackground(Color.white);
        }else if(touch==2){
            cell4[i][j].setBackground(Color.yellow);
        }else if(touch==3){
            cell4[i][j].setBackground(Color.cyan);
        }else if(touch==4){
            cell4[i][j].setBackground(Color.green);
        }else if(touch==5){
            cell4[i][j].setBackground(Color.pink);
        }else if(touch==6){
            cell4[i][j].setBackground(Color.gray);
        }else if(touch==-2){
            cell4[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("T");
            cell4[i][j].add(text);
        }else if(touch==-3){
            cell4[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("T");
            cell4[i][j].add(text);
        }
        else if(touch==-4){
            cell4[i][j].setBackground(Color.green);
            JLabel text = new JLabel("T");
            cell4[i][j].add(text);
        }
        else if(touch==-5){
            cell4[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("T");
            cell4[i][j].add(text);
        }
        else if(touch==-6){
            cell4[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("T");
            cell4[i][j].add(text);
        }else if(touch==-7){
            cell4[i][j].setBackground(Color.yellow);
            JLabel text = new JLabel("H");
            cell4[i][j].add(text);
        }else if(touch==-8){
            cell4[i][j].setBackground(Color.cyan);
            JLabel text = new JLabel("H");
            cell4[i][j].add(text);
        }else if(touch==-9){
            cell4[i][j].setBackground(Color.green);
            JLabel text = new JLabel("H");
            cell4[i][j].add(text);
        }else if(touch==-10){
            cell4[i][j].setBackground(Color.pink);
            JLabel text = new JLabel("H");
            cell4[i][j].add(text);
        }else if(touch==-11){
            cell4[i][j].setBackground(Color.gray);
            JLabel text = new JLabel("H");
            cell4[i][j].add(text);
        }
        else{
            cell4[i][j].setBackground(Color.blue);
        }
    }

    public void paintUser(Game game,int i,int j,int numPlayers){
        for (int m = 0;m<numPlayers;m++){
            int touch = game.getPlayer().getTablero().getTablero()[i][j];
            if(touch == 1){
                cellsUser[i][j].setBackground(Color.red);
            }else if(touch == -1){
                cellsUser[i][j].setBackground(Color.white);
            }else if(touch==2){
                cellsUser[i][j].setBackground(Color.yellow);
            }else if(touch==3){
                cellsUser[i][j].setBackground(Color.cyan);
            }else if(touch==4){
                cellsUser[i][j].setBackground(Color.green);
            }else if(touch==5){
                cellsUser[i][j].setBackground(Color.pink);
            }else if(touch==6){
                cellsUser[i][j].setBackground(Color.gray);
            }else if(touch==-2){
                cellsUser[i][j].setBackground(Color.yellow);
                JLabel text = new JLabel("T");
                cellsUser[i][j].add(text);
            }else if(touch==-3){
                cellsUser[i][j].setBackground(Color.cyan);
                JLabel text = new JLabel("T");
                cellsUser[i][j].add(text);
            }
            else if(touch==-4){
                cellsUser[i][j].setBackground(Color.green);
                JLabel text = new JLabel("T");
                cellsUser[i][j].add(text);
            }
            else if(touch==-5){
                cellsUser[i][j].setBackground(Color.pink);
                JLabel text = new JLabel("T");
                cellsUser[i][j].add(text);
            }
            else if(touch==-6){
                cellsUser[i][j].setBackground(Color.gray);
                JLabel text = new JLabel("T");
                cellsUser[i][j].add(text);
            }else if(touch==-7){
                cellsUser[i][j].setBackground(Color.yellow);
                JLabel text = new JLabel("H");
                cellsUser[i][j].add(text);
            }else if(touch==-8){
                cellsUser[i][j].setBackground(Color.cyan);
                JLabel text = new JLabel("H");
                cellsUser[i][j].add(text);
            }else if(touch==-9){
                cellsUser[i][j].setBackground(Color.green);
                JLabel text = new JLabel("H");
                cellsUser[i][j].add(text);
            }else if(touch==-10){
                cellsUser[i][j].setBackground(Color.pink);
                JLabel text = new JLabel("H");
                cellsUser[i][j].add(text);
            }else if(touch==-11){
                cellsUser[i][j].setBackground(Color.gray);
                JLabel text = new JLabel("H");
                cellsUser[i][j].add(text);
            }
            else{
                cellsUser[i][j].setBackground(Color.blue);
            }
        }

    }

    private void updateLabel() {
        Thread updateThread = new Thread(() -> {
            while (true) {
                long elapsedTime = System.currentTimeMillis() - startTime  - 3600000;
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                currentTime = new Date(elapsedTime);
                clk.setText(sdf.format(currentTime));
                clk.setForeground(Color.red);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updateThread.start();
    }

    public void initTime(long time){
        startTime = time;
    }


}
