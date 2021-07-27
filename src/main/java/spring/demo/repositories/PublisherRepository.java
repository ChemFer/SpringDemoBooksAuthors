package spring.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.demo.domain.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
