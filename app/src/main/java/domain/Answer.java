package domain;

/**
 * Created by Alex on 4/16/2015.
 */
public class Answer {
    String TYPE, VALUE;
    boolean CHECK, TRANSFER = false;

    public Answer(String type, boolean check) {
        setTYPE(type);
        setCHECK(check);
    }

    public Answer(String type, boolean check, boolean transfer) {
        setTYPE(type);
        setCHECK(check);
        setTRANSFER(transfer);
    }

    public Answer(String type, boolean check, String value) {
        setTYPE(type);
        setCHECK(check);
        setVALUE(value);
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

    public boolean getCHECK() {
        return CHECK;
    }

    public void setCHECK(boolean CHECK) {
        this.CHECK = CHECK;
    }

    public boolean getTRANSFER() {
        return TRANSFER;
    }

    public void setTRANSFER(boolean TRANSFER) {
        this.TRANSFER = TRANSFER;
    }
}
