package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}