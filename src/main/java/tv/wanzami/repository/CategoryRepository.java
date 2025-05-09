package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}