package Presentation.Views;

import Business.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Vista de las estadisticas del juego
 */

public class StatisticsMenuGUI extends JPanel {

    private JLabel title;

    private JButton searchUser, cancelButton, deleteButton, closeButton;
    private Color buttonColor;
    private JPanel generalPanel, borderPanel, threeB, userPanel;

    private JTextField userText;
    public static final String CANCEL_BTN = "CANCEL_BTN";
    public static final String DELETE_BTN = "DELETE_BTN";
    public static final String LOGOUT_BTN = "LOGOUT_BTN";
    public static final String SEARCH_BTN = "SEARCH_BTN";


    private SaveGame saveGame;

    /**
     * Constructor de la función donde se configura el panel
     *
     * @param saveGame de tipo SaveGame contiene la partida guardada
     */
    public StatisticsMenuGUI(SaveGame saveGame) {

        this.saveGame = saveGame;


        ArrayList<String> userList = saveGame.getUsers();


        buttonColor = new Color(124, 136, 248);

        title = new JLabel("PLAYER STATS");
        title.setFont(new Font("Iceland", Font.BOLD, 96));
        title.setForeground(Color.blue);


        // Botón de cancelar
        cancelButton = new JButton("Enrere");
        cancelButton.setActionCommand(StatisticsMenuGUI.CANCEL_BTN);
        cancelButton.setFont(new Font("Inter", Font.BOLD, 14));
        cancelButton.setBackground(buttonColor);
        cancelButton.setForeground(Color.white);

        deleteButton = new JButton("Eliminar Compte");
        deleteButton.setActionCommand(StatisticsMenuGUI.DELETE_BTN);
        deleteButton.setFont(new Font("Inter", Font.BOLD, 14));
        deleteButton.setBackground(buttonColor);
        deleteButton.setForeground(Color.white);

        closeButton = new JButton("Tancar sessió");
        closeButton.setActionCommand(StatisticsMenuGUI.LOGOUT_BTN);
        closeButton.setFont(new Font("Inter", Font.BOLD, 14));
        closeButton.setBackground(buttonColor);
        closeButton.setForeground(Color.white);

        borderPanel = new JPanel(new FlowLayout());
        threeB = new JPanel(new GridLayout(1, 3, 10, 10));
        threeB.add(cancelButton);
        threeB.add(deleteButton);
        threeB.add(closeButton);
        borderPanel.setOpaque(false);
        borderPanel.add("North", threeB);

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane(userPanel);

        // Agregar nombres de usuarios y botones
        for (String user : userList) {
            JLabel userLabel = new JLabel(user);
            userLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Alineación en el centro vertical
            userPanel.add(userLabel);
        }

        JPanel searchUserPanel = new JPanel();
        searchUserPanel.setLayout(new GridLayout(1,2,10,10));
        this.userText = new JTextField();
        searchUserPanel.add(userText);

        searchUser = new JButton("Buscar estadístiques del usuari");
        searchUser.setActionCommand(StatisticsMenuGUI.SEARCH_BTN);
        searchUser.setFont(new Font("Inter", Font.BOLD, 12));
        searchUser.setBackground(buttonColor);
        searchUser.setForeground(Color.white);
        searchUserPanel.add(searchUser);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.NORTH);
        centerPanel.add(searchUserPanel, BorderLayout.CENTER);

        generalPanel = new JPanel(new BorderLayout());
        generalPanel.add(title, BorderLayout.NORTH);
        generalPanel.add(borderPanel, BorderLayout.SOUTH);
        generalPanel.add(centerPanel, BorderLayout.CENTER);
        generalPanel.setOpaque(false);
        generalPanel.setBorder(BorderFactory.createEmptyBorder(50, 300, 50, 300));


        this.setBackground(new Color(217, 249, 253));

        this.add(generalPanel);

    }

    /**
     * Método que sirve para obtener el login del usuario registrado.
     * @return Variable tipo String que contiene el login del usuario.
     */
    public String getUser() {
        return this.userText.getText();
    }

    /**
     * Agrega oyentes de eventos al menú de estadísticas.
     * @param listener Toma un parámetro listener de tipo ActionListener y lo asigna a los siguientes botones: searchUser, cancelButton, closeButton y deleteButton.
     */
    public void addStatsMenuListeners(ActionListener listener) {
        searchUser.addActionListener(listener);
        cancelButton.addActionListener(listener);
        closeButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
    }

    /**
     * Método para vaciar el campo de texto donde se introduce el nombre del usuario.
     */
    public void clear() {
        this.userText.setText("");
    }

}
