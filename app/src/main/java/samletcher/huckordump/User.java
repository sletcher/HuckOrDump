package samletcher.huckordump;

import android.util.Log;

/**
 * Created by iyudkovich on 4/11/17.
 */

public class User {
    private int id;
    private String email;
    private String pw;
    private String first_name;
    private String last_name;
    // 0 is male, 1 is female
    private boolean gender;
    private boolean interest;
    private int team_id;
    private String position;
    private String bio;
    private String picture;

    public User() {

    }

    public User(int id, String email, String pw, String first_name, String last_name, boolean gender, boolean interest, int team_id, String position,  String picture) {
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.interest = interest;
        this.team_id = team_id;
        this.position = position;
        this.picture = picture;
    }

    public User(int id, String email, String pw, String first_name, String last_name, int gender, int interest, int team_id, String position, String bio, String picture) {
        this.id = id;
        this.email = email;
        this.pw = pw;
        this.first_name = first_name;
        this.last_name = last_name;
        if (gender == 1) {
            this.gender = true;
        } else {
            this.gender = false;
        }
        if (interest == 1) {
            this.interest = true;
        } else {
            this.interest = false;
        }

        this.team_id = team_id;
        this.position = position;
        this.bio = bio;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public void setGender(int gender) {

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

    public void setInterest(int interest) {
        if (interest == 1) {
            this.interest = true;
        } else {
            this.interest = false;
        }
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

    public void printUser() {
        Log.d("test", "The user's name is " + this.first_name + " " + this.last_name);
        Log.d("test", "The user plays the position: " + this.position);
        Log.d("test", "The user has the following bio: \n" + this.bio);
        Log.d("test", "The user's email is " + this.email);
    }
}
