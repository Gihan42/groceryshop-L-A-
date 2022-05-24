package dao.custom.impl;

import CrudUtil.CrudUtil;
import dao.custom.ItemDao;
import dto.ItemDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemDto dto) throws SQLException, ClassNotFoundException {
     return CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?)",dto.getCode(),dto.getDescription(),
                dto.getPackSize(),dto.getQtyOnhand(),dto.getUnitPrice(),dto.getDiscount());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT * FROM item WHERE code=?",id).next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
     return CrudUtil.executeUpdate("DELETE FROM item WHERE code=?",id);
    }

    @Override
    public ItemDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE code=?", id);
        if(resultSet.next()){
            return new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6)
            );
        }
        return null;
    }

    @Override
    public ArrayList<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM item");
        ArrayList<ItemDto> allItem=new ArrayList<>();
        while(rst.next()){
            allItem.add(new ItemDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5),
                    rst.getDouble(6)
                    ));
        }
        return allItem;
    }

    @Override
    public boolean update(ItemDto dto) throws SQLException, ClassNotFoundException {
    return  CrudUtil.executeUpdate("UPDATE  item SET description=? , PackSize=? , qtyOnHand=? , unitPrice=? , discount=? WHERE code=?",
        dto.getDescription(),dto.getPackSize(),dto.getQtyOnhand(),dto.getUnitPrice(),dto.getDiscount(),dto.getCode());
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getItemsCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT code FROM Item " );
        ArrayList <String>  itemcode=new ArrayList<>();
       while(rst.next()){
           itemcode.add(rst.getString(1));
       }
       return itemcode;
    }
    @Override
    public boolean updateItemQty(String itemcode, int qty) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE item SET qtyOnHand=qtyOnHand-? WHERE code=?",qty,itemcode);
    }

}
