package domain;

/**
 * Created by Alex on 4/16/2015.
 */
public class Answer {
    /*
    an answer is a value input by the user defining an attribute of a solution. Transfer is set if it is a value defining a volume of solution to be transferred.
     */
    String type, value;
    boolean check, transfer = false;

    //answer constructors
    public Answer(String type, boolean check) {
        setType(type);
        setCheck(check);
    }

    public Answer(String type, boolean check, boolean transfer) {
        setType(type);
        setCheck(check);
        setTransfer(transfer);
    }

    public Answer(String type, boolean check, String value) {
        setType(type);
        setCheck(check);
        setValue(value);
    }

    //get and set
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getTransfer() {
        return transfer;
    }

    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }
}
