package Presentation.Controllers;

import Business.*;

import Persistance.SaveGame;
import Presentation.MainView;
import Presentation.Views.GameStageGUI;
import Presentation.Views.SetupStageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameStageController implements ActionListener{
    private GameStageGUI gameStageGUI;

    private SaveGame saveGame;

    private boolean mouseClicked = false;

    private int positionBoat;

    private String positionBoatTable;

    private boolean rotation = false;
    private boolean isClickedPorta = false,isClickedDestructor=false,isClickedSubmari=false,isClickedSubmari2=false,isClickedLlanxa=false;

    private MainView mainView;

    private IAModel iaModel;

    private GameModel gameModel;

    private Game game;
    private  ArrayList<Boat> boats;

    private ArrayList<Player> players;

    public GameStageController(GameStageGUI gameStageGUI, MainView mainView , GameModel gameModel, SaveGame saveGame) {
        this.gameStageGUI = gameStageGUI;
        this.mainView = mainView;
        this.gameModel = gameModel;
        this.saveGame = saveGame;

        mainView.setListeners(this);
        //mainView.setActionMouseListeners(this, this);
    }

    public void actionPerformed(ActionEvent e) {

        //System.out.println(e.getActionCommand().equals(GameStageGUI.ROTATE));

       /* if (e.getActionCommand().startsWith("cell") && mouseClicked) {
            positionBoatTable = e.getActionCommand();
            showTable(positionBoat,positionBoatTable,rotation);
            rotation = false;
        }
        mouseClicked = false;

        */

        switch (e.getActionCommand()) {
            //case SetupStageGUI.BEGIN_BUTTON -> mainView.switchView(MainView.GAME_STAGE_VIEW);
            case GameStageGUI.ABANDONAR:
                mainView.switchView(MainView.MENU_VIEW);
                break;

            case GameStageGUI.GUARDAR:
                saveGame.anadirPartida(game,0, "pepe", 0); //TODO Crear función que registre número de ataques!!!!!!!!!!!!!!!
                break;
        }
    }

    public void initTable() {
        game = gameModel.getGame();
        gameStageGUI.setBoats(game);
        int numPlayers = game.getNumberPlayers();
        for (int i = 0;i<5;i++){
            for(int j=0;j<numPlayers;j++){
                game = gameModel.IAAttacks(game,j);
                System.out.println(game.getJugadorIA().get(j).getPositionAttackedX());
                System.out.println(game.getJugadorIA().get(j).getPositionAttackedY());
            }
            game = gameModel.updateTablero(game);

        }



        //Pintamos
        for(int m = 0;m<numPlayers;m++){
            for(int i = 0;i<15;i++){
                for(int j = 0;j<15;j++){
                    gameStageGUI.paintIA(game,i,j,numPlayers);
                }
            }
        }


    }


   /* public void displayGUI() {
        JFrame frame = new JFrame("Game Stage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameStageGUI.getMainPanel());
        frame.pack();
        frame.setVisible(true);
    }*/




}
