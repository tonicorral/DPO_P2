
package Presentation;


import Presentation.Views.StartGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        mainView.setListeners(this);
    }
    public void switchView(String view) {
        mainView.switchView(view);
    }


    public void showError(String error) {

        mainView.showError(error);
    }


    public String showInput(String text){

        return mainView.showInputPopUp(text);
    }


    public int showConfirm(String text, String[] questions) {

        return mainView.showConfirmPopUp(text, questions);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case StartGUI.START_LOGIN:
                mainView.switchView(MainView.LOGIN_VIEW);
                break;
            case StartGUI.START_SIGNUP:
                mainView.switchView(MainView.SIGNUP_VIEW);
                break;
        }
    }

}
