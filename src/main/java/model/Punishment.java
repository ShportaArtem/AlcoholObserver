package model;

import java.io.Serializable;

public class Punishment implements Serializable {
    private static final long serialVersionUID = 6637951637738299104L;
    private Integer id;
    private Integer borderValue;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBorderValue() {
        return borderValue;
    }

    public void setBorderValue(Integer borderValue) {
        this.borderValue = borderValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
