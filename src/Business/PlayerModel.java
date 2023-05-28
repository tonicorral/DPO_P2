package Business;

import java.util.ArrayList;

public class PlayerModel {


    public ArrayList<Integer> saveAttack(String positionBoatTable){
        ArrayList<Integer> positionsUser = new ArrayList<>();
        char letter = positionBoatTable.charAt(4);
        int positionLetter = letter - 'A' + 1;
        int number = Integer.parseInt(positionBoatTable.substring(5));
        positionsUser.add(number);
        positionsUser.add(positionLetter);

        return positionsUser;
    }

    public Game playerAttacks(Game game, ArrayList<Integer> positionsUser){
        int positionX = positionsUser.get(0);
        int positionY = positionsUser.get(1);

        game.getPlayer().getPositionAttackedX().add(positionX);
        game.getPlayer().getPositionAttackedY().add(positionY);

        return game;
    }











}
