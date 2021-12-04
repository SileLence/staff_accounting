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
        return new UserEntity(
                user.getFirstname(),
                user.getSurname(),
                user.getAddress(),
                user.getEmail(),
                user.getPhone());
    }

    public static CategoryEntity toCategoryEntity(
            CategoryCreationDto categoryCreationDto) {
        return new CategoryEntity(categoryCreationDto.getName());
    }

    public static CategoryDto toCategoryDto(CategoryEntity category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
