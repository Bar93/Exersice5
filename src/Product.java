public class Product {

    private boolean stock;
    private double price;
    private String description;
    private int discount;

    public Product(double price, String description, int discount) {
        this.stock = true;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Product:" +description+ "  is in stock:" + stock +"\n"+
                "price:" + price + "  discount for VIP:" + discount;

    }
}
