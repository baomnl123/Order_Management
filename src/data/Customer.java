package data;

public class Customer implements Comparable<Customer> {
    String customerID, customerName, customerAddress, customerPhone;

    public Customer() {
    }

    public Customer(String customerID, String customerName, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    public String getCustomerID() {
        return customerID;
    }

    // public void setCustomerID(String customerID) {
    // this.customerID = customerID;
    // }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void showCustomer() {
        System.out.printf("|%-5s|%-25s|%-20s|%-20s|%n", customerID, customerName, customerAddress, customerPhone);
    }

    @Override
    public int compareTo(Customer that) {
        return this.customerName.compareToIgnoreCase(that.customerName);
    }
}
