package bo.custom;

import bo.custom.bo.SuperBo;
import dto.OrderDto;
import dto.ReportDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo extends SuperBo {
    ArrayList<OrderDto> getAllOrder() throws SQLException, ClassNotFoundException;

    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;

    public boolean existOrder(String id) throws SQLException, ClassNotFoundException;

    public boolean updateOrder(OrderDto dto) throws SQLException, ClassNotFoundException;

    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;

    public String genarateNewOrderId() throws SQLException, ClassNotFoundException;

    OrderDto search(String id)throws SQLException, ClassNotFoundException;

    public ArrayList<ReportDto> generateReport(int code) throws  SQLException, ClassNotFoundException;
}
