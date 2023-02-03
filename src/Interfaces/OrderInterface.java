package Interfaces;

import data.Order;

public interface OrderInterface {
    void addOrder();

    void deleteOrder();

    // Order searchOrder(String id);

    void updateOrder();

    void saveOrder();

    void printOrders();

    void printPendingOrders();
}
