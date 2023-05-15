package main.java.system.user;

import java.util.ArrayList;

public class Caravan {
    private int id;
    private ArrayList<User> members;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public void addUser(User user){
        if(members == null)
            members = new ArrayList<User>(){{add(user);}};
        else
            members.add(user);
    }
}
