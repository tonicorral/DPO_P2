package Persistance;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL implements UserDAO {

    public void addUser(String username, String password, String mail) {
        String query = "INSERT INTO registro (Usuario, Contraseña, Mail, Partidas, Victorias) VALUES ('" +
                username + "', '" + password + "', '" + mail + "', 0, 0);";


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

    public boolean checkEmail(String name) {
        String query = "SELECT COUNT(*) FROM registro WHERE Mail = '" + name + "';";
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

    public boolean validPassword(String name, String password){
        if (checkUserName(name)){
            String query = "SELECT Contraseña AS password FROM registro WHERE Usuario = '" + name + "';";
            ResultSet result = SQLConnector.getInstance().selectQuery(query);
            try{
                if(result.next()){
                    return password.equals(result.getString("password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
        else{
            String query = "SELECT Contraseña AS password FROM registro WHERE Mail = '" + name + "';";
            ResultSet result = SQLConnector.getInstance().selectQuery(query);
            try{
                if(result.next()){
                    return password.equals(result.getString("password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }


    }

    public void deleteUser(String email) {
        String query = "DELETE FROM registro WHERE Mail = '" + email + "';";

        SQLConnector.getInstance().insertQuery(query);
    }




}
