package dv.trunov.webapp.dto;

public class UserDto {
    private Integer id;
    private String name;
    private String category;

    public UserDto(Integer id, String name, String category) {
        this.id = id;
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "User ID: " + id + "\n"
                + "Name: " + name + "\n"
                + "Category: " + category + "\n";
    }
}
