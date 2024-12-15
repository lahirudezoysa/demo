package com.example.javafx_with_jdbc.controller;

import com.example.javafx_with_jdbc.dto.VehicleDto;
import com.example.javafx_with_jdbc.model.VehicleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateFormController {

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
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        if (txtId.getText().length() == 0) {
            System.out.println("No input found");
        } else {
            VehicleDto vehicleDto = new VehicleDto(id);
            boolean isFound = VehicleModel.searchVehicle(vehicleDto);
            if (isFound) {
                txtBrand.setText(vehicleDto.getBrand());
                txtModel.setText(vehicleDto.getModel());
                txtQty.setText(String.valueOf(vehicleDto.getQty()));
                txtPrice.setText(String.valueOf(vehicleDto.getPrice()));
            } else {
                System.out.println("Vehicle not found");
            }
        }
    }

    @FXML
    void update(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        VehicleDto vehicleDto = new VehicleDto(id, brand, model, qty, price);
        boolean up = VehicleModel.updateVehicle(vehicleDto);
        if (up) {
            System.out.println("Vehicle updated");
        } else {
            System.out.println("Vehicle not updated");
        }

        txtId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/javafx_with_jdbc/save-form-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            SaveFormController controller = fxmlLoader.getController();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
