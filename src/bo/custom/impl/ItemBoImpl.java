package bo.custom.impl;

import bo.custom.ItemBo;
import dao.DAOFactory;
import dao.custom.ItemDao;
import dao.custom.impl.ItemDaoImpl;
import dto.ItemDto;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> allItem=new ArrayList<>();
        ArrayList<Item> all = itemDao.getAll();
        for (Item item : all) {
            allItem.add(new ItemDto(item.getCode(),item.getDescription(),item.getPackSize(),item.getQtyOnhand(),item.getUnitPrice(),item.getDiscount()));
        }
        return allItem;
    }

    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
       return itemDao.save(new Item(dto.getCode(),dto.getDescription(),dto.getPackSize(),dto.getQtyOnhand(),dto.getUnitPrice(),dto.getDiscount()));
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
       return itemDao.exist(id);
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
     return itemDao.update(new Item(dto.getCode(),dto.getDescription(),dto.getPackSize(),dto.getQtyOnhand(),dto.getUnitPrice(),dto.getDiscount()));
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
        Item search = itemDao.search(id);
        return new ItemDto(search.getCode(),search.getDescription(),search.getPackSize(),search.getQtyOnhand(),search.getUnitPrice(),search.getDiscount());
    }
}
