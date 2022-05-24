package bo.custom;

import bo.custom.bo.SuperBo;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo {
    ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;

    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    public boolean existItem(String id) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    public String genarateNewItemCode() throws SQLException, ClassNotFoundException;

    ItemDto search(String id)throws SQLException, ClassNotFoundException;
}
