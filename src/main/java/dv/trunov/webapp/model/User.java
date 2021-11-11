package dv.trunov.webapp.model;

import dv.trunov.webapp.domain.UserEntity;

public class User {
    private Integer id;
    private String firstname;
    private String surname;
    private String category;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static User toModel(UserEntity entity) {
        User model = new User();
        model.setId(entity.getId());
        model.setFirstname(entity.getFirstname());
        model.setSurname(entity.getSurname());
        model.setCategory(entity.getCategory().getName());
        return model;
    }
}
