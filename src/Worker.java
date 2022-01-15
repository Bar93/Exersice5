public class Worker extends User{

    private int rank;

    public Worker(String firstName, String lastName, String username, String password, boolean isWorker, int rank) {
        super(firstName, lastName, username, password, isWorker);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "rank=" + rank +
                '}';
    }
}
