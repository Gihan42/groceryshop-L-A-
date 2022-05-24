package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T,Id> extends SuperDao{
    boolean save(T dto)throws SQLException,ClassNotFoundException;
    boolean exist(Id id)throws SQLException,ClassNotFoundException;
    boolean delete(Id id)throws SQLException,ClassNotFoundException;
    T search(Id id)throws SQLException,ClassNotFoundException;
    ArrayList<T> getAll()throws SQLException,ClassNotFoundException;
    boolean update(T dto)throws SQLException,ClassNotFoundException;
    String genarateId()throws SQLException,ClassNotFoundException;
}
