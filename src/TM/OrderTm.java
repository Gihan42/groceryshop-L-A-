package TM;

public class OrderTm {
  private String oid;
  private String date;
  private String customerId;

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

    public OrderTm(String oid, String date, String customerId) {
        this.oid = oid;
        this.date = date;
        this.customerId = customerId;
    }

    public OrderTm() {
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "oid='" + oid + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
