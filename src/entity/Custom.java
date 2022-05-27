package entity;

public class Custom {
    private String itemCode;
    private Integer orderQty;
    private Integer qtyOnHand;
    private Double discount;
    private Double totalPrice;

    public Custom(String code, Double tot, Integer qty){
        setItemCode(code);
        setOrderQty(qty);
        setTotalPrice(tot);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public Integer getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(Integer qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "itemCode='" + itemCode + '\'' +
                ", orderQty=" + orderQty +
                ", qtyOnHand=" + qtyOnHand +
                ", discount=" + discount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

