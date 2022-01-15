import java.util.Arrays;

public abstract class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isWorker;
    private Cart [] personalCart;

    public User(String firstName, String lastName, String username, String password, boolean isWorker) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isWorker = isWorker;
        Cart [] personalCart1 = new Cart[0];
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setIsWorker(boolean isWorker) {
        isWorker = isWorker;
    }

    public Cart[] getPersonalCart() {
        return personalCart;
    }

    public void setPersonalCart(Cart[] personalCart) {
        this.personalCart = personalCart;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isWorker=" + isWorker +
                ", personalCart=" + Arrays.toString(personalCart) +
                '}';
    }

    public void addCart (Cart newCart){
        Cart [] newCartList;
        if (this.getPersonalCart()==null){
            newCartList = new Cart [1];
        }
        else {
            newCartList = new Cart[this.personalCart.length + 1];
            int index = 0;
            while (index < this.personalCart.length) {
                newCartList[index] = this.personalCart[index];
                index++;
            }
        }
        newCartList[newCartList.length-1] = newCart;
        setPersonalCart(newCartList);
    }
}
