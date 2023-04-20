package Persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL implements UserDAO {

    public void addUser(String username, String password, String mail) {
        String query = "INSERT INTO registro(Usuario, Contraseña, Mail) VALUES (" +
                username + ", '" +
                password + "', " +
                mail +
                ");";
        SQLConnector.getInstance().insertQuery(query);
    }

    public boolean checkUserName(String name) {
        String query = "SELECT COUNT(*) FROM registro WHERE Usuario = '" + name + "';";
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









}
