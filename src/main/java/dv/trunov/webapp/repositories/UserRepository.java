package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository
        extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity AS u WHERE u.category.name = :category")
    List<UserEntity> findByCategory(@Param("category") String category);
}
