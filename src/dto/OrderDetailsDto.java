package dto;

public class OrderDetailsDto {
    private String oid;
    private String itemCode;
    private int qty;
    private double Discount;
    private double totalPrice;


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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDetailsDto(String oid, String itemCode, int qty, double discount, double totalPrice) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        Discount = discount;
        this.totalPrice = totalPrice;
    }

    public OrderDetailsDto() {
    }

    @Override
    public String toString() {
        return "OrderDetailsDto{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", Discount=" + Discount +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public OrderDetailsDto(String oid, String itemCode, int qty, double totalPrice) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }
}
