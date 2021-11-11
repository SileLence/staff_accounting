package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository
        extends CrudRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByName(String name);
}
