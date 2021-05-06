package sample.Interfaces;

import sample.company.Shop;
import sample.products.Item;

public interface InventoryManager {
    /**
     * Bu fonksiyon itemlerin sayısını gelen değerlere göre tekrardan güncelliyor
     * quantity -5 , 5 şeklinde olabilir arttırmak için pozitif azaltmak için negatif değer
     */
    void updateItem(Item item, int quantity);

    /**
     * Removes an already existing item from a branch in specified quantity
     */
    void addNewItem(Item item, int quantity);

    /**
     * Removes an existing item from all branches and marks it as removed
     */
    void transferItem(Shop destinationShop, Item transferredItem, int quantity);


    /**
     * Searches and returns an item from Inventor
     */
    Item searchItem(int ItemID);

}
