package dao.custom;

import dao.CrudDao;
import dto.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends CrudDao<OrderDto,String> {
    public ArrayList<OrderDto> getAllOrdersByCusId(String id)throws SQLException , ClassNotFoundException;
    ArrayList<OrderDto> getAllOrderByDaily(String date) throws SQLException, ClassNotFoundException;
}
