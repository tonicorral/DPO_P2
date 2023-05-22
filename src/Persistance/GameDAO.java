package Persistance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface GameDAO {

    public void addGame(String userName, String nameGame, String fileGame, int numAttacks, LocalDate date, Boolean victoria); // Comprobar si la clase LocalDate es la correcta para almacenar la fecha!

    public boolean validGameName(String gameName);
    public String fileGame(String gameName);

    public List<String> savedNameGames (String userName) throws SQLException;

    public Date dateGame(String gameFile);

    public List<Integer> attacksInGame(String userName) throws SQLException; //Para poder calcular la media de ataques por partida te devuelve un array de enteros con los ataques hechos en cada partida

    public void deleteGame(String gameName);

    public void saveGame(String gameFile, String gameName); //En caso de que el usuario quiera volver a dejar una partida que ya tenía guardad de nuevo hay que hacer un UPDATE
}