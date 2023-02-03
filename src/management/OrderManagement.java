package management;

import java.util.*;

import Interfaces.OrderInterface;
import data.Customer;
import data.Order;
import data.Product;

public class OrderManagement implements OrderInterface {
    private Scanner sc = new Scanner(System.in);
    private String fName = "Order.txt";

    private List<Order> OrderList = getOrderFromFile(fName);
    // private List<Customer> CustomerList =
    // CustomerManagement.getCustomerFromFile("Customer.txt");
    // private List<Product> ProductList =
    // ProductManagement.getProductFromFile("Product.txt");

    private List<Order> getOrderFromFile(String fName) {
        List<Order> oList = new ArrayList<>();
        List<String> tmp = Performance.readLineFromFile(fName);

        for (String x : tmp) {
            StringTokenizer stk = new StringTokenizer(x, ";");
            String orderID = stk.nextToken();
            String customerId = stk.nextToken();
            String customerName = stk.nextToken();
            String productId = stk.nextToken();
            int quantity = Integer.parseInt(stk.nextToken());
            String date = stk.nextToken();
            String status = stk.nextToken();

            oList.add(new Order(orderID, customerId, customerName, productId, quantity, date, status));
        }
        return oList;
    }

    @Override
    public void addOrder() {
        String orderID, customerID, productID, orderDate, status, checkStatus;
        int quantity;
        Order check;

        do {
            orderID = Performance.getID("order", "(DXXX)", "D\\d{3}");
            check = searchOrderByID(orderID);
            if (check != null) {
                System.out.println("The ID has already existed!");
            }
        } while (check != null);
        do {
            System.out.print("Enter order date (dd//mm//yyyy): ");
            orderDate = sc.nextLine();
        } while (!orderDate.matches("^(1[0-2]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])/(?:[0-9]{2})?[0-9]{2}$"));
        do {
            System.out.print("Enter status [T/F]: ");
            checkStatus = sc.nextLine().toUpperCase();
            if (checkStatus.matches("T")) {
                status = "True";
            } else {
                status = "False";
            }
        } while (!checkStatus.matches("^[TF]$"));
        do {
            System.out.print("Enter quantity: ");
            quantity = Integer.parseInt(sc.nextLine());
        } while (quantity < 1);

        customerID = Performance.getID("customer", "(CXXX)", "C\\d{3}");
        Customer checkCustomerID = CustomerManagement.searchCustomerByID(customerID);
        String customerName = checkCustomerID.getCustomerName();
        productID = Performance.getID("Product", "(PXXX)", "P\\d{3}");
        Product checkProductID = ProductManagement.searchProduct(productID);

        if (checkCustomerID != null && checkProductID != null) {
            System.out.println("Add succeeded!");
            OrderList.add(new Order(orderID, customerID, customerName, productID, quantity, orderDate, status));
        } else {
            if (checkCustomerID == null || checkProductID == null) {
                System.out.println("Could not find customer'd ID!");
            }
        }
        saveOrder();
    }

    @Override
    public void deleteOrder() {
        String id;
        Order check;

        id = Performance.getID("Order", "(DXXX)", "D\\d{3}");
        check = searchOrderByID(id);

        if (check == null) {
            System.out.println("Order does not exist!");
        } else {
            System.out.println("Here is the order you want to delete:");
            printHeader();
            check.showOrder();

            String choice;
            do {
                System.out.println("Do you want to delete? [Y/N]");
                choice = sc.nextLine().toUpperCase();
            } while (!choice.matches("^[YN]$"));

            if (choice.matches("Y")) {
                System.out.println("The order is deleted!");
                OrderList.remove(check);
                saveOrder();
            } else {
                System.out.println("The order is not deleted!");
            }
        }

    }

    private void printHeader() {
        System.out.printf("|%-8s|%-13s|%-20s|%-12s|%-10s|%-20s|%-8s|%n", "Order ID", "Customer ID", "Customer Name",
                "Product ID", "Quantity", "Date", "Status");
    }

    public void searchOrder() {
        Order x;

        System.out.print("Enter Order's ID (DXXX): ");
        String enterID = Performance.getID("Order", "(DXXX)", "D\\d{3}");
        x = searchOrderByID(enterID);

        printHeader();
        x.showOrder();
    }

    public Order searchOrderByID(String id) {
        if (OrderList.isEmpty()) {
            return null;
        }
        for (Order x : OrderList) {
            if (x.getOrderID().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public void updateOrder() {
        if (OrderList.isEmpty()) {
            System.out.println("The order is empty, cannot update!");
        } else {
            String id, newStatus;
            int newQuantity;
            Order check;

            id = Performance.getID("Order", "(DXXX)", "D\\d{3}");
            check = searchOrderByID(id);

            if (check == null) {
                System.out.println("Order doesn't exist!");
            } else {
                System.out.println("Here is the order before updated the name");
                printHeader();
                check.showOrder();
                String choice;

                do {
                    System.out.print("Do you want to update [Y = Yes, N = No]: ");
                    choice = sc.nextLine().toUpperCase();
                } while (!choice.matches("^[YN]$"));

                if (choice.matches("Y")) {
                    do {
                        System.out.print("Enter new order status [T/F]: ");
                        newStatus = sc.nextLine().toUpperCase();
                    } while (!newStatus.matches("^[TF]$"));
                    do {
                        System.out.print("Enter new order quantity: ");
                        newQuantity = Integer.parseInt(sc.nextLine());
                    } while (newQuantity < 0);
                    System.out.println("The new order is updated!");
                    check.setStatus(newStatus);
                    check.setOrderQuantity(newQuantity);
                    saveOrder();
                } else {
                    System.out.println("The order is not updated!");
                }
            }
        }

    }

    @Override
    public void saveOrder() {
        List<String> tmp = new ArrayList<>();

        for (Order x : OrderList) {
            tmp.add(x.getOrderID() + ";" + x.getCustomerID() + ";" + x.getCustomerName() + ";" + x.getProductID() + ";"
                    + x.getOrderQuantity() + ";" + x.getOrderDate() + ";" + x.getStatus());
        }

        Performance.writeLineToFile(fName, tmp);
    }

    @Override
    public void printOrders() {
        if (OrderList.isEmpty()) {
            System.out.println("Product list is empty!");
        } else {
            printHeader();
            Collections.sort(OrderList);
            for (Order x : OrderList) {
                x.showOrder();
            }
        }

    }

    @Override
    public void printPendingOrders() {
        if (OrderList.isEmpty()) {
            System.out.println("Product list is empty!");
        } else {
            printHeader();
            // Collections.sort(OrderList);
            for (Order x : OrderList) {
                if (x.getStatus().matches("True")) {
                    x.showOrder();
                }
            }
        }
    }

    public void printOrderByCustomer() {
        if (OrderList.isEmpty()) {
            System.out.println("Product list is empty!");
        } else {
            String id = Performance.getID("customer", "(CXXX)", "C\\d{3}");
            printHeader();
            Collections.sort(OrderList);
            for (Order x : OrderList) {
                if (x.getCustomerID().equalsIgnoreCase(id)) {
                    x.showOrder();
                }
            }
        }
    }

    public void printPendingOrderByCustomer() {
        if (OrderList.isEmpty()) {
            System.out.println("Product list is empty!");
        } else {
            String id = Performance.getID("customer", "(CXXX)", "C\\d{3}");
            printHeader();
            Collections.sort(OrderList);
            for (Order x : OrderList) {
                if (x.getStatus().matches("True") && x.getCustomerID().equalsIgnoreCase(id)) {
                    x.showOrder();
                }
            }
        }
    }

}
