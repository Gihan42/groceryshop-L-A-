package dao.custom.impl;

import CrudUtil.CrudUtil;
import dao.custom.OrderDetailsDo;
import dto.OrderDetailsDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDoImpl implements OrderDetailsDo {

    @Override
    public boolean save(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
     return  CrudUtil.executeUpdate("INSERT INTO orderdetails VALUES (?,?,?,?,?)",dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getDiscount(),dto.getTotalPrice());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetailsDto search(String oid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orderdetails WHERE oid=?", oid);
        if(resultSet.next()){
            return new OrderDetailsDto(
              resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetailsDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
      return  CrudUtil.executeUpdate("UPDATE orderdetails SET itemcode=? , qty=? , Discount=? , totalPrice=? WHERE oid=?",
              dto.getItemCode(),dto.getQty(),dto.getDiscount(),dto.getOid(),dto.getTotalPrice());
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
