import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Cart {

    private Product [] productsCart;
    private double totalPrice;
    private Date purchaseDate;
    public static final double DISCOUNT_REGULAR_WORKER = 0.1;
    public static final double DISCOUNT_MANAGER = 0.2;
    public static final double DISCOUNT_MANGER_MEMBER = 0.3;
    public static final int RANK_REGULAR_WORKER = 1;
    public static final int RANK_MANAGER = 2;
    public static final int RANK_MANGER_MEMBER = 3;

    public Cart() {
        Date rightNow = new Date();
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
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
                if (((Worker) onlineUser).getRank()==RANK_REGULAR_WORKER) {
                    discount = productsCart[i].getPrice() * DISCOUNT_REGULAR_WORKER;
                    totalPrice =totalPrice+ productsCart[i].getPrice()-discount;
                }
                if (((Worker) onlineUser).getRank()==RANK_MANAGER) {
                    discount = productsCart[i].getPrice() * DISCOUNT_MANAGER;
                    totalPrice = totalPrice+productsCart[i].getPrice()-discount;
                }
                if (((Worker) onlineUser).getRank()==RANK_MANGER_MEMBER) {
                    discount = productsCart[i].getPrice() * DISCOUNT_MANGER_MEMBER;
                    totalPrice = totalPrice+productsCart[i].getPrice()-discount;
                }
            }
        }
        return totalPrice;
    }
}
