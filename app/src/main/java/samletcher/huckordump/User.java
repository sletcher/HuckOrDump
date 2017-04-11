package samletcher.huckordump;

/**
 * Created by iyudkovich on 4/11/17.
 */

public class User {
    int id;
    String first_name;
    String last_name;
    // 0 is male, 1 is female
    boolean gender;
    boolean interest;
    int team_id;
    String position;
    String picture;

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
}
