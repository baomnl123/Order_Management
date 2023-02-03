package data;

public class Product implements Comparable<Product> {
    String productID, productName, unit, origin;
    double price;

    public Product() {
    }

    public Product(String productID, String productName, String unit, String origin, double price) {
        this.productID = productID;
        this.productName = productName;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    // public void setProductID(String productID) {
    // this.productID = productID;
    // }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void showProduct() {
        System.out.printf("|%-5s|%-25s|%-20s|%-20s|%-8.2f|%n", productID, productName, unit, origin, price);
    }

    @Override
    public int compareTo(Product that) {
        return this.productName.compareTo(that.productName);
    }

}
