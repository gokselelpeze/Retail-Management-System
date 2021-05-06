package sample.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.util.Queue;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    @FXML
    private JFXButton btnSale;
    @FXML
    private JFXButton btnReports;
    @FXML
    private JFXButton btnCompany;
    @FXML
    private JFXButton btnProduct;
    @FXML
    private JFXButton btnCloseImg;
    @FXML
    private JFXButton btnRestoreImg;
    @FXML
    private JFXButton btnMinimizeImg;
    @FXML
    private AnchorPane pnlSaleScreen;
    @FXML
    private AnchorPane pnlCompanyScreen;
    @FXML
    private AnchorPane pnlReportsScreen;
    @FXML
    private AnchorPane pnlProductScreen;
    @FXML
    private Label labelNotificationCount;
    @FXML
    private MenuButton menuBtnUser;
    @FXML
    private JFXButton btnNotification;
    @FXML
    private Circle circleNotification;

    @FXML
    private StackPane stackPane;
    @FXML
    private AnchorPane rootAnchorPane;
    private ArrayList<String> notifications = new ArrayList<>();

    private int notificationCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoginScreenController.getCurrentUser().getPosition().getPositionName().equals("cashier")) {
            btnReports.setVisible(false);
            btnProduct.setVisible(false);
            btnCompany.setVisible(false);
        }
        notificationCount = 0;
        reloadNotifications();
        menuBtnUser.setText(LoginScreenController.getCurrentUser().getName());
        String notification = "Update available. New version: ";
        addNotification(notification);

    }

    private void addNotification(String notification) {
        notifications.add(notification);
        notificationCount += 1;
        reloadNotifications();
    }

    private void clearNotifications() {
        notifications.clear();
        notificationCount = 0;
        reloadNotifications();
    }

    public void handleButtonAction(ActionEvent event) throws IOException {
        Object eventSource = event.getSource();
        if (eventSource == btnSale) {
            pnlSaleScreen.toFront();
        } else if (eventSource == btnCompany) {
            pnlCompanyScreen.toFront();
        } else if (eventSource == btnReports) {
            pnlReportsScreen.toFront();

        } else if (eventSource == btnProduct) {
            pnlProductScreen.toFront();

        } else if (eventSource == btnCloseImg) {
            Stage s = (Stage) ((Node) eventSource).getScene().getWindow();
            Main.getDatabase().saveProducts();
            Main.getDatabase().saveShop();
            Main.getDatabase().saveEmployee();
            Main.getDatabase().saveManufacturers();
            Main.getDatabase().saveReceipt();

            s.close();
        } else if (eventSource == btnRestoreImg) {
            Stage s = (Stage) ((Node) eventSource).getScene().getWindow();
            s.setFullScreen(!s.isFullScreen());
        } else if (eventSource == btnMinimizeImg) {
            Stage s = (Stage) ((Node) eventSource).getScene().getWindow();
            s.setIconified(true);
        } else if (eventSource == btnNotification && notificationCount > 0) {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < notificationCount; i++) {
                temp.append(i + 1).append(". ").append(notifications.remove(i)).append(System.lineSeparator());
            }
            loadDialog(temp.toString());
            notificationCount = 0;
            reloadNotifications();

        }
    }

    private void reloadNotifications() {
        if (notificationCount == 0) {
            labelNotificationCount.setText("");
            btnNotification.setDisable(true);
            circleNotification.setVisible(false);
        } else {
            labelNotificationCount.setText(String.valueOf(notificationCount));
            btnNotification.setDisable(false);
            circleNotification.setVisible(true);
        }
    }

    // Notification Dialog
    private void loadDialog(String notification) {
        stackPane.toFront();
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OKAY");
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> dialog.close());
        dialogLayout.setHeading(new Label("NOTIFICATIONS"));
        dialogLayout.setBody(new Label(notification));
        dialogLayout.setActions(button);
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
//            removeNotification(notification);
            rootAnchorPane.setEffect(null);
            stackPane.toBack();
        });
        rootAnchorPane.setEffect(blur);
        dialog.show();
    }
}
