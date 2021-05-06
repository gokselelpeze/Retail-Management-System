package sample.company;

import sample.Interfaces.StaffManager;
import sample.products.Item;
import sample.utility.Address;

import java.util.ArrayList;
import java.util.HashMap;

public class Company implements StaffManager {
    private ArrayList<Item> products;
    private int phoneNumber;
    private String emailAddress;
    private Address address;
    private ArrayList<Shop> shopList;
    private ArrayList<Employee> employeeList;
    private HashMap<String,Employee> employeeHashMap;
    private HashMap<String,Shop> shopHashMap;
    private ArrayList<Manufacturer> manufacturerArraylist;


    public Company(int phoneNumber, String emailAddress, Address address) {
        this.shopList = new ArrayList<>();
        this.products = new ArrayList<>();
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.address = address;
        this.employeeList = new ArrayList<>();
        this.employeeHashMap = new HashMap<>();
        this.shopHashMap = new HashMap<>();
        this.manufacturerArraylist = new ArrayList<>();
    }

    public void addItemToManufacturer(Item item, int manufacturerID) {
        manufacturerArraylist.get(manufacturerID).addNewItem(item, Integer.MAX_VALUE);
        products.add(item);
    }

    public ArrayList<Manufacturer> getManufacturerArraylist() {
        return manufacturerArraylist;
    }

    public void setManufacturerArraylist(ArrayList<Manufacturer> manufacturerArraylist) {
        this.manufacturerArraylist = manufacturerArraylist;
    }

    public HashMap<String,Shop> getShopHashMap() {
        return shopHashMap;
    }

    public void setShopHashMap(HashMap<String,Shop> shopHashMap) {
        this.shopHashMap = shopHashMap;
    }

    public Item getItem(int itemId) {
        return products.get(itemId);
    }

    public ArrayList<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(ArrayList<Shop> shopList) {
        this.shopList = shopList;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HashMap<String,Employee> getEmployeeHashMap() {
        return employeeHashMap;
    }

    public void setEmployeeHashMap(HashMap<String,Employee> employeeHashMap) {
        this.employeeHashMap = employeeHashMap;
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Item> products) {
        this.products = products;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        employeeHashMap.put(employee.getUsername(), employee);
        System.out.println("New employee (" + employee.getName() + ") added..");
    }

    @Override
    public void removeEmployee(Employee employee) {
        int counter = 0;
        boolean wasRemoved = false;
        for (Employee item : employeeList) {
            if (item.getEmployeeID() == employee.getEmployeeID()) {
                employeeList.remove(counter);
                wasRemoved = true;
                break;
            }
            counter++;
        }

        if (wasRemoved) {
            System.out.println("Employee (" + employee.getName() + ") removed..");
        } else {
            System.out.println("Employee (" + employee.getName() + ") not found.. Please check the employee ID...");
        }
    }

    @Override
    public void editEmployee(Employee employee) {
        int counter = 0;
        boolean wasEdited = false;
        for (Employee item : employeeList) {
            if (item.getEmployeeID() == employee.getEmployeeID()) {
                employeeList.set(counter, employee);
                wasEdited = true;
                break;
            }
            counter++;
        }

        if (wasEdited) {
            System.out.println("Employee (" + employee.getName() + ") edited..");
        } else {
            System.out.println("Employee (" + employee.getName() + ") not found.. Please check the employee ID...");
        }
    }

    @Override
    public Employee searchEmployee(int employeeID) {

        Employee foundEmployee = null;
        for (Employee item : employeeList) {
            if (item.getEmployeeID() == employeeID) {
                foundEmployee = item;
                break;
            }
        }

        return foundEmployee;
    }

    public void addShop(Shop shop) {
        shopList.add(shop);
        shopHashMap.put(shop.getName(), shop);
        System.out.println("New employee (" + shop.getShopID() + ") added..");
    }

    public void removeShop(Shop shop) {
        int counter = 0;
        boolean wasRemoved = false;
        for (Shop item : shopList) {
            if (item.getShopID() == shop.getShopID()) {
                shopList.remove(counter);
                wasRemoved = true;
                break;
            }
            counter++;
        }

        if (wasRemoved) {
            System.out.println("Shop (" + shop.getShopID() + ") removed..");
        } else {
            System.out.println("Shop (" + shop.getShopID() + ") not found.. Please check the Shop ID...");
        }
    }

    public void editShop(Shop shop) {
        int counter = 0;
        boolean wasEdited = false;
        for (Shop item : shopList) {
            if (item.getShopID() == shop.getShopID()) {
                shopList.set(counter, shop);
                wasEdited = true;
                break;
            }
            counter++;
        }

        if (wasEdited) {
            System.out.println("Shop (" + shop.getShopID() + ") edited..");
        } else {
            System.out.println("Shop (" + shop.getShopID() + ") not found.. Please check the Shop ID...");
        }
    }

    public Shop searchShop(int shopID) {
        Shop foundShop = null;
        for (Shop item : shopList) {
            if (item.getShopID() == shopID) {
                foundShop = item;
                break;
            }
        }

        return foundShop;
    }
}
