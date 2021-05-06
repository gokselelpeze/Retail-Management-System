package sample.company;

import sample.products.BasketItem;
import sample.products.Item;
import sample.products.OtherProducts;
import sample.products.clothing.Footwear;
import sample.products.clothing.Pants;
import sample.products.clothing.Tshirt;
import sample.products.electronics.Computer;
import sample.products.electronics.Phone;
import sample.products.electronics.Television;
import sample.utility.Address;
import sample.utility.Position;
import sample.utility.Receipt;

import java.io.*;
import java.util.ArrayList;

public class Database {

    //Create a company
    private static Company CYGCOMPANY;

    public Database() throws IOException {
        setCYGCOMPANY(new Company(2124137544, "info@cyg.com", new Address("izmir", "buca", "Atatürk Mah")));

        addShops();
        addEmployees();
        addManufacturers();
        addProducts();
        loadProducts();
        loadReceipt();


//        //Create a item
//        Item item1 = new Item("Apple", "Iphone 6", 2800, manufacturer1);
//        Item item2 = new Item("Samsung", "S7", 2500, manufacturer2);
//        Item item3 = new Item("Xiaomi", "Redmi 8", 3000, manufacturer3);
//        Item item4 = new Item("Huawei", "P20 PRO", 2900, manufacturer3);
//        Item item5 = new Item("CAT", "S35", 500, manufacturer1);
//
//
//        //Items Assign to manufacturers and shops
//        manufacturer1.addNewItem(item1, 10);
//        shop1.addNewItem(item1, 10);
//        manufacturer2.addNewItem(item2, 5);
//        shop1.addNewItem(item2, 5);
//        manufacturer3.addNewItem(item3, 8);
//        shop1.addNewItem(item3, 8);
//        manufacturer3.addNewItem(item4, 7);
//        shop1.addNewItem(item4, 8);
//        manufacturer1.addNewItem(item5, 20);
//        shop1.addNewItem(item5, 20);


    }

    public static Company getCYGCOMPANY() {
        return CYGCOMPANY;
    }

    public static void setCYGCOMPANY(Company CYGCOMPANY) {
        Database.CYGCOMPANY = CYGCOMPANY;
    }

