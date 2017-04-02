package ktook.hackaton.rest.entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private String password;
    private Card card;
    private List<PiggyBox> piggyBoxes = new ArrayList<>();

    public Card getCard() {
        return card;
    }

    public User() {
    }

    public List<PiggyBox> getPiggyBox() {
        return piggyBoxes;
    }

    public void setPiggyBox(List<PiggyBox> piggyBox) {
        this.piggyBoxes = piggyBox;
    }

    public User(int id, String name, String password, Card card,List<PiggyBox> piggyBox) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.card = card;
        this.piggyBoxes = piggyBox;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCards() {
        return card;
    }

    public void setCards(Card card) {
        this.card = card;
    }

    public void addPig(PiggyBox piggyBox){
        piggyBoxes.add(piggyBox);
    }

}
