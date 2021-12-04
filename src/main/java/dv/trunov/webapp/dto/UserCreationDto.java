package dv.trunov.webapp.dto;


import dv.trunov.webapp.validation.Marker;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Validated
public class UserCreationDto {

    @NotBlank(
            groups = Marker.OnCreate.class,
            message = "First Name cannot be empty.")
    @Pattern(
            regexp = "[A-Z][a-z]{1,15}|[А-ЯЁ][а-яё]{1,15}",
            message = "First Name must start with a capital letter.")
    private String firstname;

    @NotBlank(
            groups = Marker.OnCreate.class,
            message = "Surname cannot be empty.")
    @Pattern(
            regexp = "[A-Z][a-z]{1,15}|[А-ЯЁ][а-яё]{1,15}",
            message = "Surname must start with a capital letter.")
    private String surname;

    @NotBlank(
            groups = Marker.OnCreate.class,
            message = "Address cannot be empty.")
    @Pattern(
            regexp = "^[A-Za-z\s.-]{1,25}/[0-9A-Za-z\s.-]{1,25}/" +
            "[0-9A-Za-z]{1,7}/[0-9]{1,5}",
            message = "Enter the address in the format: " +
                    "'City/Street/HouseNumber/ApartmentNumber'")
    private String address;

    @Email(message = "Email is incorrect.")
    private String email;

    @Pattern(
            regexp = "\\+[1-9][0-9]{10}",
            message = "Enter the phone number in the format: '+79876543210'")
    private String phone;

    private String category;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
