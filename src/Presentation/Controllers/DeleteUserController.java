package Presentation.Controllers;

import Business.UserModel;
import Presentation.Views.DeleteUserGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserController implements ActionListener {
    private final DeleteUserGUI view;
    private final UserModel userManager;

    public DeleteUserController(DeleteUserGUI view, UserModel userManager) {
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
                        //view.MostrarError(), metodo en deleteusergui que se encargue de los jpane.
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
   /* public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DeleteUserGUI.BUTTON_DELETE)) {
            view.deleteUser();
        } else if (e.getActionCommand().equals(DeleteUserGUI.DELETE_CANCEL_BTN)) {
            view.resetFields();
        }
    }*/
}
