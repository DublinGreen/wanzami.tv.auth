package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import tv.wanzami.model.VideoCast;

public interface VideoCastRepository extends JpaRepository<VideoCast, Long> {
	
	@Query("SELECT vc FROM VideoCast vc WHERE vc.videoId = :videoId")
	List<VideoCast> findVideoCastsByVideoId(@Param("videoId") Long videoId);
}