import java.util.Scanner;

public class Store {

    private User [] users;
    private Product [] products;
    public static final int MIN_CHARS_IN_PASSWORD = 6;

    public Store() {
        User [] users = new User[0];
        Product [] products = new Product[0];
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public String toString() {
       return "";
    }

    public void createUser(boolean isWorker){
        Scanner scanner = new Scanner(System.in);
        String firstName,lastName,password,username;
        boolean vip=false, correctFirstName=false, correctLastName=false, correctUsername=false;
        int rank;
        int index=0;
        int vipChoice;
        System.out.println("enter a first name");
        firstName = scanner.next();
        while (correctFirstName == false)
        {
            while (index<firstName.length())
            {
                if (Character.isDigit(firstName.charAt(index))==true)
                {
                    correctFirstName = false;
                    System.out.println("enter a correct first name");
                    firstName = scanner.next();
                    index = 0;
                    break;
                }
                index++;
               }
            if (index==firstName.length())
            {
                correctFirstName = true;
            }
        }
        index = 0;
        System.out.println("enter a last name");
        lastName = scanner.next();
        while (correctLastName == false)
        {
            while (index<lastName.length())
            {
                if (Character.isDigit(lastName.charAt(index))==true)
                {
                    correctLastName = false;
                    System.out.println("enter a correct last name");
                    lastName = scanner.next();
                    index = 0;
                    break;
                }
                index++;
            }
            if (index==lastName.length())
            {
                correctLastName = true;
            }
        }
        index = 0;
        System.out.println("enter a username");
        username = scanner.next();
        while (correctUsername == false)
        {
            while (index<username.length())
            {
                if (isUserNameExist(username)!=-1){
                    System.out.println("the username is exist");
                    username = scanner.next();
                    index=0;
                    break;
                }
                index++;
            }
            if (index==username.length())
            {
                correctUsername = true;
            }
        }
        index = 0;
        System.out.println("enter a password");
        password = scanner.next();
        while (password.length() < MIN_CHARS_IN_PASSWORD)
        {
            System.out.println("enter a password with at least 6 char");
            password = scanner.next();
        }
        if (isWorker==true)
        {
            System.out.println("enter a rank"+"\n"+"1. regular worker"+"\n"+"2. manger"+"\n"+"3. manger member");
            rank = scanner.nextInt();
            while (rank!=1&&rank!=2&&rank!=3){
                System.out.println("enter a correct rank"+"\n"+"1. regular worker"+"\n"+"2. manger"+"\n"+"3. manger member");
                rank = scanner.nextInt();
            }
            Worker newWorker = new Worker(firstName,lastName,username,password,isWorker,rank);
            addUser(newWorker);
        }
        if (isWorker==false)
        {
            System.out.println("are you VIP member?"+"\n"+"1. yes"+"\n"+"2. no");
            vipChoice = scanner.nextInt();
            while (vipChoice!=1&& vipChoice!=2){
                System.out.println("are you VIP member?"+"\n"+"1. yes"+"\n"+"2. no");
            }
            if (vipChoice==1){
                vip = true;
            }
            if (vipChoice==2){
                vip = false;
            }
            Client newClient = new Client(firstName,lastName,username,password,isWorker,vip);
            addUser(newClient);
        }
    }
    public void addUser (User newUser){
        User[] newUsers;
        if (this.getUsers()!=null) {
           newUsers = new User[this.getUsers().length + 1];
        }
        else {
            newUsers = new User[1];
        }
        int index=0;
        if (this.getUsers()!=null) {
            while (index < this.users.length) {
                newUsers[index] = users[index];
                index++;
            }
        }
        newUsers[newUsers.length-1] = newUser;
        setUsers(newUsers);
    }

    public int login () {
        Scanner scanner = new Scanner(System.in);
        String username, password;
        System.out.println("enter username");
        username = scanner.nextLine();
        System.out.println("enter password");
        password = scanner.nextLine();
        int indexOfUser = isUserNameExist(username);
        if (indexOfUser != -1) {
            if (this.users[indexOfUser].getPassword().equals(password) == false) {
                System.out.println("incorrect username or password");
                indexOfUser=-1;
            } else {
                System.out.println("you are logged in successfully");
            }
        }
        return indexOfUser;
    }

    public int isUserNameExist (String username){
        int index=0;
        int indexOfUsername=-1;
        if (this.getUsers()!=null) {
            while (index < this.users.length) {
                if (this.users[index].getUsername().equals(username) == true) {
                    indexOfUsername=index;
                    break;
                }
                index++;
            }
        }
        return indexOfUsername;
    }

    public Cart shopping (User onlineUser){
        Scanner scanner = new Scanner(System.in);
        int userChoice=0;
        int num=1;
        double totalCart;
        Product [] productsInStock = getProductsInStock();
        Cart userCart = new Cart();
        Product productToBuy;
        while (userChoice!=-1) {
            printProduct(productsInStock);
            System.out.println("pleas choice product from the list. for finis -1");
            userChoice = scanner.nextInt();
            if (userChoice==-1){
                break;
            }
            while (userChoice < 1 || userChoice > productsInStock.length) {
                System.out.println("please choice product from the list");
                userChoice = scanner.nextInt();
            }
            productToBuy = productsInStock[userChoice-1];
            System.out.println("please enter a amount");
            num = scanner.nextInt();
            while (num<1){
                System.out.println("please enter a positive amount");
                num = scanner.nextInt();
            }
            userCart.addToCart(productToBuy,num);
            printProduct(userCart.getProductsCart());
            totalCart = userCart.calculatePrice(onlineUser);
            System.out.println("the sum of cart is" + totalCart);
            System.out.println("----------------------------------------------------------");
            userCart.setTotalPrice(totalCart);
        }
        return userCart;
    }

    public void printProduct (Product [] products){
        int index=0;
        int num =1;
        while (index<products.length){
                System.out.println(num+". " + products[index].toString());
                num++;
                index++;
            }
        }

        public Product [] getProductsInStock (){
        int index1 = 0, index2=0;
        int counter = 0;
        Product [] productsInStock;
            while (index1 <this.products.length){
                if (this.products[index1].isStock()==true) {
                    counter++;
                }
                index1++;
            }
            productsInStock = new Product[counter];
            index1 =0;
            while (index1<this.products.length){
                if (this.products[index1].isStock()==true){
                    productsInStock[index2] = this.products[index1];
                    index2++;
                }
                index1++;
        }
            return productsInStock;
        }

    public void printAllClient (){
        int num=0;
        for (int i=0; i<this.users.length;i++){
            if (this.users[i] instanceof Client){
                num++;
                System.out.println(num +". "+this.users[i].getFirstName() + "  "+ this.users[i].getLastName());
            }
        }
    }

    public void printVipClient (){
        int num=0;
        for (int i=0; i<this.users.length;i++){
            if (this.users[i] instanceof Client && ((Client) this.users[i]).getIsVip()==true){
                num++;
                System.out.println(num +". "+this.users[i].getFirstName() + "  "+ this.users[i].getLastName()+"  VIP");
            }
        }
    }
    public void printClientPurchase () {
        int num = 0;
        for (int i = 0; i < this.users.length; i++) {
            if (this.users[i].getPersonalCart()!=null) {
                num++;
                System.out.println(num + ". " + this.users[i].getFirstName() + "  " + this.users[i].getLastName() + "  have a one purchase at least ");
            }
        }
        if (num ==0){
            System.out.println("no have client that purchased");
        }
    }
        public void printBiggestPurchase (){
            double sumUserCarts=0;
            double biggestPurchase=0;
            int index=-1;
            for (int i=0; i<this.users.length;i++){
                if (this.users[i].getPersonalCart()!=null) {
                    sumUserCarts = getSumCarts(this.users[i]);
                    if (sumUserCarts > biggestPurchase) {
                        biggestPurchase = sumUserCarts;
                        index = i;
                    }
                }
            }
            if (index==-1){
                System.out.println("no have client that purchased");
            }
            else {
                System.out.println("the Client with biggest purchase is:" + this.users[index].getFirstName() + " " + this.users[index].getLastName() +
                        " and he purchase in:" + biggestPurchase);
            }
        }

        public double getSumCarts (User onlineUser){
        double sumCarts=0;
        for (int i=0;i<onlineUser.getPersonalCart().length;i++){
            sumCarts = sumCarts + onlineUser.getPersonalCart()[i].getTotalPrice();
        }
        return sumCarts;
        }

        public void creactProduct(){
        Scanner scanner = new Scanner(System.in);
        Product newProduct;
        String description;
        int discount;
        double price;
        System.out.println("enter a description of product");
        description = scanner.nextLine();
        System.out.println("enter a price");
        price = scanner.nextDouble();
        while (price<0){
            System.out.println("enter a positive price");
            price = scanner.nextDouble();
        }
        System.out.println("enter a discount for VIP");
        discount = scanner.nextInt();
        while (discount<0)
        {
            System.out.println("enter a positive discount");
            discount = scanner.nextInt();
        }
        newProduct = new Product(price,description,discount);
        addProduct(newProduct);
        System.out.println("The product was added!");
    }

    public void addProduct(Product newProduct){
        Product [] newProducts;
        if (getProducts()==null){
            newProducts = new Product[1];
        }
        else {
            newProducts = new Product[getProducts().length + 1];
            int index = 0;
            while (index < this.products.length) {
                newProducts[index] = this.products[index];
                index++;
            }
        }
        newProducts[newProducts.length-1] = newProduct;
        setProducts(newProducts);
    }

    public void stockStatus (){
        Scanner scanner = new Scanner(System.in);
        printProduct(this.products);
        int index = 0;
        System.out.println("enter a number of products to chance");
        index = scanner.nextInt()-1;
        while (index<0 || index>this.products.length){
            System.out.println("enter a correct number");
            index = scanner.nextInt()-1;
        }
        if (this.products[index].isStock()==true){
            System.out.println("the product is in stock, 1. for chance 2. keep");
            if (scanner.nextInt()==1){
                this.products[index].setStock(false);
            }
            else  {
                this.products[index].setStock(true);
            }
        }
        else {
            System.out.println("the product is out of stock, 1. for chance 2. keep");
            if (scanner.nextInt()==1){
                this.products[index].setStock(true);
            }
            else  {
                this.products[index].setStock(false);
            }
        }

    }

}
