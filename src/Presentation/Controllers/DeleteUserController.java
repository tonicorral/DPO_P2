package Presentation.Controllers;

import Business.UserModel;
import Presentation.MainView;
import Presentation.Views.DeleteUserGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.UserModel.*;

public class DeleteUserController implements ActionListener {
    private DeleteUserGUI deleteUserGUI;

    private MainView mainView;

    private UserModel userModel;

    public DeleteUserController(DeleteUserGUI deleteUserGUI, MainView mainView, UserModel userModel) {
        this.deleteUserGUI = deleteUserGUI;
        this.mainView = mainView;
        this.userModel = userModel;
        mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        String email = deleteUserGUI.getInsertEmail();

        switch (e.getActionCommand()) {
            case DeleteUserGUI.BUTTON_DELETE:

                switch (userModel.deleteUser(email)) {
                    case NO_USER -> mainView.showError("User does not exist");
                    case EMPTY_FIELD -> mainView.showError("There is an empty field!");
                    case EVERYTHING_OK -> mainView.switchView(MainView.START_VIEW);

                }

            case DeleteUserGUI.DELETE_CANCEL_BTN:
                mainView.switchView(MainView.MENU_VIEW);
                break;

        }
    }

}
