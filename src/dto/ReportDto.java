package dto;

public class ReportDto {
    private String itemCode;
    private Double totalEarned;
    private Integer totalItemsSold;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(Double totalEarned) {
        this.totalEarned = totalEarned;
    }

    public Integer getTotalItemsSold() {
        return totalItemsSold;
    }

    public void setTotalItemsSold(Integer totalItemsSold) {
        this.totalItemsSold = totalItemsSold;
    }

    public ReportDto(String itemCode, Double totalEarned, Integer totalItemsSold) {
        this.itemCode = itemCode;
        this.totalEarned = totalEarned;
        this.totalItemsSold = totalItemsSold;
    }

    public ReportDto() {
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "itemCode='" + itemCode + '\'' +
                ", totalEarned=" + totalEarned +
                ", totalItemsSold=" + totalItemsSold +
                '}';
    }
}
