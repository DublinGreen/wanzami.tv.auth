package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.VideoMeta;

public interface VideoMetaRepository extends JpaRepository<VideoMeta, Long> {

}