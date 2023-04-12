package Presentation.Controllers;

import Business.UserManager;
import Presentation.Views.DeleteUserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserController implements ActionListener {
    private final DeleteUserGUI view;
    private final UserManager userManager;

    public DeleteUserController(DeleteUserGUI view, UserManager userManager) {
        this.view = view;
        this.userManager = userManager;
        this.view.addDeleteButtonListener(this);
        this.view.addCancelButtonListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DeleteUserGUI.BUTTON_DELETE)) {
            String username = view.getUsername();

            // Check if the user exists before attempting to delete
            if (userManager.userExists(username)) {
                int result = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro que deseas eliminar el usuario '" + username + "'?", "Confirmación de eliminación",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    // Delete the user from the system
                    if (userManager.deleteUser(username)) {
                        JOptionPane.showMessageDialog(null, "El usuario '" + username + "' ha sido eliminado.");
                        view.resetFields();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Error al eliminar el usuario '" + username + "'. Inténtalo de nuevo.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario '" + username + "' no existe.");
            }
        } else if (e.getActionCommand().equals(DeleteUserGUI.DELETE_CANCEL_BTN)) {
            view.resetFields();
        }
    }
}
