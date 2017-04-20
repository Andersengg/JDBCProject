package technical;

import domain.Booking;
import domain.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

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
        try {
            String findCustomer = "EXECUTE AllBookings ?, ?, ?";

            PreparedStatement pst = connection.prepareStatement(findCustomer);
            pst.setString(1, "");
            pst.setString(2, "");
            pst.setString(3, "");
            ResultSet rs = pst.executeQuery();
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
            connection.commit();
        } catch (Exception ex) {
            System.out.println("SQL Exception: " + ex.toString());
            try {
                connection.rollback();
            } catch (Exception e) {
                System.out.println("Rollback failed: " + e.toString());
            }

        }
        return customerList;
    }

    public User findUserByUserName(String userName) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * FROM tbl_users " +
                    "WHERE fld_email = (?)");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {   // only picking the first row
                String userId = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                User user = new User(name, password, userId, role);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
        return null;
    }

    public void checkLogin(String userName, String password) {
        if (findUserByUserName(userName) != null) {
            String hashPassword = createSHA(password);
            System.out.println(hashPassword);
        }
    }

    public String createSHA(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("", e);
        }
    }

    public void createNewArrangement(int arrangementID, String arrangementName, String description, String fName,
                                     String lName, double price, String phoneNumber, int roomID, String roomName,
                                     java.sql.Date startTime, java.sql.Date endTime ) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tbl_Arrangement VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,arrangementID);
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
            ps.setInt(1,arrangementID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void updateArrangement(int arrangementID) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE tbl_Arrangement WHERE fld_arrangementID = ? SET");
            ps.setInt(1,arrangementID);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void createNewCatering(int id, String description, String food, int amount, String fName,String lName,
                                  double price, String phoneNumber, java.sql.Date timeOfArrival ) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tbl_Catering VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1,id);
            ps.setString(2, description);
            ps.setString(3, food);
            ps.setString(3, fName);
            ps.setString(4, lName);
            ps.setString(5, phoneNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void deleteCatering(int id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("DELETE FROM tbl_Catering WHERE fld_cateringID = ?");
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }

    public void updateCatering(int id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE tbl_Catering WHERE fld_cateringID = ? SET");
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("", e);
        }
    }









}

