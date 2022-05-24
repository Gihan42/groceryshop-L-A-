package dto;

public class CustomerDto {
    private String id;
    private String CusTitle;
    private String name;
    private String Address;
    private String City;
    private String Province;
    private String PostalCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusTitle() {
        return CusTitle;
    }

    public void setCusTitle(String cusTitle) {
        CusTitle = cusTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public CustomerDto(String id, String cusTitle, String name, String address, String city, String province, String postalCode) {
        this.id = id;
        CusTitle = cusTitle;
        this.name = name;
        Address = address;
        City = city;
        Province = province;
        PostalCode = postalCode;
    }

    public CustomerDto() {
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id='" + id + '\'' +
                ", CusTitle='" + CusTitle + '\'' +
                ", name='" + name + '\'' +
                ", Address='" + Address + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                '}';
    }
}
