package dao.custom;

import dao.CrudDao;
import dto.OrderDetailsDto;
import entity.Custom;
import entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDo extends CrudDao<OrderDetail,String> {
    public ArrayList<Custom> generateReport(int code) throws SQLException, ClassNotFoundException;
}
