package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tv.wanzami.model.VideoProductionCrew;

public interface VideoProductionCrewRepository extends JpaRepository<VideoProductionCrew, Long> {
	
	@Query("SELECT vc FROM VideoProductionCrew vc WHERE vc.videoId = :videoId")
	List<VideoProductionCrew> findProductionCrewByVideoId(@Param("videoId") Long videoId);
	
	List<VideoProductionCrew> findByVideoIdAndStatus(Long videoId, Integer status);
}