package ktook.hackaton.rest.entities;

public class PiggyBox extends Card {

    private long targetMoney;
    private int userAmount;
    private long moneyPerUser;
    private String name;


    public PiggyBox(long id, String holder, long money, String dueDate, int cvv, long targetMoney, int userAmount, String name) {
        super(id, holder, money, dueDate, cvv);
        this.targetMoney = targetMoney;
        this.userAmount = userAmount;
        this.name = name;
        if (this.userAmount > 0) {
            this.moneyPerUser = targetMoney / userAmount;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoneyPerUser() {
        return moneyPerUser;
    }

    public void setMoneyPerUser(long moneyPerUser) {
        this.moneyPerUser = moneyPerUser;
    }

    public long getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(long targetMoney) {
        this.targetMoney = targetMoney;
    }

    public int getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(int userAmount) {
        this.userAmount = userAmount;
    }

    @Override
    public String toString() {
        return "PiggyBox{" +
                "targetMoney=" + targetMoney +
                ", userAmount=" + userAmount +
                ", moneyPerUser=" + moneyPerUser +
                ", name='" + name + '\'' +
                '}';
    }
}
