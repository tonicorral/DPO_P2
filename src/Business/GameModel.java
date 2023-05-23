package Business;

import Presentation.Controllers.SetUpController;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GameModel {

    private SetUpController setUpController;

    private IAModel iaModel;

    private Game game;
    private TableroModel tableroModel;

   private int numberPlayers = 0;

    public GameModel(IAModel iaModel,TableroModel tableroModel) {
        this.iaModel = iaModel;
        this.tableroModel =tableroModel;
    }

    public void getNumberPlayers(int numberPlayers1){
        numberPlayers = numberPlayers1;
    }
    public ArrayList<JugadorIA> getIA(){
        ArrayList<JugadorIA> iaPlayers = new ArrayList<>();

        for(int i = 0; i<numberPlayers;i++){
            iaPlayers.add(iaModel.createBoats());
        }



        return iaPlayers;
    }

    public void createGame(Player player){
        game = new Game(player,getIA(),numberPlayers,false);
    }

    public Game getGame(){
        return game;
    }

    public Game IAAttacks(Game game,int i){

        game = iaModel.makeDifferentAttack(game,i);

        return game;
    }

    public Game updateTablero(Game game){

        game = iaModel.updateTablero(game);



        return game;
    }

    public ArrayList<Integer> attackUser(String positionBoatTable){
        ArrayList<Integer> positionsUser = new ArrayList<>();
        char letter = positionBoatTable.charAt(4);
        int positionLetter = letter - 'A' + 1;
        int number = Integer.parseInt(positionBoatTable.substring(5));
        positionsUser.add(number);
        positionsUser.add(positionLetter);

        return positionsUser;
    }

    public Game insertAttack(Game game,ArrayList<Integer> positionsUser){
        int positionX = positionsUser.get(0);
        int positionY = positionsUser.get(1);

        game.getPlayer().getPositionAttackedX().add(positionX);
        game.getPlayer().getPositionAttackedY().add(positionY);


        return game;
    }

}
