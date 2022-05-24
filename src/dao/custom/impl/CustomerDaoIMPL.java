package dao.custom.impl;

import CrudUtil.CrudUtil;
import dao.custom.CustomerDao;
import dto.CustomerDto;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoIMPL implements CustomerDao {
    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)",
                dto.getId(),dto.getCusTitle(),dto.getName(),dto.getAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeQuery("SELECT * FROM Customer WHERE id=?",id).next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  Customer WHERE id=?", id);
        if(rst.next()){
          return new Customer(  rst.getString(1),
                  rst.getString(2),
                  rst.getString(3),
                  rst.getString(4),
                  rst.getString(5),
                  rst.getString(6),
                  rst.getString(7));
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomer=new ArrayList<>();
        while(rst.next()){
            allCustomer.add(new Customer(rst.getString(1), rst.getString(2),
                    rst.getString(3), rst.getString(4), rst.getString(5),
                    rst.getString(6), rst.getString(7)));
        }
        return allCustomer;
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
       return CrudUtil.executeUpdate("UPDATE  Customer SET CusTitle=? , name=? ,  address=? , City=? , Province=? , PostalCode=? WHERE id=?",
                dto.getCusTitle(), dto.getName(), dto.getAddress(), dto.getCity(), dto.getProvince(),
                              dto.getPostalCode(), dto.getId());
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public ArrayList<String> getCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.executeQuery("SELECT id FROM Customer");
        ArrayList<String> ids = new ArrayList<>();
        while (result.next()) {
            ids.add(result.getString(1));
        }
        return ids;
    }
}
