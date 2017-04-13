package samletcher.huckordump;

/**
 * Created by iyudkovich on 4/12/17.
 */

public class LoginUser {
    private int id;
    private String email;
    private String pw;

    public LoginUser(int id, String email, String pw) {
        this.id = id;
        this.email = email;
        this.pw = pw;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUser loginUser = (LoginUser) o;

        if (id != loginUser.id) return false;
        if (!email.equals(loginUser.email)) return false;
        return pw.equals(loginUser.pw);

    }

    @Override
    public int hashCode() {
        return pw.hashCode();
    }
}
