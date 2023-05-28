package Presentation.Views;

import Business.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Vista del inicio del juego
 */
public class LoadGameGUI extends JPanel {

    private SaveGame saveGame;
    private JButton cargarButton;
    private JList<String> partidaJList;
    public static final String CARGAR_BTN = "CARGAR_BTN";



    /**
     * Constructor de la funcion de inicilización del juego
     */
    public LoadGameGUI(SaveGame saveGame) {

        this.saveGame = saveGame;

        configurePanel();
    }

    /**
     * Contructor del panel para poder cargar la partida
     */
    public void configurePanel(){


        ArrayList<String> partidaList = saveGame.extraerNombresPartidas();

        setLayout(new BorderLayout());

        JLabel title1 = new JLabel("CARGAR PARTIDA", SwingConstants.CENTER);
        title1.setForeground(new Color(124, 136, 248));
        title1.setFont(new Font("Inter", Font.BOLD, 30));

        partidaJList = new JList<>(partidaList.toArray(new String[0]));
        partidaJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        partidaJList.setFont(new Font("Inter", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(partidaJList);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        cargarButton = new JButton("Volver Menu");
        cargarButton.setActionCommand(CARGAR_BTN);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(scrollPane, new GridBagConstraints());
        centerPanel.add(cargarButton, new GridBagConstraints());

        add(title1, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Inicializa el botón de cargar partida
     * @param listener parametro actionLister para detectar donde estamos
     */
    public void setLoadGameListener(ActionListener listener) {
        this.cargarButton.addActionListener(listener);

    }

}
