package Business;

import Persistance.UserDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserModel {
    private UserDAO userDAO;
    private String userName;

    public static final int MISMATCHING_PASS = 5;
    public static final int USER_EXIST = 4;
    public  static final int INCORRECT_MAIL = 3;

    public static final int INCORRECT_PASS = 6;
    public static final int ERROR_SAVE = 1;
    public static final int EVERYTHING_OK = 0;
    public static final int EMPTY_FIELD = 2;
    public static final int INCORRECT_LOGIN = 7;
    public static final int DUPLICATED_LOGIN = 8;

    public static final int NO_USER = 9;
    public static final int BAD_PASSWORD = 10;


    public UserModel(UserDAO userDAO) {
        this.userDAO = userDAO;

    }


    public int signUp(String user, String password, String passwordConfirmation, String email ) {

        switch(validSignUp(user, password, passwordConfirmation, email)) {
            case EVERYTHING_OK:

                if(userDAO.checkUserName(user)){ //TODO

                    return DUPLICATED_LOGIN;
                } else if(userDAO.checkEmail(email)){
                    return DUPLICATED_LOGIN;
                }

                else {
                    userDAO.addUser(user, password, email);
                    setUser(userName);
                    return EVERYTHING_OK;
                    //return ERROR_SAVE;
                }

            case EMPTY_FIELD:
              return EMPTY_FIELD;

            case INCORRECT_MAIL:
                return INCORRECT_MAIL;


            case INCORRECT_PASS:
                return INCORRECT_PASS;

            case MISMATCHING_PASS:
                return MISMATCHING_PASS;

            default: return ERROR_SAVE;
        }
    }


    public boolean isEmail(String mail) {
        boolean result;

        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        Matcher matcher = pattern.matcher(mail);
        result = matcher.find();

        return result;
    }

    public boolean passwordValidator(String pass) {
        boolean result;

        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcher = pattern.matcher(pass);
        result = matcher.matches();

        return result;
    }

    public int validSignUp(String user, String password, String passwordConfirmation, String email) {

        if (user.equals("") || password.equals("") || email.equals("") || passwordConfirmation.equals("")) {
            return EMPTY_FIELD;
        }
        if (!password.equals(passwordConfirmation)) {
            return MISMATCHING_PASS;
        }
        if (!isEmail(email)) {
            return INCORRECT_MAIL;
        }
        if (!passwordValidator(password)) {
            return INCORRECT_PASS;

        }

        return EVERYTHING_OK;
    }

    public int deleteUser(String email) {

        if ( email.equals("") ) {
            return EMPTY_FIELD;
        }if (!userDAO.checkEmail(email)) {
            return NO_USER;
        }else{
            userDAO.deleteUser(email);
            return EVERYTHING_OK;
        }


    }

    public void setUser(String userName) {
        this.userName = userName;
    }


    public int validLogin(String user, String password) {
        if (user.equals("") || password.equals("")) {
            return EMPTY_FIELD;
        }
        else {
            return EVERYTHING_OK;
        }
    }

    public int login(String user, String password){
        switch(validLogin(user, password)){
            case EVERYTHING_OK:
                if (!userDAO.checkUserName(user) && !userDAO.checkEmail(user)){
                    return NO_USER;
                }
                else{
                    if(!userDAO.validPassword(user,password)){
                        return BAD_PASSWORD;
                    }
                    else{
                        return EVERYTHING_OK;
                    }
                }
            case EMPTY_FIELD:
                return EMPTY_FIELD;

            default: return INCORRECT_LOGIN;
        }
    }





}