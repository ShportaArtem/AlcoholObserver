package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Verification implements Serializable {
    private static final long serialVersionUID = 8707365101001519696L;

    private Integer id;

    private boolean check;

    private Timestamp date;

    private String description;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        if(description == null || description.isEmpty()){
            return "None";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
