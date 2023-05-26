package Presentation;

import Presentation.Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

/**
 * Configuramos todas las vistas del videojuego para poder cambiar en funcion de las voluntades del usuario
 */
public class MainView extends JFrame {
    private LoginGUI loginGUI;
    private SignUpGUI signUpGUI;
    private MenuGUI menuGUI;

    private StartGUI startGUI;

    private LogoutGUI logoutGUI;
    private DeleteUserGUI deleteUserGUI;
    private SetupStageGUI setupStageGUI;

    private GameStageGUI gameStageGUI;
    private StatisticsGUI statisticsGUI;

    private CardLayout cardLayout;
    public static final String LOGIN_VIEW = "LOGIN_VIEW";
    public static final String SIGNUP_VIEW = "SIGNUP_VIEW";
    public static final String START_VIEW = "START_VIEW";
    public static final String MENU_VIEW = "MENU_VIEW";
    public static final String LOGOUT_VIEW = "LOGOUT_VIEW";
    public static final String DELETE_VIEW = "DELETE_VIEW";
    public static final String SETUP_VIEW = "SETUP_VIEW";

    public static final String GAME_STAGE_VIEW = "GAME_STAGE_VIEW";

    public static final String STATISTICS_VIEW = "STATISTICS_VIEW";

    /**
     * Constructor donde inicializamos todas las vistas
     * @param loginGUI vista de inicar sesion
     * @param signUpGUI vista para registrarse
     * @param menuGUI vista principal del menu
     * @param startGUI vista inical del juego
     * @param logoutGUI vista para cerrar sesion
     * @param deleteUserGUI vista para borrar usuario
     * @param setupStageGUI vista encargada de la inizalización de los barcos
     * @param gameStageGUI  vista de la partida
     * @param statisticsGUI vista de las estatisticas
     */
    public MainView(LoginGUI loginGUI, SignUpGUI signUpGUI, MenuGUI menuGUI, StartGUI startGUI, LogoutGUI logoutGUI,
                    DeleteUserGUI deleteUserGUI,SetupStageGUI setupStageGUI, GameStageGUI gameStageGUI, StatisticsGUI statisticsGUI) {
        this.loginGUI = loginGUI;
        this.signUpGUI = signUpGUI;
        this.menuGUI = menuGUI;
        this.startGUI = startGUI;
        this.logoutGUI = logoutGUI;
        this.deleteUserGUI = deleteUserGUI;
        this.setupStageGUI = setupStageGUI;
        this.gameStageGUI = gameStageGUI;
        this.statisticsGUI = statisticsGUI;

        configurationFrame();
        configLayout();
    }

    /**
     * Diseño y título del cardLayout
     */
    private void configurationFrame() {
        pack();     //dejarlo compacto
        setTitle("Battleship");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();      //dimensiones de tu pantalla
        setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //que pasara cuando cierres la pantalla //Acabar el programa
    }

    /**
     * Configuración del cardLayout
     */
    private void configLayout(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        this.add(MainView.START_VIEW, startGUI);
        this.add(MainView.LOGIN_VIEW, loginGUI);    //le asginamos una vista con una frase y cuando le pasamos la frase pasa la vista asociada
        this.add(MainView.SIGNUP_VIEW, signUpGUI);
        this.add(MainView.MENU_VIEW, menuGUI);
        this.add(MainView.LOGOUT_VIEW, logoutGUI);
        this.add(MainView.DELETE_VIEW, deleteUserGUI);


        this.add(MainView.SETUP_VIEW, setupStageGUI);
        this.add(MainView.GAME_STAGE_VIEW, gameStageGUI);
        this.add(MainView.STATISTICS_VIEW, statisticsGUI);
    }

    /**
     * Cambiar de vista
     * @param view String que identifica la vista que se desea modificar
     */

    public void switchView(String view) {
        cardLayout.show(getContentPane(),view);
        loginGUI.clear();
        signUpGUI.clear();

    }


    /**
     * Asignamos el mismo listener a todas las vistas
     * @param listener parametro de tipo AcitonListener para saber donde estamos
     */
    public void setListeners(ActionListener listener){
        startGUI.setStartListenters(listener);

        loginGUI.LoginController(listener);


        signUpGUI.setSignUpListeners(listener);


        menuGUI.menuButtonController(listener);

        logoutGUI.addLogoutButtonListener(listener);

        deleteUserGUI.addCancelButtonListener(listener);
        deleteUserGUI.addDeleteButtonListener(listener);

        statisticsGUI.addStatListeners(listener);

       // setupStageGUI.setUpButtonController(listener);
        gameStageGUI.setGameListener(listener);
    }


    /**
     * Asinamos una accion al ratón de tipo Listener
     * @param listener parametro de tipo AcitonListener para saber donde estamos
     * @param mouseListener paramentro de tipo MouseListener para que el ratón detecte acciones
      */
    public void setActionMouseListeners(ActionListener listener, MouseListener mouseListener){

        setupStageGUI.setUpButtonController(listener, mouseListener);

    }



    public int showConfirmPopUp(String text, String[] questions) {
        return JOptionPane.showOptionDialog(this, text, null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, questions, questions[0]);

    }


    /**
     * Mostrar error
     * @param error String que muestra el error
     */
public void showError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    public String showInputPopUp(String text){

        String message = JOptionPane.showInputDialog(this, text);

        return message;
    }



}

