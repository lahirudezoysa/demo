package com.example.javafx_with_jdbc.model;

import com.example.javafx_with_jdbc.db.DBConnection;
import com.example.javafx_with_jdbc.dto.OrderDetailsDto;
import com.example.javafx_with_jdbc.dto.OrderDto;

import java.sql.*;

public class OrderModel {
    public static boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getDbConnection().getConnection();

        //diable auto commit feature
        connection.setAutoCommit(false);

        //insert data to order table
        PreparedStatement stm1=connection.prepareStatement("insert into orders (date,amount) values(?,?)", Statement.RETURN_GENERATED_KEYS);
        stm1.setObject(1,orderDto.getOrderDate());
        stm1.setObject(2,orderDto.getSubTotal());

        int orderSave=stm1.executeUpdate();
        if(orderSave>0){
            //get order id from generated keys
            ResultSet generatedKeys=stm1.getGeneratedKeys();
            if(generatedKeys.next()){
                int id=generatedKeys.getInt(1);

                //save order details
                for(OrderDetailsDto dto: orderDto.getOrderDetailsDtos()){
                    PreparedStatement stm2=connection.prepareStatement("insert into order_details(oid,vid,qty,price) values(?,?,?,?)");
                    stm2.setObject(1,id);
                    stm2.setObject(2,dto.getVehicleId());
                    stm2.setObject(3,dto.getQty());
                    stm2.setObject(4,dto.getPrice());

                    int OrderDetailsSave=stm2.executeUpdate();
                    if(OrderDetailsSave>0){
                        PreparedStatement stm3=connection.prepareStatement("update vehicle set qty=qty-? where id=?");
                        stm3.setObject(1,dto.getQty());
                        stm3.setObject(2,dto.getVehicleId());

                        int vehicleQtyUpdated=stm3.executeUpdate();
                        if(vehicleQtyUpdated<=0){
                            connection.rollback();
                            connection.setAutoCommit(true);
                            return false;
                        }

                    }else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
        connection.rollback();
        connection.setAutoCommit(true);
        return false;
    }
}
