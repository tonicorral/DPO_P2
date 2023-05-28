package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Vista del inicio del juego
 */
public class StartGUI extends JPanel {
    private JButton signUp;
    private JButton logIn;
    public static final String START_LOGIN = "START_LOGIN";
    public static final String START_SIGNUP = "START_SIGNUP";
    private JPanel generalPanel;


    /**
     * Constructor de la funcion de inicilización del juego
     */
    public StartGUI(){

        generalPanel = new JPanel(new GridBagLayout());

        JLabel title1 = new JLabel("WELCOME TO BATTLESHIP", SwingConstants.CENTER);

        this.setBackground(Color.white);

        title1.setForeground(new Color(124,136,248));
        title1.setFont(new Font("Inter", Font.BOLD, 100));

        //BUTTONS
        signUp = new JButton("Sign Up");
        signUp.setActionCommand(StartGUI.START_SIGNUP);
        signUp.setFont(new Font("Inter", Font.PLAIN, 20));
        signUp.setBackground(new Color(124,136,248));
        signUp.setForeground(Color.white);


        logIn = new JButton("Log In");
        logIn.setActionCommand(StartGUI.START_LOGIN);
        logIn.setFont(new Font("Helvetica", Font.PLAIN, 20));
        logIn.setBackground(new Color(124,136,248));
        logIn.setForeground(Color.white);


        JLabel Register = new JLabel("Select Sign up");
        Register.setFont(new Font("Inter", Font.BOLD, 30));


        JLabel Log = new JLabel("Select Log in");
        Log.setFont(new Font("Inter", Font.BOLD, 30));


        GridBagConstraints c = new GridBagConstraints();
        JPanel gridButtons = new JPanel(new GridLayout(1,2,30,30));

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(350, 400, 20, 400);
        generalPanel.add(title1,c);

        c.gridy = 2;
        c.insets = new Insets(100, 400, 500, 400);
        gridButtons.add(logIn);
        gridButtons.add(signUp);
        generalPanel.add(gridButtons,c);

        this.add(generalPanel);
    }

    /**
     * Control de los botones del log in y el sign up
     * @param listener parametro actionLister para detectar donde estamos
     */
    public void setStartListenters(ActionListener listener) {
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);

    }


}
