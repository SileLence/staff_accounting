package dv.trunov.webapp.repositories;

import dv.trunov.webapp.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
