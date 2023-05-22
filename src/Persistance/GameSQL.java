package Persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameSQL implements GameDAO{

    public void addGame(String userName, String nameGame, String fileGame, int numAttacks, LocalDate date, int victoria) {
        String query = "INSERT INTO guardarpartida (Usuario, NombrePartida, FicheroPartidas, AtackPartida, Fecha, Victoria) VALUES ('"+ userName + "', '"+ nameGame +"', '" + fileGame + "', '" + numAttacks + "', '" + date + "', '" + victoria +"');";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     *
     * @param gameName
     * @return Devuelve verdadero si ya hay un nombre que coincide en la base de datos. De lo contrario, falso.
     */
    public boolean validGameName(String gameName) {
        String query = "SELECT COUNT(*) FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
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

    //Mostrar partidas guardadas
    public List<String> savedNameGames(String userName) throws SQLException {
        String query = "SELECT FicheroPartidas AS fichero FROM guardarpartida WHERE Usuario = '" + userName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        List<String> savedGames = new ArrayList<>();
        while (result.next()) {
            String game = result.getString("fichero");
            savedGames.add(game);
        }
        return savedGames;
    }

    public Date dateGame(String gameName) {
        String query = "SELECT Fecha AS fecha FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        Date date;
        try {
            date = result.getDate("fecha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    public String fileGame(String gameName) {
        String query = "SELECT FicheroPartidas AS fichero FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        String fileGame;
        try {
            fileGame = result.getString("fichero");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fileGame;
    }

    public List<Integer> attacksInGame(String gameName) throws SQLException {
        String query = "SELECT AtackPartida AS Attack FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        List<Integer> numbers = new ArrayList<>();
        while (result.next()) {
            int num = result.getInt("number");
            numbers.add(num);
        }

        return numbers;
    }

    public void deleteGame(String gameName) {
        String query = "DELETE FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

    }

    public void saveGame(String gameFile, String gameName) {
        String query = "UPDATE guardar SET '" + gameName + "' WHERE NombrePartida = '" + gameName + "';";
        SQLConnector.getInstance().selectQuery(query);
    }


}
