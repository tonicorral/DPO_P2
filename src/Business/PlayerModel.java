package Business;

import java.util.ArrayList;


/**
 * Logica del jugador en cuanto a los ataques
 */
public class PlayerModel {



    /**
     * Arraylist de enteros para guardar los ataques
     * @param positionBoatTable String con la posicion de los barocs en el tablero
     * @return la posicion del ataque del usuario
     */
    public ArrayList<Integer> saveAttack(String positionBoatTable){
        ArrayList<Integer> positionsUser = new ArrayList<>();
        char letter = positionBoatTable.charAt(4);
        int positionLetter = letter - 'A' + 1;
        int number = Integer.parseInt(positionBoatTable.substring(5));
        positionsUser.add(number);
        positionsUser.add(positionLetter);

        return positionsUser;
    }

    /**
     * registrar en el juego los distintos ataques del usuario
     * @param game la propia partida
     * @param positionsUser Arraylist de las posiciones de ataque
     * @return el juego modificado
     */
    public Game playerAttacks(Game game, ArrayList<Integer> positionsUser){
        int positionX = positionsUser.get(0);
        int positionY = positionsUser.get(1);

        game.getPlayer().getPositionAttackedX().add(positionX);
        game.getPlayer().getPositionAttackedY().add(positionY);

        return game;
    }











}
