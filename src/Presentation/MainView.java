package Presentation;

import Presentation.Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {


    private LoginGUI loginGUI;
    private SignUpGUI signUpGUI;
    private MenuGUI menuGUI;

    private StartGUI startGUI;

    private LogoutGUI logoutGUI;
    private DeleteUserGUI deleteUserGUI;
    private JPanel mainViewPanel;
    private CardLayout cardLayout;
    public static final String LOGIN_VIEW = "LOGIN_VIEW";
    public static final String SIGNUP_VIEW = "SIGNUP_VIEW";
    public static final String START_VIEW = "START_VIEW";
    public static final String BOARD_VIEW = "BOARD_VIEW";
    public static final String MENU_VIEW = "MENU_VIEW";
    public static final String LOGOUT_VIEW = "LOGOUT_VIEW";
    public static final String DELETE_VIEW = "DELETE_VIEW";


    public MainView(LoginGUI loginGUI, SignUpGUI signUpGUI, MenuGUI menuGUI, StartGUI startGUI, LogoutGUI logoutGUI, DeleteUserGUI deleteUserGUI) {
        this.loginGUI = loginGUI;
        this.signUpGUI = signUpGUI;
        this.menuGUI = menuGUI;
        this.startGUI = startGUI;
        this.logoutGUI = logoutGUI;
        this.deleteUserGUI = deleteUserGUI;
        configurationFrame();
        configLayout();
    }
    private void configurationFrame() {
        pack();     //dejarlo compacto
        setTitle("Battleship");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();      //dimensiones de tu pantalla
        setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //que pasara cuando cierres la pantalla //Acabar el programa
    }
    private void configLayout(){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        this.add(MainView.START_VIEW, startGUI);
        this.add(MainView.LOGIN_VIEW, loginGUI);    //le asginamos una vista con una frase y cuando le pasamos la frase pasa la vista asociada
        this.add(MainView.SIGNUP_VIEW, signUpGUI);
        this.add(MainView.MENU_VIEW, menuGUI);
        this.add(MainView.LOGOUT_VIEW, logoutGUI);
        this.add(MainView.DELETE_VIEW, deleteUserGUI);
    }

    public void switchView(String view) {
        cardLayout.show(getContentPane(),view);
        loginGUI.clear();
        signUpGUI.clear();

    }

    public void setListeners(ActionListener listener){
        startGUI.registerController(listener);

        loginGUI.LoginController(listener);
        loginGUI.registerController(listener);

        signUpGUI.registerSignUpController(listener);
        signUpGUI.registerController(listener);

        menuGUI.menuButtonController(listener);

        logoutGUI.addLogoutButtonListener(listener);

        deleteUserGUI.addCancelButtonListener(listener);
        deleteUserGUI.addDeleteButtonListener(listener);

    }
    public int showConfirmPopUp(String text, String[] questions) {
        return JOptionPane.showOptionDialog(this, text, null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, questions, questions[0]);

    }

    public void showError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    public String showInputPopUp(String text){

        String message = JOptionPane.showInputDialog(this, text);

        return message;
    }





            /**
            this.setSize(819, 642);
            this.setTitle("Battleships");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);

            mainViewPanel = new JPanel();
            cardLayout = new CardLayout();
            mainViewPanel.setLayout(cardLayout);

            loginGUI = new LoginGUI(this);
            JPanel card1 = new JPanel();
            card1.add(loginGUI);
            mainViewPanel.add(card1,"LOGIN");

            signUpGUI = new SignUpGUI(this);
            JPanel card2 = new JPanel();
            card2.add(signUpGUI);
            mainViewPanel.add(card2,"SIGNUP");

            menuGUI = new MenuGUI(this);
            JPanel card3 = new JPanel();
            card3.add(menuGUI);
            mainViewPanel.add(card3,"MENU");

            add(mainViewPanel,BorderLayout.CENTER);

            cardLayout.show(mainViewPanel,"LOGIN");

            setVisible(true);


        }

        public void showSignUp(){
            cardLayout.show(mainViewPanel,"SIGNUP");
        }

        public void showLogin(){
            cardLayout.show(mainViewPanel,"LOGIN");
        }

        public void showMenu(){
            cardLayout.show(mainViewPanel,"MENU");
        }

**/
}

