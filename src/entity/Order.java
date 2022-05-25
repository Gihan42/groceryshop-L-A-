package entity;

import java.time.LocalDate;

public class Order {
    private String oid;
    private String date;
    private String customerId;
    private double total;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Order(String oid, String date, String customerId, double total) {
        this.oid = oid;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
    }

    public Order(String orderId, LocalDate orderDate, String customerId, double total) {
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                '}';
    }
}
