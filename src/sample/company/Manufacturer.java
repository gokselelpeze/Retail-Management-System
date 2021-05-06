package sample.company;

import sample.Interfaces.InventoryManager;
import sample.products.Item;
import sample.utility.Address;

import java.util.HashMap;

public class Manufacturer implements InventoryManager {
    private static int _manufacturerID = 0;
    private String name;
    private int manufacturerID;
    private Address address;
    private String phoneNumber;
    private HashMap<Integer,Item> itemBag;


    public Manufacturer(String name, Address address, String phoneNumber) {
        this.name = name;
        this.manufacturerID = _manufacturerID++;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.itemBag = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HashMap<Integer,Item> getItemBag() {
        return itemBag;
    }

    public void setItemBag(HashMap<Integer,Item> itemBag) {
        this.itemBag = itemBag;
    }


    @Override
    public void updateItem(Item item, int updateQuantity) {
        System.err.println("Update item function is not implemented for Manufacturer class");
    }

    @Override
    public void addNewItem(Item item, int quantity) {
        this.itemBag.put(item.getItemID(), item);
    }

    @Override
    public void transferItem(Shop destinationShop, Item transferredItem, int quantity) {
        if (destinationShop.getQuantityOfItem(transferredItem.getItemID()) == 0) {
            destinationShop.addNewItem(transferredItem, quantity);
        } else {
            destinationShop.updateItem(transferredItem, quantity);
        }
    }

    @Override
    public Item searchItem(int itemId) {
        return itemBag.get(itemId);
    }
}
