package sample.company;

import sample.products.Item;
import sample.utility.Address;
import sample.utility.Position;

public class StarterPack {

    //Create a company
    Company CYGCOMPANY = new Company(02124137544, "info@cyg.com", new Address("izmir", "buca", "Atatürk Mah"));

    public StarterPack() {
        //Create a shop
        Shop shop1 = new Shop(1111111, "shop1@cyg.com", new Address("izmir", "buca", "Atatürk Mah"));
        CYGCOMPANY.addShop(shop1);


        //Create a employee
        Employee employee1 = new Employee("Oktay Türkdağlı", new Address("izmir", "buca", "Atatürk Mah"), 53415532,
            40, new Position("cashier", 2300), 1, "1234", 100);
        CYGCOMPANY.addEmployee(employee1);


        //Create a Manufacturer
        Manufacturer manufacturer1 = new Manufacturer("Penta Group", new Address("İstanbul", "Yenibosna", "Yeşiloba Mah"), "111");
        Manufacturer manufacturer2 = new Manufacturer("Armada", new Address("Ankara", "Ulus", "Meclis Mah"), "222");
        Manufacturer manufacturer3 = new Manufacturer("Art System", new Address("izmir", "buca", "Atatürk Mah"), "333");


        //Create a item
        Item item1 = new Item("Apple", "Iphone 6", 2800, manufacturer1);
        Item item2 = new Item("Samsung", "S7", 2500, manufacturer2);
        Item item3 = new Item("Xiaomi", "Redmi 8", 3000, manufacturer3);
        Item item4 = new Item("Huawei", "P20 PRO", 2900, manufacturer3);
        Item item5 = new Item("CAT", "S35", 500, manufacturer1);


        //Items Assign to manufacturers and shops
        manufacturer1.addNewItem(item1, 10);
        shop1.addNewItem(item1, 10);
        manufacturer2.addNewItem(item2, 5);
        shop1.addNewItem(item2, 5);
        manufacturer3.addNewItem(item3, 8);
        shop1.addNewItem(item3, 8);
        manufacturer3.addNewItem(item4, 7);
        shop1.addNewItem(item4, 8);
        manufacturer1.addNewItem(item5, 20);
        shop1.addNewItem(item5, 20);


    }
}
