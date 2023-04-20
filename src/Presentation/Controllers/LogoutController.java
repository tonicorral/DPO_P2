package Presentation.Controllers;

import Business.UserModel;
//import Domain.UserManager;
import Presentation.Views.LogoutGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController implements ActionListener {
    private final UserModel userManager;
    private final LogoutGUI view;

    public LogoutController(UserModel userManager, LogoutGUI view) {
        this.userManager = userManager;
        this.view = view;
    }


    public void actionPerformed(ActionEvent e) {
       /* if (e.getActionCommand().equals(LogoutGUI.BUTTON_LOGOUT)) {
            userManager.logoutCurrentUser();
            view.dispose();
        }
        */

    }

    //cambio de vistas con el mainView
}
