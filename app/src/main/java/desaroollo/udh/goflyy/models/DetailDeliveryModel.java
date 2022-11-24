package desaroollo.udh.goflyy.models;

public class DetailDeliveryModel {
    String delivery_code, description;
    Integer price, quantity;
    public DetailDeliveryModel(){

    }

    public DetailDeliveryModel(String delivery_code, String description, Integer price, Integer quantity) {
        this.delivery_code = delivery_code;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDelivery_code() {
        return delivery_code;
    }

    public void setDelivery_code(String delivery_code) {
        this.delivery_code = delivery_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
