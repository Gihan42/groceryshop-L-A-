package bo.custom.impl;

import CrudUtil.CrudUtil;
import bo.custom.SystemReportBo;
import dao.DAOFactory;
import dao.custom.OrderDao;
import dao.custom.impl.OrderDaoImpl;
import dto.OrderDetailsDto;
import dto.OrderDto;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SystemReportBoImpl implements SystemReportBo {
    OrderDao orderDao= (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public ArrayList<OrderDetailsDto> mostMovableItem() throws Exception, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("select * from orderdetails order by qty desc");
        ArrayList<OrderDetailsDto>allItem=new ArrayList<>();
        while (resultSet.next()) {
            allItem.add(new OrderDetailsDto(
               resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            ));
        }
         return allItem;
    }

    @Override
    public ArrayList<OrderDetailsDto> leastMovableItem() throws Exception, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery(" select * from orderdetails order by qty asc");
        ArrayList<OrderDetailsDto>arrayList=new ArrayList<>();
        while (resultSet.next()){
            arrayList.add(new OrderDetailsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            ));
        }
        return arrayList;
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
    //////////////
   /* @Override
    public boolean DailyInCome(String date) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT SUM(total) AS TotalItemsOrdered FROM Orders WHERE date=?", date);
        if(resultSet.next()){
            OrderDto orderDto=new OrderDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );

        }
        return null;
    }*/
}
