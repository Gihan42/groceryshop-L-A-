package TM;

public class LeastMovableMostMovbleTm {
    private String oid;
    private String itemCode;
    private int qty;
    private double Discount;
    private double total;

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
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public LeastMovableMostMovbleTm(String oid, String itemCode, int qty, double discount) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        Discount = discount;
    }

    public LeastMovableMostMovbleTm(String oid, String itemCode, int qty, double discount, double total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        Discount = discount;
        this.total = total;
    }

    @Override
    public String toString() {
        return "LeastMovableMostMovbleTm{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", Discount=" + Discount +
                ", total=" + total +
                '}';
    }
}
