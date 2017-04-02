package ktook.hackaton.rest.entities;

public class Card {
    private long id;
    private String holder;
    private long money;
    private String dueDate;
    private int cvv;


    public Card() {
    }

    public Card(long id, String holder, long money, String dueDate, int cvv) {
        this.id = id;
        this.holder = holder;
        this.money = money;
        this.dueDate = dueDate;
        this.cvv = cvv;

    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
