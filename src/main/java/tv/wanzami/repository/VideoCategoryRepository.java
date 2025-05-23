package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tv.wanzami.model.VideoCategory;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {
	
	@Query("SELECT r FROM VideoCategory r WHERE category_id = :categoryId")
	List<VideoCategory> findByCategoryId(@Param("categoryId") Long categoryId);
	
	@Query("SELECT r FROM VideoCategory r WHERE video_id = :videoId")
	List<VideoCategory> findAllSubCategoryByVideoId(@Param("videoId") Long videoId);
}