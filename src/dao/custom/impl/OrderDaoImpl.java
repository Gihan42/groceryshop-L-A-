package dao.custom.impl;

import CrudUtil.CrudUtil;
import dao.custom.OrderDao;
import dto.OrderDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
     return  CrudUtil.executeUpdate("INSERT INTO orders VALUES (?,?,?,?)",dto.getOid(),dto.getDate(),dto.getCustomerId(),dto.getTotal());
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT oid FROM orders WHERE oid=?", oid).next();
    }

    @Override
    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
      return  CrudUtil.executeUpdate("DELETE FROM orders WHERE oid=?",oid);
    }

    @Override
    public OrderDto search(String oid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE oid=?", oid);
        if(resultSet.next()){
            return new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public ArrayList<OrderDto> getAllOrdersByCusId(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE customerId=?", id);
        ArrayList<OrderDto> allOrders=new ArrayList<>();
        while(resultSet.next()){
            OrderDto order= new OrderDto(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );

            allOrders.add(order);
        }
        return allOrders;
    }

    @Override
    public ArrayList<OrderDto> getAllOrderByDaily(String date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orders WHERE date=?", date);
        ArrayList<OrderDto>allOrders=new ArrayList<>();
        while(resultSet.next()){
            OrderDto orderDto=new OrderDto(
              resultSet.getString(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getDouble(4)

            );
            allOrders.add(orderDto);
        }
        return allOrders;
    }

}
