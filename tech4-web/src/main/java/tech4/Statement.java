package tech4;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Statement Model fuer Demo
 */
public class Statement {

    private String text;
    private Date created = new Date();
    private String user;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate(){
        SimpleDateFormat fm = new SimpleDateFormat("hh:mm:ss");
        return fm.format(created);
    }

}
