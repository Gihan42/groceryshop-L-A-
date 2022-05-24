package TM;

public class OrderDailyTm {
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

    public OrderDailyTm(String oid, String date, String customerId, double total) {
        this.oid = oid;
        this.date = date;
        this.customerId = customerId;
        this.total = total;
    }

    public OrderDailyTm(String oid, String date, String customerId) {
        this.oid = oid;
        this.date = date;
        this.customerId = customerId;
    }

    public OrderDailyTm() {
    }

    @Override
    public String toString() {
        return "OrderDailyTm{" +
                "oid='" + oid + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                '}';
    }
}
