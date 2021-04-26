package model;

import java.io.Serializable;

public class Company implements Serializable {
    private static final long serialVersionUID = -7376048066508572314L;

    private Integer id;
    private String name;
    private String description;
    private Integer userId;
    private Integer punishmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
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

    public Integer getPunishmentId() {
        return punishmentId;
    }

    public void setPunishmentId(Integer punishmentId) {
        this.punishmentId = punishmentId;
    }
}
