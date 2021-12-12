package dv.trunov.webapp.dto;

import dv.trunov.webapp.validation.Marker;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
public class UserCreationDto {

    @NotBlank(message = "Firstname cannot be empty.")
    @Pattern(regexp = "[A-Z][a-z]*",
            message = "Firstname must be capitalized.")
    @Size(min = 2, max = 35,
            message = "Firstname length between 2 and 35 symbols.")
    private String firstname;

    @NotBlank(message = "Surname cannot be empty.")
    @Pattern(regexp = "[A-Z][a-z]*",
            message = "Surname must be capitalized.")
    @Size(min = 2, max = 35,
            message = "Surname length between 2 and 35 symbols.")
    private String surname;

    @NotBlank(groups = Marker.OnCreate.class,
            message = "Address cannot be empty.")
    @Pattern(regexp = "^[A-Za-z\s.-]{1,50}/[0-9A-Za-z\s.-]{1,50}/"
            + "[0-9A-Za-z]{1,10}/[0-9]{1,10}",
            message = "Correct address format: "
                   + "City/Street/HouseNumber/ApartmentNumber")
    private String address;

    @Email(message = "Email is incorrect.")
    private String email;

    @Pattern(regexp = "\\+[1-9][0-9]{10}",
            message = "Correct phone number format: +79876543210")
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
