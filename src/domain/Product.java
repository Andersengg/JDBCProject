package domain;

/**
 * Created by suman on 4/18/2017.
 */
public class Product {
    int productID;
    double price;
    String menus, productName, roomRent, arrangements,roomServices;

    public Product(int productID, double price, String menus, String productName, String roomRent, String arrangements, String roomServices) {
        this.productID = productID;
        this.price = price;
        this.menus = menus;
        this.productName = productName;
        this.roomRent = roomRent;
        this.arrangements = arrangements;
        this.roomServices = roomServices;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(String roomRent) {
        this.roomRent = roomRent;
    }

    public String getArrangements() {
        return arrangements;
    }

    public void setArrangements(String arrangements) {
        this.arrangements = arrangements;
    }

    public String getRoomServices() {
        return roomServices;
    }

    public void setRoomServices(String roomServices) {
        this.roomServices = roomServices;
    }
}
