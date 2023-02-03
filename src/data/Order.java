package data;

public class Order implements Comparable<Order> {
    String orderID, customerID, customerName, productID, orderDate, status;
    int orderQuantity;

    public Order() {
    }

    public Order(String orderID, String customerID, String customerName, String productID, int orderQuantity,
            String orderDate, String status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.productID = productID;
        this.orderDate = orderDate;
        this.status = status;
        this.orderQuantity = orderQuantity;
    }

    public String getOrderID() {
        return orderID;
    }

    // public void setOrderID(String orderID) {
    // this.orderID = orderID;
    // }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void showOrder() {
        System.out.printf("|%-8s|%-13s|%-20s|%-12s|%-10d|%-20s|%-8s|%n", orderID, customerID, customerName, productID,
                orderQuantity, orderDate, status);
    }

    @Override
    public int compareTo(Order that) {
        return this.customerName.compareTo(that.customerName);
    }

}
