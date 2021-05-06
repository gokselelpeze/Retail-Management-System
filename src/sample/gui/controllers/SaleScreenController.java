package sample.gui.controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import sample.company.Database;
import sample.company.Employee;
import sample.company.Shop;
import sample.products.BasketItem;
import sample.products.Item;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SaleScreenController implements Initializable {


    ArrayList<Item> itemList = new ArrayList<>();
    @FXML
    private AnchorPane pnlSale;
    @FXML
    private TextField txtAdd;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtSearchItem;
    @FXML
    private ListView<String> listViewItems;
    @FXML
    private TextArea txtTest;
    @FXML
    private Button btnRemove;
    @FXML
    private Label txtAmount;
    @FXML
    private AnchorPane pnlReceipt;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnFinishSale;
    @FXML
    private TextArea txtReceipt;
    @FXML
    private TableView<BasketItem> tableBasket;
    private TableColumn idCol = new TableColumn("ID");
    private TableColumn nameCol = new TableColumn("NAME");
    private TableColumn quantityCol = new TableColumn("QUANTITY");
    private TableColumn priceCol = new TableColumn("PRICE");
    private TableColumn totalCol = new TableColumn("TOTAL");
    private Database database;
    private Employee currentUser;
    private Shop currentShop;


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
        currentUser = LoginScreenController.getCurrentUser();
        currentShop = database.getCYGCOMPANY().getShopList().get(currentUser.getShopID());

        setListViewItems();
        setTableBasket();
        setAmount();
        setItemActions();

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
        for (Item item : Database.getCYGCOMPANY().getShopList().get(currentUser.getShopID()).getItemBag().values()) {
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
    void cancelSale() {
        basket.clear();
        setAmount();
    }

    @FXML
    void generateReceipt() {
        ArrayList<BasketItem> basketItems = new ArrayList<>(basket);
        String temp ="";

        for (BasketItem basketItem: basketItems){
            temp += basketItem.toString() + "\n";
        }
        temp += "\n\t\t\tTotal Amount: " + txtAmount.getText();
        txtReceipt.setText(temp);
        new FadeIn(pnlReceipt).play();
        pnlReceipt.toFront();
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

    @FXML
    void backToSalePanel() {
        new FadeIn(pnlSale).play();
        pnlSale.toFront();
    }

    @FXML
    void finishSale() {
        ArrayList<BasketItem> basketItems = new ArrayList<>(basket);
        currentShop.sellItem(basketItems, currentUser);
        cancelSale();

        new FadeIn(pnlSale).play();
        pnlSale.toFront();
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


}
