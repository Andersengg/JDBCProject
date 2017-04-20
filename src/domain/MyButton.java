package domain;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louise Windows on 18-04-2017.
 */
public class MyButton extends JButton {

    private String style = "Ariel";
    private int type = Font.PLAIN;
    private int size = 20;

    public MyButton(String text) {
        super(text);
        setFont(new Font(style, type, size));
    }
}
