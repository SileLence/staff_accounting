package dv.trunov.webapp.model;

import dv.trunov.webapp.domain.UserEntity;

public class UserModel {
    private Integer id;
    private String firstname;
    private String surname;

    public UserModel() {
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

    public static UserModel toModel(UserEntity entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setFirstname(entity.getFirstname());
        model.setSurname(entity.getSurname());
        return model;
    }
}
