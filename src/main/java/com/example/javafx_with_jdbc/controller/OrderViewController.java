package com.example.javafx_with_jdbc.controller;

import com.example.javafx_with_jdbc.dto.OrderDetailsDto;
import com.example.javafx_with_jdbc.dto.OrderDto;
import com.example.javafx_with_jdbc.dto.VehicleDto;
import com.example.javafx_with_jdbc.model.OrderModel;
import com.example.javafx_with_jdbc.model.VehicleModel;
import com.example.javafx_with_jdbc.tm.OrderTM;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderViewController implements Initializable {

    @FXML
    private Label lblSubTotal;

    @FXML
    private TableView<OrderTM> orderTableView;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtOrderQty;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;
    private Stage stage;

    private List<OrderTM> orderTMList;
    private double subTotal = 0;
    private List<OrderDetailsDto> orderDetailsDto;
    public OrderViewController() {
        orderDetailsDto = new ArrayList<>();
    }

    @FXML
    void searchId(ActionEvent event) {
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
                txtUnitPrice.setText(String.valueOf(vehicleDto.getPrice()));
            } else {
                txtId.setText("No such vehicle.");
                txtBrand.setText("");
                txtModel.setText("");
                txtQty.setText("");
                txtUnitPrice.setText("");

            }
        }
    }

    @FXML
    void addToCart(ActionEvent event) {
        int orderQty = Integer.parseInt(txtOrderQty.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double totalUnitPrice = unitPrice * orderQty;

        orderTMList.add(new OrderTM(brand, model, orderQty, unitPrice, totalUnitPrice));
        // bellow initialization is implemented below
        orderTableView.setItems(FXCollections.observableArrayList(orderTMList));
        subTotal+=totalUnitPrice;
        lblSubTotal.setText("Rs." + subTotal+"/=");

        int id = Integer.parseInt(txtId.getText());
        orderDetailsDto.add(new OrderDetailsDto(id,orderQty,totalUnitPrice));



    }

    @FXML
    void placeOrder(ActionEvent event) throws SQLException, ClassNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        boolean isDataPassed=OrderModel.placeOrder(new OrderDto(dateString,subTotal, (ArrayList<OrderDetailsDto>) orderDetailsDto));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brand"));
        orderTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        orderTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        orderTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        orderTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        orderTMList=new ArrayList<>();

        // the above line will be useful in the addToCart function
    }
}