    public void addShops() throws IOException {
        File file = new File("src/sample/database/shop.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        System.out.println("-----------------SHOPS--------------------");
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",");
            System.out.println("------------------------------------------");
            System.out.println("Name: " + data[0]);
            System.out.println("Phone number: " + data[1]);
            System.out.println("Email: " + data[2]);
            System.out.println("City: " + data[3]);
            System.out.println("Town: " + data[4]);
            System.out.println("Street: " + data[5]);
            // Creating Shop and adding into Company
            Shop shop = new Shop(data[0], data[1], data[2], new Address(data[3], data[4], data[5]));
            getCYGCOMPANY().addShop(shop);
            System.out.println(data[0] + " shop is added to Company");
        }
        System.out.println("-------------SHOPS ARE ADDED--------------");
        System.out.println("------------------------------------------");

    }

    public void addEmployees() throws IOException {
        File file = new File("src/sample/database/employee.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        System.out.println("----------------EMPLOYEES-----------------");
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",");
            System.out.println("------------------------------------------");
            System.out.println("Name: " + data[0]);
            System.out.println("City: " + data[1]);
            System.out.println("Town: " + data[2]);
            System.out.println("Street: " + data[3]);
            System.out.println("Phone number: " + data[4]);
            System.out.println("Weekly Work Hours: " + data[5]);
            System.out.println("Position Name: " + data[6]);
            System.out.println("Base Wage: " + data[7]);
            System.out.println("Shop ID: " + data[8]);
            System.out.println("Username: " + data[9]);
            System.out.println("Password: " + data[10]);
            System.out.println("Weekly Wage Bonus: " + data[11]);
            Employee employee = new Employee(data[0], new Address(data[1], data[2], data[3]), data[4],
                Integer.parseInt(data[5]), new Position(data[6], Integer.parseInt(data[7])), Integer.parseInt(data[8]), data[9], data[10], Integer.parseInt(data[11]));
            getCYGCOMPANY().addEmployee(employee);
        }
        System.out.println("----------EMPLOYEES ARE ADDED------------");
        System.out.println("------------------------------------------");

    }

    public void addManufacturers() throws IOException {
        File file = new File("src/sample/database/manufacturer.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        System.out.println("-------------MANUFACTURERS----------------");
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",");
            System.out.println("------------------------------------------");
            System.out.println("Name: " + data[0]);
            System.out.println("City: " + data[1]);
            System.out.println("Town: " + data[2]);
            System.out.println("Street: " + data[3]);
            System.out.println("Phone number: " + data[4]);
            Manufacturer manufacturer = new Manufacturer(data[0], new Address(data[1], data[2], data[3]), data[4]);
            getCYGCOMPANY().getManufacturerArraylist().add(manufacturer);
            System.out.println(" Manufacturer " + data[0] + " is added to Database");
        }
        System.out.println("--------MANUFACTURERS ARE ADDED---------");
        System.out.println("------------------------------------------");

    }

    public void addProducts() throws IOException {
        File file = new File("src/sample/database/product.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        System.out.println("---------------PRODUCTS-------------------");
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",");
            System.out.println("------------------------------------------");
            Manufacturer manufacturer = getCYGCOMPANY().getManufacturerArraylist().get(Integer.parseInt(data[4]));
            switch (data[0]) {
                case "Footwear":
                    Footwear footwear = new Footwear(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5], data[6], Double.parseDouble(data[7]));
                    getCYGCOMPANY().addItemToManufacturer(footwear, Integer.parseInt(data[4]));

                    System.out.println(footwear.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());

                    break;
                case "Pants":
                    Pants pants = new Pants(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5], data[6], Integer.parseInt(data[7]), Integer.parseInt(data[8]));
                    getCYGCOMPANY().addItemToManufacturer(pants, Integer.parseInt(data[4]));
                    System.out.println(pants.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());
                    break;
                case "Tshirt":
                    Tshirt tshirt = new Tshirt(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5], data[6], data[7]);
                    getCYGCOMPANY().addItemToManufacturer(tshirt, Integer.parseInt(data[4]));
                    System.out.println(tshirt.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());
                    break;
                case "Computer":
                    Computer computer = new Computer(data[1], data[2], Double.parseDouble(data[3]), manufacturer, Double.parseDouble(data[5]), data[6], data[7], data[8]);
                    getCYGCOMPANY().addItemToManufacturer(computer, Integer.parseInt(data[4]));
                    System.out.println(computer.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());
                    break;
                case "Phone":
                    Phone phone = new Phone(data[1], data[2], Double.parseDouble(data[3]), manufacturer, Double.parseDouble(data[5]), data[6], data[7], data[8]);
                    getCYGCOMPANY().addItemToManufacturer(phone, Integer.parseInt(data[4]));
                    System.out.println(phone.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());
                    break;
                case "Television":
                    Television television = new Television(data[1], data[2], Double.parseDouble(data[3]), manufacturer, Double.parseDouble(data[5]), data[6], data[7]);
                    getCYGCOMPANY().addItemToManufacturer(television, Integer.parseInt(data[4]));
                    System.out.println(television.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());
                    break;
                case "Misc":
                    OtherProducts misc = new OtherProducts(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5]);
                    getCYGCOMPANY().addItemToManufacturer(misc, Integer.parseInt(data[4]));
                    System.out.println(misc.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());
                    break;
            }

        }
        System.out.println("----------PRODUCTS ARE ADDED------------");
        System.out.println("------------------------------------------");

    }

    public void loadProducts() throws IOException {
        File file = new File("src/sample/database/productInShop.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",");
            Item tempItem = getCYGCOMPANY().getManufacturerArraylist().get(Integer.parseInt(data[0])).getItemBag().get(Integer.parseInt(data[2]));
            getCYGCOMPANY().getShopList().get(Integer.parseInt(data[1])).addNewItem(tempItem, Integer.parseInt(data[3]));
            System.out.println(tempItem.getName());
        }
        System.out.println("----------PRODUCTS ARE READED------------");
        System.out.println("------------------------------------------");
    }

    public void saveProducts() throws IOException {
        FileWriter file = new FileWriter("src/sample/database/productInShop.txt");
        String data = "";

        for (Shop shop : CYGCOMPANY.getShopList()) {
            for (Item item : shop.getItemBag().values()) {
                data += item.getManufacturer().getManufacturerID() + "," + shop.getShopID() + "," + item.getItemID() + "," + shop.getItemQuantity().get(item.getItemID()) + "\n";
            }
        }

        file.write(data);
        file.close();
        System.out.println("----------PRODUCTS ARE SAVED------------");
        System.out.println("------------------------------------------");
    }

    public void saveManufacturers() throws IOException {
        FileWriter file = new FileWriter("src/sample/database/manufacturer.txt");
        String data = "";

        for (Manufacturer manufacturer : CYGCOMPANY.getManufacturerArraylist()) {

            data += manufacturer.getName() + "," + manufacturer.getAddress().getCity() + "," + manufacturer.getAddress().getTown()
                + "," + manufacturer.getAddress().getStreet() + "," + manufacturer.getPhoneNumber() + "\n";
        }

        file.write(data);
        file.close();
        System.out.println("----------MANUFACTURERS ARE SAVED------------");
        System.out.println("------------------------------------------");
    }

    public void saveEmployee() throws IOException {
        FileWriter file = new FileWriter("src/sample/database/employee.txt");
        String data = "";

        for (Employee employee : CYGCOMPANY.getEmployeeList()) {
            data += employee.getName() + "," + employee.getAddress().getCity() + "," + employee.getAddress().getTown() + ","
                + employee.getAddress().getStreet() + "," + employee.getPhoneNumber() + "," + employee.getWeeklyWorkHours() + ","
                + employee.getPosition().getPositionName() + "," + employee.getPosition().getBaseWage() + ","
                + employee.getShopID() + "," + employee.getUsername() + "," + employee.getPassword() + ","
                + employee.getWeeklyWageBonus() + "\n";
        }

        file.write(data);
        file.close();
        System.out.println("----------EMPLOYEE ARE SAVED------------");
        System.out.println("------------------------------------------");
    }

    public void saveShop() throws IOException {
        FileWriter file = new FileWriter("src/sample/database/shop.txt");
        String data = "";

        for (Shop shop : CYGCOMPANY.getShopList()) {
            data += shop.getName() + "," + shop.getPhoneNumber() + "," + shop.getEmailAddress() + ","
                + shop.getAddress().getCity() + "," + shop.getAddress().getTown() + "," + shop.getAddress().getStreet()
                + "\n";
        }

        file.write(data);
        file.close();
        System.out.println("----------SHOP ARE SAVED------------");
        System.out.println("------------------------------------------");
    }

    public void saveReceipt() throws IOException {
        FileWriter file = new FileWriter("src/sample/database/receipt.txt");
        String data = "";
        String dataBasketItems = "";


        for (Shop shop : CYGCOMPANY.getShopList()) {
            int n = shop.getReceiptHashMap().size();
            for (int i = 0; i < n; i++) {
                Receipt receipt = shop.getReceiptHashMap().get(i);
                for (int j = 0; j < receipt.getCart().size(); j++) {
                    if (j == receipt.getCart().size() - 1) {
                        dataBasketItems += receipt.getCart().get(j).getItemID() + "-" + receipt.getCart().get(j).getName()
                            + "-" + receipt.getCart().get(j).getQuantity() + "-" + receipt.getCart().get(j).getPrice() + "-"
                            + receipt.getCart().get(j).getTotal();
                    } else {
                        dataBasketItems += receipt.getCart().get(j).getItemID() + "-" + receipt.getCart().get(j).getName()
                            + "-" + receipt.getCart().get(j).getQuantity() + "-" + receipt.getCart().get(j).getPrice() + "-"
                            + receipt.getCart().get(j).getTotal() + ":";
                    }
                }
                data += i + "," + shop.getShopID() + "," + receipt.getDate() + "," + receipt.getTotalPrice() + "," +
                    receipt.getCashier().getEmployeeID() + "," + dataBasketItems + "\n";
                dataBasketItems ="";
            }
        }

        file.write(data);
        file.close();
        System.out.println("----------RECEIPT ARE SAVED------------");
        System.out.println("------------------------------------------");
    }


    public void loadReceipt() throws IOException {
        File file = new File("src/sample/database/receipt.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        ArrayList<BasketItem> basketItems = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            String[] data = st.split(",");
            Shop shop = CYGCOMPANY.getShopList().get(Integer.parseInt(data[1]));
            basketItems.clear();
            for (String tempBasketItem : data[5].split(":")) {
                String[] temp = tempBasketItem.split("-");
                basketItems.add(new BasketItem(Integer.parseInt(temp[0]), temp[1], Integer.parseInt(temp[2]),
                    Double.parseDouble(temp[3]), Double.parseDouble(temp[4])));
            }

            //ArrayList<BasketItem> items, double totalPrice, Employee employee
            shop.sellItem(basketItems, CYGCOMPANY.getEmployeeList().get(Integer.parseInt(data[4])));

        }
        System.out.println("----------RECEIPT ARE READED------------");
        System.out.println("------------------------------------------");
    }
}