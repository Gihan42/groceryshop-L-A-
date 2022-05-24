package TM;

public class OrderDetailsTm {
    private String oid;
    private String itemCode;
    private int qty;
    private double Discount;
    private double tottalPrice;


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getTottalPrice() {
        return tottalPrice;
    }

    public void setTottalPrice(double tottalPrice) {
        this.tottalPrice = tottalPrice;
    }

    public OrderDetailsTm(String oid, String itemCode, int qty, double discount, double tottalPrice) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        Discount = discount;
        this.tottalPrice = tottalPrice;
    }

    public OrderDetailsTm() {
    }

    @Override
    public String toString() {
        return "OrderDetailsTm{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", Discount=" + Discount +
                ", tottalPrice=" + tottalPrice +
                '}';
    }
}
