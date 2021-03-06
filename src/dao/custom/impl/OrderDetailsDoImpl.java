package dao.custom.impl;

import CrudUtil.CrudUtil;
import dao.custom.OrderDetailsDo;
import dto.OrderDetailsDto;
import dto.ReportDto;
import entity.Custom;
import entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDoImpl implements OrderDetailsDo {

    @Override
    public boolean save(OrderDetail dto) throws SQLException, ClassNotFoundException {
     return  CrudUtil.executeUpdate("INSERT INTO orderdetails VALUES (?,?,?,?,?)",dto.getOid(),dto.getItemCode(),dto.getQty(),dto.getDiscount(),dto.getTotalPrice());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String oid) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM orderdetails WHERE oid=?", oid);
        if(resultSet.next()){
            return new OrderDetail(
              resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
      return  CrudUtil.executeUpdate("UPDATE orderdetails SET itemcode=? , qty=? , Discount=? , totalPrice=? WHERE oid=?",
              dto.getItemCode(),dto.getQty(),dto.getDiscount(),dto.getTotalPrice(),dto.getOid());
    }

    @Override
    public String genarateId() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public ArrayList<Custom> generateReport(int code) throws SQLException, ClassNotFoundException {
        ArrayList<Custom> report = new ArrayList<>();
        if(code==0){
            ResultSet rst = CrudUtil.executeQuery("CALL DAILY_REPORT()");
            while(rst.next()){
                report.add(new Custom(rst.getString(1),rst.getDouble(2),rst.getInt(3)));
            }
            return report;
        }
        else if(code==1){
            ResultSet rst = CrudUtil.executeQuery("CALL MONTHLY_REPORT()");
            while(rst.next()){
                report.add(new Custom(rst.getString(1),rst.getDouble(2),rst.getInt(3)));
            }
            return report;
        }
        else if(code==2){
            ResultSet rst = CrudUtil.executeQuery("CALL ANNUAL_REPORT()");
            while(rst.next()){
                report.add(new Custom(rst.getString(1),rst.getDouble(2),rst.getInt(3)));
            }
            return report;
        }
        return null;
    }
}
