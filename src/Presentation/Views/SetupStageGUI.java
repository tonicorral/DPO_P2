package Presentation.Views;

import Presentation.Controllers.SetUpController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetupStageGUI extends JPanel{

    private int dragX, dragY;

    private Point mouseDownCompCoords = null;

    private JLabel titleLabel,players,numberTable,titleBoats,titlePorta,titleDestructor,titleSubmari,titleLlanxa;
    private JPanel titlePanel,playersPanel,generalPanel,tablePanel,emptyPanel,boatsPanel,grid2,porta,destructor,submari,llanxa,rectPorta,rectDestructor,rectSubmari,rectSubmari2,rectLlanxa;

    private JPanel twoB,threeB;
    private JComboBox<Integer> comboBox;
    private JButton startGame,logout,delete,cell2,rotate,eliminate;

    private JButton[][] cells;

    private JPanel[] llanxaCells,portaCells,destructorCells,submariCells,submari2Cells;

    private Color buttonColor;

    public static final String CELL = "CELL";
    public static final String ROTATE = "ROTATE";
    public static final String ELIMINATE = "ELIMINATE";

    public static final String BEGIN_BUTTON = "BEGIN_BUTTON";
    public static final String DELETE_BTN = "DELETE_BTN";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";


    public static final String PORTAAVIONS = "PORTAAVIONS";

    private boolean isClickedPorta = false,isClickedDestructor=false,isClickedSubmari=false,isClickedSubmari2=false,isClickedLlanxa=false;

    public SetupStageGUI(){

        rectPorta = new JPanel(new GridLayout(1,5));
        buttonColor = new Color(124,136,248);

        //Title: Iniciar Sessió
        titleLabel = new JLabel("PREPARACIÓ");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 48));

        //User
        players = new JLabel("JUGADORS");
        players.setFont(new Font("Inter", Font.BOLD, 13));

        //Add to the layouts
        generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel,BoxLayout.Y_AXIS));


        twoB = new JPanel();
        twoB.setLayout(new BoxLayout(twoB,BoxLayout.X_AXIS));
        twoB.add(Box.createHorizontalGlue());

        threeB = new JPanel();
        threeB.setLayout(new BoxLayout(threeB,BoxLayout.X_AXIS));

        delete = new JButton("Eliminar compte");
        delete.setActionCommand(SetupStageGUI.DELETE_BTN);
        delete.setFont(new Font("Inter",Font.BOLD,14));
        delete.setBackground(buttonColor);
        delete.setForeground(Color.white);
        twoB.add(delete);
        twoB.add(Box.createHorizontalStrut(20));


        logout = new JButton("Tancar sessió");
        logout.setActionCommand(SetupStageGUI.LOGOUT_BTN);
        logout.setFont(new Font("Inter",Font.BOLD,14));
        logout.setBackground(buttonColor);
        logout.setForeground(Color.white);
        twoB.add(logout);

        generalPanel.add(Box.createVerticalStrut(50));
        generalPanel.add(twoB);
        generalPanel.add(Box.createVerticalStrut(30));

        //Title
        titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);
        generalPanel.add(titlePanel);
        generalPanel.add(Box.createVerticalStrut(50));

        //User
        playersPanel = new JPanel(new FlowLayout());
        playersPanel.add(players);

        comboBox = new JComboBox<Integer>();
        comboBox.setBounds(10,10,10,20);
        playersPanel.add(comboBox);
        comboBox.addItem(1);
        comboBox.addItem(2);
        comboBox.addItem(3);
        comboBox.addItem(4);
        //comboBox.addItemListener(this);
        playersPanel.setOpaque(false);
        threeB.add(playersPanel);
        rotate = new JButton("ROTATE");
        rotate.setActionCommand(SetupStageGUI.ROTATE);
        threeB.add(rotate);
        threeB.add(Box.createHorizontalStrut(10));
        eliminate = new JButton("ELIMINATE");
        eliminate.setActionCommand(SetupStageGUI.ELIMINATE);
        threeB.add(eliminate);
        threeB.add(Box.createHorizontalStrut(200));

        JPanel startPanel = new JPanel(new FlowLayout());
        startGame = new JButton("COMENÇAR");
        startGame.setActionCommand(SetupStageGUI.BEGIN_BUTTON);
        startGame.setBackground(new Color(124,136,248));
        startGame.setForeground(Color.white);
        startPanel.add(startGame);
        startPanel.setOpaque(false);

        threeB.add(startPanel);

        generalPanel.add(threeB);
        generalPanel.add(Box.createVerticalStrut(50));

        tablePanel = new JPanel(new GridLayout(16,16));

        emptyPanel = new JPanel();
        emptyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        emptyPanel.setBackground(new Color(89,185,198));
        tablePanel.add(emptyPanel);

        for (int i = 1; i <= 15; i++) {
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Integer.toString(i), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.red);
            cell.add(label);
            tablePanel.add(cell);
        }

        cells = new JButton[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells[i][j] = new JButton();
                cells[i][j].setName(String.format("cell%d%d", i, j));
                // Add button to panel or container
            }
        }

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanel.add(cell1);

            for (int i = 1; i <= 15; i++) {
                String actionCommand = String.format("cell%c%d", c, i);
                System.out.println(actionCommand);
                cells[i - 1][c - 'A'].setActionCommand(actionCommand);
                cells[i - 1][c - 'A'].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells[i - 1][c - 'A'].setBackground(Color.white);
                tablePanel.add(cells[i - 1][c - 'A']);
            }
        }



        tablePanel.setPreferredSize(new Dimension(400,400));

        grid2 = new JPanel();
        grid2.setLayout(new BoxLayout(grid2,BoxLayout.X_AXIS));



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


        portaCells = new JPanel[5];

        for (int i=0; i<5;i++){
            portaCells[i] = new JPanel();
            JLabel p = new JLabel("P");
            portaCells[i].setBackground(new Color(124,136,248));
            portaCells[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            p.setHorizontalAlignment(JLabel.CENTER);
            p.setVerticalAlignment(JLabel.CENTER);
            portaCells[i].add(p);
            rectPorta.add(portaCells[i]);
        }
        rectPorta.setBorder(BorderFactory.createEmptyBorder(20,120,20,120));
        boatsPanel.add(titlePortaLayout);
        boatsPanel.add(rectPorta);

        titleDestructor = new JLabel("Destructor");
        JPanel titleDestructorLayout = new JPanel(new FlowLayout());
        titleDestructorLayout.add(titleDestructor);
        titleDestructorLayout.setBackground(Color.white);

        rectDestructor = new JPanel(new GridLayout(1,4));
        destructorCells = new JPanel[4];

        for (int i=0; i<4;i++){
            destructorCells[i] = new JPanel();
            JLabel p = new JLabel("D");
            destructorCells[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            destructorCells[i].setBackground(Color.yellow);
            destructorCells[i].add(p);
            rectDestructor.add(destructorCells[i]);
        }
        rectDestructor.setBorder(BorderFactory.createEmptyBorder(20,130,20,130));
        boatsPanel.add(titleDestructorLayout);
        boatsPanel.add(rectDestructor);

        titleSubmari = new JLabel("Submarí");
        JPanel titleSubmariLayout = new JPanel(new FlowLayout());
        titleSubmariLayout.add(titleSubmari);
        titleSubmariLayout.setBackground(Color.white);

        rectSubmari = new JPanel(new GridLayout(1,3));
        submariCells = new JPanel[3];
        for (int i=0; i<3;i++){
            submariCells[i] = new JPanel();
            JLabel p = new JLabel("S");
            submariCells[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            submariCells[i].setBackground(Color.pink);
            submariCells[i].add(p);
            rectSubmari.add(submariCells[i]);
        }
        rectSubmari.setBorder(BorderFactory.createEmptyBorder(20,140,20,140));

        rectSubmari2 = new JPanel(new GridLayout(1,3));
        submari2Cells = new JPanel[3];
        for (int i=0; i<3;i++){
            submari2Cells[i] = new JPanel();
            JLabel p = new JLabel("S");
            submari2Cells[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            submari2Cells[i].setBackground(Color.pink);
            submari2Cells[i].add(p);
            rectSubmari2.add(submari2Cells[i]);
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

        llanxaCells = new JPanel[2];

        for (int i=0; i<2;i++){
            //JPanel cell = new JPanel();
            llanxaCells[i] = new JPanel();
            JLabel p = new JLabel("L");
            llanxaCells[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            llanxaCells[i].setBackground(Color.green);
            llanxaCells[i].add(p);
            rectLlanxa.add(llanxaCells[i]);
        }
        rectLlanxa.setBorder(BorderFactory.createEmptyBorder(20,150,20,150));
        boatsPanel.add(titleLlanxaLayout);
        boatsPanel.add(rectLlanxa);



        tablePanel.setBorder(BorderFactory.createEmptyBorder(40,0,40,0));
        tablePanel.setOpaque(false);
        grid2.add(tablePanel);
        grid2.add(Box.createHorizontalStrut(20));
        grid2.add(boatsPanel);

        generalPanel.add(grid2);
        // generalPanel.setBorder(BorderFactory.createEmptyBorder(100,200,20,200));


        generalPanel.setOpaque(false);
        titlePanel.setOpaque(false);
        grid2.setOpaque(false);
        twoB.setOpaque(false);
        rectLlanxa.setOpaque(false);
        rectDestructor.setOpaque(false);
        rectSubmari.setOpaque(false);
        rectPorta.setOpaque(false);
        rectSubmari2.setOpaque(false);
        threeB.setOpaque(false);
        boatsPanel.setBackground(Color.white);

        //Center the GridLayout

        this.add(generalPanel);
        //this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(new Color(217,249,253));

        rectPorta.setOpaque(false);
        //rectPorta.setActionCommand(PORTAAVIONS);

        setVisible(true);
    }

    public void setUpButtonController(ActionListener listener,MouseListener mouseListener) {
        //Añadir todos los listeners
        logout.addActionListener(listener);
        delete.addActionListener(listener);
        startGame.addActionListener(listener);
        //cell2.addActionListener(listener);
        rectPorta.addMouseListener(mouseListener);
        rectDestructor.addMouseListener(mouseListener);
        rectSubmari.addMouseListener(mouseListener);
        rectSubmari2.addMouseListener(mouseListener);
        rectLlanxa.addMouseListener(mouseListener);
        rotate.addActionListener(listener);
        eliminate.addActionListener(listener);
        //tablePanel.addMouseListener(mouseListener);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells[i][j].addActionListener(listener);
                // Add button to panel or container
            }
        }

    }







    public void paintUsedBoats(int i,String boat){
        switch(boat){
            case "PortaAvions" -> portaCells[i].setBackground(Color.gray);
            case "Destructor" -> destructorCells[i].setBackground(Color.gray);
            case "Submari" -> submariCells[i].setBackground(Color.gray);
            case "Submari2" -> submari2Cells[i].setBackground(Color.gray);
            case "Llanxa" -> llanxaCells[i].setBackground(Color.gray);
        }
    }



    public boolean  checkCellVertical(int number,int positionLetter, int i){
        boolean ok1 = false,ok2 = false,ok3 = false,ok4 = false,ok5 = false,ok6 = false,ok7 = false,ok8=false;

        if(number > 15){
            ok1 = true;
        } else{
            ok1 = cells[number][positionLetter + i - 1].getBackground().equals(Color.white);
        }

        if(number-2 < 0){
            ok2 = true;
        } else{
            ok2 = cells[number - 2][positionLetter + i - 1].getBackground().equals(Color.white);
        }

        if(positionLetter+i-2 < 0){
            ok3 = true;
        } else{
            ok3 = cells[number - 1][positionLetter + i - 2].getBackground().equals(Color.white);
        }

        if(positionLetter+i > 15){
            ok4 = true;
        } else{
            ok4 = cells[number - 1][positionLetter + i].getBackground().equals(Color.white);
        }

        if(positionLetter+i > 15 && number > 15) {
            ok5 = true;
        }   else{
                ok5 = cells[number][positionLetter + i].getBackground().equals(Color.white);
        }

        if(positionLetter+i > 15 && number-2 < 0) {
            ok6 = true;
        }   else{
            ok6 = cells[number-2][positionLetter + i].getBackground().equals(Color.white);
        }

        if(positionLetter+i-2 < 0 && number-2 < 0) {
            ok7 = true;
        }   else{
            ok7 = cells[number-2][positionLetter + i-2].getBackground().equals(Color.white);
        }

        if(positionLetter+i-2 < 0 && number > 15) {
            ok8 = true;
        }   else{
            ok8 = cells[number][positionLetter + i-2].getBackground().equals(Color.white);
        }


        return cells[number - 1][positionLetter + i - 1].getBackground().equals(Color.white) && ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8;
    }


    public boolean  checkCellHorizontal(int number,int positionLetter, int i){

        boolean ok1 = false,ok2 = false,ok3 = false,ok4 = false,ok5 = false,ok6 = false,ok7 = false,ok8=false;

        if(number+i >= 15){
            ok1 = true;
        } else{
            ok1 = cells[number+i][positionLetter- 1].getBackground().equals(Color.white);
        }

        if(number-2+i <= 0){
            ok2 = true;
        } else{
            ok2 = cells[number+i - 2][positionLetter - 1].getBackground().equals(Color.white);
        }

        if(positionLetter-2 < 0){
            ok3 = true;
        } else{
            ok3 = cells[number+i - 1][positionLetter - 2].getBackground().equals(Color.white);
        }

        if(positionLetter > 15){
            ok4 = true;
        } else{
            ok4 = cells[number+i - 1][positionLetter].getBackground().equals(Color.white);
        }

        if(positionLetter+i >= 15 && number+i >= 15) {
            ok5 = true;
        }   else{
            ok5 = cells[number+i][positionLetter].getBackground().equals(Color.white);
        }

        if(positionLetter > 15 && number-2+i <= 0) {
            ok6 = true;
        }   else{
            ok6 = cells[number+i-2][positionLetter].getBackground().equals(Color.white);
        }

        if(positionLetter-2 < 0 && number-2+i <= 0) {
            ok7 = true;
        }   else{
            ok7 = cells[number+i-2][positionLetter-2].getBackground().equals(Color.white);
        }

        if(positionLetter-2 < 0 && number+i >= 15) {
            ok8 = true;
        }   else{
            ok8 = cells[number+i][positionLetter-2].getBackground().equals(Color.white);
        }

        return cells[number + i - 1][positionLetter - 1].getBackground().equals(Color.white) && ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8;

    }

    public void paintBoatVertical(int number,int positionLetter, int i, Color boatColor, String text){
        cells[number-1][positionLetter+i-1].setBackground(boatColor);
        cells[number-1][positionLetter+i-1].setText(text);
    }
    public void paintBoatHorizontal(int number,int positionLetter, int i, Color boatColor, String text){
        cells[number+i-1][positionLetter-1].setBackground(boatColor);
        cells[number+i-1][positionLetter-1].setText(text);
    }

    public int getNumPlayers(){
        return (int) comboBox.getSelectedItem();
    }

    public void unPaintBoats(){
        for (int i = 0;i<5;i++){
            portaCells[i].setBackground(new Color(124,136,248));
        }
        for (int i = 0;i<4;i++){
            destructorCells[i].setBackground(Color.yellow);
        }
        for(int i = 0;i<3;i++){
            submariCells[i].setBackground(Color.pink);
        }
        for(int i = 0;i<3;i++){
            submari2Cells[i].setBackground(Color.pink);
        }
        for(int i = 0;i<2;i++){
            llanxaCells[i].setBackground(Color.green);
        }
    }

    public void unPaintTable(){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                cells[i][j].setBackground(Color.white);
                cells[i][j].setText("");
            }
        }
    }

}
