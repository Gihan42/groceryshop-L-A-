package dao.custom;

import dao.CrudDao;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item,String> {
    ArrayList<String> getItemsCode()throws SQLException,ClassNotFoundException;
    boolean updateItemQty(String itemcode ,int qty)throws SQLException,ClassNotFoundException;
}
