package dv.trunov.webapp.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Validated
public class CategoryCreationDto {

    @Pattern(regexp = "([A-Z]{1,3}[a-z]{0,20}\\s{0,4}){1,4}",
            message = "Invalid category format. "
                    + "Only words with a capital letter or abbreviations.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
