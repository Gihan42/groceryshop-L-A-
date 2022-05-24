package bo.custom.impl;

import TM.CartTm;
import bo.custom.PurchOrderBo;
import dao.DAOFactory;
import dao.custom.CustomerDao;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.custom.OrderDetailsDo;
import dao.custom.impl.CustomerDaoIMPL;
import dao.custom.impl.ItemDaoImpl;
import dao.custom.impl.OrderDaoImpl;
import dao.custom.impl.OrderDetailsDoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchOrderBoImpl implements PurchOrderBo {
    CustomerDao dto = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDao itemDAO = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDao orderDAO = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDo orderDetailsDAO = (OrderDetailsDo) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    ObservableList<CartTm> tmList = FXCollections.observableArrayList();

    @Override
    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        //  Connection connection = DBConnection.getDbConnection().getConnection();
        /*if order id already exist*/
       /* if (orderDAO.exist(orderId)) {

        }
        connection.setAutoCommit(false);
        boolean save = orderDAO.save(new OrderDto(orderId, orderDate, customerId));

        if (!save) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailsDto detail : orderDetails) {
            boolean save1 = orderDetailsDAO.save(detail);
            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
//                ItemDTO item = findItem(detail.getItemCode());
            ItemDto item = searchItem(detail.getItemCode());
            item.setQtyOnhand((item.getQtyOnhand() - detail.getQty()));

            //update item
            System.out.println(item);
            boolean update = itemDAO.update(item);

            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }
        connection.commit();
        connection.setAutoCommit(true);
        return true;*/

        return false;
    }



    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return dto.search(id);
    }

    @Override
    public ItemDto searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.search(code);
    }

    @Override
    public boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException {
       return itemDAO.exist(code);
    }

    @Override
    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(id);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.genarateId();
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        return dto.getAll();
    }

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }
    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(dto);
    }
    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailsDto>details) throws SQLException, ClassNotFoundException {

        for (OrderDetailsDto data:details
             ) {
            boolean isDetailsSave = orderDetailsDAO.save(data);
           if(isDetailsSave){
               boolean b =itemDAO.updateItemQty(data.getItemCode(), data.getQty());
               if(!b) {
                   return false;
               }
            }
            else{
                return false;
            }
        }
        return true;
    }

}
