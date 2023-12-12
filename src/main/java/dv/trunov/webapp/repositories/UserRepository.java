package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository
        extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT user FROM users AS user "
           + "WHERE user.category.name = :category")
    List<UserEntity> findByCategory(@Param("category") String category);
}
