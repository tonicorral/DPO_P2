package Presentation.Controllers;


import Presentation.MainView;
import Presentation.Views.LogoutGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController implements ActionListener {
    private LogoutGUI logoutGUI;
    private MainView mainView;

    public LogoutController(LogoutGUI logoutGUI, MainView mainView) {
        this.logoutGUI = logoutGUI;
        this.mainView = mainView;

        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case LogoutGUI.BUTTON_LOGOUT:
                mainView.switchView(MainView.START_VIEW);

                break;
            case LogoutGUI.BUTTON_CANCEL:
                mainView.switchView(MainView.MENU_VIEW);
                break;
        }
    }

}
