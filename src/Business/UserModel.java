package Business;

import Persistance.UserDAO;

public class UserModel {
    private UserDAO userDAO;
    private UserOption userOption;
    private String userName;

    public UserModel(UserDAO userDAO, UserOption userOption) {
        this.userDAO = userDAO;
        this.userOption= userOption;
    }

    public int signUp(String user, String password, String passwordConfirmation,String email ) {

        switch(userOption.validSignUp(user, password, passwordConfirmation, email)) {
            case UserOption.EVERYTHING_OK:

                if(!userDAO.checkUserName(user)){ //TODO

                    return UserOption.DUPLICATED_LOGIN;
                }

                if(userDAO.validSignUp(user, password, email)) {

                    setUser(userName);      //que usuario ha iniciado sesion
                    return UserOption.EVERYTHING_OK;
                }
                else {
                    return UserOption.ERROR_SAVE;
                }

            case UserOption.EMPTY_FIELD:
                return UserOption.EMPTY_FIELD;

            case UserOption.INCORRECT_MAIL:
                return UserOption.INCORRECT_MAIL;


            case UserOption.INCORRECT_PASS:
                return UserOption.INCORRECT_PASS;

            case UserOption.MISMATCHING_PASS:
                return UserOption.MISMATCHING_PASS;

            default: return UserOption.ERROR_SAVE;
        }
    }


    public void setUser(String userName) {
        this.userName = userName;
    }

    public boolean deleteUser(String username) {
        return userDAO.deleteUser(username);
    }
    public boolean userExists(String username) {
        return userDAO.checkUserName(username);
    }

}