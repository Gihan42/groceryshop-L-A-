package bo.custom.bo;

import bo.custom.impl.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return  boFactory;
    }
    public enum BoTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAILS,PURCHASE_ORDER,SYSTEM_REPORT
    }
    public SuperBo getBo(BoTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case ORDER:
                return new OrderBoImpl();
            case ORDERDETAILS:
                return new OrderDetailsBoImpl();
            case PURCHASE_ORDER:
                return new PurchOrderBoImpl();
            case SYSTEM_REPORT:
                return new SystemReportBoImpl();
            default:
                return null;
        }

    }
}
