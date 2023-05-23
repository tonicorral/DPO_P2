package Persistance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface GameDAO {

    public void addGame(String userName, String nameGame, String fileGame, int numAttacks, LocalDate date, int victoria); // Comprobar si la clase LocalDate es la correcta para almacenar la fecha!

    public int calcularNumeroPartidas(String user);

    public int calcularNumeroVictorias(String user);

    public boolean validGameName(String gameName);
    public String fileGame(String gameName);

    public List<String> savedNameGames (String userName) throws SQLException;

    public Date dateGame(String gameFile);

    public List<Integer> attacksInGame(String userName) throws SQLException; //Para poder calcular la media de ataques por partida te devuelve un array de enteros con los ataques hechos en cada partida

    public ArrayList<Integer> extraerArrayAtaques(String user);

}