package management;

import java.util.*;

import data.Customer;
import Interfaces.CustomerInterface;

public class CustomerManagement implements CustomerInterface {
    private Scanner sc = new Scanner(System.in);
    private static String fName = "Customer.txt";
    private static List<Customer> CustomerList = getCustomerFromFile(fName);

    public static List<Customer> getCustomerFromFile(String fName) {
        List<Customer> cList = new ArrayList<>();
        List<String> tmp = Performance.readLineFromFile(fName);

        for (String x : tmp) {
            StringTokenizer stk = new StringTokenizer(x, ";");
            String customerID = stk.nextToken();
            String customerName = stk.nextToken();
            String customerAddress = stk.nextToken();
            String customerPhone = stk.nextToken();

            cList.add(new Customer(customerID, customerName, customerAddress, customerPhone));
        }

        return cList;
    }

    @Override
    public void addCustomer() {
        String customerID, customerName, customerAddress, customerPhone;
        Customer check;

        do {
            customerID = Performance.getID("customer", "(CXXX)", "C\\d{3}");
            check = searchCustomerByID(customerID);
            if (check != null) {
                System.out.println("The ID has already existed!");
            }
        } while (check != null);
        customerName = Performance.getName("Customer");
        do {
            System.out.print("Enter customer's address: ");
            customerAddress = sc.nextLine();
        } while (customerAddress == null);
        do {
            System.out.print("Enter customer's phonenumber: ");
            customerPhone = sc.nextLine();
        } while (!customerPhone.matches("\\d{9,12}"));

        System.out.println("Add succeeded!");

        CustomerList.add(new Customer(customerID, customerName, customerAddress, customerPhone));
        SaveCustomer();
    }

    @Override
    public void deleteCustomer() {
        String id, choice;
        Customer check;

        id = Performance.getID("customer", "(CXXX)", "C\\d{3}");
        check = searchCustomerByID(id);

        if (check == null) {
            System.out.println("The customer does not exist!");
        } else {
            System.out.println("Here is the customer before deleted");
            printHeader();
            check.showCustomer();

            do {
                System.out.print("Do you want to delete customer? [Y/N]: ");
                choice = sc.nextLine();
            } while (!choice.matches("^[YN]$"));
            if (choice.matches("Y")) {
                CustomerList.remove(check);
                System.out.println("The customer is deleted successfully!");
                SaveCustomer();
            } else {
                System.out.println("The customer is not deleted!");
            }
        }
    }

    public void searchCustomer() {
        Customer check;

        System.out.print("Enter customer's ID: ");
        String enterID = Performance.getID("customer", "(CXXX)", "C\\d{3}");
        check = searchCustomerByID(enterID);

        printHeader();
        check.showCustomer();
    }

    public static Customer searchCustomerByID(String id) {
        if (CustomerList.isEmpty()) {
            return null;
        }

        for (Customer x : CustomerList) {
            if (x.getCustomerID().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    @Override
    public void updateCustomer() {
        String id;
        Customer check;

        id = Performance.getID("customer", "(CXXX)", "C\\d{3}");
        check = searchCustomerByID(id);

        if (check == null) {
            System.out.println("Customer does not exist!");
        } else {
            System.out.println("Here is the Customer before updated!");
            printHeader();
            check.showCustomer();

            System.out.print("Enter new customer's name: ");
            String newName = sc.nextLine();
            System.out.print("Enter new customer's address: ");
            String newAddress = sc.nextLine();
            System.out.print("Enter new customer's phone number: ");
            String newPhone = sc.nextLine();

            if (!newName.matches("")) {
                check.setCustomerName(newName);
            }
            if (!newAddress.matches("")) {
                check.setCustomerAddress(newAddress);
            }
            if (!newPhone.matches("")) {
                check.setCustomerPhone(newPhone);
            }
            System.out.println("Update succeeded!");
            SaveCustomer();
        }
    }

    @Override
    public void SaveCustomer() {
        List<String> tmp = new ArrayList<>();

        for (Customer x : CustomerList) {
            tmp.add(x.getCustomerID() + ";" + x.getCustomerName() + ";" + x.getCustomerAddress() + ";"
                    + x.getCustomerPhone());
        }

        Performance.writeLineToFile(fName, tmp);
    }

    private void printHeader() {
        System.out.printf("|%-5s|%-25s|%-20s|%-20s|%n", "ID", "Name", "Address", "Phone");
    }

    @Override
    public void printCustomerInformation() {
        if (CustomerList.isEmpty()) {
            System.out.println("Customer list is empty!");
        } else {
            printHeader();
            Collections.sort(CustomerList);
            for (Customer x : CustomerList) {
                x.showCustomer();
            }
        }
    }

}
