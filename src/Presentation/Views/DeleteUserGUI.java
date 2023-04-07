package Presentation.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteUserGUI extends JPanel {
    private JTextField userText;
    private JButton deleteButton;
    private JButton cancelButton;

    public static final String BUTTON_DELETE = "BUTTON_DELETE";
    public static final String DELETE_CANCEL_BTN = "DELETE_CANCEL_BTN";


    public DeleteUserGUI() throws HeadlessException {
        configurePanel();
    }


    private void configurePanel() {
        JPanel main = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel bottom = new JPanel();
        setLayout(new BorderLayout());

        main.setBackground(Color.DARK_GRAY);
        center.setBackground(Color.LIGHT_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
        JLabel title = new JLabel("ELIMINAR USUARIO");
        this.setBackground(Color.DARK_GRAY);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        title.setForeground(Color.WHITE);
        deleteButton = new JButton("Eliminar");
        cancelButton = new JButton("Cancelar");

        deleteButton.setActionCommand(DeleteUserGUI.BUTTON_DELETE);
        cancelButton.setActionCommand(DeleteUserGUI.DELETE_CANCEL_BTN);

        Container user = new Container();
        user.setLayout(new BoxLayout(user, BoxLayout.PAGE_AXIS));
        bottom.setLayout(new FlowLayout());

        JLabel usernametext = new JLabel("Introduce el nombre del usuario a eliminar:");
        usernametext.setFont(new Font("Helvetica", Font.PLAIN, 25));
        user.add(usernametext);
        userText = new JTextField(20);
        user.add(userText);

        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        bottom.add(deleteButton);
        bottom.add(cancelButton);

        center.add(user);

        title.setHorizontalAlignment(JLabel.CENTER);
        main.add(Box.createRigidArea(new Dimension(50, 100)), BorderLayout.NORTH);
        main.add(center, BorderLayout.CENTER);
        main.add(bottom, BorderLayout.SOUTH);

        add(title, BorderLayout.NORTH);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(300, 300)), BorderLayout.SOUTH);
        add(main, BorderLayout.CENTER);
    }


    public void addDeleteButtonListener(ActionListener listener) {

        deleteButton.addActionListener(listener);
    }
}
