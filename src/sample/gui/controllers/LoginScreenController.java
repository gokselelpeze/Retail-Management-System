package sample.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;
import sample.company.Database;
import sample.company.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginScreenController implements Initializable {

    private static Employee currentUser;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private JFXButton btnCloseImg;
    @FXML
    private JFXButton btnRestoreImg;
    @FXML
    private JFXButton btnMinimizeImg;
    private Database database;

    public static Employee getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Employee currentUser) {
        LoginScreenController.currentUser = currentUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = Main.getDatabase();
    }

    public void handleButtonAction(ActionEvent event) {
        Object eventSource = event.getSource();
        if (eventSource == btnCloseImg) {
            Stage s = (Stage) ((Node) eventSource).getScene().getWindow();
            s.close();
        } else if (eventSource == btnRestoreImg) {
            Stage s = (Stage) ((Node) eventSource).getScene().getWindow();
            s.setFullScreen(!s.isFullScreen());
        } else if (eventSource == btnMinimizeImg) {
            Stage s = (Stage) ((Node) eventSource).getScene().getWindow();
            s.setIconified(true);
        }
    }

    public void login(ActionEvent event) {
        Object eventSource = event.getSource();
        if (eventSource == btnSignIn) {
            String username = txtUsername.getText();
            String password = txtPassword.getText();


            if (username.length() > 0 && database.getCYGCOMPANY().getEmployeeHashMap().get(username) != null &&
                database.getCYGCOMPANY().getEmployeeHashMap().get(username).getPassword().equals(password)) {
                setCurrentUser(database.getCYGCOMPANY().getEmployeeHashMap().get(username));
                System.out.println("Giriş başarılı");
                changeScene("../fxmls/homeScreen.fxml");
            } else
                System.out.println("şifre yanlış");
        }
    }

    private void changeScene(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Scene scene = new Scene(root, 1280, 720);
            Main.appStage.setScene(scene);
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
    }
}
