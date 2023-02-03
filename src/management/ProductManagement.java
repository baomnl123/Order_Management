package management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import data.Product;
import Interfaces.ProductInterface;

public class ProductManagement implements ProductInterface {
    private static List<Product> ProductList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
    private String fName = "Product.txt";

    // Get Product list from file
    public ProductManagement() {
        ProductList = getProductFromFile(fName);
    }

    public static List<Product> getProductFromFile(String fName) {
        List<Product> pList = new ArrayList<>();
        List<String> tmp = Performance.readLineFromFile(fName);

        for (String x : tmp) {
            StringTokenizer stk = new StringTokenizer(x, ";");
            String productID = stk.nextToken();
            String productName = stk.nextToken();
            String productUnit = stk.nextToken();
            String productOrigin = stk.nextToken();
            double productPrice = Double.parseDouble(stk.nextToken());

            pList.add(new Product(productID, productName, productUnit, productOrigin, productPrice));
        }
        return pList;
    }

    // ADD PRODUCT
    @Override
    public void addProduct() {
        String productID, productName, productUnit, productOrigin;
        double productPrice;
        Product check;

        do {
            productID = Performance.getID("Product", "(PXXX)", "P\\d{3}");
            check = searchProduct(productID);
            if (check != null) {
                System.out.println("The ID has already existed!");
            }
        } while (check != null);
        productName = Performance.getName("Product");
        do {
            System.out.print("Enter product's unit: ");
            productUnit = sc.nextLine();
        } while (!productUnit.matches("\\d"));
        do {
            System.out.print("Enter product's origin: ");
            productOrigin = sc.nextLine();
        } while (productOrigin == null);
        do {
            System.out.print("Enter product's price: ");
            productPrice = Double.parseDouble(sc.nextLine());
        } while (productPrice <= 0);

        ProductList.add(new Product(productID, productName, productUnit, productOrigin, productPrice));
        System.out.println("Add product succeeded!");
        saveProduct();
    }

    // DELETE PRODUCT
    @Override
    public void deleteProduct() {
        String id;
        Product check;

        id = Performance.getID("Product", "(PXXX)", "P\\d{3}");
        check = searchProduct(id);

        if (check == null) {
            System.out.println("Product does not exist!");
        } else {
            System.out.println("Here is the product you want to delete:");
            printHeader();
            check.showProduct();

            String choice;
            do {
                System.out.println("Do you want to delete? [Y/N]");
                choice = sc.nextLine().toUpperCase();
            } while (!choice.matches("^[YN]$"));

            if (choice.matches("Y")) {
                System.out.println("The product is deleted!");
                ProductList.remove(check);
                saveProduct();
            } else {
                System.out.println("The product is not deleted!");
            }
        }
    }

    private void printHeader() {
        System.out.printf("|%-5s|%-25s|%-20s|%-20s|%-8s|%n", "ID", "Name", "Unit", "Origin", "Price");
    }

    public void searchProduct() {
        Product x;

        System.out.print("Enter product's ID: ");
        String enterID = Performance.getID("customer", "(CPXXX)", "P\\d{3}");
        x = searchProduct(enterID);

        printHeader();
        x.showProduct();
    }

    @Override
    public void updateProduct() {
        String id;
        Product check;

        id = Performance.getID("customer", "(CXXX)", "C\\d{3}");
        check = searchProduct(id);

        if (check == null) {
            System.out.println("Customer does not exist!");
        } else {
            System.out.println("Here is the Customer before updated!");
            printHeader();
            check.showProduct();

            System.out.print("Enter new product's name: ");
            String newName = sc.nextLine();
            System.out.print("Enter new product's unit: ");
            String newUnit = sc.nextLine();
            System.out.print("Enter new customer's origin: ");
            String newOrigin = sc.nextLine();
            System.out.print("Enter new customer's price: ");
            Double newPrice = Double.parseDouble(sc.nextLine());

            if (!newName.matches("")) {
                check.setProductName(newName);
            }
            if (!newUnit.matches("")) {
                check.setUnit(newUnit);
            }
            if (!newOrigin.matches("")) {
                check.setOrigin(newOrigin);
            }
            if (newPrice >= 0) {
                check.setPrice(newPrice);
            }
            System.out.println("Update succeeded!");
            saveProduct();
        }
    }

    // @Override
    public static Product searchProduct(String id) {
        if (ProductList.isEmpty()) {
            return null;
        }
        for (Product x : ProductList) {
            if (x.getProductID().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }

    // SAVE PRODUCT
    @Override
    public void saveProduct() {
        List<String> tmp = new ArrayList<>();

        for (Product x : ProductList) {
            tmp.add(x.getProductID() + ";" + x.getProductName() + ";" + x.getUnit() + ";" + x.getOrigin() + ";"
                    + x.getPrice());
        }
        Performance.writeLineToFile(fName, tmp);
    }

    // PRINT PRODUCT
    @Override
    public void printProduct() {
        if (ProductList.isEmpty()) {
            System.out.println("Product list is empty!");
        } else {
            printHeader();
            Collections.sort(ProductList);
            for (Product x : ProductList) {
                x.showProduct();
            }
        }
    }

}
