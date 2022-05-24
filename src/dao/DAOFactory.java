package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static  DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
      if(daoFactory==null){
          daoFactory=new DAOFactory();
      }
      return daoFactory;
    }
    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAILS, QUERYDAO
    }
    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDaoIMPL();
            case ITEM:
                return new ItemDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case ORDERDETAILS:
                return new OrderDetailsDoImpl();
            case QUERYDAO:
                return (SuperDao) new QueryDaoImpl();
            default:
                return null;
        }
    }
}
