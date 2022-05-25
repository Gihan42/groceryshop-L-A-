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
import db.DBConnection;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
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
    public boolean purchaseOrder(OrderDto orderDTO, ArrayList<OrderDetailsDto> orderDetailDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);
        boolean isOrderAdded = orderDAO.save(new Order(orderDTO.getOid(),orderDTO.getDate(),orderDTO.getCustomerId(),orderDTO.getTotal()));

        if(!isOrderAdded){
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
        for (OrderDetailsDto dto : orderDetailDTO) {
            boolean isOderDetailsSaved = orderDetailsDAO.save(new OrderDetail(dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getDiscount(),dto.getTotalPrice()));
            itemDAO.updateItemQty(dto.getItemCode(),dto.getQty());
            if (!isOderDetailsSaved){
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }
    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer search = dto.search(id);
        return  new CustomerDto(search.getId(),search.getCusTitle(),search.getName(),search.getAddress(),search.getCity(),search.getProvince(),search.getPostalCode());
    }

    @Override
    public ItemDto searchItem(String code) throws SQLException, ClassNotFoundException {
        Item search = itemDAO.search(code);
        return new ItemDto(search.getCode(),search.getDescription(),search.getPackSize(),search.getQtyOnhand(),search.getUnitPrice(),search.getDiscount());
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
        ArrayList<CustomerDto>arrayList=new ArrayList<>();
        ArrayList<Customer> all = dto.getAll();
        for(Customer customer:all){
            arrayList.add(new CustomerDto(customer.getId(),customer.getCusTitle(),customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> allItem=new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll();
        for (Item item : all) {
            allItem.add(new ItemDto(item.getCode(),item.getDescription(),item.getPackSize(),item.getQtyOnhand(),item.getUnitPrice(),item.getDiscount()));
        }
        return allItem;
    }
    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(dto.getOid(),dto.getDate(),dto.getCustomerId(),dto.getTotal()));
    }
    @Override
    public boolean saveOrderDetails(ArrayList<OrderDetailsDto>details) throws SQLException, ClassNotFoundException {

        for (OrderDetailsDto data:details
             ) {
            boolean isDetailsSave = orderDetailsDAO.save(new OrderDetail(data.getOid(),data.getItemCode(),data.getQty(),data.getTotalPrice()));
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
