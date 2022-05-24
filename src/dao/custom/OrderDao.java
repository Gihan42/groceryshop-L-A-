package dao.custom;

import dao.CrudDao;
import dto.OrderDto;
import entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDao extends CrudDao<Order,String> {

}
