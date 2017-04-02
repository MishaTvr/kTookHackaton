package ktook.hackaton.POJOFiller;

import com.google.gson.Gson;
import ktook.hackaton.rest.entities.Card;
import ktook.hackaton.rest.entities.User;

import java.io.*;
import java.util.*;

public class UserFiller {
    private static List<User> users = new ArrayList<>();
    private static Map<String, String > owners = new HashMap<String, String>(){};

    public static void fillUserList() {
        owners = new HashMap<String, String>() {};
        fillOwners();

            for (String name : owners.keySet()) {
                User user = getPojofromfile(name);
                users.add(user);
            }
    }

    public static void fillOwners(){
        owners.put("Lev Teplyakov","1");
        owners.put("Bulat Yakupov", "2");
        owners.put("Mikhail Tvere", "3");
        owners.put("Nikita Kharichkin", "4");
    }

    public static Set<String> getOwnersNames(){
        fillOwners();
        return owners.keySet();
    }

    public static List<User> getUsersSamples(){
        fillUserList();
        return users;
    }

    public static void fillFileByPojo(User user){
        Gson gson = new Gson();
            try {
                Writer writer = new FileWriter("D:\\kTookHackaton\\src\\main\\resources\\"+user.getName().replace(" ", "-")+".json");
                writer.write(gson.toJson(user));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static User getPojofromfile(String name){
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(
                    new FileReader("D:\\kTookHackaton\\src\\main\\resources\\"+name.replace(" ", "-")+".json"));
            return gson.fromJson(br, User.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
