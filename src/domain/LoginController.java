package domain;

import technical.DBFacade;
import ui.MainFrame;

import javax.swing.*;

/**
 * Created by Louise Windows on 18-04-2017.
 */
public class LoginController {
    DBFacade controller = new DBFacade();

    public boolean validateLogin(String user, String password) {
     if(controller.findUserByUserName(user) != null){
         if(controller.createSHA(password).equals(controller.findUserByUserName(user).getPassword())){
             return true;
         }
     }
        JOptionPane.showMessageDialog(null, "Log in failed: Username and " +
                "password does not match!");
        return false;
    }
}
