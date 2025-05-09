package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}