package dv.trunov.webapp.dto;

public class UserInfoDto {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String category;

    public UserInfoDto(Integer id, String name,
                       String address, String email,
                       String phone, String category) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.category = category;
    }

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

    @Override
    public String toString() {
        return "User Information:\n"
                + "User ID: " + id + "\n"
                + "Name:" + name + ":\n"
                + "Address: " + address + "\n"
                + "Email: " + email + "\n"
                + "Phone Number: " + phone + "\n";
    }
}
