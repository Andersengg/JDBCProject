package ui;

import domain.Booking;

import technical.DBFacade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by suman on 4/19/2017.
 */
public class Reservation extends JFrame {
    Booking booking;
    public DBFacade dbFacade = new DBFacade();
    private JLabel firstNamelbl,lastNamelbl, emaillbl,bookingIDlbl,roomsizelbl,pricelbl,roomnumberlbl,
                    descriptionlbl,startdatelbl,enddate;
    private JTextField fnametxtfld,lnametxtfld,emailtxtfld,bookIDtxtfld,rsizetxtfld,pricetxtfld,rnumbertxtfld,
                    descriptiontxtfld,startdatetxtfld,enddatetxtfld;
    private JPanel comppnl,mainpnl;
    private JComboBox combo;

    private JTextArea txtarea;
    private JButton addbtn, deletebtn,updatebtn;
    private JScrollPane scrollPane;

    public Reservation(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.setTitle("Reservation");
        createComponents();
        this.setVisible(true);
    }
    public void createComponents(){
        firstNamelbl= new JLabel("First Name");
        lastNamelbl= new JLabel("Last Name");
        emaillbl= new JLabel("Email");
        bookingIDlbl= new JLabel("BookingID");
        roomnumberlbl= new JLabel("Room Number");
        roomsizelbl= new JLabel("Room Size");
        pricelbl= new JLabel("Price");
        descriptionlbl= new JLabel("Description");
        startdatelbl= new JLabel("Start Date (hint:YYYY-MM-DD");
        enddate= new JLabel("End Date (hint:YYYY-MM-DD");
        combo= new JComboBox();
        combo.addItem("Single");
        combo.addItem("Double");
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsizetxtfld.setText(combo.getSelectedItem().toString());
            }
        });

        fnametxtfld= new JTextField();
        lnametxtfld= new JTextField();
        emailtxtfld= new JTextField();
        bookIDtxtfld= new JTextField();
        rsizetxtfld= new JTextField();
        rnumbertxtfld= new JTextField();
        pricetxtfld= new JTextField();
        descriptiontxtfld= new JTextField();
        startdatetxtfld= new JTextField();
        enddatetxtfld= new JTextField();

        addbtn= new JButton("Add");
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //txtarea.setText("");
                Booking booking= new Booking(Integer.parseInt(bookIDtxtfld.getText().toString()),Integer.parseInt(rnumbertxtfld.getText().toString()),
                        Double.parseDouble(pricetxtfld.getText().toString()),fnametxtfld.getText(),lnametxtfld.getText(),
                        emailtxtfld.getText(),rsizetxtfld.getText(),descriptiontxtfld.getText(),
                        startdatetxtfld.getText(), enddatetxtfld.getText());

                txtarea.append("BookingID:"+ booking.getBookingID()+"------------- "+"FirstName:"+ booking.getFirstName()+"\n"+
                    "LastName:"+ booking.getLastName()+ "---------"+ "Email:"+ booking.getEmail()+"\n"+"Description:"+booking.getDescription()+"--------"+
                         "RoomSize:"+booking.getRoomSize()+"\n"+"RoomNumber:"+ booking.getRoomNumber()+ "----------" +  "StartDate:" + booking.getStartDate()+"\n"+ "EndDate:"+ booking.getEndDate()+"--------"+ "Price:"+ booking.getPrice());

                    dbFacade.addBooking(Integer.parseInt(bookIDtxtfld.getText()), fnametxtfld.getText(), lnametxtfld.getText(), Integer.parseInt(rnumbertxtfld.getText()), emailtxtfld.getText(),
                            rsizetxtfld.getText(), Double.parseDouble(pricetxtfld.getText()),
                            descriptiontxtfld.getText(), startdatetxtfld.getText(),
                            enddatetxtfld.getText());
            }
        });
        deletebtn= new JButton("Delete");
        updatebtn= new JButton("Update");

        comppnl= new JPanel();
        comppnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(5, 10, 5, 10);

        c.gridx=0;
        c.gridy=1;
        comppnl.add(bookingIDlbl,c);

        c.gridx=1;
        c.gridy=1;
        comppnl.add(bookIDtxtfld,c);
        bookIDtxtfld.setPreferredSize(new Dimension(115,25));

        c.gridx=2;
        c.gridy=1;
        comppnl.add(firstNamelbl,c);
        c.gridx=3;
        c.gridy=1;
        comppnl.add(fnametxtfld,c);
        fnametxtfld.setPreferredSize(new Dimension(115,25));

        c.gridx=0;
        c.gridy=2;
        comppnl.add(lastNamelbl,c);
        c.gridx=1;
        c.gridy=2;
        comppnl.add(lnametxtfld,c);
        lnametxtfld.setPreferredSize(new Dimension(105,25));

        c.gridx=2;
        c.gridy=2;
        comppnl.add(emaillbl,c);
        c.gridx=3;
        c.gridy=2;
        comppnl.add(emailtxtfld,c);
        emailtxtfld.setPreferredSize(new Dimension(105,25));

        c.gridx=0;
        c.gridy=3;
        comppnl.add(descriptionlbl,c);
        c.gridx=1;
        c.gridy=3;
        comppnl.add(descriptiontxtfld,c);
        descriptiontxtfld.setPreferredSize(new Dimension(105,25));

        c.gridx=2;
        c.gridy=3;
        comppnl.add(roomsizelbl,c);
        c.gridx=3;
        c.gridy=3;
        comppnl.add(rsizetxtfld,c);
        rsizetxtfld.setPreferredSize(new Dimension(105,25));
        c.gridx=4;
        c.gridy=3;
        comppnl.add(combo,c);


        c.gridx=0;
        c.gridy=4;
        comppnl.add(roomnumberlbl,c);
        c.gridx=1;
        c.gridy=4;
        comppnl.add(rnumbertxtfld,c);
        rnumbertxtfld.setPreferredSize(new Dimension(105,25));

        c.gridx=2;
        c.gridy=4;
        comppnl.add(startdatelbl,c);
        c.gridx=3;
        c.gridy=4;
        comppnl.add(startdatetxtfld,c);
        startdatetxtfld.setPreferredSize(new Dimension(105,25));

        c.gridx=0;
        c.gridy=5;
        comppnl.add(enddate,c);
        c.gridx=1;
        c.gridy=5;
        comppnl.add(enddatetxtfld,c);
        enddatetxtfld.setPreferredSize(new Dimension(105,25));

        c.gridx=2;
        c.gridy=5;
        comppnl.add(pricelbl,c);
        c.gridx=3;
        c.gridy=5;
        comppnl.add(pricetxtfld,c);
        pricetxtfld.setPreferredSize(new Dimension(105,25));


        c.gridx=1;
        c.gridy=7;
        comppnl.add(addbtn,c);
        c.gridx=2;
        c.gridy=7;
        comppnl.add(deletebtn,c);
        c.gridx=3;
        c.gridy=7;
        comppnl.add(updatebtn,c);

        txtarea = new JTextArea();
        scrollPane = new JScrollPane(txtarea);
        scrollPane.setPreferredSize(new Dimension(550, 250));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mainpnl= new JPanel();
        mainpnl.add(comppnl,BorderLayout.NORTH);
        mainpnl.add(scrollPane,BorderLayout.CENTER);
        this.add(mainpnl);
    }
}
