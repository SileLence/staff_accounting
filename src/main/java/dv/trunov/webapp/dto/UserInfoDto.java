package dv.trunov.webapp.dto;

public class UserInfoDto extends UserDto {
    private String address;
    private String email;
    private String phone;

    public UserInfoDto(Integer id, String name,
                       String address, String email,
                       String phone, String category) {
        super(id, name, category);
        this.address = address;
        this.email = email;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "User Information:\n"
                + "User ID: " + getId() + "\n"
                + "Name:" + getName() + ":\n"
                + "Address: " + address + "\n"
                + "Email: " + email + "\n"
                + "Phone Number: " + phone + "\n";
    }
}
