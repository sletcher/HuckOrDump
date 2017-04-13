package samletcher.huckordump;

/**
 * Created by iyudkovich on 4/13/17.
 */

public class Action {
    // this is being weird
    //public Enum enum_action { Dump, Huck };


    private int action_id;
    private int user1_id;
    private int user2_id;
    // 1 is huck, 0 is dump
    private int action;

    public Action(int action_id, int user1_id, int user2_id, int action) {
        this.action_id = action_id;
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.action = action; // this needs to be changed to an enum
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}

