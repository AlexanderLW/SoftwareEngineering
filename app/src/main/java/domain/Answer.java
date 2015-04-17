package domain;

/**
 * Created by Alex on 4/16/2015.
 */
public class Answer {
    String TYPE, VALUE;

    public Answer(String type) {
        setTYPE(type);
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
