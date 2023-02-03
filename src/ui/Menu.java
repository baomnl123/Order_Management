package ui;

import java.util.Scanner;

import management.CustomerManagement;
import management.OrderManagement;
import management.ProductManagement;

public class Menu {
    CustomerManagement CM = new CustomerManagement();
    OrderManagement OM = new OrderManagement();
    ProductManagement PM = new ProductManagement();

    public Menu() {
    }

    private void loginMenu() {
        System.out.println("-------------------LOGIN------------------");
        System.out.println("| 1. Login as Administrator              |");
        System.out.println("| 2. Login as Customer                   |");
        System.out.println("|                                        |");
        System.out.println("| 0. Exit                                |");
        System.out.println("------------------------------------------");
    }

    private void printAdminMenu() {
        System.out.println("----------------ADMIN MENU----------------");
        System.out.println("| 1. Adding (Customer, Order, Product)   |");
        System.out.println("|                                        |");
        System.out.println("| 2. Deleting (Customer, Order, Product) |");
        System.out.println("|                                        |");
        System.out.println("| 3. Searching (Customer, Order, Product)|");
        System.out.println("|                                        |");
        System.out.println("| 4. Update (Customer, Order, Product)   |");
        System.out.println("|                                        |");
        System.out.println("| 5. Print (Customer, Order, Product)    |");
        System.out.println("|                                        |");
        System.out.println("| 0. Back                                |");
        System.out.println("-----------------------------------------");
        // System.out.println("| 8. Print all orders |");
        // System.out.println("| 9. Print all pending orders |");
        // System.out.println("| 10. Print all customers information |");
    }

    private void addingMenu() {
        System.out.println("----------------ADDING MENU----------------");
        System.out.println("| 1. Add new Customer                     |");
        System.out.println("| 2. Add new Order                        |");
        System.out.println("| 3. Add new Product                      |");
        System.out.println("|                                         |");
        System.out.println("| 0. Back                                 |");
        System.out.println("-------------------------------------------");
    }

    private void deleteMenu() {
        System.out.println("----------------DELETE MENU----------------");
        System.out.println("| 1. Delete Customer                      |");
        System.out.println("| 2. Delete Order                         |");
        System.out.println("| 3. Delete Product                       |");
        System.out.println("|                                         |");
        System.out.println("| 0. Back                                 |");
        System.out.println("-------------------------------------------");
    }

    private void searchingMenu() {
        System.out.println("---------------SEARCHING MENU--------------");
        System.out.println("| 1. Search Customer                      |");
        System.out.println("| 2. Search Order                         |");
        System.out.println("| 3. Search Product                       |");
        System.out.println("|                                         |");
        System.out.println("| 0. Back                                 |");
        System.out.println("-------------------------------------------");
    }

    private void updatingMenu() {
        System.out.println("---------------UPDATING MENU---------------");
        System.out.println("| 1. Update Customer                      |");
        System.out.println("| 2. Update Order                         |");
        System.out.println("| 3. Update Product                       |");
        System.out.println("|                                         |");
        System.out.println("| 0. Back                                 |");
        System.out.println("-------------------------------------------");
    }

    private void updateCustomerMenu() {
        System.out.println("-------------UPDATE CUSTOMER-------------");
        System.out.println("| 1. Update customer information        |");
        System.out.println("| 2. Delete customer                    |");
        System.out.println("|                                       |");
        System.out.println("| 0. Back                               |");
        System.out.println("-----------------------------------------");
    }

    private void updateOrderMenu() {
        System.out.println("---------------UPDATE ORDER--------------");
        System.out.println("| 1. Update order information           |");
        System.out.println("| 2. Delete order                       |");
        System.out.println("|                                       |");
        System.out.println("| 0. Back                               |");
        System.out.println("-----------------------------------------");
    }

    private void updateProductMenu() {
        System.out.println("--------------UPDATE PRODUCT-------------");
        System.out.println("| 1. Update product information         |");
        System.out.println("| 2. Delete product                     |");
        System.out.println("|                                       |");
        System.out.println("| 0. Back                               |");
        System.out.println("-----------------------------------------");
    }

    private void printingMenu() {
        System.out.println("---------------PRINTING MENU---------------");
        System.out.println("| 1. Print all Customer                   |");
        System.out.println("| 2. Print all Order                      |");
        System.out.println("| 3. Print all Pending Order              |");
        System.out.println("| 4. Print all Product                    |");
        System.out.println("|                                         |");
        System.out.println("| 0. Back                                 |");
        System.out.println("-------------------------------------------");
    }

    private void printCustomerMenu() {
        System.out.println("--------------CUSTOMER MENU--------------");
        System.out.println("| 1. Add new customer                   |");
        System.out.println("| 2. Add new order                      |");
        System.out.println("|                                       |");
        System.out.println("| 3. Update Customer                    |");
        System.out.println("|                                       |");
        System.out.println("| 4. Print all products                 |");
        System.out.println("| 5. Print customer orders              |");
        System.out.println("| 6. Print customer pending orders      |");
        System.out.println("|                                       |");
        System.out.println("| 0. Back                               |");
        System.out.println("-----------------------------------------");
    }

