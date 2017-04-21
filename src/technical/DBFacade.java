package technical;

import domain.Booking;
import domain.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import domain.LoginController;

/**
 * Created by suman on 4/18/2017.
 */
public class DBFacade {
    public List<Booking> customerList;

    private static String userName = "sa";
    private static String password = "lagkage123";

    private static String port = "1433";
    private static String databaseName = "DB_Hotel";

    Connection connection = null;

    public DBFacade() {
        getConnection();
    }

    @Override
    public void finalize() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO LOG
            }
        }
    }

    public Connection getConnection() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:" + port + ";databaseName=" +
                    databaseName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * closes an opened connection
     */
    public void closeConnect() {

        try {
            connection.close();
            System.out.println("Connection closed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("nooo");
        }

    }

    public List<Booking> allBookings() {
        customerList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        try {
            connection.setAutoCommit(false);
            sql = "EXECUTE AllBookings ?, ?, ?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, "");
            pst.setString(2, "");
            pst.setString(3, "");
            rs = pst.executeQuery();
            while (rs.next()) {
                Booking b = new Booking(
                        rs.getInt("fld_bookingID"),
                        rs.getInt("fld_Roomnumber"),
                        rs.getDouble("fld_Price"),
                        rs.getString("fld_Email"),
                        rs.getString("fld_RoomSize"),
                        rs.getString("fld_FirstName"),
                        rs.getString("fld_LastName"),
                        rs.getString("fld_Description"),
                        rs.getString("fld_StartDate"),
                        rs.getString("fld_EndDate"));
                customerList.add(b);
            }
            //connection.commit();
            // TODO rs.close
        } catch (SQLException e) {
            tryRollback(connection);
            throw new RuntimeException("Exception executing: '" + sql + "'", e);
        } finally {
            tryClose(pst, sql);
            }
            return customerList;
        }

    private void tryRollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not roll back", e);
        }
    }

    public void addBooking(int bookingID, String firstName, String lastName, int roomNumber, String email,
                           String roomSize, double price, String description, String startDate,
                           String endDate) {
        String sql = null;
        PreparedStatement pst = null;
        try {
            sql = "EXEC addBooking ?,?,?,?,?,?,?,?,?,?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, bookingID);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setInt(5, roomNumber);
            pst.setString(4, email);
            pst.setString(6, roomSize);
            pst.setDouble(7, price);
            pst.setString(8, description);
            pst.setDate(9, getSQLDate(startDate));
            pst.setDate(10, getSQLDate(endDate));
            pst.execute();
            connection.commit();
        } catch (SQLException e) {
            tryRollback(connection);
            throw new RuntimeException("Exception executing: '" + sql + "'", e);
        } catch (ParseException e) {
            tryRollback(connection);
            throw new RuntimeException("Exception executing: '" + sql + "'", e);
        } finally {
            tryClose(pst, sql);
        }
    }

    private void tryClose(PreparedStatement pst, String sql) {
        try {
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not close prepared statement: '" + sql + "'", e);
        }
    }

    public User findUserByUserName(String userName) {
        User result = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM tbl_users " +
                    "WHERE fld_email = (?)");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {   // only picking the first row
                int userId = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                result = new User(userId, name, password, role);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void createNewArrangement(int arrangementID, String arrangementName, String description, String fName,
                                     String lName, double price, String phoneNumber, int roomID, String roomName,
                                     java.sql.Date startTime, java.sql.Date endTime) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tbl_Arrangement VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, arrangementID);
            ps.setString(2, arrangementName);
            ps.setString(3, description);
            ps.setString(4, fName);
            ps.setString(5, lName);
            ps.setDouble(6, price);
            ps.setString(7, phoneNumber);
            ps.setInt(8, roomID);
            ps.setString(9, roomName);
            ps.setDate(10, startTime);
            ps.setDate(11, endTime);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void deleteArrangement(int arrangementID) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM tbl_Arrangement WHERE fld_arrangementID = ?");
            ps.setInt(1, arrangementID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void updateArrangement(int arrangementID, String arrangementName, String description, String fName,
                                  String lName, double price, String phoneNumber, int roomID, String roomName,
                                  java.sql.Date startTime, java.sql.Date endTime) {
        // TODO update everything every time
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE tbl_Arrangement WHERE fld_arrangementID = ? SET");
            ps.setInt(1, arrangementID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void createNewCatering(int id, String description, String food, int amount, String fName, String lName,
                                  double price, String phoneNumber, java.sql.Date timeOfArrival) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tbl_Catering VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, description);
            ps.setString(3, food);
            ps.setInt(4, amount);
            ps.setString(5, fName);
            ps.setString(6, lName);
            ps.setDouble(7, price);
            ps.setString(8, phoneNumber);
            ps.setDate(9, timeOfArrival);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void deleteCatering(int id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM tbl_Catering WHERE fld_cateringID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void updateCatering(int id, String description, String food, int amount, String fName, String lName,
                               double price, String phoneNumber, java.sql.Date timeOfArrival) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE tbl_Catering WHERE fld_cateringID = ? SET");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void deleteFromTable(String tableName, String currentID, int ID) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM ? WHERE ? = ?");
            ps.setString(1, tableName);
            ps.setString(2, currentID);
            ps.setInt(3, ID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void createNewUser(int ID, String userName, String password, String role) {
        LoginController controller = new LoginController();
        PreparedStatement ps;
        String hashPassword = controller.createSHA(userName, password);
        try {
            ps = connection.prepareStatement("INSERT INTO tbl_Users VALUES(?, ?, ?, ?)");
            ps.setInt(1, ID);
            ps.setString(2, userName);
            ps.setString(3, hashPassword);
            ps.setString(4, role);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void deleteUser(int ID) {

    }

    public static java.sql.Date getSQLDate(String date) throws java.text.ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date parsed = dateFormat.parse("" + date);
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        return sqlDate;
    }
}

