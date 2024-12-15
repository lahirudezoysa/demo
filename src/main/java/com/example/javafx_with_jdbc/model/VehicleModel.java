package com.example.javafx_with_jdbc.model;

import com.example.javafx_with_jdbc.db.DBConnection;
import com.example.javafx_with_jdbc.dto.VehicleDto;
import com.example.javafx_with_jdbc.tm.VehicleTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleModel {


    public static boolean saveVehicle(VehicleDto vehicleDto) {

        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            // sql query
            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicle values(?,?,?,?,?)");
            preparedStatement.setObject(1, vehicleDto.getId());
            preparedStatement.setObject(2, vehicleDto.getBrand());
            preparedStatement.setObject(3, vehicleDto.getModel());
            preparedStatement.setObject(4, vehicleDto.getQty());
            preparedStatement.setObject(5, vehicleDto.getPrice());

            //execute update
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                System.out.println("Data Saved");
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean deleteVehicle(VehicleDto vehicleDto) {
        try {

            Connection connection = DBConnection.getDbConnection().getConnection();
            // sql query
            PreparedStatement preparedStatement = connection.prepareStatement("delete from vehicle where id=?");
            preparedStatement.setObject(1, vehicleDto.getId());


            //execut query
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                System.out.println("Data deleted");
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean searchVehicle(VehicleDto vehicleDto) {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            // sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle where id=?");
            preparedStatement.setInt(1, vehicleDto.getId());
            //execut query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                vehicleDto.setId(resultSet.getInt(1));
                vehicleDto.setBrand(resultSet.getString(2));
                vehicleDto.setModel(resultSet.getString(3));
                vehicleDto.setQty(resultSet.getInt(4));
                vehicleDto.setPrice(resultSet.getDouble(5));
                System.out.println("Data viewed successfully");
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateVehicle(VehicleDto vehicleDto) {
        try {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set brand=?, model=?, qty=?, price=? where id=?");
            preparedStatement.setObject(1, vehicleDto.getBrand());
            preparedStatement.setObject(2, vehicleDto.getModel());
            preparedStatement.setObject(3, vehicleDto.getQty());
            preparedStatement.setObject(4, vehicleDto.getPrice());
            preparedStatement.setObject(5, vehicleDto.getId());

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                System.out.println("Update Saved");
                return true;
            } else {
                System.out.println("Update Failed");
                return false;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<VehicleTM> loadAllVehicles() {
        try{
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<VehicleTM> vehiclesTM = new ArrayList<>();
            while (resultSet.next()) {
                vehiclesTM.add(new VehicleTM(
                    resultSet.getInt("id"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    resultSet.getInt("qty"),
                    resultSet.getDouble("price")

                ));
            }
            return vehiclesTM;
        }catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
