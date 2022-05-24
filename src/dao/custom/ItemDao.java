package dao.custom;

import dao.CrudDao;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<ItemDto,String> {
    ArrayList<String> getItemsCode()throws SQLException,ClassNotFoundException;
    boolean updateItemQty(String itemcode ,int qty)throws SQLException,ClassNotFoundException;
}
