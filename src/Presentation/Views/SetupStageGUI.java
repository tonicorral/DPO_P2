package Presentation.Views;

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
    private JButton startGame,logout,delete;

    private Color buttonColor;

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
        delete.setActionCommand(MenuGUI.DELETE_MENU_BTN);
        delete.setFont(new Font("Inter",Font.BOLD,14));
        delete.setBackground(buttonColor);
        delete.setForeground(Color.white);
        twoB.add(delete);
        twoB.add(Box.createHorizontalStrut(20));


        logout = new JButton("Tancar sessió");
        logout.setActionCommand(MenuGUI.LOGOUT_MENU_BTN);
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
        threeB.add(Box.createHorizontalStrut(200));

        JPanel startPanel = new JPanel(new FlowLayout());
        startGame = new JButton("COMENÇAR");
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

        for (char c = 'A'; c <= 'O'; c++) {
            JPanel cell1 = new JPanel();
            cell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(Character.toString(c), JLabel.CENTER);
            label.setFont(new Font("Inter", Font.BOLD, 12));
            label.setForeground(Color.green);
            cell1.add(label);
            tablePanel.add(cell1);

            for (int i = 2; i <= 16; i++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablePanel.add(cell);
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





        grid2.add(boatsPanel);
        grid2.add(Box.createHorizontalStrut(20));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(40,0,40,0));
        tablePanel.setOpaque(false);
        grid2.add(tablePanel);


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

        MouseAdapter ma = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
        };

        rectPorta.addMouseListener(ma);
        rectDestructor.addMouseListener(ma);
        rectSubmari.addMouseListener(ma);
        rectSubmari2.addMouseListener(ma);
        rectLlanxa.addMouseListener(ma);

        MouseMotionAdapter mma = new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        };
        rectPorta.addMouseMotionListener(mma);


        rectPorta.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                // Check if the Ctrl key is pressed
                if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0) {
                    // Invert the grid layout based on the mouse wheel direction
                        rectPorta.setLayout(new GridLayout(5, 1));
                        rectPorta.revalidate();


                }
            }
        });



        setVisible(true);
    }

}


