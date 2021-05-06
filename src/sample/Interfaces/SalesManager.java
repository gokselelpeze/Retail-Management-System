package sample.Interfaces;

import sample.company.Employee;
import sample.products.BasketItem;
import sample.products.Item;
import sample.utility.Receipt;

import java.util.ArrayList;

public interface SalesManager {
    /**
     * Sells specified item in given quantity
     */

    void sellItem(ArrayList<BasketItem> items, Employee employee);

    /**
     * Generates final receipt of customer and saves it
     */

    void generateReceipt(ArrayList<BasketItem> items, double totalPrice, Employee employee);

    /**
     * Finds and returns specified receipt
     */

    Receipt searchReceipt(int receiptID);

    /**
     * Returns an item and saves a return receipt
     */

    void returnItem(Item item, int receiptID, int quantity, Employee employee);
}
