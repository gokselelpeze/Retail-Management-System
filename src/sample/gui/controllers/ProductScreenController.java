package sample.gui.controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.company.Database;
import sample.company.Manufacturer;
import sample.products.BasketItem;
import sample.products.Item;
import sample.products.OtherProducts;
import sample.products.clothing.Footwear;
import sample.products.clothing.Pants;
import sample.products.clothing.Tshirt;
import sample.products.electronics.Computer;
import sample.products.electronics.Phone;
import sample.products.electronics.Television;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductScreenController implements Initializable {

    ArrayList<Item> itemList = new ArrayList<>();
    ObservableList<String> category = FXCollections.observableArrayList("Clothing", "Electronics", "Other");
    ObservableList<String> clothing = FXCollections.observableArrayList("Footwear", "Pants", "T-shirt");
    ObservableList<String> electronics = FXCollections.observableArrayList("Computer", "Phone", "Television");
    ObservableList<String> others = FXCollections.observableArrayList("Misc");
    @FXML
    private ComboBox<String> comboCategory;
    @FXML
    private ComboBox<String> comboProduct;
    @FXML
    private AnchorPane pnlProduct;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private TextField txt3;
    @FXML
    private TextField txt4;
    @FXML
    private TextField txt5;
    @FXML
    private TextField txt6;
    @FXML
    private TextField txt7;
    @FXML
    private TextField txt8;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnRemove;
    @FXML
    private Label txtAmount;
    @FXML
    private TextField txtSearchItem;
    @FXML
    private ListView<String> listViewItems;
    private String selectedProduct;
    @FXML
    private TableView<BasketItem> tableBasket;
    private TableColumn idCol = new TableColumn("ID");
    private TableColumn nameCol = new TableColumn("NAME");
    private TableColumn quantityCol = new TableColumn("QUANTITY");
    private TableColumn priceCol = new TableColumn("PRICE");
    private TableColumn totalCol = new TableColumn("TOTAL");
    private Database database;
    private ObservableList<BasketItem> basket = FXCollections.observableArrayList();

    public static ArrayList<String> getItemNames(ArrayList<Item> items) {
        ArrayList<String> itemNames = new ArrayList<>();
        for (Item item : items) {
            itemNames.add(item.getName());
        }
        return itemNames;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = Main.getDatabase();

        setListViewItems();
        setTableBasket();
        setAmount();
        setItemActions();

        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        txt8.setText("");
        comboCategory.setItems(category);
    }

    public void setTableBasket() {
        idCol.setCellValueFactory(
            new PropertyValueFactory<BasketItem,Integer>("itemID"));

        nameCol.setCellValueFactory(
            new PropertyValueFactory<BasketItem,String>("name"));

        quantityCol.setCellValueFactory(
            new PropertyValueFactory<BasketItem,Integer>("quantity"));
        priceCol.setCellValueFactory(
            new PropertyValueFactory<BasketItem,Double>("price"));

        totalCol.setCellValueFactory(
            new PropertyValueFactory<BasketItem,Double>("total"));

        tableBasket.setItems(basket);
        tableBasket.getColumns().addAll(idCol, nameCol, quantityCol, priceCol, totalCol);
        tableBasket.setPlaceholder(new Label(""));
    }

    public void setListViewItems() {
        for (Item item : database.getCYGCOMPANY().getProducts()) {
            System.out.println(item.getName());
            itemList.add(item);
        }
    }

    public void setAmount() {
        double amount = 0;
        for (BasketItem item : basket) {
            amount += item.getTotal();
        }
        double val = amount * 100;
        val = Math.round(val);
        val = val / 100;
        txtAmount.setText(String.valueOf(val));
    }

    @FXML
    void order() {
        // TODO
    }

    @FXML
    void cancelOrder() {
        basket.clear();
        setAmount();
    }

    @FXML
    void removeItem() {
        if (tableBasket.getSelectionModel().getSelectedItem() == null) {
            new Shake(btnRemove).play();
        } else {
            BasketItem selectedItem = tableBasket.getSelectionModel().getSelectedItem();
            basket.remove(selectedItem);
            System.out.println(selectedItem.getName() + " deleted");
            setAmount();
        }

    }

    public void setItemActions() {

        for (Item item : itemList) {
            listViewItems.getItems().add(item.getName());
        }
        txtSearchItem.setOnKeyReleased(e -> {
            listViewItems.getItems().clear();
            if (!txtSearchItem.getText().isEmpty()) {
                for (int i = 0; i < itemList.size(); i++) {
                    if (itemList.get(i).getName().toLowerCase().contains(txtSearchItem.getText().toLowerCase())) {
                        listViewItems.getItems().add(itemList.get(i).getName());
                    }
                }
            } else {
                listViewItems.getItems().addAll(getItemNames(itemList));
            }
            setAmount();
        });

        listViewItems.setOnMouseClicked(click -> {

            if (click.getClickCount() == 2) {
                Item item = getItemWithName(listViewItems.getSelectionModel().getSelectedItem());
                if (containsName(basket, item.getItemID())) {
                    BasketItem temp = getItem(item.getItemID());

                    System.out.println(temp.getQuantity());
                    temp.setQuantity();
                    System.out.println(temp.getQuantity());

                    tableBasket.getColumns().get(0).setVisible(false);
                    tableBasket.getColumns().get(0).setVisible(true);
                } else {
                    basket.add(new BasketItem(item.getItemID(), item.getName(), 1, item.getPrice(), item.getPrice()));
                }
            }
            setAmount();
        });
        txtSearchItem.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if (!listViewItems.getItems().isEmpty()) {
                    listViewItems.getSelectionModel().select(0);
                    Item item = getItemWithName(listViewItems.getSelectionModel().getSelectedItem());
                    if (containsName(basket, item.getItemID())) {
                        BasketItem temp = getItem(item.getItemID());

                        System.out.println(temp.getQuantity());
                        temp.setQuantity();
                        System.out.println(temp.getQuantity());

                        tableBasket.getColumns().get(0).setVisible(false);
                        tableBasket.getColumns().get(0).setVisible(true);
                    } else {
                        basket.add(new BasketItem(item.getItemID(), item.getName(), 1, item.getPrice(), item.getPrice()));
                    }
                } else
                    new Shake(txtSearchItem).play();
            }
            setAmount();
        });

    }

    public Item getItemWithName(String name) {
        for (Item variable : itemList) {
            if (variable.getName() == name) {
                return variable;
            }
        }
        return null;
    }

    public boolean containsName(final ObservableList<BasketItem> list, final int itemID) {
        return list.stream().anyMatch(o -> o.getItemID() == itemID);
    }

    public BasketItem getItem(int id) {
        for (BasketItem variable : basket) {
            if (variable.getItemID() == id) {
                return variable;
            }
        }
        return null;
    }

    @FXML
    void categoryChoice() {
        switch (comboCategory.getSelectionModel().getSelectedItem()) {
            case "Clothing":
                pnlProduct.setVisible(true);
                new FadeIn(pnlProduct).play();
                unVisible();
                lbl5.setText("Color:");
                lbl6.setText("Gender:");
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                txt5.setVisible(true);
                txt6.setVisible(true);
                comboProduct.setItems(clothing);
                comboProduct.setVisible(true);
                break;
            case "Electronics":
                pnlProduct.setVisible(true);
                new FadeIn(pnlProduct).play();
                unVisible();
                lbl5.setText("Size:");
                lbl6.setText("Resolution:");
                lbl5.setVisible(true);
                lbl6.setVisible(true);
                txt5.setVisible(true);
                txt6.setVisible(true);
                comboProduct.setItems(electronics);
                comboProduct.setVisible(true);
                break;
            case "Other":
                pnlProduct.setVisible(true);
                new FadeIn(pnlProduct).play();
                unVisible();
                comboProduct.setItems(others);
                comboProduct.setVisible(true);
                break;
        }
    }

    @FXML
    void productChoice() {
        if (comboProduct.getValue() != null) {
            switch (comboProduct.getSelectionModel().getSelectedItem()) {
                case "Footwear":
                    selectedProduct = "Footwear";
                    lbl7.setText("Size:");
                    lbl7.setVisible(true);
                    txt7.setVisible(true);
                    lbl8.setVisible(false);
                    txt8.setVisible(false);
                    new FadeIn(lbl7).play();
                    new FadeIn(txt7).play();
                    break;
                case "Pants":
                    selectedProduct = "Pants";
                    lbl7.setText("Size Width:");
                    lbl8.setText("Size Length:");
                    lbl7.setVisible(true);
                    lbl8.setVisible(true);
                    txt7.setVisible(true);
                    txt8.setVisible(true);
                    new FadeIn(lbl7).play();
                    new FadeIn(txt7).play();
                    new FadeIn(lbl8).play();
                    new FadeIn(txt8).play();
                    break;
                case "T-shirt":
                    selectedProduct = "T-shirt";
                    lbl7.setText("Size:");
                    lbl7.setVisible(true);
                    txt7.setVisible(true);
                    lbl8.setVisible(false);
                    txt8.setVisible(false);
                    new FadeIn(lbl7).play();
                    new FadeIn(txt7).play();
                    System.out.println();
                    break;
                case "Computer":
                    selectedProduct = "Computer";
                    lbl7.setText("Ram:");
                    lbl8.setText("Storage:");
                    lbl7.setVisible(true);
                    lbl8.setVisible(true);
                    txt7.setVisible(true);
                    txt8.setVisible(true);
                    new FadeIn(lbl7).play();
                    new FadeIn(txt7).play();
                    new FadeIn(lbl8).play();
                    new FadeIn(txt8).play();
                    break;
                case "Phone":
                    selectedProduct = "Phone";
                    lbl7.setText("Memory:");
                    lbl8.setText("Ram:");
                    lbl7.setVisible(true);
                    lbl8.setVisible(true);
                    txt7.setVisible(true);
                    txt8.setVisible(true);
                    new FadeIn(lbl7).play();
                    new FadeIn(txt7).play();
                    new FadeIn(lbl8).play();
                    new FadeIn(txt8).play();
                    break;
                case "Television":
                    selectedProduct = "Television";
                    lbl7.setText("Smart:");
                    lbl7.setVisible(true);
                    txt7.setVisible(true);
                    lbl8.setVisible(false);
                    txt8.setVisible(false);
                    new FadeIn(lbl7).play();
                    new FadeIn(txt7).play();
                    break;
                case "Misc":
                    selectedProduct = "Misc";
                    unVisible();
                    lbl5.setText("Category Name:");
                    lbl5.setVisible(true);
                    txt5.setVisible(true);
                    new FadeIn(lbl5).play();
                    new FadeIn(txt5).play();
                    break;
            }
        }
    }

    @FXML
    void addProduct(ActionEvent event) {
        if (!txt1.getText().equals("") && !txt2.getText().equals("") && !txt3.getText().equals("") && !txt4.getText().equals("") &&
            !txt5.getText().equals("") && !txt6.getText().equals("") && !txt7.getText().equals("") && !txt8.getText().equals("")) {
            String[] data = new String[10];
            data[1] = txt1.getText();
            data[2] = txt2.getText();
            data[3] = txt3.getText();
            data[4] = txt4.getText();
            data[5] = txt5.getText();
            data[6] = txt6.getText();
            data[7] = txt7.getText();
            data[8] = txt8.getText();

            Manufacturer manufacturer = Database.getCYGCOMPANY().getManufacturerArraylist().get(Integer.parseInt(data[4]));
            switch (comboProduct.getSelectionModel().getSelectedItem()) {
                case "Footwear":
                    Footwear footwear = new Footwear(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5], data[6], Double.parseDouble(data[7]));
                    manufacturer.addNewItem(footwear, Integer.MAX_VALUE);
                    System.out.println(footwear.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());


                    break;
                case "Pants":
                    Pants pants = new Pants(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5], data[6], Integer.parseInt(data[7]), Integer.parseInt(data[8]));
                    manufacturer.addNewItem(pants, Integer.MAX_VALUE);
                    System.out.println(pants.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());


                    break;
                case "T-shirt":
                    Tshirt tshirt = new Tshirt(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5], data[6], data[7]);
                    manufacturer.addNewItem(tshirt, Integer.MAX_VALUE);
                    System.out.println(tshirt.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());

                    break;
                case "Computer":
                    Computer computer = new Computer(data[1], data[2], Double.parseDouble(data[3]), manufacturer, Double.parseDouble(data[5]), data[6], data[7], data[8]);
                    manufacturer.addNewItem(computer, Integer.MAX_VALUE);
                    System.out.println(computer.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());

                    break;
                case "Phone":
                    Phone phone = new Phone(data[1], data[2], Double.parseDouble(data[3]), manufacturer, Double.parseDouble(data[5]), data[6], data[7], data[8]);
                    manufacturer.addNewItem(phone, Integer.MAX_VALUE);
                    System.out.println(phone.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());

                    break;
                case "Television":
                    Television television = new Television(data[1], data[2], Double.parseDouble(data[3]), manufacturer, Double.parseDouble(data[5]), data[6], data[7]);
                    manufacturer.addNewItem(television, Integer.MAX_VALUE);
                    System.out.println(television.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());

                    break;
                case "Misc":
                    OtherProducts misc = new OtherProducts(data[1], data[2], Double.parseDouble(data[3]), manufacturer, data[5]);
                    manufacturer.addNewItem(misc, Integer.MAX_VALUE);
                    System.out.println(misc.getName() + " is added to Manufacturer-" + manufacturer.getManufacturerID());

                    break;
            }
        } else {
            System.out.println("yanlş");
            if (txt1.getText().equals(""))
                new Shake(txt1).play();
            else if (txt2.getText().equals(""))
                new Shake(txt2).play();
            else if (txt3.getText().equals(""))
                new Shake(txt3).play();
            else if (txt4.getText().equals(""))
                new Shake(txt4).play();
            else if (txt5.getText().equals(""))
                new Shake(txt5).play();
            else if (txt6.getText().equals(""))
                new Shake(txt6).play();
            else if (txt7.getText().equals(""))
                new Shake(txt7).play();
            else if (txt8.getText().equals(""))
                new Shake(txt8).play();
        }
    }

    public void unVisible() {
        // LABELS
        lbl5.setVisible(false);
        lbl6.setVisible(false);
        lbl7.setVisible(false);
        lbl8.setVisible(false);

        // TEXTFIELDS
        txt5.setVisible(false);
        txt6.setVisible(false);
        txt7.setVisible(false);
        txt8.setVisible(false);
    }

}
