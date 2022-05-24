package bo.custom.impl;

import CrudUtil.CrudUtil;
import bo.custom.CustomerBo;
import dao.DAOFactory;
import dao.custom.CustomerDao;
import dao.custom.impl.CustomerDaoIMPL;
import dto.CustomerDto;
import entity.Customer;

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
        ArrayList<CustomerDto>arrayList=new ArrayList<>();
        ArrayList<Customer> all = customerDao.getAll();
         for(Customer customer:all){
            arrayList.add(new CustomerDto(customer.getId(),customer.getCusTitle(),customer.getName(),customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode()));
         }
         return arrayList;
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
       return customerDao.save(new Customer(dto.getId(),dto.getCusTitle(),dto.getName(), dto.getAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.exist(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDao.update(new Customer(dto.getId(),dto.getCusTitle(),dto.getName(), dto.getAddress(),dto.getCity(),dto.getProvince(),dto.getPostalCode()));
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
        Customer search = customerDao.search(id);
        return  new CustomerDto(search.getId(),search.getCusTitle(),search.getName(),search.getAddress(),search.getCity(),search.getProvince(),search.getPostalCode());
    }

}
