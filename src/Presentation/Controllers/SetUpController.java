package Presentation.Controllers;

import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.SetupStageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLOutput;

public class SetUpController implements ActionListener, MouseListener {

    private SetupStageGUI setUpGUI;

    private boolean mouseClicked = false;

    private int positionBoat;

    private String positionBoatTable;

    private boolean rotation = false;

    // MainController mainController;
    private MainView mainView;
    private MainController mainController;

    public SetUpController(SetupStageGUI setUpGUI, MainView mainView,MainController mainController) {
        this.setUpGUI = setUpGUI;
        this.mainView = mainView;
        setUpGUI.setUpButtonController(this,this);
        //this.mainController = mainController;
        //mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand().equals(SetupStageGUI.ROTATE));

        if (e.getActionCommand().startsWith("cell") && mouseClicked) {
            positionBoatTable = e.getActionCommand();
            setUpGUI.showBoatTable(positionBoat,positionBoatTable,rotation);
            rotation = false;
        }
        mouseClicked = false;

        switch (e.getActionCommand()) {
            case SetupStageGUI.BEGIN_BUTTON -> mainView.switchView(MainView.GAME_STAGE_VIEW);
            case SetupStageGUI.LOGOUT_BTN -> mainView.switchView(MainView.LOGOUT_VIEW);
            case SetupStageGUI.DELETE_BTN -> mainView.switchView(MainView.DELETE_VIEW);
            case SetupStageGUI.ROTATE -> rotation = true;
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
        positionBoat = e.getComponent().getY();
        System.out.println("mouse click");
        System.out.println(e.getComponent());

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}








