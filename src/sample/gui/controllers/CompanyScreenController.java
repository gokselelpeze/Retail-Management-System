package sample.gui.controllers;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.company.Database;
import sample.company.Employee;
import sample.company.Shop;
import sample.utility.Address;
import sample.utility.Position;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CompanyScreenController implements Initializable {

    ArrayList<Employee> employeeArrayList;
    ArrayList<Shop> shopArrayList;

    @FXML
    private AnchorPane pnlEmployee;

    @FXML
    private AnchorPane pnlShop;

    @FXML
    private TextField txtSearchEmployee;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Button btnEditEmployee;

    @FXML
    private Button btnRemoveEmployee;

    @FXML
    private ListView<String> listViewEmployees;

    @FXML
    private Button btnAddShop;

    @FXML
    private Button btnEditShop;

    @FXML
    private Button btnRemoveShop;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeePosition;

    @FXML
    private TextField txtEmployeeShopID;

    @FXML
    private TextField txtEmployeeCity;

    @FXML
    private TextField txtEmployeeTown;

    @FXML
    private TextField txtEmployeeStreet;

    @FXML
    private TextField txtEmployeePhone;

    @FXML
    private TextField txtEmployeeUsername;

    @FXML
    private TextField txtEmployeePassword;

    @FXML
    private TextField txtEmployeeWage;

    @FXML
    private TextField txtEmployeeBonus;

    @FXML
    private TextField txtEmployeeHour;

    @FXML
    private TextField txtSearchShop;
    @FXML
    private Button btnSaveEmployee;
    @FXML
    private Button btnSaveShop;
    @FXML
    private TextField txtShopName;

    @FXML
    private TextField txtShopCity;

    @FXML
    private TextField txtShopTown;

    @FXML
    private TextField txtShopStreet;

    @FXML
    private TextField txtShopPhone;

    @FXML
    private TextField txtShopEmail;

    @FXML
    private ListView<String> listViewShop;

    @FXML
    private JFXButton btnBackToShop;

    @FXML
    private JFXButton btnBackToEmployee;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeArrayList = Database.getCYGCOMPANY().getEmployeeList();
        shopArrayList = Database.getCYGCOMPANY().getShopList();

        setEmployeeActions();
        setShopActions();


    }

    @FXML
    void addShop(ActionEvent event) {
        txtSearchShop.setVisible(false);
        listViewShop.setVisible(false);
        btnEditShop.setVisible(false);
        btnRemoveShop.setVisible(false);
        btnAddShop.setVisible(false);
        btnBackToShop.setVisible(true);
        clearTxt();
        btnSaveShop.setVisible(true);
        new FadeIn(pnlShop).play();
        new FadeIn(btnSaveShop).play();
    }

    @FXML
    void addEmployee(ActionEvent event) {
        txtSearchEmployee.setVisible(false);
        listViewEmployees.setVisible(false);
        btnEditEmployee.setVisible(false);
        btnRemoveEmployee.setVisible(false);
        btnAddEmployee.setVisible(false);
        btnBackToEmployee.setVisible(true);
        clearTxt();
        btnSaveEmployee.setVisible(true);
        new FadeIn(pnlEmployee).play();
        new FadeIn(btnSaveEmployee).play();
    }

    @FXML
    void editEmployee(ActionEvent event) {

    }

    @FXML
    void editShop(ActionEvent event) {

    }

    @FXML
    void removeShop(ActionEvent event) {

    }

    @FXML
    void removeEmployee(ActionEvent event) {

    }

    @FXML
    void saveEmployee(ActionEvent event) {
        String[] data = new String[12];
        data[0] = txtEmployeeName.getText();
        data[1] = txtEmployeeCity.getText();
        data[2] = txtEmployeeTown.getText();
        data[3] = txtEmployeeStreet.getText();
        data[4] = txtEmployeePhone.getText();
        data[5] = txtEmployeeHour.getText();
        data[6] = txtEmployeePosition.getText();
        data[7] = txtEmployeeWage.getText();
        data[8] = txtEmployeeShopID.getText();
        data[9] = txtEmployeeUsername.getText();
        data[10] = txtEmployeePassword.getText();
        data[11] = txtEmployeeBonus.getText();
        Employee employee = new Employee(data[0], new Address(data[1], data[2], data[3]), data[4],
            Integer.parseInt(data[5]), new Position(data[6], Integer.parseInt(data[7])), Integer.parseInt(data[8]), data[9], data[10], Integer.parseInt(data[11]));
        Database.getCYGCOMPANY().addEmployee(employee);
        saveEmployeeAnimations();
    }

    public void saveEmployeeAnimations() {
        clearTxt();
        txtSearchEmployee.setVisible(true);
        listViewEmployees.setVisible(true);
        btnEditEmployee.setVisible(true);
        btnRemoveEmployee.setVisible(true);
        btnAddEmployee.setVisible(true);
        btnSaveEmployee.setVisible(false);
        btnBackToEmployee.setVisible(false);
        new FadeIn(pnlEmployee).play();
        new FadeIn(txtSearchEmployee).play();
        new FadeIn(listViewEmployees).play();
        new FadeIn(btnEditEmployee).play();
        new FadeIn(btnRemoveEmployee).play();
        new FadeIn(btnAddEmployee).play();
        listViewEmployees.getItems().clear();
        for (Employee employee : employeeArrayList) {
            listViewEmployees.getItems().add(employee.getName());
        }
    }

    @FXML
    void backToShop(ActionEvent event) {

        saveShopAnimations();
    }

    @FXML
    void backToEmployee(ActionEvent event) {

        saveEmployeeAnimations();
    }

    @FXML
    void saveShop(ActionEvent event) {

        String[] data = new String[6];
        data[0] = txtShopName.getText();
        data[1] = txtShopPhone.getText();
        data[2] = txtShopEmail.getText();
        data[3] = txtShopCity.getText();
        data[4] = txtShopTown.getText();
        data[5] = txtShopStreet.getText();
        Shop shop = new Shop(data[0], data[1], data[2], new Address(data[3], data[4], data[5]));
        Database.getCYGCOMPANY().addShop(shop);
        saveShopAnimations();
    }

    public void saveShopAnimations() {
        clearTxt();
        txtSearchShop.setVisible(true);
        listViewShop.setVisible(true);
        btnEditShop.setVisible(true);
        btnRemoveShop.setVisible(true);
        btnAddShop.setVisible(true);
        btnSaveShop.setVisible(false);
        btnBackToShop.setVisible(false);
        new FadeIn(pnlShop).play();
        new FadeIn(txtSearchShop).play();
        new FadeIn(listViewShop).play();
        new FadeIn(btnEditShop).play();
        new FadeIn(btnRemoveShop).play();
        new FadeIn(btnAddShop).play();
        listViewShop.getItems().clear();
        for (Shop shop : shopArrayList) {
            listViewShop.getItems().add(shop.getName());
        }
    }

    public String getEmployeeUsername(String name){
        for (Employee employee : employeeArrayList) {
            if (employee.getName().equals(name))
                return employee.getUsername();
        }
        return name;
    }
    public void setEmployeeActions() {
        listViewEmployees.getItems().clear();
        for (Employee employee : employeeArrayList) {
            listViewEmployees.getItems().add(employee.getName());
        }
        txtSearchEmployee.setOnKeyReleased(e -> {
            listViewEmployees.getItems().clear();
            if (!txtSearchEmployee.getText().isEmpty()) {
                for (int i = 0; i < employeeArrayList.size(); i++) {
                    if (employeeArrayList.get(i).getName().toLowerCase().contains(txtSearchEmployee.getText().toLowerCase())) {
                        listViewEmployees.getItems().add(employeeArrayList.get(i).getName());
                    }
                }
            } else {
                listViewEmployees.getItems().addAll(getEmployeeNames(employeeArrayList));
            }
        });


        listViewEmployees.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                Employee employee = Database.getCYGCOMPANY().getEmployeeHashMap().get(getEmployeeUsername(listViewEmployees.getSelectionModel().getSelectedItem()));
                if (employee != null) {
                    txtEmployeeName.setText(employee.getName());
                    txtEmployeePosition.setText(employee.getPosition().getPositionName());
                    txtEmployeeShopID.setText(String.valueOf(employee.getShopID()));
                    txtEmployeeCity.setText(employee.getAddress().getCity());
                    txtEmployeeTown.setText(employee.getAddress().getTown());
                    txtEmployeeStreet.setText(employee.getAddress().getStreet());
                    txtEmployeePhone.setText(employee.getPhoneNumber());
                    txtEmployeeUsername.setText(employee.getUsername());
                    txtEmployeePassword.setText(employee.getPassword());
                    txtEmployeeWage.setText(String.valueOf(employee.getPosition().getBaseWage()));
                    txtEmployeeBonus.setText(String.valueOf(employee.getWeeklyWageBonus()));
                    txtEmployeeHour.setText(String.valueOf(employee.getWeeklyWorkHours()));
                }
            }
        });


    }

    public void setShopActions() {
        listViewShop.getItems().clear();
        for (Shop shop : shopArrayList) {
            listViewShop.getItems().add(shop.getName());
        }
        txtSearchShop.setOnKeyReleased(e -> {
            listViewShop.getItems().clear();
            if (!txtSearchShop.getText().isEmpty()) {
                for (int i = 0; i < shopArrayList.size(); i++) {
                    if (shopArrayList.get(i).getName().toLowerCase().contains(txtSearchShop.getText().toLowerCase())) {
                        listViewShop.getItems().add(shopArrayList.get(i).getName());
                    }
                }
            } else {
                listViewShop.getItems().addAll(getShopNames(shopArrayList));
            }
        });

        listViewShop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Shop shop = Database.getCYGCOMPANY().getShopHashMap().get(listViewShop.getSelectionModel().getSelectedItem());
                if (shop != null) {
                    txtShopName.setText(shop.getName());
                    txtShopPhone.setText(shop.getPhoneNumber());
                    txtShopCity.setText(shop.getAddress().getCity());
                    txtShopTown.setText(shop.getAddress().getTown());
                    txtShopStreet.setText(shop.getAddress().getStreet());
                    txtShopEmail.setText(shop.getEmailAddress());
                }
            }
        });

    }

    public ArrayList<String> getEmployeeNames(ArrayList<Employee> employees) {
        ArrayList<String> employeeNames = new ArrayList<>();
        for (Employee employee : employees) {
            employeeNames.add(employee.getName());
        }
        return employeeNames;
    }

    public ArrayList<String> getShopNames(ArrayList<Shop> shops) {
        ArrayList<String> shopNames = new ArrayList<>();
        for (Shop shop : shops) {
            shopNames.add(shop.getName());
        }
        return shopNames;
    }

    public void clearTxt() {
        txtEmployeeName.clear();
        txtEmployeePosition.clear();
        txtEmployeeShopID.clear();
        txtEmployeeCity.clear();
        txtEmployeeTown.clear();
        txtEmployeeStreet.clear();
        txtEmployeePhone.clear();
        txtEmployeeUsername.clear();
        txtEmployeePassword.clear();
        txtEmployeeWage.clear();
        txtEmployeeBonus.clear();
        txtEmployeeHour.clear();
        txtShopName.clear();
        txtShopPhone.clear();
        txtShopEmail.clear();
        txtShopCity.clear();
        txtShopTown.clear();
        txtShopStreet.clear();
    }

}


