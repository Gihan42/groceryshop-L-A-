package dao.custom;

import dao.CrudDao;
import dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<CustomerDto,String> {
    public ArrayList<String> getCustomerIds() throws SQLException, ClassNotFoundException;
}
