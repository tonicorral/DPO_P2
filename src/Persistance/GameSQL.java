package Persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameSQL {

    public void addGame(String userName, String fileGame, int numAttacks, LocalDate date) {
        String query = "INSERT INTO guardar partida (Usuario, FicheroPartidas, Atack/Partida, Fecha) VALUES ('" +
                userName + "', '" + fileGame + "', '" + numAttacks + "','" + date + ");";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     *
     * @param gameName
     * @return Devuelve verdadero si ya hay un nombre que coincide en la base de datos. De lo contrario, falso.
     */
    public boolean validGameName(String gameName) {
        String query = "SELECT COUNT(*) FROM guardar partida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> savedNameGames(String userName) throws SQLException {
        String query = "SELECT FicheroPartidas AS fichero FROM guardar partida WHERE Usuario = '" + userName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        List<String> savedGames = new ArrayList<>();
        while (result.next()) {
            String game = result.getString("fichero");
            savedGames.add(game);
        }
        return savedGames;
    }

    public LocalDate dateGame(String gameName) {
        String query = "SELECT Fecha AS fecha FROM guardar partida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            Date date = result.getDate("fecha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Integer> attacksInGame(String gameName) throws SQLException {
        String query = "SELECT Atack/Partida AS Attack FROM guardar partida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        List<Integer> numbers = new ArrayList<>();
        while (result.next()) {
            int num = result.getInt("number");
            numbers.add(num);
        }

        return numbers;
    }

    public void deleteGame(String gameName) {
        String query = "DELETE FROM guardar partida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

    }

    public void saveGame(String gameFile, String gameName) {
        String query = "UPDATE guardar SET '" + gameName + "' WHERE NombrePartida = '" + gameName + "';";
        SQLConnector.getInstance().selectQuery(query);
    }
}
