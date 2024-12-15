package com.example.javafx_with_jdbc.controller;

import com.example.javafx_with_jdbc.model.VehicleModel;
import com.example.javafx_with_jdbc.tm.VehicleTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadAllController implements Initializable {
    @FXML
    private TableView<VehicleTM> table;
    private Stage stage;

    @FXML
    void loadVehicles(ActionEvent event) {
        ArrayList<VehicleTM> vehicleTMS = VehicleModel.loadAllVehicles();

        //configure fx table
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(FXCollections.observableArrayList(vehicleTMS));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<VehicleTM> initializeVehicleTMS = VehicleModel.loadAllVehicles();
        //configure fx table
        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(FXCollections.observableArrayList(initializeVehicleTMS));
    }
}
