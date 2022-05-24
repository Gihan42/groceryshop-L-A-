package bo.custom;

import bo.custom.bo.SuperBo;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDetailsDto;
import dto.OrderDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface PurchOrderBo extends SuperBo {
    boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;

    ItemDto searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean checkItemIsAvailable(String code) throws SQLException, ClassNotFoundException;

    boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException;

    String generateNewOrderID() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;
    public boolean saveOrderDetails(ArrayList<OrderDetailsDto>details) throws SQLException, ClassNotFoundException;
    public boolean saveOrder(OrderDto dto) throws SQLException, ClassNotFoundException;

}
