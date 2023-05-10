package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
       

public class LogoutGUI extends JPanel {
    private JButton logoutButton; //Botón para hacer el logout

    private JButton cancelLogoutButton;

    public static final String BUTTON_LOGOUT = "BUTTON_LOGOUT";

    public static final String BUTTON_CANCEL = "BUTTON_CANCEL";

    public LogoutGUI() throws HeadlessException {
        //Si no hay ningún medio de visualización, hago el configurePanel
        configurePanel();
    }


    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());
        setLayout(new BorderLayout());

        main.setBackground(Color.DARK_GRAY);
        center.setBackground(Color.LIGHT_GRAY);
        JLabel title = new JLabel("¿Do you want to log out?");
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        title.setForeground(Color.YELLOW);
        title.setHorizontalAlignment(JLabel.CENTER);

        main.add(Box.createRigidArea(new Dimension(50, 100)), BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);

        //creo el botón para hacer el logout
        logoutButton = new JButton("Log out");
        logoutButton.setActionCommand(LogoutGUI.BUTTON_LOGOUT);

        cancelLogoutButton = new JButton("Cancel");
        cancelLogoutButton.setActionCommand(LogoutGUI.BUTTON_CANCEL);

        center.add(logoutButton);
        center.add(cancelLogoutButton);

        add(title, BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);

        setVisible(true);
    }


    public void addLogoutButtonListener(ActionListener listener) {

        logoutButton.addActionListener(listener);
        cancelLogoutButton.addActionListener(listener);
    }

}