    // Press key to Continue
    private void pressKeyContinue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress any key to continue...");
        scanner.nextLine();
    }

    public void printLoginMenu() {
        do {
            loginMenu();
            int choice = getChoice(0, 2);
            System.out.print("\033[H\033[2J");
            loginAction(choice);
        } while (true);
    }

    private int getChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input, please try again!");
            }
        }
        return choice;
    }

    private void loginAction(int choice) {
        switch (choice) {
            case 1:
                adminMenu();
                break;
            case 2:
                customerMenu();
                break;
            case 0:
                System.exit(0);
        }
    }

    private void adminMenu() {
        System.out.print("Enter password (type \"exit\" to back to menu screen): ");
        Scanner sc = new Scanner(System.in);
        String choice;

        do {
            choice = sc.nextLine().toLowerCase();
            if (choice.matches("^exit$")) {
                printLoginMenu();
            }
            if (!choice.matches("12345")) {
                System.out.println("Wrong password, please try again!");
            } else {
                adminMenuAction();
            }
        } while (!choice.matches("12345"));
    }

    private void adminMenuAction() {
        printAdminMenu();
        int adminChoice = getChoice(0, 5);
        System.out.print("\033[H\033[2J");

        switch (adminChoice) {
            case 1:
                addingAction();
                break;
            case 2:
                deleteAction();
                break;
            case 3:
                searchAction();
                break;
            case 4:
                updatingAction();
                break;
            case 5:
                printAction();
                break;
            case 0:
                printLoginMenu();
                break;

        }
    }

    private void addingAction() {
        addingMenu();
        int addChoice = getChoice(0, 3);
        System.out.print("\033[H\033[2J");

        switch (addChoice) {
            case 1:
                CM.addCustomer();
                pressKeyContinue();
                break;
            case 2:
                OM.addOrder();
                pressKeyContinue();
                break;
            case 3:
                PM.addProduct();
                pressKeyContinue();
                break;
            case 0:
                adminMenuAction();
                break;
        }
    }

    private void deleteAction() {
        deleteMenu();
        int deleteChoice = getChoice(0, 3);
        System.out.print("\033[H\033[2J");

        switch (deleteChoice) {
            case 1:
                CM.deleteCustomer();
                pressKeyContinue();
                break;
            case 2:
                OM.deleteOrder();
                pressKeyContinue();
                break;
            case 3:
                PM.deleteProduct();
                pressKeyContinue();
                break;
            case 0:
                adminMenuAction();
                break;
        }
    }

    private void searchAction() {
        searchingMenu();
        int seachChoice = getChoice(0, 3);
        System.out.print("\033[H\033[2J");

        switch (seachChoice) {
            case 1:
                CM.searchCustomer();
                pressKeyContinue();
                break;
            case 2:
                OM.searchOrder();
                pressKeyContinue();
                break;
            case 3:
                PM.searchProduct();
                pressKeyContinue();
                break;
            case 0:
                adminMenuAction();
                break;
        }
    }

    private void updatingAction() {
        updatingMenu();
        int updateChoice = getChoice(0, 3);
        System.out.print("\033[H\033[2J");
        switch (updateChoice) {
            case 1:
                updateCustomer();
                break;
            case 2:
                updateOrder();
                break;
            case 3:
                updateProduct();
                break;
            case 0:
                adminMenuAction();
                break;
        }
    }

    private void updateCustomer() {
        updateCustomerMenu();
        int updateChoice = getChoice(0, 2);
        System.out.print("\033[H\033[2J");

        switch (updateChoice) {
            case 1:
                CM.updateCustomer();
                pressKeyContinue();
                break;
            case 2:
                CM.deleteCustomer();
                pressKeyContinue();
                break;
            case 0:
                updatingAction();
                break;
        }
    }

    private void updateOrder() {
        updateOrderMenu();
        int updateChoice = getChoice(0, 2);
        System.out.print("\033[H\033[2J");

        switch (updateChoice) {
            case 1:
                OM.updateOrder();
                pressKeyContinue();
                break;
            case 2:
                OM.deleteOrder();
                pressKeyContinue();
                break;
            case 0:
                updatingAction();
                break;
        }
    }

    private void updateProduct() {
        updateProductMenu();
        int updateChoice = getChoice(0, 2);
        System.out.print("\033[H\033[2J");

        switch (updateChoice) {
            case 1:
                PM.updateProduct();
                pressKeyContinue();
                break;
            case 2:
                PM.deleteProduct();
                pressKeyContinue();
                break;
            case 0:
                updatingAction();
                break;
        }
    }

    private void printAction() {
        printingMenu();
        int printChoice = getChoice(0, 4);
        System.out.print("\033[H\033[2J");

        switch (printChoice) {
            case 1:
                CM.printCustomerInformation();
                pressKeyContinue();
                break;
            case 2:
                OM.printOrders();
                pressKeyContinue();
                break;
            case 3:
                OM.printPendingOrders();
                pressKeyContinue();
                break;
            case 4:
                PM.printProduct();
                pressKeyContinue();
                break;
            case 0:
                adminMenuAction();
                break;
        }
    }

    private void customerMenu() {
        printCustomerMenu();
        int customerChoice = getChoice(0, 6);
        System.out.print("\033[H\033[2J");

        switch (customerChoice) {
            case 1:
                CM.addCustomer();
                pressKeyContinue();
                break;
            case 2:
                OM.addOrder();
                pressKeyContinue();
                break;
            case 3:
                CM.updateCustomer();
                pressKeyContinue();
                break;
            case 4:
                PM.printProduct();
                pressKeyContinue();
                break;
            case 5:
                OM.printOrderByCustomer();
                pressKeyContinue();
                break;
            case 6:
                OM.printPendingOrderByCustomer();
                pressKeyContinue();
                break;
            case 0:
                printLoginMenu();
                break;
        }
    }
}
