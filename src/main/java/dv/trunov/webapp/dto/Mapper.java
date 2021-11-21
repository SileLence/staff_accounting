package dv.trunov.webapp.dto;

import dv.trunov.webapp.domain.CategoryEntity;
import dv.trunov.webapp.domain.UserEntity;

public class Mapper {

    public static UserDto toUserDto(UserEntity userEntity) {
        Integer id = userEntity.getId();
        String name = userEntity.getFirstname() + " " + userEntity.getSurname();
        String category = userEntity.getCategory().getName();
        return new UserDto(id, name, category);
    }

    public static UserInfoDto toUserInfoDto(UserEntity userEntity) {
        String name = userEntity.getFirstname() + " " + userEntity.getSurname();
        return new UserInfoDto(
                userEntity.getId(),
                name,
                userEntity.getAddress(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getCategory().getName());
    }

    public static UserEntity toUserEntity(UserCreationDto user) {
        String firstname = user.getFirstname();
        if (firstname == null) {
            firstname = "<none>";
        }
        String surname = user.getSurname();
        if (surname == null) {
            surname = "<none>";
        }
        String address = user.getAddress();
        if (address == null) {
            address = "<none>";
        }
        String email = user.getEmail();
        if (email == null) {
            email = "<none>";
        }
        String phone = user.getPhone();
        if (phone == null) {
            phone = "<none>";
        }
        return new UserEntity(firstname, surname, address, email, phone);
    }

    public static CategoryEntity toCategoryEntity(String categoryName) {
        return new CategoryEntity(categoryName);
    }

    public static CategoryDto toCategoryDto(CategoryEntity category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
