package samletcher.huckordump;

/**
 * Created by iyudkovich on 4/11/17.
 */

public class User {
    private int id;
    private String first_name;
    private String last_name;
    // 0 is male, 1 is female
    private boolean gender;
    private boolean interest;
    private int team_id;
    private String position;
    private String picture;

    public User(int id, String first_name, String last_name, boolean gender, boolean interest, int team_id, String position, String picture) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.interest = interest;
        this.team_id = team_id;
        this.position = position;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isGender() {
        return gender;
    }

    public int getGender() {
        if (isGender()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isInterest() {
        return interest;
    }

    public int getInterestedIn() {
        if (isInterest()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setInterest(boolean interest) {
        this.interest = interest;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
