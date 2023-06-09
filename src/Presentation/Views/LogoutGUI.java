package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * Vista de cerrar sesión
 */
public class LogoutGUI extends JPanel {
    private JButton logoutButton; //Botón para hacer el logout

    private JButton cancelLogoutButton;

    public static final String BUTTON_LOGOUT = "BUTTON_LOGOUT";

    public static final String BUTTON_CANCEL = "BUTTON_CANCEL";


    /**
     * Constructor del panel de cerrar sesion
     * @throws HeadlessException excepción de tiempo de ejecución en Java que se produce cuando se llama a un código que depende de un teclado, una pantalla o un mouse en un entorno que no es compatible con un teclado, una pantalla o un mouse
     */
    public LogoutGUI() throws HeadlessException {
        //Si no hay ningún medio de visualización, hago el configurePanel
        configurePanel();
    }

    /**
     * Configura el panel de la vista
     */
    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());
        setLayout(new BorderLayout());

        main.setBackground(Color.DARK_GRAY);
        center.setBackground(Color.LIGHT_GRAY);
        JLabel title = new JLabel("¿Do you want to log out?");
        title.setFont(new Font("Inter", Font.BOLD, 48));
        title.setForeground(Color.DARK_GRAY);
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

    /**
     * Controla el boton para salir del panel
     * @param listener parametro actionLister para detectar donde estamos
     */
    public void addLogoutButtonListener(ActionListener listener) {

        logoutButton.addActionListener(listener);
        cancelLogoutButton.addActionListener(listener);
    }

}
