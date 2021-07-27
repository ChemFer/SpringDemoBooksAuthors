package spring.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.demo.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
