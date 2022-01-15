public class Client extends User{

    private boolean isVip;

    public Client(String firstName, String lastName, String username, String password, boolean isWorker, boolean isVip) {
        super(firstName, lastName, username, password, isWorker);
        this.isVip = isVip;
    }

    public boolean getIsVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
