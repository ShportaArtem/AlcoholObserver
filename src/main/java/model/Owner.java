package model;

import java.io.Serializable;

public class Owner implements Serializable {

    private static final long serialVersionUID = -6985451998934066131L;

    private Integer userId;

    private Integer countOfCompany;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCountOfCompany() {
        return countOfCompany;
    }

    public void setCountOfCompany(Integer countOfCompany) {
        this.countOfCompany = countOfCompany;
    }
}
