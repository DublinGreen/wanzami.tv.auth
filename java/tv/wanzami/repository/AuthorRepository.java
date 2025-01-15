package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}