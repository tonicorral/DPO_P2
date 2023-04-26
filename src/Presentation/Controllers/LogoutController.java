package Presentation.Controllers;

import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.LogoutGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController implements ActionListener {
    private LogoutGUI logoutGUI;

    private MainController mainController;
    private MainView mainView;

    public LogoutController(LogoutGUI logoutGUI, MainController mainController, MainView mainView) {
        this.logoutGUI = logoutGUI;
        this.mainView = mainView;
        this.mainController = mainController;
        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case LogoutGUI.BUTTON_LOGOUT:
                mainView.switchView(MainView.START_VIEW);

                break;
        }
    }

}
