package bo.custom.impl;

import CrudUtil.CrudUtil;
import bo.custom.CustomerBo;
import dao.DAOFactory;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoIMPL;
import dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao= (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public ArrayList<String> getCustomerIds() throws SQLException, ClassNotFoundException {
     return customerDao.getCustomerIds();
    }
    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
      return  customerDao.getAll();
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
       return customerDao.save(dto);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.exist(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(dto);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
       return customerDao.delete(id);
    }

    @Override
    public String genarateNewCustomerId() throws SQLException, ClassNotFoundException {
        return null;
    }
    public CustomerDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM  Customer WHERE id=?", id);
        if(rst.next()){
            return new CustomerDto(  rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7));
        }
        return null;
    }

}
