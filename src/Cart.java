import java.util.Arrays;
import java.util.Calendar;

public class Cart {

    private Product [] productsCart;
    private double totalPrice;
    private Calendar purchaseDate;

    public Cart() {
        Calendar rightNow = Calendar.getInstance();
        Product [] productsCart = new Product[0];
        this.totalPrice =0;
        this.purchaseDate = rightNow;
    }

    public Product[] getProductsCart() {
        return productsCart;
    }

    public void setProductsCart(Product[] productsCart) {
        this.productsCart = productsCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Calendar getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Calendar purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productsCart=" + Arrays.toString(productsCart) +
                ", totalPrice=" + totalPrice +
                ", purchaseDate=" + purchaseDate +
                '}';
    }

    public void addToCart (Product product,int num){
        Product [] newListProducts;
        int index=0;
        if (getProductsCart()==null){
            newListProducts= new Product[num];
        }
        else {
            newListProducts= new Product[getProductsCart().length+num];
            for (int i=0;i<this.productsCart.length;i++){
                newListProducts[i] = this.productsCart[i];
                index++;
            }
        }
        for (int i=0; i<num; i++){
            newListProducts[index] = product;
            index++;
        }
        setProductsCart(newListProducts);
    }

    public double calculatePrice (User onlineUser){
        double totalPrice=0;
        double discount;
        for (int i=0; i<this.productsCart.length;i++){
            if (onlineUser instanceof Client){
                if (((Client) onlineUser).getIsVip()==true){
                    discount= productsCart[i].getPrice()*productsCart[i].getDiscount()/100;
                    totalPrice = totalPrice+productsCart[i].getPrice()-discount;
                }
                else {
                    totalPrice = totalPrice + productsCart[i].getPrice();
                }
            }
            if (onlineUser instanceof Worker){
                if (((Worker) onlineUser).getRank()==1) {
                    discount = productsCart[i].getPrice() * 10 / 100;
                    totalPrice =totalPrice+ productsCart[i].getPrice()-discount;
                }
                if (((Worker) onlineUser).getRank()==2) {
                    discount = productsCart[i].getPrice() * 20 / 100;
                    totalPrice = totalPrice+productsCart[i].getPrice()-discount;
                }
                if (((Worker) onlineUser).getRank()==3) {
                    discount = productsCart[i].getPrice() * 30 / 100;
                    totalPrice = totalPrice+productsCart[i].getPrice()-discount;
                }
            }
        }
        return totalPrice;
    }
}
