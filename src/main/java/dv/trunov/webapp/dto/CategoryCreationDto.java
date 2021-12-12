package dv.trunov.webapp.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
public class CategoryCreationDto {

    @NotBlank(message = "Category name cannot be empty.")
    @Pattern(regexp = "([A-Z]{1,5}[a-z]{0,20}\\s{0,4}){1,4}",
            message = "Enter capitalized words or abbreviation.")
    @Size(min = 2, max = 70,
            message = "Category name length between 2 and 70 symbols.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
