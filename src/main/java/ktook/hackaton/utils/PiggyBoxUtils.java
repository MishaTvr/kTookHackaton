package ktook.hackaton.utils;

import ktook.hackaton.POJOFiller.UserFiller;
import ktook.hackaton.rest.entities.Card;
import ktook.hackaton.rest.entities.PiggyBox;
import ktook.hackaton.rest.entities.User;

import java.util.Iterator;
import java.util.List;

public class PiggyBoxUtils {

    public static PiggyBox getPigByUserAndName(User user, String name){
        List<PiggyBox> piggyBoxList = user.getPiggyBox();

        for(PiggyBox piggyBox:piggyBoxList){
            if(piggyBox.getName().equals(name)){
                return piggyBox;
            }
        }
        return null;
    }

    public static User getUserFromListByName(List<User> userList, String name){
        for(User user:userList){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public static boolean refreshPigs(List<User> userList, String namePig, long money){
        for(User user1:userList){
            PiggyBox piggyBox1 = PiggyBoxUtils.getPigByUserAndName(user1, namePig);
            if(piggyBox1!=null){
                piggyBox1.setMoney(piggyBox1.getMoney()+money);
                deletePigByUser(user1.getName(), piggyBox1.getName());
                user1.addPig(piggyBox1);
                UserFiller.fillFileByPojo(user1);
                return true;
            }

        }
        return false;
    }

    public static boolean deletePigByUser(String login, String pigName){
        List<User> list = UserFiller.getUsersSamples();

        for (User user : list) {
            if (user.getName().equals(login)){
                List<PiggyBox> piggyBoxList = user.getPiggyBox();
                Iterator<PiggyBox> iterator= piggyBoxList.iterator();
                while (iterator.hasNext()){
                    PiggyBox tmp = iterator.next();
                    if(tmp.getName().equals(pigName)){
                        iterator.remove();
                        user.setPiggyBox(piggyBoxList);
                        UserFiller.fillFileByPojo(user);
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public static boolean sendMoneyBack(List<User> userList, String pigName){
        for(User user: userList){
            PiggyBox piggyBox = getPigByUserAndName(user,pigName);
            if(piggyBox == null){
                continue;
            }
            long moneyOnPigPer = piggyBox.getMoney()/piggyBox.getUserAmount();
            Card card = user.getCard();
            card.setMoney(card.getMoney()+moneyOnPigPer);
            user.setCards(card);
            UserFiller.fillFileByPojo(user);
        }
//        Iterator<User> iterator = userList.iterator();
//        while (iterator.hasNext()){
//            User usert = iterator.next();
//            deletePigByUser(usert.getName(),pigName);
//            UserFiller.fillFileByPojo(usert);
//        }
        return true;
    }


}
