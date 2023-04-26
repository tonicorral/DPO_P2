package Presentation.Controllers;

import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.DeleteUserGUI;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserController implements ActionListener {
    private DeleteUserGUI deleteUserGUI;

    private MainController mainController;
    private MainView mainView;

    public DeleteUserController(DeleteUserGUI deleteUserGUI, MainController mainController, MainView mainView) {
        this.deleteUserGUI = deleteUserGUI;
        this.mainView = mainView;
        this.mainController = mainController;
        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case DeleteUserGUI.BUTTON_DELETE:
                mainView.switchView(MainView.MENU_VIEW);
                break;
            case DeleteUserGUI.DELETE_CANCEL_BTN:
                mainView.switchView(MainView.START_VIEW);
                break;

        }
    }

}
