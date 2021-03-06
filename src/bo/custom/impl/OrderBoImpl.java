package bo.custom.impl;

import CrudUtil.CrudUtil;
import bo.custom.OrderBo;
import dao.DAOFactory;
import dao.custom.OrderDao;
import dao.custom.OrderDetailsDo;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDto;
import dto.ReportDto;
import entity.Custom;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDao= (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDo orderDetailsDo= (OrderDetailsDo) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    @Override
    public ArrayList<OrderDto> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDto>arrayList=new ArrayList<>();
        ArrayList<Order> all = orderDao.getAll();
        for (Order order : all) {
            arrayList.add(new OrderDto(order.getOid(),order.getDate(),order.getCustomerId(),order.getTotal()));
        }
        return arrayList;
    }

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.save(new Order(dto.getOid(),dto.getDate(),dto.getCustomerId(),dto.getTotal()));
    }

    @Override
    public boolean existOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDao.exist(id);
    }

    @Override
    public boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDao.update(new Order(dto.getOid(),dto.getDate(),dto.getCustomerId(),dto.getTotal()));
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
        Order search = orderDao.search(id);
        return new OrderDto(search.getOid(),search.getDate(),search.getCustomerId(),search.getTotal());
    }

    public ArrayList<ReportDto> generateReport(int code) throws  SQLException, ClassNotFoundException{
        ArrayList<Custom> rpt = orderDetailsDo.generateReport(code);
        ArrayList<ReportDto> report = new ArrayList<>();
        if(rpt!=null){
            for (Custom customOrder : rpt) {
                report.add(new ReportDto(customOrder.getItemCode(),customOrder.getTotalPrice(),customOrder.getOrderQty()));
            }
            return report;
        }
        return null;
    }
}
