package domain;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louise Windows on 07-04-2017.
 */
public class MyJLabel extends JLabel {
    private String style = "Ariel";
    private int type = Font.PLAIN;
    private int size = 20;

    public MyJLabel(String text) {
        super(text);
        setFont(new Font(style, type, size));
    }
}


