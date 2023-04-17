package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartGUI extends JPanel {
    private JButton signUp;
    private JButton logIn;
    public static final String START_LOGIN = "START_LOGIN";
    public static final String START_SIGNUP = "START_SIGNUP";

    public StartGUI(){

        this.setLayout(new GridBagLayout()); // la interfaz tendra un GrindBag layout

        JLabel title1 = new JLabel("Battleship", SwingConstants.CENTER);

        GridBagConstraints constraints = new GridBagConstraints();// necesario para el layout
        this.setBackground(Color.DARK_GRAY);

        this.add(title1);
        title1.setForeground(Color.YELLOW);
        title1.setFont(new Font("Inter", Font.BOLD, 100));
        this.add(title1, constraints);
        constraints.weightx = 0.0;



        //BUTTONS
        signUp = new JButton("Sign Up");
        signUp.setActionCommand(StartGUI.START_SIGNUP);
        signUp.setFont(new Font("Inter", Font.PLAIN, 20));
        constraints.gridx= 2;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(signUp, constraints);
        constraints.weightx = 0.0;



        logIn = new JButton("Log In");
        logIn.setActionCommand(StartGUI.START_LOGIN);
        logIn.setFont(new Font("Helvetica", Font.PLAIN, 20));
        constraints.gridx= 1;
        constraints.gridy=3;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        constraints.weightx = 1.0;
        this.add(logIn, constraints);
        constraints.weightx = 1.0;

        JLabel Register = new JLabel("Select Sign up");
        Register.setForeground(Color.WHITE);
        Register.setFont(new Font("Inter", Font.BOLD, 30));
        constraints.gridx= 2;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        this.add(Register, constraints);

        JLabel Log = new JLabel("Select Log in");
        Log.setFont(new Font("Inter", Font.BOLD, 30));
        Log.setForeground(Color.WHITE);
        constraints.gridx= 1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.gridheight=1;
        this.add(Log, constraints);


    }

    /**
     * Control de los botons del log in i el sign up
     * @param listener paramete per saber on estem
     */
    public void registerController(ActionListener listener) {
        logIn.addActionListener(listener);
        signUp.addActionListener(listener);

    }


}
