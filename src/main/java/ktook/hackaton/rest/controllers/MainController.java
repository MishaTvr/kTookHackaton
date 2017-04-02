package ktook.hackaton.rest.controllers;


import ktook.hackaton.POJOFiller.UserFiller;
import ktook.hackaton.rest.entities.Card;
import ktook.hackaton.rest.entities.PiggyBox;
import ktook.hackaton.rest.entities.User;
import ktook.hackaton.utils.PiggyBoxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/myservice")
public class MainController {

    @RequestMapping(value= "/login/{login}/password/{password}", method = RequestMethod.GET)
    @ResponseBody
    public boolean login(@PathVariable String login, @PathVariable String password) {
        List<User> list = UserFiller.getUsersSamples();
        for (User user : list) {
            if (user.getName().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value= "/{login}/card", method = RequestMethod.GET)
    @ResponseBody
    public Card getCardByUser(@PathVariable String login) {
        return PiggyBoxUtils.getUserFromListByName(UserFiller.getUsersSamples(), login).getCard();
    }
    @RequestMapping(value= "/{login}/pig", method = RequestMethod.GET)
    @ResponseBody
    public List<PiggyBox> getPigesByUser(@PathVariable String login) {
        return PiggyBoxUtils.getUserFromListByName(UserFiller.getUsersSamples(), login).getPiggyBox();
    }
    @RequestMapping(value= "/{login}/createPig/{pigName}/targetMoney/{targetMoney}/userAmount/{userAmount}/dueDate/{dueDate}", method = RequestMethod.GET)
    @ResponseBody
    public long createPigByUser(@PathVariable String login, @PathVariable String pigName, @PathVariable long targetMoney, @PathVariable int userAmount, @PathVariable String dueDate) {
        User user = PiggyBoxUtils.getUserFromListByName(UserFiller.getUsersSamples(), login);
        long piggyId = 4000000000000000L;
        long max = 100000L;
        long min = 1L;
        long var3 = (long)(Math.random() * (max - min) + min) + piggyId;
        PiggyBox piggyBox = new PiggyBox(Math.abs(piggyId+var3),user.getName(),0,dueDate,123,targetMoney,userAmount,pigName);
        user.addPig(piggyBox);
        UserFiller.fillFileByPojo(user);
        return var3;
    }

    @RequestMapping(value= "/{login}/deletePig/{pigName}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deletePigByUserAndName(@PathVariable String login, @PathVariable String pigName) {
        return PiggyBoxUtils.deletePigByUser(login,pigName);
    }

    @RequestMapping(value= "/{login}/addPig/{pigName}/hostUser/{hostUser}", method = RequestMethod.GET)
    @ResponseBody
    public boolean connectToPigByUserAndName(@PathVariable String login, @PathVariable String pigName, @PathVariable String hostUser) {
        List<User> list = UserFiller.getUsersSamples();

        for (User user : list) {
            if (user.getName().equals(hostUser)) {
                List<PiggyBox> piggyBoxList = user.getPiggyBox();
                for(PiggyBox piggyBox: piggyBoxList){
                    if(piggyBox.getName().equals(pigName)){
                        for (User usertmp : list) {
                            if (usertmp.getName().equals(login)) {
                                usertmp.addPig(piggyBox);
                                UserFiller.fillFileByPojo(usertmp);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    @RequestMapping(value= "/{login}/fillPig/{pigName}", method = RequestMethod.GET)
    @ResponseBody
    public boolean transferMoneyFromCardToPig(@PathVariable String login, @PathVariable String pigName) throws NullPointerException {
        List<User> list = UserFiller.getUsersSamples();
        User user = PiggyBoxUtils.getUserFromListByName(list,login);
        PiggyBox piggyBox = PiggyBoxUtils.getPigByUserAndName(user,pigName);
        long moneyOnCard = user.getCard().getMoney();
        if(piggyBox.getMoneyPerUser()>moneyOnCard){
            return false;
        }
        user.getCard().setMoney(moneyOnCard-piggyBox.getMoneyPerUser());

        return PiggyBoxUtils.refreshPigs(list,pigName,piggyBox.getMoneyPerUser());
    }
    @RequestMapping(value= "/{login}/payPig/{pigName}/money/{money}", method = RequestMethod.GET)
    @ResponseBody
    public boolean payByPig(@PathVariable String login, @PathVariable String pigName, @PathVariable long money) throws NullPointerException {
        List<User> list = UserFiller.getUsersSamples();
        User user = PiggyBoxUtils.getUserFromListByName(list,login);
        PiggyBox piggyBox = PiggyBoxUtils.getPigByUserAndName(user,pigName);
        if(!piggyBox.getHolder().equals(login)){
            return false;
        }

        return PiggyBoxUtils.refreshPigs(list,pigName,-money);
    }

    @RequestMapping(value= "/{login}/deletePigAndSendMoneyBack/{pigName}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deletePigAndSendMoneyBack(@PathVariable String login, @PathVariable String pigName) throws NullPointerException {
        List<User> list = UserFiller.getUsersSamples();
        User user = PiggyBoxUtils.getUserFromListByName(list,login);
        PiggyBox piggyBox = PiggyBoxUtils.getPigByUserAndName(user,pigName);
        if(!piggyBox.getHolder().equals(login)){
            return false;
        }

        PiggyBoxUtils.sendMoneyBack(list,pigName);
        deletePigByUserAndName("Lev Teplyakov",pigName);
        deletePigByUserAndName("Bulat Yakupov", pigName);
        deletePigByUserAndName("Mikhail Tvere", pigName);
        deletePigByUserAndName("Nikita Kharichkin", pigName);
        return true;
    }


}
