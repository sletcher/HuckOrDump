package samletcher.huckordump;

/**
 * Created by iyudkovich on 4/13/17.
 */

public class Message {

    private int message_id;
    private int message_number;
    private int match_id;
    private int user_id;
    private int created; // this definitely needs work
    private String text;

    public Message(int message_id, int message_number, int match_id, int user_id, int created, String text) {
        this.message_id = message_id;
        this.match_id = match_id;
        this.user_id = user_id;
        this.created = created;
        this.text = text;
    }


    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (message_id != message.message_id) return false;
        if (match_id != message.match_id) return false;
        if (user_id != message.user_id) return false;
        if (created != message.created) return false;
        return text != null ? text.equals(message.text) : message.text == null;

    }

    @Override
    public int hashCode() {
        int result = message_id;
        result = 31 * result + match_id;
        result = 31 * result + user_id;
        result = 31 * result + created;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    public int getMessageNumber() {
        return message_number;
    }
}
