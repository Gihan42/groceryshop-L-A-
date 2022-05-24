package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DAOFactory;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDao.getAll();
    }

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
       return itemDao.save(dto);
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
       return existItem(id);
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
     return itemDao.update(dto);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
      return itemDao.delete(id);
    }

    @Override
    public String genarateNewItemCode() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ItemDto search(String id) throws SQLException, ClassNotFoundException {
     return itemDao.search(id);
    }
}
