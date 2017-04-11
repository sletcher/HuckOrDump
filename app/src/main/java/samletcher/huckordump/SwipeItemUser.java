package samletcher.huckordump;

import java.sql.Blob;
import java.sql.Struct;

public class SwipeItemUser {
    final String name;
    final Integer age;
    final byte[] picture;
    final String teams;
    final String bio;

    public SwipeItemUser(String name, Integer age, byte[] picture, String teams, String bio) {
        this.name = name;
        this.age = age;
        this.picture = picture;
        this.teams = teams;
        this.bio = bio;
    }
}
