package bo.custom.impl;

import bo.custom.OrderBo;
import dao.DAOFactory;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao= (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public ArrayList<OrderDto> getAllOrder() throws SQLException, ClassNotFoundException {
        return orderDao.getAll();
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.save(dto);
    }

    @Override
    public boolean existOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDao.exist(id);
    }

    @Override
    public boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.update(dto);
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDao.delete(id);
    }

    @Override
    public String genarateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDao.genarateId();
    }

    @Override
    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        return orderDao.search(id);
    }
}
