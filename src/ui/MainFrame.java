package ui;

import domain.AllBookingTable;
import technical.DBFacade;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by suman on 4/18/2017.
 */
public class MainFrame extends JFrame {
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 700;

    protected DBFacade dbFacade;
    private JMenuBar jmb;
    private JMenu start, customer, hotel;
    private JMenuItem logout, reserveRoom, createProducts;
    private JLabel  customerLabel;
    private JTable jtable;
    private JPanel mainPanel, compPanel, tablePanel, menuPanel;

    public MainFrame(DBFacade dbFacade) {
        this.dbFacade = dbFacade;
        this.setTitle("Administration Panel");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initCom();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void initCom() {
        //JMENU + ITEMS
        Border bo = new LineBorder(Color.lightGray);
        jmb = new JMenuBar();
        jmb.setBorder(bo);
        start = new JMenu("Start");
        customer = new JMenu("Booking");
        reserveRoom = new JMenuItem("Make Reservation");
        reserveRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new visitorFrame(dbFac);
                dispose();
            }
        });
        hotel = new JMenu("Products");
        createProducts = new JMenuItem("Create Products");
        createProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new SecretaryFrame(dbFac);
                dispose();
            }
        });
        logout = new JMenuItem("Logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbFacade.closeConnect();
                new LoginFrame();
                dispose();
            }
        });

        jmb.add(start);
        start.add(logout);
        jmb.add(customer);
        customer.add(reserveRoom);
        jmb.add(hotel);
        hotel.add(createProducts);

        customerLabel = new JLabel("Booking List");
        customerLabel.setFont(new Font("Calibri", Font.PLAIN, 24));

        jtable = new JTable();

        jtable.setModel(new AllBookingTable(dbFacade));
        JScrollPane jscp1 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jscp1.setWheelScrollingEnabled(true);
        jscp1.setPreferredSize(new Dimension(1400, 500));
        jscp1.getVerticalScrollBar().setUnitIncrement(16);
        jscp1.getViewport().add(jtable);

        //set size of specific columns
        jtable.getColumnModel().getColumn(1).setMinWidth(125);
        jtable.getColumnModel().getColumn(2).setMinWidth(125);
        jtable.getColumnModel().getColumn(8).setPreferredWidth(220);

        jtable.setAutoCreateRowSorter(true);

        mainPanel = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(5, 10, 5, 10);

        constraints.gridy = 0;
        constraints.gridx = 0;
        tablePanel.add(customerLabel, constraints);
        constraints.gridy = 1;
        tablePanel.add(jscp1, constraints);

        menuPanel = new JPanel(new BorderLayout());

        menuPanel.add(jmb, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        this.setJMenuBar(jmb);
        this.add(mainPanel);

    }
}
