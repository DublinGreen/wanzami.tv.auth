package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tv.wanzami.model.VideoCountryRestriction;

public interface VideoCountryRestrictionRepository extends JpaRepository<VideoCountryRestriction, Long> {
		
	@Query("SELECT r FROM VideoCountryRestriction r WHERE r.video.id = :videoId")
	List<VideoCountryRestriction> findByVideoId(@Param("videoId") Long videoId);
}