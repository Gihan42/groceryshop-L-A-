package entity;

public class Item {
    private String code;
    private String description;
    private String packSize;
    private int qtyOnhand;
    private double unitPrice;
    private double discount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public int getQtyOnhand() {
        return qtyOnhand;
    }

    public void setQtyOnhand(int qtyOnhand) {
        this.qtyOnhand = qtyOnhand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Item(String code, String description, String packSize, int qtyOnhand, double unitPrice, double discount) {
        this.code = code;
        this.description = description;
        this.packSize = packSize;
        this.qtyOnhand = qtyOnhand;
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", qtyOnhand=" + qtyOnhand +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                '}';
    }
}
