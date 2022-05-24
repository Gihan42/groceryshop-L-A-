package bo.custom.impl;

import bo.custom.OrderDetailsBo;
import dao.DAOFactory;
import dao.custom.OrderDetailsDo;
import dao.custom.impl.OrderDetailsDoImpl;
import dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBoImpl implements OrderDetailsBo {
    OrderDetailsDo orderDetailsDo= (OrderDetailsDo) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailsDto> getAllOrderdetails() throws SQLException, ClassNotFoundException {
        return orderDetailsDo.getAll();
    }

    @Override
    public boolean saveOrderdetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.save(dto);
    }

    @Override
    public boolean existOrderdetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.exist(id);
    }

    @Override
    public boolean updateOrderdetails(OrderDetailsDto dto) throws SQLException, ClassNotFoundException {
        return orderDetailsDo.update(dto);
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
        return orderDetailsDo.search(id);
    }
}
