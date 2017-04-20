package ui;

import domain.LoginController;
import technical.DBFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by suman on 4/18/2017.
 */
public class LoginFrame extends JFrame {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;

    private JButton loginButton;
    private JLabel titlelabel, emailLabel, Passwordlabel, forgotPasswordLabel;
    public JTextField emailText;
    private JPasswordField passwordText;
    private JPanel logo, comp, panel;
    protected DBFacade db;
    public LoginController loginController = new LoginController();


    public LoginFrame() {
        db = new DBFacade();
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Login Window");
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        initializeComp();
        this.setVisible(true);
    }

    /**
    /*
     * Initialize all components.
     */
    public void initializeComp() {
        ImageIcon img = new ImageIcon("logo.png");
        titlelabel = new JLabel(img);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            loginValidation(emailText.getText(), passwordText.getText());
            }
        });

        emailLabel = new JLabel("E-Mail");
        Passwordlabel = new JLabel("Password");
        forgotPasswordLabel = new JLabel("Forgot password?");
        forgotPasswordLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //doStuff
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //doStuff
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        emailText = new JTextField(15);
        passwordText = new JPasswordField(15);

        passwordText.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginValidation(emailText.getText(), passwordText.getText());
            }
        });

        logo = new JPanel();
        logo.add(titlelabel);

        comp = new JPanel();
        comp.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(5, 10, 5, 10);

        constraints.gridy = 1;
        constraints.gridx = 0;
        comp.add(emailLabel, constraints);

        constraints.gridx = 1;
        comp.add(emailText, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        comp.add(Passwordlabel, constraints);

        constraints.gridx = 1;
        comp.add(passwordText, constraints);

        constraints.gridy = 3;
        comp.add(forgotPasswordLabel, constraints);

        constraints.gridy = 4;
        constraints.gridx = 1;
        comp.add(loginButton, constraints);

        panel = new JPanel(new BorderLayout());
        panel.add(logo, BorderLayout.NORTH);
        panel.add(comp, BorderLayout.CENTER);
        logo.setBackground(Color.LIGHT_GRAY);
        comp.setBackground(Color.lightGray);

        this.add(panel);
    }

    public void loginValidation(String user, String password) {
        boolean isValid = loginController.validateLogin(user, password);
        DBFacade db = new DBFacade();
        if (db.findUserByUserName(user).getRole().equals("management") && isValid) {
            JOptionPane.showMessageDialog(null, "Logged in as management");
            new MainFrame(db);
        } else if (db.findUserByUserName(user).getRole().equals("staff") && isValid) {
            JOptionPane.showMessageDialog(null, "Logged in as staff");
            new MainFrame(db);
        } else if (!isValid) {
            JOptionPane.showMessageDialog(null, "Log in failed: Username and " +
                    "password does not match!");
        }
    }
}
