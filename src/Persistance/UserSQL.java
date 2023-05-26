package Persistance;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * gestion de base de datos
 */
public class UserSQL implements UserDAO {

    /**
     * Añade un usuario
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param mail el mail
     */
    public void addUser(String username, String password, String mail) {
        String query = "INSERT INTO registro (Usuario, Contraseña, Mail, Partidas, Victorias) VALUES ('" +
                username + "', '" + password + "', '" + mail + "', 0, 0);";

        SQLConnector.getInstance().insertQuery(query);
    }


    /**
     * comprueba el nombre del usuario
     * @param name el nombre del usuario
     * @return si es correcto
     */
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

    /**
     * comprueba el nombre del mail
     * @param name el nombre del mail
     * @return si es correcto
     */
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

    /**
     * valida la contraseña
     * @param name el nombre de usuario
     * @param password la contraseña
     * @return si es correcto
     */
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

    /**
     * Borra el usuario
     * @param email variable del mail
     */

    public void deleteUser(String email) {
        String query = "DELETE FROM registro WHERE Mail = '" + email + "';";

        SQLConnector.getInstance().insertQuery(query);
    }




}
