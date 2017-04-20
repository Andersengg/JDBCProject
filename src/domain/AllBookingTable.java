package domain;

import technical.DBFacade;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by suman on 4/18/2017.
 */
public class AllBookingTable extends AbstractTableModel {
    private final List<Booking> customerList;
    protected DBFacade dbFac;

    public AllBookingTable(DBFacade dbFac){
        this.dbFac = dbFac;
        customerList = dbFac.allBookings();

    }


    /**
     *
     * @param columnIndex number of the columns
     * @return an empty string.
     */
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:{
                return "BookingID";
            }
            case 1: {
                return "FirstName";
            }
            case 2: {
                return "LastName";
            }
            case 3: {
                return "Room Number";
            }
            case 4: {
                return "Email";
            }
            case 5: {
                return "RoomSize";
            }
            case 6: {
                return "Price";
            }
            case 7: {
                return "Description";
            }
            case 8: {
                return "Visit Date";
            }
            case 9: {
                return "End Date";
            }


        }
        return "";
    }

    /**
     * Gets the rows of the table
     *
     * @return size of the arraylist
     */
    @Override
    public int getRowCount() {
        return customerList.size();
    }

    /**
     * Column count
     *
     * @return the amount of columns
     */
    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Booking c  = customerList.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return c.getBookingID();
            }
            case 1: {
                return c.getEmail();
            }
            case 2: {
                return c.getRoomSize();
            }

            case 3: {
                return c.getRoomNumber();
            }
            case 4: {
                return c.getFirstName();
            }
            case 5: {
                return c.getLastName();
            }
            case 6: {
                return c.getPrice();
            }
            case 7: {
                return c.getDescription();
            }
            case 8: {
                return c.getStartDate();
            }
            case 9: {
                return c.getEndDate();
            }

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }
}
