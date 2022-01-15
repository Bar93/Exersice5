import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        int mainUserChoice = 0,secondUserChoice = 0,workerOrClient,indexOfUser;
        boolean isWorker = false;
        User onlineUser;
        while (mainUserChoice != 3) {
            System.out.println("1. create account" + "\n" + "2. login" + "\n" + "3. exit");
            mainUserChoice = scanner.nextInt();
            if (mainUserChoice < 1 || mainUserChoice > 3) {
                System.out.println("enter correct number");
                System.out.println("1.create account" + "\n" + "2. login" + "\n" + "3. exit");
                mainUserChoice = scanner.nextInt();
            }
            if (mainUserChoice == 1) {
                System.out.println("Hello, which user you want create?" + "\n" + "1. worker" + "\n" + "2. client");
                workerOrClient = scanner.nextInt();
                while (workerOrClient != 1 && workerOrClient != 2) {
                    System.out.println("enter correct number" + "\n" + "1. worker" + "\n" + "2. client");
                    workerOrClient = scanner.nextInt();
                }
                if (workerOrClient == 1) {
                    isWorker = true;
                }
                if (workerOrClient == 2) {
                    isWorker = false;
                }
                store.createUser(isWorker);
            }
            if (mainUserChoice == 2) {
                indexOfUser = store.login();
                secondUserChoice=0;
                while (secondUserChoice != 8) {
                if (indexOfUser==-1){
                    System.out.println("login fail");
                    break;
                }else {
                    onlineUser = store.getUsers()[indexOfUser];
                }
                    if (onlineUser instanceof Client) {
                        if (store.getProducts()==null){
                            System.out.println("the store are closed");
                            break;
                        }
                        if (((Client) onlineUser).getIsVip() == true) {
                            System.out.println("Hello" + "\n" + onlineUser.getFirstName() + " " + onlineUser.getLastName() + "  VIP! ");
                        } else {
                            System.out.println("Hello" + "\n" + onlineUser.getFirstName() + " " + onlineUser.getLastName());
                        }
                        Cart newCart = store.shopping(onlineUser);
                        onlineUser.addCart(newCart);
                    }
                    if (onlineUser instanceof Worker) {
                        if (((Worker) onlineUser).getRank() == 1) {
                            System.out.println("Hello" + "\n" + onlineUser.getFirstName() + " " + onlineUser.getLastName() + "  {regular worker} ");
                        }
                        if (((Worker) onlineUser).getRank() == 2) {
                            System.out.println("Hello" + "\n" + onlineUser.getFirstName() + " " + onlineUser.getLastName() + "  {manger} ");
                        }
                        if (((Worker) onlineUser).getRank() == 3) {
                            System.out.println("Hello" + "\n" + onlineUser.getFirstName() + " " + onlineUser.getLastName() + "  {manager member} ");
                        }
                            System.out.println("1.print all client" + "\n" + "2. print VIP client" + "\n" + "3. print client with one purchase" + "\n" + "4. client with the biggest purchases"
                                    + "\n" + "5. add product" + "\n" + "6. stoke status" + "\n" + "7. shopping" + "\n" + "8. logout");
                            secondUserChoice = scanner.nextInt();
                            while (secondUserChoice < 1 || secondUserChoice > 8) {
                                System.out.println("enter correct number");
                                System.out.println("1.print all client" + "\n" + "2. print VIP client" + "\n" + "3. print client with one purchase" + "\n" + "4. client with the biggest purchases"
                                        + "\n" + "5. add product" + "\n" + "6. stocke status" + "\n" + "7. shopping" + "\n" + "8. logout");
                                secondUserChoice = scanner.nextInt();
                            }
                            if (secondUserChoice == 1) {
                                if (store.getUsers()==null){
                                    System.out.println("no have client");
                                    secondUserChoice = 8;
                                }
                                else {
                                    store.printAllClient();
                                }
                            }
                            if (secondUserChoice == 2) {
                                if (store.getUsers()==null){
                                    System.out.println("no have client");
                                    secondUserChoice = 8;
                                }
                                else {
                                    store.printVipClient();
                                }
                            }
                            if (secondUserChoice == 3) {
                                if (store.getUsers()==null){
                                    System.out.println("no have client");
                                    secondUserChoice = 8;
                                }
                                else {
                                    store.printClientPurchase();
                                }
                            }
                            if (secondUserChoice == 4) {
                                if (store.getUsers()==null){
                                    System.out.println("no have client");
                                    secondUserChoice = 8;
                                }
                                else {
                                    store.printBiggestPurchase();
                                }
                            }
                            if (secondUserChoice == 5) {
                                store.creactProduct();
                            }
                            if (secondUserChoice == 6) {
                                if (store.getProducts()==null){
                                    System.out.println("no have products");
                                }
                                else {
                                    store.stockStatus();
                                }
                            }
                            if (secondUserChoice == 7) {
                                if (store.getProducts()==null){
                                    System.out.println("no have products");
                                }
                                else {
                                    Cart newCart = store.shopping(onlineUser);
                                    onlineUser.addCart(newCart);
                                }
                            }
                        }

                    }

                }
            }
        }
    }

