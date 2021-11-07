package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<UserEntity, Integer> {
}
