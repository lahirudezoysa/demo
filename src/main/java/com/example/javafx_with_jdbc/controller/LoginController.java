package com.example.javafx_with_jdbc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController {
    String user = "admin";
    String pass = "pass";
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private Stage stage;

    @FXML
    void save(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);

        if (userName.equals(user) && password.equals(pass)) {
            try {
                System.out.println("Trying to load: " + LoginController.class.getResource("/com/example/javafx_with_jdbc/save-form-view.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/javafx_with_jdbc/save-form-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                SaveFormController controller = fxmlLoader.getController();
                controller.setStage(stage);

                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println("Error loading FXML: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            alert.setContentText("Wrong Username or Password");
            alert.showAndWait();
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
