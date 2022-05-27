package bo.custom.impl;

import CrudUtil.CrudUtil;
import bo.custom.OrderDetailsBo;
import dao.DAOFactory;
import dao.custom.OrderDetailsDo;
import dao.custom.impl.OrderDetailsDoImpl;
import dto.OrderDetailsDto;
import dto.OrderDto;
import dto.ReportDto;
import entity.Custom;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBoImpl implements OrderDetailsBo {
    OrderDetailsDo orderDetailsDo= (OrderDetailsDo) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailsDto> getAllOrderdetails() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDto>arrayList=new ArrayList<>();
        ArrayList<OrderDetail> all = orderDetailsDo.getAll();
        for (OrderDetail orderDetail : all) {
            arrayList.add(new OrderDetailsDto(orderDetail.getOid(),orderDetail.getItemCode(),orderDetail.getQty(),orderDetail.getTotalPrice()));
        }
        return arrayList;
    }

    @Override
    public boolean saveOrderdetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.save(new OrderDetail(dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getTotalPrice()));
    }

    @Override
    public boolean existOrderdetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.exist(id);
    }

    @Override
    public boolean updateOrderdetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.update(new OrderDetail(dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getTotalPrice()));
    }

    @Override
    public boolean deleteOrderdetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.delete(id);
    }

    @Override
    public String genarateNewOrderCode() throws SQLException, ClassNotFoundException {
        return orderDetailsDo.genarateId();
    }

    @Override
    public OrderDetailsDto search(String id) throws SQLException, ClassNotFoundException {
        OrderDetail search = orderDetailsDo.search(id);
        return new OrderDetailsDto(search.getOid(),search.getItemCode(),search.getQty(),search.getTotalPrice());
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

}
