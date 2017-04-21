package domain;

import technical.DBFacade;
import ui.MainFrame;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import javax.swing.*;

/**
 * Created by Louise Windows on 18-04-2017.
 */
public class LoginController {
    DBFacade controller = new DBFacade();

    public boolean validateLogin(String userName, String password) {
        if (controller.findUserByUserName(userName) != null){
            if(createSHA(userName,password).equals(controller.findUserByUserName(userName).getPassword())){
                return true;
            }
        }
        return false;
    }

    public String createSHA(String userName, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String x = password + userName + "LouiseRocks";
            byte[] hash = md.digest(x.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("", e);
        }
    }
}
