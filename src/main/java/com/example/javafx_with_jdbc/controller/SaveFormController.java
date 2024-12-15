package com.example.javafx_with_jdbc.controller;

import com.example.javafx_with_jdbc.dto.VehicleDto;
import com.example.javafx_with_jdbc.model.VehicleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SaveFormController {


    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;
    private Stage stage;

    @FXML
    void cancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void save(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());
        if (price <= 0 || price > 9999999) { // Adjust the max limit based on your schema
            throw new IllegalArgumentException("Price must be a positive value within a valid range.");
        } else {
            boolean sv = VehicleModel.saveVehicle(new VehicleDto(id, brand, model, qty, price));
        }

        txtId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }


    @FXML
    void delete(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        boolean del = VehicleModel.deleteVehicle(new VehicleDto(id));

        txtId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }

    @FXML
    void update(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafx_with_jdbc/update-form.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            UpdateFormController controller = fxmlLoader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void loadAllView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafx_with_jdbc/load-all-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoadAllController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void service(ActionEvent event) {

    }
    @FXML
    void makeOrder(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafx_with_jdbc/order-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        OrderViewController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
