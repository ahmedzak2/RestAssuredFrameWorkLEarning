package pojo.EcommerceAPI;

public class OrderDetail {
    private String country;
    String productOrderedId;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProductOrderId(String productOrderId) {
        this.productOrderedId = productOrderId;
    }

    public String getCountry() {
        return country;
    }

    public String getProductOrderId() {
        return productOrderedId;
    }
}
