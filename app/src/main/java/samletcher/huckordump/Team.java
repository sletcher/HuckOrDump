package samletcher.huckordump;

/**
 * Created by iyudkovich on 4/13/17.
 */

public class Team {

    private int team_id;
    private String team_name;
    private String division;
    private String city;

    public Team(int team_id, String team_name, String division, String city) {

        this.team_id = team_id;
        this.team_name = team_name;
        this.division = division;
        this.city = city;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
